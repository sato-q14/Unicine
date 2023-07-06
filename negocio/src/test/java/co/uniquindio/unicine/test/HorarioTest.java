package co.uniquindio.unicine.test;

import co.uniquindio.unicine.repositorio.HorarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HorarioTest {

    @Autowired
    private HorarioRepositorio horarioRepo;


}
