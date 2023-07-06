package co.uniquindio.unicine.repositorio;

import co.uniquindio.unicine.dto.HorarioSalaDTO;
import co.uniquindio.unicine.dto.PeliculaFuncion;
import co.uniquindio.unicine.entidades.Ciudad;
import co.uniquindio.unicine.entidades.EstadoPelicula;
import co.uniquindio.unicine.entidades.Genero;
import co.uniquindio.unicine.entidades.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculaRepositorio extends JpaRepository<Pelicula,Integer> {

    //Cree una consulta que devuelva una lista con todas las películas que contengan en su nombre una cadena de búsqueda y que tengan un estado (cartelera o próximamente). Use LIKE.

    @Query("select p from Pelicula p where p.nombre like concat('%',:nombre,'%') and p.estadoPelicula = :estado")
    List<Pelicula> buscarPeliculaConCadena(String nombre, EstadoPelicula estado);


    @Query("select new co.uniquindio.unicine.dto.HorarioSalaDTO(f.horario,f.sala) from Pelicula p join p.funciones f where p.id = :idPelicula and f.sala.teatro.id = :idTeatro")
    List<HorarioSalaDTO> listarHorarios(Integer idPelicula, Integer idTeatro);


    @Query("select p from Pelicula p join p.generos g where g = :generoPelicula order by p.nombre asc ")
    List<Pelicula> ordenarPeliculasGenero(Genero generoPelicula);


    @Query("select p from Pelicula p where p.nombre like concat('%',:nombre,'%')")
    List<Pelicula> buscarPelicula(String nombre);

    @Query("select distinct f.pelicula from Funcion f where f.sala.teatro.ciudad.id = :idCiudad and f.pelicula.estadoPelicula = :estadoPelicula")
    List<Pelicula> listarPeliculaEstado(EstadoPelicula estadoPelicula, Integer idCiudad);

    @Query("select distinct f.pelicula from Funcion f where f.pelicula.estadoPelicula = :estadoPelicula")
    List<Pelicula> listarPeliculaEstadoNormal(EstadoPelicula estadoPelicula);


    @Query("select new co.uniquindio.unicine.dto.PeliculaFuncion(p,f) from Pelicula p left join p.funciones f where p.nombre like concat('%',:nombre,'%')")
    List<PeliculaFuncion> listarPeliculaFuncion(String nombre);

}
