package co.uniquindio.unicine.test;
import co.uniquindio.unicine.entidades.Administrador;
import co.uniquindio.unicine.entidades.Cliente;
import co.uniquindio.unicine.repositorio.AdministradorRepositorio;
import co.uniquindio.unicine.repositorio.ClienteRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AdministradorTest {

    @Autowired
    private AdministradorRepositorio administradorRepo;


    @Test
    @Sql("classpath:dataset.sql" )
    public void listarAdministradores(){

        List<Administrador> lista = administradorRepo.listarAdmins();
        lista.forEach(System.out::println);

    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void obtenerAdminPorCorreo(){

        Optional <Administrador> admin = administradorRepo.obtenerPorEmail("admin04@gmail.com");
        System.out.println(admin);

    }


    @Test
    @Sql("classpath:dataset.sql" )
    public void comprobarAutenticacion(){

      Administrador admin = administradorRepo.findByCorreoAndPassword("admin04@gmail.com","adesdse453");
        System.out.println(admin);
        Assertions.assertNotNull(admin);

    }







}
