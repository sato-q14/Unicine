package co.uniquindio.unicine.repositorio;

import co.uniquindio.unicine.entidades.Cupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuponRepositorio extends JpaRepository<Cupon,Integer> {


    Optional<Cupon> findById(Integer id);
    Cupon findByFechaExpiracion(String fecha);


    @Query("select c from Cupon c where c.fechaExpiracion = :fecha")
    List<Cupon> obtenerPorFechaExpiracion(String fecha);




}
