package co.uniquindio.unicine.bean;

import co.uniquindio.unicine.dto.PeliculaFuncion;
import co.uniquindio.unicine.servicios.ClienteServicio;
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
public class BusquedaBean implements Serializable {

    @Getter @Setter
    private String busqueda;


    @Value("#{param['busqueda']}")
    private String busquedaParam;

    @Autowired
    private ClienteServicio clienteServicio;

    @Getter @Setter
    List<PeliculaFuncion> peliculas;

    @PostConstruct
    public void init(){
       if (busquedaParam != null && !busquedaParam.isEmpty()){
           peliculas=clienteServicio.listarFuncionesPelicula(busquedaParam);
       }
    }
    public String buscar() {

        if (!busqueda.isEmpty()) {
            return "/resultados_busqueda?faces-redirect=true&amp;busqueda=" + busqueda;
        }
        return "";
    }

}
