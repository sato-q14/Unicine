package co.uniquindio.unicine.repositorio;

import co.uniquindio.unicine.dto.InformacionCompraDTO;
import co.uniquindio.unicine.entidades.Compra;
import co.uniquindio.unicine.entidades.Entrada;
import co.uniquindio.unicine.entidades.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepositorio extends JpaRepository<Compra,Integer> {

    @Query("select c from Compra c where c.cliente.email = :email")
    List<Compra> obtenerCompras(String email);

    @Query("select e from Compra c join c.entradas e where c.id = :idCompra")
    List<Entrada> obtenerEntradas(Integer idCompra);

    @Query("select c.cliente.email, count(c) from Compra c where c.cuponCliente is not null group by c.cliente")
    List<Object[]> contarCuponesRedimidos();

    @Query("select sum(c.valorTotal) from Compra c where c.cliente.cedula = :idCliente")
    float calcularTotalGastadoCliente(Integer idCliente);


    @Query("select c1.cliente.cedula,c1 from Compra c1 where c1.valorTotal = (select max(c.valorTotal) from Compra c)")
    List<Object[]> calcularCompraMasCostosa();


    @Query("select new co.uniquindio.unicine.dto.InformacionCompraDTO(c.valorTotal, c.fecha, c.funcion.id, (select sum(e.precio) from Entrada e where e.compra.id = c.id) , (select sum(conf.precio*conf.unidades) from CompraConfiteria conf where conf.compra.id = c.id))from Compra c where c.cliente.cedula = :cedulaCliente")
    List<InformacionCompraDTO> obtenerInformacionCompra(Integer cedulaCliente);


    //pelicula más vista
    @Query("select c.funcion.pelicula.nombre, count(c) from Compra c where c.funcion.sala.teatro.ciudad.id = :idCiudad group by c.funcion.pelicula")
    List<Object[]> obtenerPeliculaMasVista(Integer idCiudad);


    @Query("select c from Compra c where c.funcion.id = :idFuncion")
    List<Compra> obtenerComprasAsociadasFuncion(Integer idFuncion);

    //hacer un query que busque si existe una compra con la misma fila y columna de la entrada
    @Query("select e.compra from Entrada e where e.fila = :fila and e.columna = :columna")
    Compra verificarExistenciaEntrada(int fila, int columna);


}
