package co.uniquindio.unicine.test;


import co.uniquindio.unicine.repositorio.ConfiteriaRepositorio;
import co.uniquindio.unicine.repositorio.EntradaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ConfiteriaTest {

    @Autowired
    private ConfiteriaRepositorio confiteriaRepo;
}
