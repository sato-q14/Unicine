package co.uniquindio.unicine.repositorio;

import co.uniquindio.unicine.entidades.Cupon;
import co.uniquindio.unicine.entidades.CuponCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuponClienteRepositorio extends JpaRepository<CuponCliente,Integer> {

    CuponCliente findByCliente(Integer idCliente);


    @Query("select c from CuponCliente c where c.cupon.id = :id")
    Cupon obtenerCupon(Integer id);
}
