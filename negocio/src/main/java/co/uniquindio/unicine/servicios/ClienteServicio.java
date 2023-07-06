package co.uniquindio.unicine.servicios;

import co.uniquindio.unicine.dto.PeliculaFuncion;
import co.uniquindio.unicine.entidades.*;

import java.util.List;

public interface ClienteServicio {


    Cliente obtener(Integer cedula)throws Exception;

    Cliente comprobarAutenticacionCliente(String email, String password)throws Exception;

  //  Cliente login(String correo, String contrasenia) throws Exception;

    Cliente registrarCliente(Cliente cliente)throws Exception;
    Cliente actualizarCliente(Cliente cliente)throws Exception;
    void    eliminarCliente(Integer cedula)throws Exception;
    List<Cliente> listarClientes();

    List<Compra> listarCompras(String email)throws Exception;

    Float redimirCupon(Integer idCupon, Float valorInicialCompra) throws Exception;

    Cliente cambiarcontrasenia(Cliente cliente, String nuevaContrasenia) throws Exception;

    List<Pelicula> buscarPelicula(String nombrePelicula) throws Exception;


    Compra hacerCompra(List<Entrada> entradas, Cliente cliente, List<CompraConfiteria> confiterias, Funcion funcion, CuponCliente cupon, MetodoPago metodoPago) throws Exception;


    EncuestaSatisfaccion realizarEncuesta(EncuestaSatisfaccion ec,Cliente cliente) throws Exception;


    Entrada obtenerEntrada(Integer idEntrada)throws Exception;

    List<PeliculaFuncion> listarFuncionesPelicula(String nombre);

    List<Pelicula> listarPeliculasEstado(EstadoPelicula estadoPelicula,Integer idCiudad)throws Exception;

    List<Pelicula> listarPeliculasEstadoNormal(EstadoPelicula estadoPelicula)throws Exception;

}
