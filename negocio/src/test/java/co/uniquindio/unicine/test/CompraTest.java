package co.uniquindio.unicine.test;

import co.uniquindio.unicine.dto.InformacionCompraDTO;
import co.uniquindio.unicine.entidades.Cliente;
import co.uniquindio.unicine.entidades.Compra;
import co.uniquindio.unicine.entidades.Entrada;
import co.uniquindio.unicine.entidades.Estado;
import co.uniquindio.unicine.repositorio.ClienteRepositorio;
import co.uniquindio.unicine.repositorio.CompraRepositorio;
import co.uniquindio.unicine.repositorio.FuncionRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompraTest {

    @Autowired
    private CompraRepositorio compraRepo;

    @Test
    @Sql("classpath:dataset.sql" )
    public void obtenerComprasDadoEmail(){
        List<Compra> compras = compraRepo.obtenerCompras("luism.lopez@gmail.com");
        compras.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void obtenerEntradas(){
        List<Entrada> entradas = compraRepo.obtenerEntradas(1);
        //entradas.forEach(System.out::println);
        Assertions.assertEquals(2,entradas.size());
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void contarCuponesCliente(){
        List<Object[]> cupones = compraRepo.contarCuponesRedimidos();
        cupones.forEach(o ->
                System.out.println(o[0] +","+ o[1]));
    }


    @Test
    @Sql("classpath:dataset.sql" )
    public void sumarTotalGastado(){
        float sumaValorTotal = compraRepo.calcularTotalGastadoCliente(1007303046);
        //entradas.forEach(System.out::println);
        System.out.println("El total gastado por el cliente es: " + sumaValorTotal);
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void obtenerCompraMaxima(){
        List<Object[]> compras = compraRepo.calcularCompraMasCostosa();
        compras.forEach(o ->
                System.out.println(o[0] +","+ o[1]));
    }



    @Test
    @Sql("classpath:dataset.sql" )
    public void obtenerInformacionCompra(){
        List<InformacionCompraDTO> infoCompras = compraRepo.obtenerInformacionCompra(1007303046);
        infoCompras.forEach(System.out::println);
    }



    @Test
    @Sql("classpath:dataset.sql" )
    public void obtenerPeliculaMasVista(){
        List<Object[]> peliculas = compraRepo.obtenerPeliculaMasVista(1);
        peliculas.forEach(o ->
                System.out.println(o[0] +","+ o[1]));
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void obtenerComprasFuncion(){
        List<Compra> compras = compraRepo.obtenerComprasAsociadasFuncion(1);
        compras.forEach(System.out::println);
    }


    @Test
    @Sql("classpath:dataset.sql" )
    public void verificarEntrada(){
        Compra c = compraRepo.verificarExistenciaEntrada(13,2);
        System.out.println(c);


    }

}
