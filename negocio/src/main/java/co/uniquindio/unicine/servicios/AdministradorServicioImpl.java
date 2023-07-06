package co.uniquindio.unicine.servicios;

import co.uniquindio.unicine.entidades.*;
import co.uniquindio.unicine.repositorio.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorServicioImpl  implements AdministradorServicio{

    private final AdministradorRepositorio administradorRepo;
    private final CuponRepositorio cuponRepo;
    private final AdministradorTeatroRepositorio administradorTeatroRepo;

    private final PeliculaRepositorio peliculaRepo;

    private final ConfiteriaRepositorio confiteriaRepo;

    private final CiudadRepositorio ciudadRepo;

    private final EncuestaSatisfaccionRepositorio encuestaRepo;


    public AdministradorServicioImpl(AdministradorRepositorio administradorRepo, CuponRepositorio cuponRepo, AdministradorTeatroRepositorio administradorTeatroRepo, PeliculaRepositorio peliculaRepo, ConfiteriaRepositorio confiteriaRepo, CiudadRepositorio ciudadRepo, EncuestaSatisfaccionRepositorio encuestaRepo) {
        this.administradorRepo = administradorRepo;
        this.cuponRepo = cuponRepo;
        this.administradorTeatroRepo = administradorTeatroRepo;
        this.peliculaRepo = peliculaRepo;
        this.confiteriaRepo = confiteriaRepo;
        this.ciudadRepo = ciudadRepo;
        this.encuestaRepo = encuestaRepo;
    }

    @Override
    public Administrador obtener(String email) {
        Optional<Administrador> guardado = administradorRepo.obtenerPorEmail(email);

        if (guardado == null){
            try {
                throw new Exception("El Administrador no existe");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return guardado.get();
    }

    @Override
    public boolean iniciarSesion(String email, String password) throws Exception {
        return false;
    }
/*
    @Override
    public boolean iniciarSesion(String email, String password) {

        Administrador guardado = administradorRepo.findByCorreoAndPassword(email,password);
        boolean flag = false;

        if (guardado!=null){
             return true;
        }else{
            try {
                throw new Exception("Usuario o contraseña Incorrectos");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
 */


    private boolean verificarExistencia(String email, String password){

        if(administradorRepo.findByCorreoAndPassword(email, password)==null){
            return false;
        }
        return true;

    }

    //Gestion de Cupones

    @Override
    public Cupon obtenerCupon(Integer idCupon) throws Exception {
     Cupon encontrado = cuponRepo.findById(idCupon).orElse(null);
        if (encontrado ==null){
            throw new Exception("El Cupon no existe");
        }else{
            return encontrado;
        }

    }

    @Override
    public Cupon crearCupon(Cupon cupon) throws Exception {
            return cuponRepo.save(cupon);

    }

    @Override
    public void eliminarCupon(Integer idCupon) throws Exception {

        if (obtenerCupon(idCupon)==null){
            throw new Exception("El cupon que desea eliminar no existe");

        }else{
            cuponRepo.delete(obtenerCupon(idCupon));
        }

    }

    @Override
    public Cupon actualizarCupon(Cupon cupon) throws Exception {

        if (obtenerCupon(cupon.getId())==null){
            throw new Exception("El cupon no existe");
        }
       return  cuponRepo.save(cupon);

    }

    @Override
    public List<Cupon> listarCupones() throws Exception {
        List<Cupon> cupones = cuponRepo.findAll();
        return cupones;

    }

    //Gestion de Administradores de Teatro
    @Override
    public AdministradorTeatro obtenerAdministradorTeatro(Integer id) throws Exception {
        Optional<AdministradorTeatro> encontrado = administradorTeatroRepo.findById(id);

        if (encontrado.isEmpty()){

            throw new Exception("Administrador Teatro no encontrado :(");
        }
            return encontrado.get();



    }

    @Override
    public AdministradorTeatro crearAdministradorTeatro(AdministradorTeatro adminTeatro) throws Exception {
            return  administradorTeatroRepo.save(adminTeatro);
        }




    @Override
    public void eliminarAdministradorTeatro(Integer idAdminTeatro) throws Exception {

        if (obtenerAdministradorTeatro(idAdminTeatro)==null){
            throw new Exception("El Administrador que desea eliminar no existe  :( ");

        }else{
            administradorTeatroRepo.delete(obtenerAdministradorTeatro(idAdminTeatro));
        }

    }

    @Override
    public AdministradorTeatro actualizarAdministradorTeatro(AdministradorTeatro adminTeatro) throws Exception {


        return  administradorTeatroRepo.save(adminTeatro);
    }

    @Override
    public List<AdministradorTeatro> listarAdminTeatro() {

        List<AdministradorTeatro> adminsTeatro = administradorTeatroRepo.listarAdmins();
        return adminsTeatro;

    }

    //ciudad
    @Override
    public Ciudad obtenerCiudad(Integer id) throws Exception {

        Optional<Ciudad> encontrada = ciudadRepo.findById(id);
        if (encontrada.isEmpty()){
            throw new Exception("La ciudad no se encuentra :(");
        }
        return encontrada.get();
    }

    @Override
    public Ciudad agregarCiudad(Ciudad ciudad) throws Exception {


        return ciudadRepo.save(ciudad);
    }

    @Override
    public void eliminarCiudad(Integer idCiudad) throws Exception {

        if (obtenerCiudad(idCiudad)==null){
            throw new Exception("la ciudad que desea eliminar no existe  :( ");

        }else{
            ciudadRepo.delete(obtenerCiudad(idCiudad));
        }

    }

    @Override
    public Ciudad actualizarCiudad(Ciudad ciudaad) throws Exception {
        if (obtenerCiudad(ciudaad.getId())==null){
            throw new Exception("La ciudad que busca no existe");
        }
        return  ciudadRepo.save(ciudaad);

    }

    @Override
    public List<Ciudad> listarCiudades() {
        return ciudadRepo.findAll();
    }


    //Gestion Peliculas
    @Override
    public Pelicula obtenerPelicula(Integer id) throws Exception {

        Optional<Pelicula> encontrada = peliculaRepo.findById(id);
        if (encontrada.isEmpty()){
            throw new Exception("La pelicula no existe");
        }
        return encontrada.get();

    }

    @Override
    public Pelicula crearPelicula(Pelicula pelicula) throws Exception {
            return peliculaRepo.save(pelicula);

    }

    @Override
    public void eliminarPelicula(Integer idPelicula) throws Exception {

            if (peliculaRepo.findById(idPelicula).isEmpty()){
                throw new Exception("La pelicula que desea borrar no existe :(");
            }
            peliculaRepo.delete(obtenerPelicula(idPelicula));

    }

    @Override
    public Pelicula actualizarPelicula(Pelicula pelicula) throws Exception {

        if (peliculaRepo.findById(pelicula.getId()).isEmpty()){
            throw new Exception("La pelicula no existe :(");
        }
        return peliculaRepo.save(pelicula);

    }

    @Override
    public List<Pelicula> listarPeliculas() {

        List<Pelicula> peliculas = peliculaRepo.findAll();
        return peliculas;
    }





    /**
     * Gestion de Confiteria
     */
    @Override
    public Confiteria obtenerConfiteria(Integer id) throws Exception {

        Optional<Confiteria> encontrada = confiteriaRepo.findById(id);
        if (encontrada.isEmpty()){
            throw new Exception("Confiteria no existe");
        }
        return encontrada.get();

    }

    @Override
    public Confiteria crearConfiteria(Confiteria confiteria) throws Exception {


            return confiteriaRepo.save(confiteria);

    }

    @Override
    public void eliminarConfiteria(Integer idConfiteria) throws Exception {
        if (confiteriaRepo.findById(idConfiteria).isEmpty()){
            throw new Exception("La confiteria que desea borrar no existe :(");
        }
        confiteriaRepo.delete(obtenerConfiteria(idConfiteria));
    }

    @Override
    public Confiteria actualizarConfiteria(Confiteria confiteria) throws Exception {

        if (confiteriaRepo.findById(confiteria.getId()).isEmpty()){
            throw new Exception("La confiteria no existe :(");
        }
        return confiteriaRepo.save(confiteria);

    }

    @Override
    public List<Confiteria> listarConfiteria() {
        List<Confiteria> listaConfiteria = confiteriaRepo.findAll();

        return listaConfiteria;
    }


    //Encuestas

    @Override
    public EncuestaSatisfaccion obtenerEncuesta(Integer id) throws Exception {

        Optional<EncuestaSatisfaccion> encuestaSatisfaccion = encuestaRepo.findById(id);
        if (encuestaSatisfaccion.isEmpty()){
            throw new Exception("La encuesta no existe");
        }
        return encuestaSatisfaccion.get();

    }

    @Override
    public EncuestaSatisfaccion agregarEncuesta(EncuestaSatisfaccion encuesta) throws Exception {
        if (encuestaRepo.findById(encuesta.getId())==null){
            throw new Exception("Ya existe una encuesta con el id "+ encuesta.getId());
        }
        return encuestaRepo.save(encuesta);
    }

    @Override
    public void eliminarEncuesta(Integer idEncuesta) throws Exception {

        if (encuestaRepo.findById(idEncuesta)==null){
            throw new Exception("No se puede eliminar la encuesta porque no existe");
        }
        encuestaRepo.delete(obtenerEncuesta(idEncuesta));

    }

    @Override
    public EncuestaSatisfaccion actualizarEncuesta(EncuestaSatisfaccion encuesta) throws Exception {

        if (encuestaRepo.findById(encuesta.getId())==null){
            throw new Exception("No se puede actualizar la encuesta porque no existe");
        }
       return encuesta;
    }

    @Override
    public List<EncuestaSatisfaccion> listarEncuestas() {
        List<EncuestaSatisfaccion> encuestas = encuestaRepo.findAll();

        return encuestas;
    }


}
