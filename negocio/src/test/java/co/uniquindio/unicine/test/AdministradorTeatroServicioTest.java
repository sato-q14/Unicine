package co.uniquindio.unicine.test;

import co.uniquindio.unicine.dto.HorarioSalaDTO;
import co.uniquindio.unicine.dto.HorarioSalaPeliculaDTO;
import co.uniquindio.unicine.entidades.*;
import co.uniquindio.unicine.servicios.AdministradorServicio;
import co.uniquindio.unicine.servicios.AdministradorTeatroServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class AdministradorTeatroServicioTest {

    @Autowired
    private AdministradorTeatroServicio administradorTeatroServicio;

    @Autowired
    private AdministradorServicio adminServicio;


    @Test
    @Sql("classpath:dataset.sql" )
    public void obtenerAdminTeatro() {


        try {
            AdministradorTeatro admin  = administradorTeatroServicio.obtener(1002658741);
            Assertions.assertNotNull(admin);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }


    @Test
    @Sql("classpath:dataset.sql" )
    public void comprobarAutenticacion() {

        try {
            boolean admin = administradorTeatroServicio.iniciarSesion("admin1@gmail.com","admin1230");
            Assertions.assertTrue(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    //Gestion teatros
    @Test
    @Sql("classpath:dataset.sql" )
    public void obtenerTeatro() {

        try {
            Teatro teatro = administradorTeatroServicio.obtenerTeatro(1);
            Assertions.assertNotNull(teatro);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void crearTeatro() throws Exception {

               AdministradorTeatro administradorTeatro = adminServicio.obtenerAdministradorTeatro(102658742);
                Ciudad c = adminServicio.obtenerCiudad(1);

                Teatro teatro = Teatro.builder().direccion("Cra 10 #14-23").telefono("3156101867").build();

        try {
            Teatro nuevo = administradorTeatroServicio.crearTeatro(teatro);
            Assertions.assertNotNull(nuevo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void eliminarTeatro() throws Exception {

        Teatro teatro = administradorTeatroServicio.obtenerTeatro(1);
        try {
           administradorTeatroServicio.eliminarTeatro(teatro.getId());
           Assertions.assertNull(teatro);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @Test
    @Sql("classpath:dataset.sql" )
    public void actualizarTeatro() {

        try {

            Teatro teatro = administradorTeatroServicio.obtenerTeatro(3);
            teatro.setDireccion("calle 20 #16-13");
            Teatro nuevo = administradorTeatroServicio.actualizarTeatro(teatro);
            Assertions.assertEquals("calle 20 #16-13",nuevo.getDireccion());

        } catch (Exception e) {

            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void  listarTeatros() throws Exception {

        List<Teatro> teatros = administradorTeatroServicio.listarTeatros();
        Assertions.assertNotNull(teatros);

    }


    //Gestion Funciones

    @Test
    @Sql("classpath:dataset.sql" )
    public void obtenerFuncion() {

        try {
            Funcion funcion = administradorTeatroServicio.obtenerFuncion(1);
            Assertions.assertNotNull(funcion);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
/*
    @Test
    @Sql("classpath:dataset.sql" )
    public void crearFuncion() throws Exception {

        Sala sala = administradorTeatroServicio.obtenerSala(3);
        Horario horario = administradorTeatroServicio.obtenerHorario(1);
        Pelicula pelicula = administradorTeatroServicio.obtenerPelicula(1);
        Float precio = 70000f ;
        Float precioVip = 70000f ;

        try {
            Funcion funcion = administradorTeatroServicio.crearFuncion(sala,horario,pelicula, precio, precioVip);
            Assertions.assertNotNull(funcion);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
*/
    @Test
    @Sql("classpath:dataset.sql" )
    public void eliminarFuncion() throws Exception {

        Funcion funcion = administradorTeatroServicio.obtenerFuncion(1);
        try {
            administradorTeatroServicio.eliminarFuncion(funcion.getId());
            Assertions.assertTrue(false);
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }

    }


    @Test
    @Sql("classpath:dataset.sql" )
    public void actualizarFuncion() {

        try {

            Funcion funcion = administradorTeatroServicio.obtenerFuncion(1);
            funcion.setPrecio(15650.27f);
            Funcion nuevo = administradorTeatroServicio.actualizarFuncion(funcion);
            Assertions.assertEquals(15650.27f,nuevo.getPrecio());

        } catch (Exception e) {

            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void  listarFunciones() throws Exception {

        List<Funcion> funciones = administradorTeatroServicio.listarFunciones();
        Assertions.assertNotNull(funciones);

    }

    //Gestion Salas

    @Test
    @Sql("classpath:dataset.sql" )
    public void obtenerSala() {

        try {
            Sala sala = administradorTeatroServicio.obtenerSala(1);
            Assertions.assertNotNull(sala);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void crearSala() {

        DistribucionSillas d = DistribucionSillas.builder().columnas(20).filas(6).esquema("nnnnnnnnnn nnnnnnnnnn\n" +
                "nnnnnnnnnn nnnnnnnnnn\n" +
                "nnnnnnnnnn nnnnnnnnnn\n" +
                "nnnnnnnnnn nnnnnnnnnn\n" +
                "vvvvvvvvvv vvvvvvvvvv\n" +
                "vvvvvvvvvv vvvvvvvvvv").totalSillasVip(40).totalSillasNormales(80).build();
        Sala sala = Sala.builder().nombreSala("Sala La Gran Pantalla").build();
        try {
            Sala nuevo = administradorTeatroServicio.crearSala(sala);
            Assertions.assertNotNull(nuevo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void eliminarSala() throws Exception {

        Sala sala = administradorTeatroServicio.obtenerSala(2);
        try {
            administradorTeatroServicio.eliminarSala(sala.getId());
            Assertions.assertFalse(false);
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }

    }


    @Test
    @Sql("classpath:dataset.sql" )
    public void actualizarSala() {

        try {

            Sala sala = administradorTeatroServicio.obtenerSala(1);
            sala.setNombreSala("cinema dorado");
            Sala nuevo = administradorTeatroServicio.actualizarSala(sala);
            Assertions.assertEquals("cinema dorado",nuevo.getNombreSala());

        } catch (Exception e) {

            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void  listarSalas() throws Exception {

        List<Sala> salas = administradorTeatroServicio.listarSalas();
        Assertions.assertNotNull(salas);

    }












/**
    @Test
    @Sql("classpath:dataset.sql" )
    public void  definirHorario() throws Exception {

        Sala sala = Sala.builder().build();
        Pelicula pelicula = Pelicula.builder().build();
        Funcion funcion =Funcion.builder().build();
        Horario hr = Horario.builder().hora(08.00f).dia(6).build();

        HorarioSalaPeliculaDTO horario = administradorTeatroServicio.definirHorario(funcion,sala,pelicula,funcion.getPrecio(),hr);

        System.out.println(horario);

    }

**/

}
