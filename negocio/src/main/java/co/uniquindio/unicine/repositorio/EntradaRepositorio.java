package co.uniquindio.unicine.repositorio;

import co.uniquindio.unicine.entidades.Cliente;
import co.uniquindio.unicine.entidades.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EntradaRepositorio extends JpaRepository<Entrada,Integer> {

    @Query("select e from Entrada e where e.fila = :fila and e.columna = :columna")
    Entrada buscarFilaYColumna(int fila, int columna);

    @Query("select e from Entrada e where e.compra.funcion.sala.distribucionSillas.columnas <= :columna and e.compra.funcion.sala.distribucionSillas.filas <= :fila")
    Entrada verificarFilaYColumna(int fila, int columna);

}
