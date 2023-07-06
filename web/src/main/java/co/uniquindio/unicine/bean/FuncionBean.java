package co.uniquindio.unicine.bean;


import co.uniquindio.unicine.entidades.*;
import co.uniquindio.unicine.servicios.AdministradorServicio;
import co.uniquindio.unicine.servicios.AdministradorTeatroServicio;
import co.uniquindio.unicine.servicios.ClienteServicio;
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
public class FuncionBean implements Serializable {

    @Getter @Setter
    private Funcion funcion;

    @Setter @Getter
    private List<Funcion> funciones;

    @Getter @Setter
    private Horario horario;

    @Getter @Setter
    private List<Horario> horarios;

    @Getter @Setter
    private Pelicula pelicula;

    @Setter @Getter
    private List<Pelicula> peliculas;

    @Getter @Setter
    private Sala sala;

    @Setter @Getter
    private List<Sala> salas;

    @Setter @Getter
    private List<Funcion> funcionesSeleccionadas;

    private boolean editar;

    @Autowired
    private AdministradorTeatroServicio adminTeatroServicio;

    @Autowired
    private AdministradorServicio adminServicio;

    @Autowired
    private ClienteServicio clienteServicio;

    @PostConstruct
    public void inicilizar(){
        funcion = new Funcion();
        peliculas = adminServicio.listarPeliculas();
        horarios = adminTeatroServicio.listarHorarios();
        try {
            salas = adminTeatroServicio.listarSalas();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            funciones = adminTeatroServicio.listarFunciones();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        funcionesSeleccionadas = new ArrayList<>();
        editar = false;
    }

    public void crearFuncion(){
        try {
            if(!editar) {

                Funcion registro = adminTeatroServicio.crearFuncion(funcion);
                funciones.add(registro);

                funcion = new Funcion();

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Función creada correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }else {
                adminTeatroServicio.actualizarFuncion(funcion);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Función actualizada correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }
        }catch (Exception e){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean",fm);

        }
    }

    public void eliminarFuncion(){
        try{
            for(Funcion f : funcionesSeleccionadas) {
                adminTeatroServicio.eliminarFuncion(f.getId());
                funciones.remove(f);
            }
            funcionesSeleccionadas.clear();
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }

    public String getMensajeBorrar(){
        if(funcionesSeleccionadas.isEmpty()){
            return "Borrar";
        }else{
            return funcionesSeleccionadas.size() == 1 ? "Borrar 1 elemento" :
                    "Borrar " +  funcionesSeleccionadas.size() +  " elementos";
        }
    }

    public String getMensajeCrear(){
        return editar ? "Editar función" : "Crear función";
    }

    public void seleccionarFuncion(Funcion funcionSeleccionada){
        this.funcion = funcionSeleccionada;
        editar = true;
    }


    public void crearFuncionDialogo(){
        this.funcion = new Funcion();
        editar=false;
    }
}
