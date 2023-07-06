package co.uniquindio.unicine.repositorio;

import co.uniquindio.unicine.dto.FuncionDTO;
import co.uniquindio.unicine.entidades.Funcion;
import co.uniquindio.unicine.entidades.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface FuncionRepositorio extends JpaRepository<Funcion, Integer> {

    @Query("select f.pelicula.nombre from Funcion f where f.id = :idFuncion")
    String obtenerNombrePelicula(Integer idFuncion);


    //Usando distinct
    @Query("select distinct f.pelicula from Funcion f")
    List<Pelicula> obtenerPeliculas();


   @Query("select new co.uniquindio.unicine.dto.FuncionDTO(f.pelicula.nombre, f.pelicula.estadoPelicula,f.pelicula.imagenes, f.sala.id, f.sala.teatro.direccion, f.sala.teatro.ciudad.nombre, f.horario) from Funcion f where f.pelicula.id = :idPelicula")
   List<FuncionDTO> obtenerFunciones(Integer idPelicula);


    //Cree una consulta que permita determinar qué funciones no tienen compras asociadas en un teatro específico. Use IS EMPTY.
    @Query("select f from Funcion f where f.sala.teatro.id = :idTeatro and f.compras is empty ")
    List<Funcion> listarFuncionesSinCompras(Integer idTeatro);


    @Query("select f from Funcion f where f.sala.teatro.id = :idTeatro and f.horario.inicio < :inicio or f.horario.fin > :fin")
    List<Funcion> obtenerFuncionesTeatro(Integer idTeatro, LocalDate inicio, LocalDate fin);

    @Query("select f from Funcion f where f.sala.id = :idSala and f.horario.id = :idHorario")
    Funcion verificarFuncionExiste(Integer idSala, Integer idHorario);

    @Query("select f.pelicula From Funcion f where f.id = :idFuncion")
    Pelicula obtenerPelicula(Integer idFuncion);


}
