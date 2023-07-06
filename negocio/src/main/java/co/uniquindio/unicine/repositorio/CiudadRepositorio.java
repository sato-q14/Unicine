package co.uniquindio.unicine.repositorio;


import co.uniquindio.unicine.entidades.Ciudad;
import co.uniquindio.unicine.entidades.Teatro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CiudadRepositorio extends JpaRepository<Ciudad,Integer> {


    @Query("select c.id, c.nombre, count(t) from Ciudad c join c.teatros t group by c.id")
    List<Object[]> contarTeatros();


}
