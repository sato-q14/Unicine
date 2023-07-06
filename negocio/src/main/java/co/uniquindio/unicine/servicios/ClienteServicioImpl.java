package co.uniquindio.unicine.servicios;

import co.uniquindio.unicine.dto.PeliculaFuncion;
import co.uniquindio.unicine.entidades.*;
import co.uniquindio.unicine.repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicioImpl implements ClienteServicio{

    private final ClienteRepositorio clienteRepo;

    private final PeliculaRepositorio peliculaRepo;

    private final CompraRepositorio compraRepo;

    private final CompraConfiteriaRepositorio compraConfiteriaRepo;

    private final EntradaRepositorio entradaRepo;

    private final CuponClienteRepositorio cuponClienteRepo;

    private final CuponRepositorio cuponRepo;

    private final EncuestaSatisfaccionRepositorio encuestaRepo;

    @Autowired
    private final EmailService emailService;


    public ClienteServicioImpl(ClienteRepositorio clienteRepo, PeliculaRepositorio peliculaRepo, CompraRepositorio compraRepo, CompraConfiteriaRepositorio compraConfiteriaRepo, EntradaRepositorio entradaRepo, CuponClienteRepositorio cuponClienteRepo, CuponRepositorio cuponRepo, EncuestaSatisfaccionRepositorio encuestaRepo, EmailService emailService) {
        this.clienteRepo = clienteRepo;
        this.peliculaRepo = peliculaRepo;
        this.compraRepo = compraRepo;
        this.compraConfiteriaRepo = compraConfiteriaRepo;
        this.entradaRepo = entradaRepo;
        this.cuponClienteRepo = cuponClienteRepo;
        this.cuponRepo = cuponRepo;
        this.encuestaRepo = encuestaRepo;
        this.emailService = emailService;

    }

    @Override
    public Cliente obtener(Integer cedula) {
        Optional<Cliente> guardado = Optional.ofNullable(clienteRepo.findByCedula(cedula));

        if (guardado.isEmpty()){
            try {
                throw new Exception("El cliente no existe");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return guardado.get();
    }



    @Override
    public Cliente comprobarAutenticacionCliente(String email, String password)throws Exception {
        Cliente cliente = clienteRepo.comprobarAuntenticacion(email, password);

        if (cliente == null){
            throw new Exception("Los datos de autenticacion son incorrectos");
        }
/**
        if( cliente.getEstado() == Estado.NO_REGISTRADO ){
            throw new Exception("El cliente no está activo");
        }
**/
        return cliente;
    }

/*
    @Override
    public Cliente login(String email, String password) throws Exception {

        return clienteRepo.findByEmailAndPassword(email,password).orElseThrow( ()-> new Exception("Los datos de autenticacion son incorrectos"));


    }
 */

    @Override
    public Cliente registrarCliente( Cliente cliente) throws Exception {

        boolean correoExiste = esRepetido(cliente.getEmail());

        if (correoExiste) {
            throw new Exception("Este correo ya esta registrado");
        }
        //enviar correo para activación
        Cliente registro = clienteRepo.save(cliente);
        emailService.enviarEmail("Activación De Cuenta","Acabas de registrarte en Unicine Colombia, click en el siguiente link para activar tu cuenta\n https://www.unicine.com", cliente.getEmail());
       //registro = activarCliente(registro.getEmail());
        return registro;
    }

  public Cliente activarCliente(String correo){
        //Activar Cliente
        Cliente cliente = clienteRepo.findByEmail(correo);
        if(cliente!=null) {
            cliente.setEstado(Estado.REGISTRADO);
            Cupon cupon = cuponRepo.findById(1).orElse(null);
            CuponCliente cuponCliente = new CuponCliente();
            cuponCliente.setEstado(EstadoCupon.DISPONIBLE);
            cuponCliente.setCupon(cupon);
            cuponCliente.setCliente(cliente);
            cuponClienteRepo.save(cuponCliente);
            emailService.enviarEmail("Cupon Primer registro", "Al parecer acabas de registrarte exitosamente en Unicine Colombia, cómo muestra de agradecimiento te obsequiamos el siguiente cupon el cual es redimible para cualquier compra que hagas. " + "\n" + cupon.toString(), cliente.getEmail());
        }
        return cliente;
    }



    private boolean esRepetido(String email){

        return clienteRepo.findByEmail(email) != null;

    }
    @Override
    public Cliente actualizarCliente(Cliente cliente)throws Exception {
        Optional<Cliente> guardado = Optional.ofNullable(clienteRepo.findByCedula(cliente.getCedula()));

        if (guardado.isEmpty()){

            throw new Exception("El cliente no existe");
        }

        return clienteRepo.save(cliente);
    }

    @Override
    public void eliminarCliente(Integer cedula) throws Exception{

        Optional<Cliente> guardado = Optional.ofNullable(clienteRepo.findByCedula(cedula));

        if (guardado.isEmpty()){
            throw new Exception("El cliente no existe");
        }

        clienteRepo.delete(guardado.get());

    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepo.findAll();
    }

    @Override
    public List<Compra> listarCompras(String email) throws Exception{
        return compraRepo.obtenerCompras(email);
    }

    @Override
    public Float redimirCupon(Integer idCupon, Float valorInicialCompra) throws Exception {

        Float valorFinalCompra ;

        CuponCliente cuponCliente = cuponClienteRepo.findById(idCupon).orElse(null);

        if (cuponCliente.getEstado() == EstadoCupon.DISPONIBLE) {
            valorFinalCompra = valorInicialCompra - (valorInicialCompra *cuponCliente.getCupon().getDescuento());
            cuponCliente.setEstado(EstadoCupon.NO_DISPONIBLE);
        }else{
            throw new Exception("El cupon no esta disponible");
        }
        return valorFinalCompra;
    }

    @Override
    public Cliente cambiarcontrasenia(Cliente cliente,String nuevaContrasenia) throws Exception {
        Optional<Cliente> guardado = Optional.ofNullable(clienteRepo.findByCedula(cliente.getCedula()));

        if (guardado.isEmpty()){

            throw new Exception("El cliente no existe");
        }
        emailService.enviarEmail("Cambio de Contraseña","Para cambio de contraseña ingresa al siguiente link www.unicine.com", cliente.getEmail());
        cliente.setContrasenia(nuevaContrasenia);
        return clienteRepo.save(cliente);
    }

    @Override
    public List<Pelicula> buscarPelicula(String nombrePelicula) throws Exception {

        List<Pelicula> peliculas = peliculaRepo.buscarPelicula(nombrePelicula);

        if (peliculas.isEmpty()){
            throw new Exception("No existe ninguna pelicula con el nombre " + nombrePelicula);
        }

        return peliculas;

    }


    @Override
    public Compra hacerCompra(List<Entrada> entradas, Cliente cliente, List<CompraConfiteria> confiterias, Funcion funcion, CuponCliente cupon, MetodoPago metodoPago) throws Exception {

        float total = 0;

        for (Entrada e : entradas) {
            boolean disponible = verificarDisponibilidad(e);
            if (!disponible) {
                throw new Exception("La silla no está disponible");
            }
            if (e.getTipoSilla() == TipoSilla.NORMAL) {
                total+= funcion.getPrecio();
            }
            total += funcion.getPrecioVip();
        }

        for (CompraConfiteria c : confiterias) {
            total += c.getPrecio() * c.getUnidades();

        }



        //Validar que el cupón exista y que esté disponible
        if (verificarDisponibilidadCupon(cupon.getId())){
             total =   redimirCupon(cupon.getId(),total);
        }



        Compra compra = new Compra();
        compra.setId(6);
        compra.setValorTotal(total); //antes validar el cupón y restar el descuento
        compra.setCliente(cliente);
        compra.setFuncion(funcion);
        compra.setCuponCliente(cupon);
        compra.setFecha(LocalDate.now());
        compra.setMetodoPago(metodoPago);

        Compra registro = compraRepo.save(compra);

        for (CompraConfiteria c : confiterias) {
            c.setCompra(registro);
            compraConfiteriaRepo.save(c);
        }

        for(Entrada e : entradas){
            e.setCompra(registro);
            entradaRepo.save(e);
        }

        //enviar correo de compra
        emailService.enviarEmail("Compra exitosa","Acabas de realizar una compra en unicine, estos son los detalles: \n " +compraRepo.obtenerInformacionCompra(cliente.getCedula()), cliente.getEmail());//compraRepo.obtenerInformacionCompra(cliente.getCedula())

        //si es la primera enviar cupón
        if (clienteRepo.obtenerComprasPorEmail(cliente.getEmail()).size() == 1){
            Cupon c = cuponClienteRepo.obtenerCupon(1);
            CuponCliente cuponCliente = CuponCliente.builder().estado(EstadoCupon.DISPONIBLE).build();
            cuponCliente.setCupon(c);
            emailService.enviarEmail("Cupon por primera compra","Gracias por comprar en Unicine, como agradecimiento te obsequiamos el siguiente cupon: "+ c,cliente.getEmail());
            cliente.getCuponClientes().add(cuponCliente);
        }
        return  compra;
    }



    private boolean verificarDisponibilidadCupon(Integer id) {

        CuponCliente cupon = cuponClienteRepo.findById(id).orElse(null);

        if (cupon != null && cupon.getEstado()!=EstadoCupon.NO_DISPONIBLE){
            return true;
        }
        return false;
    }


    private boolean verificarDisponibilidad(Entrada entrada) {
        //hacer un query que busque si existe una compra con la misma fila y columna de la entrada
        if (compraRepo.verificarExistenciaEntrada(entrada.getFila(), entrada.getColumna())==null ){
            return true;
        }
        return false;

    }






    @Override
    public EncuestaSatisfaccion realizarEncuesta(EncuestaSatisfaccion encuesta,Cliente cliente) throws Exception {


        EncuestaSatisfaccion ec = encuestaRepo.verificarRealizacion(cliente.getCedula());
        if (ec== null){

            return encuesta;

        }
        throw new Exception("El cliente realizó la encuesta anteriormente");

    }

    @Override
    public Entrada obtenerEntrada(Integer idEntrada) throws Exception {

        Entrada e = entradaRepo.findById(idEntrada).orElse(null);
        if (e == null){
            throw new Exception("la entrada no existe");
        }

        return e;

    }

    @Override
    public List<PeliculaFuncion> listarFuncionesPelicula(String nombre) {

        List<PeliculaFuncion> peliculaFunciones = peliculaRepo.listarPeliculaFuncion(nombre);
        return peliculaFunciones;
    }

    @Override
    public List<Pelicula> listarPeliculasEstado(EstadoPelicula estadoPelicula,Integer idCiudad) {

        return peliculaRepo.listarPeliculaEstado(estadoPelicula,idCiudad);
    }

    @Override
    public List<Pelicula> listarPeliculasEstadoNormal(EstadoPelicula estadoPelicula) throws Exception {
        return peliculaRepo.listarPeliculaEstadoNormal(estadoPelicula);
    }


}

