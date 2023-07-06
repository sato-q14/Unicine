package co.uniquindio.unicine.bean;

import co.uniquindio.unicine.entidades.*;
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
public class TeatroBean implements Serializable {

    @Setter
    @Getter
    private Teatro teatro;


    @Autowired
    private AdministradorTeatroServicio adminTeatroServicio;

    @Autowired
    private AdministradorServicio adminServicio;

    @Getter @Setter
    List<Ciudad> ciudades ;

    @Getter @Setter
    List<Teatro> teatros;

    @Getter @Setter
    List<Teatro> teatrosSeleccionados;

    public boolean editar;

    @PostConstruct
    public void init() throws Exception {
        teatro= new Teatro();
        ciudades=adminServicio.listarCiudades();
        teatros = adminTeatroServicio.listarTeatros();
        teatrosSeleccionados = new ArrayList<>();
        editar = false;
        }



    public void crearTeatro(){
        try {

            if (!editar){
                AdministradorTeatro adminTeatro = adminServicio.obtenerAdministradorTeatro(102658742);

                teatro.setAdministradorTeatro(adminTeatro);
                Teatro registro = adminTeatroServicio.crearTeatro(teatro);
                teatros.add(registro);

                teatro = new Teatro();

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Teatro creado exitosamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }else{
                adminTeatroServicio.actualizarTeatro(teatro);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Teatro actualizado correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }


        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }


    }

    public void eliminarTeatros(){

        try {
        for (Teatro t: teatrosSeleccionados) {

                adminTeatroServicio.eliminarTeatro(t.getId());
                teatros.remove(t);
        }
        teatrosSeleccionados.clear();

        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }


    }

    public String getTextoBorrar(){
        if (teatrosSeleccionados.isEmpty()){
            return "Eliminar";
        }
        return teatrosSeleccionados.size() == 1 ? " eliminar 1 elemento" : "Eliminar " + teatrosSeleccionados.size() + " elementos";
    }


    public void seleccionarTeatro(Teatro teatroSeleccionado){

        this.teatro = teatroSeleccionado;
        editar = true;

    }

    public String getMensajeCrear(){
        if (editar){
            return "Actualizar Teatro";
        }
        return "Crear Teatro";
    }

    public void crearTeatroDialogo(){
        this.teatro = new Teatro();
        editar=false;
    }

}
