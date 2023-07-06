package co.uniquindio.unicine.bean;


import co.uniquindio.unicine.entidades.AdministradorTeatro;
import co.uniquindio.unicine.entidades.Ciudad;
import co.uniquindio.unicine.entidades.Teatro;
import co.uniquindio.unicine.servicios.AdministradorServicio;
import co.uniquindio.unicine.servicios.AdministradorTeatroServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class CiudadBean implements Serializable {

    @Setter
    @Getter
    private Ciudad ciudad;

    @Autowired
    private AdministradorServicio adminServicio;

    @Getter @Setter
    List<Ciudad> ciudades ;

    @Getter @Setter
    List<Ciudad> ciudadesSeleccionadas;

    public boolean editar;

    @PostConstruct
    public void init() throws Exception {
        ciudad= new Ciudad();
        ciudades = adminServicio.listarCiudades();
        ciudadesSeleccionadas = new ArrayList<>();
        editar = false;
    }



    public void crearCiudad(){
        try {

            if (!editar){
                Ciudad registro = adminServicio.agregarCiudad(ciudad);
                ciudades.add(registro);

                ciudad = new Ciudad();

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Ciudad creada exitosamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }else{
                adminServicio.actualizarCiudad(ciudad);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Ciudad actualizada correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }


        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }


    }

    public void eliminarCiudades(){

        try {
            for (Ciudad c: ciudadesSeleccionadas) {

                adminServicio.eliminarCiudad(c.getId());
                ciudades.remove(c);
            }
            ciudadesSeleccionadas.clear();

        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }


    }

    public String getTextoBorrar(){
        if (ciudadesSeleccionadas.isEmpty()){
            return "Eliminar";
        }
        return ciudadesSeleccionadas.size()==1 ? " eliminar 1 elemento" : "Eliminar " + ciudadesSeleccionadas.size() + " elementos";
    }


    public void seleccionarCiudad(Ciudad ciudadSleccionada){

        this.ciudad = ciudadSleccionada;
        editar = true;

    }

    public String getMensajeCrear(){
        if (editar){
            return "Actualizar Ciudad";
        }
        return "Crear Ciudad";
    }

    public void crearCiudadDialogo(){
        this.ciudad = new Ciudad();
        editar=false;
    }


}
