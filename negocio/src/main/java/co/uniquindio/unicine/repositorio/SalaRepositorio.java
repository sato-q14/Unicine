package co.uniquindio.unicine.repositorio;

import co.uniquindio.unicine.entidades.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepositorio extends JpaRepository<Sala,Integer> {
}
