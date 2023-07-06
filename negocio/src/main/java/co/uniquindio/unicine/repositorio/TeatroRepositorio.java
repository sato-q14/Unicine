package co.uniquindio.unicine.repositorio;

import co.uniquindio.unicine.entidades.Teatro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeatroRepositorio extends JpaRepository<Teatro,Integer> {

    @Query("select t from Teatro t where t.ciudad.nombre = :nombreCiudad")
    List<Teatro> listar(String nombreCiudad);


    //Cree una consulta que permita contar el número de teatros que hay por cada ciudad. Use GROUP BY.

    @Query("select t.ciudad.nombre, count(t) from Teatro t group by t.ciudad")
    List<Object[]> contarTeatros();




}
