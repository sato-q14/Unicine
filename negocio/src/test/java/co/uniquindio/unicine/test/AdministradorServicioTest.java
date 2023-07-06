package co.uniquindio.unicine.test;

import co.uniquindio.unicine.entidades.*;
import co.uniquindio.unicine.servicios.AdministradorServicio;
import co.uniquindio.unicine.servicios.AdministradorTeatroServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class AdministradorServicioTest {


    @Autowired
    private AdministradorServicio administradorServicio;

    @Autowired
    private AdministradorTeatroServicio administradorTeatroServicio;
    @Test
    @Sql("classpath:dataset.sql" )
    public void obtenerAdmin() {

        try {
            Administrador admin = administradorServicio.obtener("admin02>@gmail.com");
            Assertions.assertNotNull(admin);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @Test
    @Sql("classpath:dataset.sql" )
    public void comprobarAutenticacion() {

        try {
            boolean admin = administradorServicio.iniciarSesion("admin02@gmail.com","abcdft");
            Assertions.assertNotNull(admin);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }



    //Gestión cupones
    @Test
    @Sql("classpath:dataset.sql" )
    public void crearCupon() {

        Cupon cupon = Cupon.builder().concepto("primera compra").descripcion(
                "descuento del 15% en la primera compra").descuento(0.15f).fechaExpiracion(LocalDate.of(2022,12,21)).build();

        try {
            Cupon nuevo = administradorServicio.crearCupon(cupon);
            Assertions.assertNotNull(nuevo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void obtenerCupon() {

        try {
            Cupon cupon = administradorServicio.obtenerCupon(1);
            Assertions.assertNotNull(cupon);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void eliminarCupon() throws Exception {

        try {

           administradorServicio.eliminarCupon(1);
            Cupon cupon = administradorServicio.obtenerCupon(1);
            Assertions.assertTrue(false);
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }


    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void actualizarCupon() {

        try {

            Cupon cupon = administradorServicio.obtenerCupon(1);
            cupon.setConcepto("compra mas alta");
            Cupon nuevo = administradorServicio.actualizarCupon(cupon);
            Assertions.assertEquals("compra mas alta",nuevo.getConcepto());

        } catch (Exception e) {

            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void listarCupones() throws Exception {

        List<Cupon> cupones = administradorServicio.listarCupones();
        cupones.forEach(System.out::println);
        Assertions.assertNotNull(cupones);

    }


    //Gestión Administrador Teatro
    @Test
    @Sql("classpath:dataset.sql" )
    public void obtenerAdminTeatro() {

        try {
            AdministradorTeatro admin = administradorServicio.obtenerAdministradorTeatro(1007202125);
            Assertions.assertNotNull(admin);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void crearAdministradorTeatro() {

        AdministradorTeatro admin = AdministradorTeatro.builder().id(1234567890).email("admin06@gmai.com"
        ).password("admin666").build();

        try {
            AdministradorTeatro nuevo = administradorServicio.crearAdministradorTeatro(admin);
            Assertions.assertNotNull(nuevo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void eliminarAdministradorTeatro() throws Exception {

        try {
            administradorServicio.eliminarAdministradorTeatro(1007302102);
            Assertions.assertTrue(false);
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }

    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void actualizarAdministradorTeatro() throws Exception {

        AdministradorTeatro admin = administradorServicio.obtenerAdministradorTeatro(1007302102);
        admin.setEmail("adminUno@gmail");
        AdministradorTeatro nuevo = administradorServicio.actualizarAdministradorTeatro(admin);
        Assertions.assertEquals("adminUno@gmail",nuevo.getEmail());


    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void listarAdministradorTeatro() throws Exception {

       List<AdministradorTeatro> administradorTeatros = administradorServicio.listarAdminTeatro();
       Assertions.assertNotNull(administradorTeatros);
    }

    //Gestión peliculas
    @Test
    @Sql("classpath:dataset.sql" )
    public void obtenerPelicula() {

        try {
            Pelicula pelicula = administradorServicio.obtenerPelicula(1);
            System.out.println(pelicula);
            Assertions.assertNotNull(pelicula);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
/**
    @Test
    @Sql("classpath:dataset.sql" )
    public void crearPelicula() {

            Pelicula pelicula = Pelicula.builder().nombre("Resident Evil").estadoPelicula(EstadoPelicula.EN_CARTELERA).reparto(
                    "Kaya Scodelario,Hannah John-Kamen,Robbie AmellL").urlTrailer("https://youtu.be/sKNQM7JmF-k").sinopsis(
                            "En un centro clandestino de investigación genética, se produce un brote vírico. Para contener la fuga,se sella toda la instalación y, en principio, se cree que mueren todos los empleados, pero en realidad se convierten en feroces zombis."
            ).urlImagen("url_imagen").build();

        try {
            Pelicula nueva = administradorServicio.crearPelicula(pelicula);
            System.out.println(nueva);
            Assertions.assertNotNull(pelicula);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
**/
    @Test
    @Sql("classpath:dataset.sql" )
    public void eliminarPelicula() {

        try {
            administradorServicio.eliminarPelicula(1);
            Assertions.assertTrue(false);
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }

    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void actualizarPelicula() throws Exception {

       Pelicula encontrada = administradorServicio.obtenerPelicula(3);
       encontrada.setNombre("Vengadores 4");
       Pelicula nueva = administradorServicio.actualizarPelicula(encontrada);
       Assertions.assertEquals("Vengadores 4",nueva.getNombre());
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void listarPelicula() {

        List<Pelicula> peliculas = administradorServicio.listarPeliculas();
        Assertions.assertNotNull(peliculas);

    }
/**
    @Test
    @Sql("classpath:dataset.sql" )
    public void listarPeliculaEstado() {

        List<Pelicula> peliculas = administradorServicio.listarPeliculasPorEstado(EstadoPelicula.EN_CARTELERA);
        peliculas.forEach(System.out::println);
        Assertions.assertNotNull(peliculas);

    }

**/
    //Gestión confiteria

    @Test
    @Sql("classpath:dataset.sql" )
    public void obtenerConfiteria() {

        try {
            Confiteria confiteria = administradorServicio.obtenerConfiteria(3);
            Assertions.assertNotNull(confiteria);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
/**
    @Test
    @Sql("classpath:dataset.sql" )
    public void crearConfiteria() {

        Confiteria confiteria = Confiteria.builder().nombreAlimento("cerveza").precio(7300.00f).urlImagenConfiteria("url").build();

        try {
            Confiteria nueva = administradorServicio.crearConfiteria(confiteria);
            Assertions.assertNotNull(nueva);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
**/

    @Test
    @Sql("classpath:dataset.sql" )
    public void eliminarConfiteria() {

        try {
            administradorServicio.eliminarConfiteria(3);
            Assertions.assertTrue(false);
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }

    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void actualizarConfiteria() throws Exception {

        Confiteria encontrada = administradorServicio.obtenerConfiteria(3);
        encontrada.setNombreAlimento("Takis");
        Confiteria nueva = administradorServicio.actualizarConfiteria(encontrada);
        Assertions.assertEquals("Takis",nueva.getNombreAlimento());
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void listarConfiteria() {

        List<Confiteria> listaConfiteria = administradorServicio.listarConfiteria();
        Assertions.assertNotNull(listaConfiteria);

    }


    @Test
    @Sql("classpath:dataset.sql" )
    public void obtenerEncuesta() {

        try {
            EncuestaSatisfaccion encuesta = administradorServicio.obtenerEncuesta(1);
            Assertions.assertNotNull(encuesta);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void crearEncuesta() {

        EncuestaSatisfaccion  ec = EncuestaSatisfaccion.builder().build();

        try {
            EncuestaSatisfaccion nueva = administradorServicio.agregarEncuesta(ec);
            Assertions.assertNotNull(nueva);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void eliminarEncuesta() {

        try {
            administradorServicio.eliminarEncuesta(1);
            Assertions.assertTrue(false);
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }

    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void actualizarEncuesta() throws Exception {

        EncuestaSatisfaccion encontrada = administradorServicio.obtenerEncuesta(1);
        encontrada.setId(5);
        EncuestaSatisfaccion nueva = administradorServicio.actualizarEncuesta(encontrada);
        Assertions.assertEquals(5, nueva.getId());
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void listarEncuestas() {

        List<EncuestaSatisfaccion> encuestas = administradorServicio.listarEncuestas();
        Assertions.assertNotNull(encuestas);

    }



    //Ciudad

    @Test
    @Sql("classpath:dataset.sql" )
    public void obtenerCiudad() {

        try {
            Ciudad ciudad = administradorServicio.obtenerCiudad(1);
            Assertions.assertNotNull(ciudad);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void crearCiudad() throws Exception {

       Ciudad c = new Ciudad();
        try {
            Ciudad nueva = administradorServicio.agregarCiudad(c);
            System.out.println(nueva);
            Assertions.assertNotNull(nueva);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void eliminarCiudad() {

        try {
            administradorServicio.eliminarCiudad(1);
            Assertions.assertTrue(false);
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }

    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void actualizarCiudad() throws Exception {

        Ciudad encontrada = administradorServicio.obtenerCiudad(1);
        encontrada.setNombre("Armenia");
        Ciudad nueva = administradorServicio.actualizarCiudad(encontrada);
        Assertions.assertEquals("Armenia", nueva.getNombre());
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void listarCiudades() {

        List<Ciudad> ciudades = administradorServicio.listarCiudades();
        ciudades.forEach(System.out::println);
        Assertions.assertNotNull(ciudades);

    }




}
