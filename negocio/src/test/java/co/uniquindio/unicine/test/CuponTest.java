package co.uniquindio.unicine.test;


import co.uniquindio.unicine.entidades.Cliente;
import co.uniquindio.unicine.entidades.Cupon;
import co.uniquindio.unicine.repositorio.ClienteRepositorio;
import co.uniquindio.unicine.repositorio.CuponRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CuponTest {


    @Autowired
    private CuponRepositorio cuponRepo;

    @Test
    @Sql("classpath:dataset.sql" )
    public void listar(){

        List<Cupon> lista = cuponRepo.findAll();
        lista.forEach(System.out::println);

    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void obtenerPorFechaExpiracion(){

        List<Cupon> lista = cuponRepo.obtenerPorFechaExpiracion("30-10-2022");
        lista.forEach(System.out::println);

    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void agregarCupon(){

        Cupon cupon = Cupon.builder().concepto("Primera compra").descripcion("Descuento del 20% primera compra").fechaExpiracion(LocalDate.of(2022,11,30)).build();
        Cupon guardado = cuponRepo.save(cupon);
        Assertions.assertNotNull(guardado);

    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void eliminarCupon(){

        Optional<Cupon> cupon = cuponRepo.findById(1);
        cuponRepo.delete(cupon.get());
        Assertions.assertNull(cuponRepo.findById(1));
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void actualizarCupon(){

        Optional<Cupon> guardado = cuponRepo.findById(1);
        guardado.get().setDescuento(0.15f);
        Cupon nuevo = cuponRepo.save(guardado.get());
        //System.out.println(nuevo);
        Assertions.assertEquals(0.15f,nuevo.getDescuento());
    }





}
