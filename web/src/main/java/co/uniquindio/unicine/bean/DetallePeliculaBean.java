package co.uniquindio.unicine.bean;


import co.uniquindio.unicine.entidades.Funcion;
import co.uniquindio.unicine.entidades.Pelicula;
import co.uniquindio.unicine.servicios.AdministradorServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class DetallePeliculaBean implements Serializable {


    @Autowired
    private AdministradorServicio adminServicio;
    @Value("#{param['pelicula_id']}")
    private String peliculaId;


    @Getter @Setter
    private Pelicula pelicula;

    @Setter @Getter
    private List<Funcion> funciones;

    //Traerse los Funciones dada idpelicula y idciudad
    //traerse los teatros dada la pelicula y la ciudad

    @PostConstruct
    public void inicializar(){

        try{
            if (peliculaId != null && !peliculaId.isEmpty()){
                pelicula = adminServicio.obtenerPelicula(Integer.parseInt(peliculaId));

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
