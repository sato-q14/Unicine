package co.uniquindio.unicine.test;


import co.uniquindio.unicine.entidades.*;
import co.uniquindio.unicine.servicios.AdministradorServicio;
import co.uniquindio.unicine.servicios.AdministradorTeatroServicio;
import co.uniquindio.unicine.servicios.ClienteServicio;
import co.uniquindio.unicine.servicios.EmailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@Transactional
public class ClienteServicioTest {

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private AdministradorServicio adminServicio;

    @Autowired
    private AdministradorTeatroServicio adminTeatroServicio;

    @Autowired
    private EmailService emailService;


    @Test
    @Sql("classpath:dataset.sql" )
    public void iniciarSesion() {

        try {
            Cliente cliente = clienteServicio.comprobarAutenticacionCliente("lopezhoyosluismiguel7@gmail.com","aeiou");
            Assertions.assertNotNull(cliente);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @Test
    @Sql("classpath:dataset.sql" )
    public void obtenerCliente() {

        try {
            Cliente c = clienteServicio.obtener(1007303046);
            System.out.println(c);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void registrarCliente() {

        List<String>telefonos = new ArrayList<>();
        telefonos.add("3214567890");
        telefonos.add("3145879520");


        Cliente cliente = Cliente.builder().cedula(123456789).nombreCompleto("Valeria Arias").email("lopezhoyosluismiguel7@gmail.com").urlFoto("url").contrasenia("abcd123").build();
        cliente.setTelefonos(telefonos);
        try {
            Cliente nuevo = clienteServicio.registrarCliente(cliente);
            Assertions.assertNotNull(nuevo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void actualizarCliente() {

        try {
            Cliente cliente = clienteServicio.obtener(1007303046);
            cliente.setNombreCompleto("Miguel Luis");
            Cliente nuevo = clienteServicio.actualizarCliente(cliente);
            Assertions.assertEquals("Miguel Luis",nuevo.getNombreCompleto());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void eliminarCliente() {

        try {
           clienteServicio.eliminarCliente(1007303046);
            Cliente cliente = clienteServicio.obtener(1007303046);
            Assertions.assertTrue(false);
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }

    }



    @Test
    @Sql("classpath:dataset.sql" )
    public void listarClientes() {

        List<Cliente> clientes = clienteServicio.listarClientes();
       // clientes.forEach(System.out::println);
        Assertions.assertNotNull(clientes);

    }

    @Test
    public void enviarCorreoTest(){

      emailService.enviarEmail("Prueba","Hola, esto es una prueba :3","lopezhoyosluismiguel7@gmail.com");
    }


    @Test
    @Sql("classpath:dataset.sql" )
    public void cambiarContrasenia() {

        try {
            Cliente cliente = clienteServicio.obtener(1007303046);
            String nuevaContrasenia = "Luis123";
            cliente.setContrasenia(nuevaContrasenia);
            Cliente nuevo = clienteServicio.cambiarcontrasenia(cliente,nuevaContrasenia);
            Assertions.assertEquals(nuevaContrasenia,nuevo.getContrasenia());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @Test
    @Sql("classpath:dataset.sql" )
    public void buscarPeliculas() {


        try {
            List<Pelicula>  peliculas = clienteServicio.buscarPelicula("thor");
            //peliculas.forEach(System.out::println);
            Assertions.assertNotNull(peliculas);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void realizarCompra() throws Exception {

        //obtenemos el cliente
        Cliente cliente = clienteServicio.obtener(1007303046);
        //obtenemos la confiteria
        Confiteria confiteria = adminServicio.obtenerConfiteria(1);
        CompraConfiteria compraConfiteria =new CompraConfiteria();
        compraConfiteria.setUnidades(5);
        compraConfiteria.setPrecio(5000f);
        List<CompraConfiteria> confiterias = new ArrayList<>();
        confiterias.add(compraConfiteria);


        // obtenemos la funcion
        Funcion funcion = adminTeatroServicio.obtenerFuncion(1);

        //El cupon
        Cupon cupon = adminServicio.obtenerCupon(1);
        CuponCliente cuponCliente = new CuponCliente();
        cuponCliente.setId(1);
        cuponCliente.setEstado(EstadoCupon.DISPONIBLE);
        cuponCliente.setCupon(cupon);
        cuponCliente.setCliente(cliente);


        //Entradas
        List<Entrada> entradas = new ArrayList<>();

        Entrada e1 = clienteServicio.obtenerEntrada(1);
        entradas.add(e1);
       // entradas.add(e2);

        //metodo de pago
        MetodoPago metodoPago =MetodoPago.DAVIPLATA;

        try {
            Compra compra =  clienteServicio.hacerCompra(entradas,cliente,confiterias,funcion,cuponCliente,metodoPago);
            Assertions.assertNotNull(compra);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void listarCompras()  {


        try {
            List<Compra> compras = clienteServicio.listarCompras("andres@gmail.com");
            compras.forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


    @Test
    @Sql("classpath:dataset.sql" )
    public void responderEncuesta()  {

        Cliente cliente = Cliente.builder().cedula(1007303052).build();
        EncuestaSatisfaccion ec = EncuestaSatisfaccion.builder().calificacion(8).cliente(cliente).build();

        try {
           EncuestaSatisfaccion nueva =  clienteServicio.realizarEncuesta(ec,cliente);
           System.out.println(nueva);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }



}
