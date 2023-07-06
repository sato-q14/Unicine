package co.uniquindio.unicine.repositorio;

import co.uniquindio.unicine.entidades.Administrador;
import co.uniquindio.unicine.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdministradorRepositorio extends JpaRepository<Administrador,Integer> {


    @Query("select a from Administrador a")
    List<Administrador> listarAdmins();

    @Query("select a from Administrador a where a.correo = :email")
    Optional <Administrador> obtenerPorEmail(String email);

    Administrador findByCorreoAndPassword(String correo, String password);










}
