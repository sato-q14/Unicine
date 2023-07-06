package co.uniquindio.unicine.servicios;

import co.uniquindio.unicine.dto.HorarioSalaDTO;
import co.uniquindio.unicine.dto.HorarioSalaPeliculaDTO;
import co.uniquindio.unicine.entidades.*;

import java.util.List;

public interface AdministradorTeatroServicio {


    AdministradorTeatro obtener(Integer id)throws Exception;
    boolean iniciarSesion(String email, String password)throws Exception;

    //Gestion de Teatros
    Teatro obtenerTeatro(Integer id)throws Exception;
    Teatro crearTeatro(Teatro teatro)throws Exception;
    void eliminarTeatro(Integer idTeatro) throws Exception;
    Teatro actualizarTeatro(Teatro teatro)throws Exception;
    List<Teatro> listarTeatros()throws Exception;

    //Gestion de Funciones
    Funcion obtenerFuncion(Integer id)throws Exception;
    Funcion crearFuncion(Funcion funcion)throws Exception;
    void eliminarFuncion(Integer idFuncion) throws Exception;
    Funcion actualizarFuncion(Funcion funcion)throws Exception;
    List<Funcion> listarFunciones()throws Exception;

    //Gestion de Salas
    Sala obtenerSala(Integer id)throws Exception;
    Sala crearSala(Sala sala)throws Exception;
    void eliminarSala(Integer idSala) throws Exception;
    Sala actualizarSala(Sala sala)throws Exception;
    List<Sala> listarSalas()throws Exception;

    Pelicula obtenerPelicula(Integer idFuncion) throws Exception;

    Horario obtenerHorario(Integer id) throws Exception;

    List<Horario> listarHorarios();

    //Definir horario

   // HorarioSalaPeliculaDTO definirHorario(Funcion funcion, Sala sala, Pelicula pelicula, Float precio , Horario horario) throws Exception;

}
