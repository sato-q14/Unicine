package co.uniquindio.unicine.test;

import co.uniquindio.unicine.dto.FuncionDTO;
import co.uniquindio.unicine.entidades.Cliente;
import co.uniquindio.unicine.entidades.Funcion;
import co.uniquindio.unicine.entidades.Pelicula;
import co.uniquindio.unicine.repositorio.ClienteRepositorio;
import co.uniquindio.unicine.repositorio.FuncionRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FuncionTest {


    @Autowired
    private FuncionRepositorio funcionRepo;

    @Test
    @Sql("classpath:dataset.sql" )
    public void obtenerNombrePeliculaFuncion(){

        String nombrePelicula = funcionRepo.obtenerNombrePelicula(1);
       // System.out.println(nombrePelicula);
        Assertions.assertEquals("Los Minions y sus amigos Volumen 1",nombrePelicula);

    }


    @Test
    @Sql("classpath:dataset.sql" )
    public void obtenerPeliculas(){

       List<Pelicula> peliculas = funcionRepo.obtenerPeliculas();
       peliculas.forEach(System.out::println);

    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void obtenerFunciones(){

        List<FuncionDTO> funciones = funcionRepo.obtenerFunciones(1);
        funciones.forEach(System.out::println);

    }


    //Test para una consulta que permita determinar qué funciones no tienen compras asociadas en un teatro específico. Use IS EMPTY.
    @Test
    @Sql("classpath:dataset.sql" )
    public void listarFuncionesSinCompra(){

        List<Funcion> funciones = funcionRepo.listarFuncionesSinCompras(1);
        funciones.forEach(System.out::println);


    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void listarFuncionesTeatro(){

        List<Funcion> funciones = funcionRepo.obtenerFuncionesTeatro(1, LocalDate.parse("2022-10-20"), LocalDate.parse("2022-09-27"));
        funciones.forEach(System.out::println);

    }



}
