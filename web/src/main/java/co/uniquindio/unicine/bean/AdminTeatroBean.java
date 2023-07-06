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
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class AdminTeatroBean {


    @Setter
    @Getter
    private AdministradorTeatro adminTeatro;

    @Autowired
    private AdministradorServicio adminServicio;



    @Getter @Setter
    private List<AdministradorTeatro> adminteatros;

    @Getter @Setter
    private List<AdministradorTeatro> adminteatrosSeleccionados;

    public boolean editar;

    @PostConstruct
    public void init() throws Exception {
        adminTeatro= new AdministradorTeatro();
        adminteatros = adminServicio.listarAdminTeatro();
        adminteatrosSeleccionados = new ArrayList<>();
        editar = false;
    }



    public void crearAdminTeatro(){
        try {

            if (!editar){
                AdministradorTeatro registro = adminServicio.crearAdministradorTeatro(adminTeatro);
                adminteatros.add(registro);

                adminTeatro = new AdministradorTeatro();

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Administrador de teatro creado");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }else{
                adminServicio.actualizarAdministradorTeatro(adminTeatro);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Administrador de teatro actualizado correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }


        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }


    }

    public void eliminarAdminTeatro(){

        try {
            for (AdministradorTeatro a: adminteatrosSeleccionados) {

                adminServicio.eliminarAdministradorTeatro(a.getId());
                adminteatros.remove(a);
            }
            adminteatrosSeleccionados.clear();

        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage() + "No es posible eliminar el registro ya que depende de otros");
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }


    }

    public String getTextoBorrar(){
        if (adminteatrosSeleccionados.isEmpty()){
            return "Eliminar";
        }
        return adminteatrosSeleccionados.size() == 1 ? " eliminar 1 elemento" : "Eliminar " + adminteatrosSeleccionados.size() + " elementos";
    }


    public void seleccionarAdminTeatro(AdministradorTeatro adminSeleccionado){

        this.adminTeatro = adminSeleccionado;
        editar = true;

    }

    public String getMensajeCrear(){
        if (editar){
            return "Actualizar Administrador Teatro";
        }
        return "Crear Administrador Teatro";
    }

    public void crearAdminTeatroDialogo(){
        this.adminTeatro = new AdministradorTeatro();
        editar=false;
    }

}











