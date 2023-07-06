package co.uniquindio.unicine.test;

import co.uniquindio.unicine.dto.HorarioSalaDTO;
import co.uniquindio.unicine.entidades.CuponCliente;
import co.uniquindio.unicine.entidades.EstadoPelicula;
import co.uniquindio.unicine.entidades.Genero;
import co.uniquindio.unicine.entidades.Pelicula;
import co.uniquindio.unicine.repositorio.ClienteRepositorio;
import co.uniquindio.unicine.repositorio.PeliculaRepositorio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PeliculaTest {

    @Autowired
    private PeliculaRepositorio peliculaRepo;

    @Test
    @Sql("classpath:dataset.sql" )
    public void buscarPeliculas(){
        List<Pelicula> peliculas = peliculaRepo.buscarPeliculaConCadena("World", EstadoPelicula.EN_CARTELERA);
        peliculas.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void obtenerHorariosySala(){
        List<HorarioSalaDTO> horarios = peliculaRepo.listarHorarios(1,1);
        horarios.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void listarPeliculasAscendente(){
        List<Pelicula> peliculas = peliculaRepo.ordenarPeliculasGenero(Genero.TERROR);
        peliculas.forEach(System.out::println);
    }


}
