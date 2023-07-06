package co.uniquindio.unicine.bean;


import co.uniquindio.unicine.entidades.AdministradorTeatro;
import co.uniquindio.unicine.entidades.Sala;
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
public class salaBean {

    @Setter
    @Getter
    private Sala sala;

    @Autowired
    private AdministradorTeatroServicio adminTeatroServicio;

    @Getter @Setter
    private List<Sala> salas;

    @Getter @Setter
    private List<Sala> salasSeleccionadas;

    @Getter @Setter
    private List<Teatro> teatros;

    public boolean editar;

    @PostConstruct
    public void init() throws Exception {
        sala= new Sala();
        teatros = adminTeatroServicio.listarTeatros();
        salas = adminTeatroServicio.listarSalas();
        salasSeleccionadas = new ArrayList<>();
        editar = false;
    }



    public void crearSala(){
        try {

            if (!editar){
                Sala registro = adminTeatroServicio.crearSala(sala);
                salas.add(registro);

                sala = new Sala();

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Sala Creada con exito");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }else{
                adminTeatroServicio.actualizarSala(sala);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Salaactualizada correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }


        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }


    }

    public void eliminarSalas(){

        try {
            for (Sala s: salasSeleccionadas) {

                adminTeatroServicio.eliminarSala(s.getId());
                salas.remove(s);
            }
            salasSeleccionadas.clear();

        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage() + "No es posible eliminar el registro ya que depende de otros");
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }


    }

    public String getTextoBorrar(){
        if (salasSeleccionadas.isEmpty()){
            return "Eliminar";
        }
        return salasSeleccionadas.size() == 1 ? " eliminar 1 elemento" : "Eliminar " + salasSeleccionadas.size() + " elementos";
    }


    public void seleccionarSala(Sala salaSeleccionada){

        this.sala = salaSeleccionada;
        editar = true;

    }

    public String getMensajeCrear(){
        if (editar){
            return "Actualizar Sala";
        }
        return "Crear Sala";
    }

    public void crearSalaDialogo(){
        this.sala = new Sala();
        editar=false;
    }

}


