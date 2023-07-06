package co.uniquindio.unicine.repositorio;

import co.uniquindio.unicine.entidades.DistribucionSillas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistribucionSillasRepositorio extends JpaRepository<DistribucionSillas,String> {
}
