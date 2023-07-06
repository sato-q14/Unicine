package co.uniquindio.unicine.repositorio;

import co.uniquindio.unicine.entidades.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente,Integer> {


    @Query("select c from Cliente c where c.email= :email")
    Cliente obtener(String email);
/*
    Optional<Cliente> findByEmailAndPassword(String email, String password);
    Cliente findByEmail(String email);


 */
    Cliente findByEmail(String correo);
    Cliente findByCedula(Integer cedula);


    @Query("select c from Cliente c where c.email= :email and c.contrasenia = :contrasenia")
    Cliente comprobarAuntenticacion(String email, String contrasenia);

    Cliente findByEmailAndContrasenia(String email, String contrasenia);


    @Query("select c from Cliente c where c.estado= :estado")
    List<Cliente> obtenerPorEstado(Estado estado, Pageable paginador);


    @Query("select compra from Cliente cliente, in(cliente.compras) compra where cliente.email = :email")
    List<Compra> obtenerComprasPorEmail(String email);


    //Join
    @Query("select compra from Cliente cliente join cliente.compras compra where cliente.email = :email")
    List<Compra> obtenerComprasPorEmail3(String email);


    //cupones dado un correo
    @Query("select cupon from Cliente c join c.cuponClientes cupon where c.email = :email")
    List<CuponCliente> obtenerCuponesEmail(String email);


    //Compra de todos los clientes con el left join
    @Query("select cliente.nombreCompleto, compra from Cliente cliente left join cliente.compras compra" )
    List<Object[]> obtenerComprasTodos();

    //Compra de todos los clientes(correos) con el left join
    @Query("select cliente.email, compra from Cliente cliente left join cliente.compras compra" )
    List<Object[]> obtenerComprasTodosConEmail();



}
