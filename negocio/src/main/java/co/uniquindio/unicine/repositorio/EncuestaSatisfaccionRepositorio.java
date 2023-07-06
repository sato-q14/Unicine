package co.uniquindio.unicine.repositorio;

import co.uniquindio.unicine.entidades.EncuestaSatisfaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;

@Repository
public interface EncuestaSatisfaccionRepositorio extends JpaRepository<EncuestaSatisfaccion,Integer> {


    @Query("select e from EncuestaSatisfaccion e where e.cliente.cedula = :idCliente")
    EncuestaSatisfaccion verificarRealizacion(Integer idCliente);
}
