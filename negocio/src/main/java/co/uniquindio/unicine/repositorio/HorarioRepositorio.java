package co.uniquindio.unicine.repositorio;

import co.uniquindio.unicine.dto.HorarioSalaDTO;
import co.uniquindio.unicine.dto.HorarioSalaPeliculaDTO;
import co.uniquindio.unicine.entidades.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioRepositorio extends JpaRepository<Horario,Integer> {

    @Query("select new co.uniquindio.unicine.dto.HorarioSalaDTO(f.horario,f.sala) from Pelicula p join p.funciones f where p.id = :idPelicula and f.sala.id = :idSala")
    HorarioSalaPeliculaDTO definirHorario(Integer idPelicula, Integer idSala);


    @Query("select h from Horario h where h.id = :idHorario")
    Horario obtenerHorario(Integer idHorario);
}
