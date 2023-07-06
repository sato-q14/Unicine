package co.uniquindio.unicine.test;

import co.uniquindio.unicine.entidades.Teatro;
import co.uniquindio.unicine.repositorio.TeatroRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TeatroTest {

    @Autowired
    private TeatroRepositorio teatroRepo;

    @Test
    @Sql("classpath:dataset.sql" )
    public void obtenerTeatrosPorCiudad(){

        List<Teatro> teatros = teatroRepo.listar("Armenia");
        teatros.forEach(System.out::println);

    }

    // test de consulta que permita contar el número de teatros que hay por cada ciudad. Use GROUP BY.
    @Test
    @Sql("classpath:dataset.sql" )
    public void contarTeatrosCiudad(){

        List<Object[]> teatros = teatroRepo.contarTeatros();
        teatros.forEach(o ->
                System.out.println(o[0] +","+ o[1]));
    }


}
