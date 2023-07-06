package co.uniquindio.unicine.bean;

import co.uniquindio.unicine.entidades.AdministradorTeatro;
import co.uniquindio.unicine.entidades.Cupon;
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
public class CuponBean {


    @Setter
    @Getter
    Cupon cupon;

    @Autowired
    private AdministradorServicio adminServicio;

    @Getter @Setter
    List<Cupon> cupones;

    @Getter @Setter
    List<Cupon> cuponesSeleccionados;

    public boolean editar;

    @PostConstruct
    public void init() throws Exception {
        cupon= new Cupon();
        cupones = adminServicio.listarCupones();
        cuponesSeleccionados = new ArrayList<>();
        editar = false;
    }



    public void crearCupon(){
        try {

            if (!editar){
                Cupon registro = adminServicio.crearCupon(cupon);
                cupones.add(registro);

                cupon = new Cupon();

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Cupon creado exitosamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }else{
                adminServicio.actualizarCupon(cupon);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Cupon actualizado correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }


        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }


    }

    public void eliminarCupones(){

        try {
            for (Cupon c: cuponesSeleccionados) {

                adminServicio.eliminarCupon(c.getId());
                cupones.remove(c);
            }
            cuponesSeleccionados.clear();

        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }


    }

    public String getTextoBorrar(){
        if (cuponesSeleccionados.isEmpty()){
            return "Eliminar";
        }
        return cuponesSeleccionados.size() == 1 ? " eliminar 1 elemento" : "Eliminar " + cuponesSeleccionados.size() + " elementos";
    }


    public void seleccionarCupon(Cupon cuponSeleccionado){

        this.cupon = cuponSeleccionado;
        editar = true;

    }

    public String getMensajeCrear(){
        if (editar){
            return "Actualizar Cupon";
        }
        return "Crear Cupon";
    }

    public void crearCuponDialogo(){
        this.cupon = new Cupon();
        editar=false;
    }







}
