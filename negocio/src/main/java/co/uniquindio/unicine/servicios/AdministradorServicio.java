package co.uniquindio.unicine.servicios;

import ch.qos.logback.core.encoder.EchoEncoder;
import co.uniquindio.unicine.entidades.*;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AdministradorServicio {


    //Administrador
    Administrador obtener(String email)throws Exception;
    boolean iniciarSesion(String email, String password)throws Exception;

    //Gestion de Cupones
    Cupon obtenerCupon(Integer id)throws Exception;
    Cupon crearCupon(Cupon cupon)throws Exception;
    void eliminarCupon(Integer idCupon) throws Exception;
    Cupon actualizarCupon(Cupon cupon)throws Exception;
    List<Cupon> listarCupones()throws Exception;


    //Gestion Administrador teatros
    AdministradorTeatro obtenerAdministradorTeatro(Integer id)throws Exception;
    AdministradorTeatro crearAdministradorTeatro(AdministradorTeatro adminTeatro)throws Exception;

    void eliminarAdministradorTeatro(Integer idAdminTeatro) throws Exception;

    AdministradorTeatro actualizarAdministradorTeatro(AdministradorTeatro adminTeatro)throws Exception;

    List<AdministradorTeatro> listarAdminTeatro();

    //Gestion de Ciudades

    Ciudad obtenerCiudad(Integer id)throws Exception;
    Ciudad agregarCiudad(Ciudad ciudad)throws Exception;

    void eliminarCiudad(Integer idCiudad)throws Exception;
    Ciudad actualizarCiudad(Ciudad ciudaad)throws Exception;

    List<Ciudad> listarCiudades();



    //Gestion de Encuestas
    EncuestaSatisfaccion obtenerEncuesta(Integer id)throws Exception;
    EncuestaSatisfaccion agregarEncuesta(EncuestaSatisfaccion encuesta)throws Exception;

    void eliminarEncuesta(Integer idEncuesta)throws Exception;
    EncuestaSatisfaccion actualizarEncuesta(EncuestaSatisfaccion encuesta)throws Exception;

    List<EncuestaSatisfaccion> listarEncuestas();

    //Gestion de Peliculas

    Pelicula obtenerPelicula(Integer id)throws Exception;
    Pelicula crearPelicula(Pelicula pelicula)throws Exception;

    void eliminarPelicula(Integer idPelicula)throws Exception;

    Pelicula actualizarPelicula(Pelicula pelicula)throws Exception;

    List<Pelicula> listarPeliculas();




    //Gestión de Confiteria

    Confiteria obtenerConfiteria(Integer id) throws Exception;
    Confiteria crearConfiteria(Confiteria confiteria)throws Exception;
    void eliminarConfiteria(Integer idConfiteria)throws Exception;

    Confiteria actualizarConfiteria(Confiteria confiteria)throws Exception;

    List<Confiteria> listarConfiteria();



}
