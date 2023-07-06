package co.uniquindio.unicine.test;

import co.uniquindio.unicine.entidades.Ciudad;
import co.uniquindio.unicine.repositorio.CiudadRepositorio;
import co.uniquindio.unicine.repositorio.ClienteRepositorio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CiudadTest {

    @Autowired
    private CiudadRepositorio ciudadRepo;

    @Test
    @Sql("classpath:dataset.sql" )
    public void contarTeatrosCiudad(){
        List<Object[]> teatros = ciudadRepo.contarTeatros();
        teatros.forEach(o ->
                System.out.println(o[0] +","+ o[1] + "," + o[2]));
    }


    @Test
    @Sql("classpath:dataset.sql" )
    public void listarCiudades(){

        List<Ciudad> ciudades = ciudadRepo.findAll();
        ciudades.forEach(System.out::println);
    }



}
