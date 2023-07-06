package co.uniquindio.unicine.repositorio;

import co.uniquindio.unicine.entidades.AdministradorTeatro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdministradorTeatroRepositorio extends JpaRepository<AdministradorTeatro,Integer> {

    @Query("select a from AdministradorTeatro a")
    List<AdministradorTeatro> listarAdmins();

    @Query("select a from AdministradorTeatro a where a.email = :email")
    AdministradorTeatro obtenerPorEmail(String email);

    @Query("select a from AdministradorTeatro a where a.email = :email and a.password = :contrasenia")
    AdministradorTeatro comprobarAuntenticacion(String email, String contrasenia);

    Optional<AdministradorTeatro> findById(Integer id);











}
