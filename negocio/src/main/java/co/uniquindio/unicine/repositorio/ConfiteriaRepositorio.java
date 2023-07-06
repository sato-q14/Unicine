package co.uniquindio.unicine.repositorio;

import co.uniquindio.unicine.entidades.Confiteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfiteriaRepositorio extends JpaRepository<Confiteria,Integer> {
}
