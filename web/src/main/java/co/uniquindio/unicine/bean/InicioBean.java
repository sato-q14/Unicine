package co.uniquindio.unicine.bean;

import co.uniquindio.unicine.entidades.Ciudad;
import co.uniquindio.unicine.entidades.EstadoPelicula;
import co.uniquindio.unicine.entidades.Pelicula;
import co.uniquindio.unicine.servicios.AdministradorServicio;
import co.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class InicioBean implements Serializable {


    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private AdministradorServicio adminServicio;

    @Getter @Setter
    private List<Pelicula> peliculasCartelera;

    @Getter @Setter
    private List<Pelicula> peliculasPreventa;


    @Getter @Setter
    private List<Ciudad> ciudades;

    @Getter @Setter
    private Ciudad ciudad;

    @Getter @Setter
    private List<String> imagenes;

    @Getter @Setter
    private List<String> imagenesCarousel;
    @PostConstruct
    public void inicializar(){
        try {
            imagenesCarousel= new ArrayList<>();
            imagenesCarousel.add("https://i.imgur.com/dLVgFdO.png");
            imagenesCarousel.add("https://i.imgur.com/dLVgFdO.png");
            imagenesCarousel.add("https://i.imgur.com/dLVgFdO.png");
            peliculasCartelera = clienteServicio.listarPeliculasEstadoNormal(EstadoPelicula.EN_CARTELERA);
            peliculasPreventa = clienteServicio.listarPeliculasEstadoNormal(EstadoPelicula.PREVENTA);
            ciudades = adminServicio.listarCiudades();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public void elegirCiudad(){
        try {
            if (ciudad!=null) {
                peliculasCartelera = clienteServicio.listarPeliculasEstado(EstadoPelicula.EN_CARTELERA, ciudad.getId());
                peliculasPreventa = clienteServicio.listarPeliculasEstado(EstadoPelicula.PREVENTA, ciudad.getId());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }



}
