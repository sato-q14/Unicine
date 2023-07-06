package co.uniquindio.unicine.test;

import co.uniquindio.unicine.entidades.*;
import co.uniquindio.unicine.repositorio.ClienteRepositorio;
import co.uniquindio.unicine.repositorio.FuncionRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClienteTest {

    @Autowired
    private ClienteRepositorio clienteRepo;

    @Test
    @Sql("classpath:dataset.sql" )
    public void registrar(){

        List<String> telefonos = new ArrayList<>();
        telefonos.add("3212644521");
        Cliente cliente = new Cliente(1007303049,"Pepito Perez","pepito@gmail.com","url_foto","321");
        Cliente guardado =  clienteRepo.save(cliente);
        Assertions.assertNotNull(guardado);
    }
    @Test
    @Sql("classpath:dataset.sql" )
    public void eliminar(){

        Cliente buscado =  clienteRepo.findByCedula(1007303046);

        clienteRepo.delete(buscado);
        Assertions.assertNull(clienteRepo.findByCedula(1007303046));
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void actualizar(){

        Cliente guardado = clienteRepo.findByCedula(1007303046);
        guardado.setNombreCompleto("Alejandra");

        Cliente nuevo = clienteRepo.save(guardado);

        Assertions.assertEquals("Alejandra",nuevo.getNombreCompleto());

    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void obtener(){
        Cliente cliente = clienteRepo.findByCedula(1007303046);
        Assertions.assertNotNull(cliente);
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void listar(){

        List<Cliente> lista = clienteRepo.findAll();
        lista.forEach(System.out::println);

    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void obtenerPorCorreo(){

        Cliente cliente = clienteRepo.findByEmail("luism.lopez@gmail.com");
        Assertions.assertNotNull(cliente);
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void comprobarAuntenticacion(){
        Cliente cliente = clienteRepo.findByEmailAndContrasenia("luism.lopez@gmail.com","3212644521");
        Assertions.assertNotNull(cliente);

    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void paginador(){
        List<Cliente> clientes = clienteRepo.findAll(PageRequest.of(0,2)).toList();

        clientes.forEach(System.out::println);
    }


    @Test
    @Sql("classpath:dataset.sql" )
    public void paginadorEstado(){
        List<Cliente> clientes = clienteRepo.obtenerPorEstado(Estado.NO_REGISTRADO, PageRequest.of(0,3));

        clientes.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void ordenarRegistros(){
        List<Cliente> clientes = clienteRepo.findAll(PageRequest.of(0,3,Sort.by("nombreCompleto"))).toList();

        clientes.forEach(System.out::println);
    }



    @Test
    @Sql("classpath:dataset.sql" )
    public void obtenerCompras(){
        List<Compra> compras = clienteRepo.obtenerComprasPorEmail("luism.lopez@gmail.com");
        compras.forEach(System.out::println);
    }

    //usando el Join
    @Test
    @Sql("classpath:dataset.sql" )
    public void obtenerComprasPorEmail(){
        List<Compra> compras = clienteRepo.obtenerComprasPorEmail("luism.lopez@gmail.com");
        compras.forEach(System.out::println);
    }


    @Test
    @Sql("classpath:dataset.sql" )
    public void obtenerCuponesPorEmail(){
        List<CuponCliente> cupones = clienteRepo.obtenerCuponesEmail("luism.lopez@gmail.com");
        cupones.forEach(System.out::println);
    }



    @Test
    @Sql("classpath:dataset.sql" )
    public void obtenerComprasPorCliente(){
        List<Object[]> compras = clienteRepo.obtenerComprasTodos();
        compras.forEach(o ->
                System.out.println(o[0] +","+ o[1]));
    }


    @Test
    @Sql("classpath:dataset.sql" )
    public void obtenerComprasPorClienteEmail(){
        List<Object[]> compras = clienteRepo.obtenerComprasTodosConEmail();
        compras.forEach(o ->
                System.out.println(o[0] +","+ o[1]));
    }

}
