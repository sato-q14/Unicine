package co.uniquindio.unicine.bean;

import co.uniquindio.unicine.entidades.Administrador;
import co.uniquindio.unicine.entidades.AdministradorTeatro;
import co.uniquindio.unicine.entidades.Ciudad;
import co.uniquindio.unicine.entidades.Cliente;
import co.uniquindio.unicine.servicios.AdministradorServicio;
import co.uniquindio.unicine.servicios.AdministradorTeatroServicio;
import co.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@Component
@Scope("session")
public class SeguridadBean implements Serializable {



    @Autowired
    private ClienteServicio clienteServicio;

    @Setter @Getter
    private boolean autenticado;

    @Setter @Getter
    private String email;

    @Setter @Getter
    private String password;

    @Setter @Getter
    private String tipoSesion;

    @Getter @Setter
    private Ciudad ciudadSeleccionada;

    @Getter @Setter
    private boolean administrador;
    @Getter @Setter
    private Cliente cliente;

    @Autowired
    private AdministradorTeatroServicio adminTeatroServicio;

    @Autowired
    private AdministradorServicio adminServicio;

    @PostConstruct
    public void inicializar(){

        autenticado=false;
    }

    public String iniciarSesionCliente(){

        if (!email.isEmpty() && !password.isEmpty()){
            try {
                cliente = clienteServicio.comprobarAutenticacionCliente(email,password);
                tipoSesion = "cliente";
                autenticado = true;
                return "/index?faces-redirect=true";
            } catch (Exception e) {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
            }
        }else {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "por favor ingrese todos los datos");
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
        }
        return null;
    }


    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }


    public void seleccionarCiudad(Ciudad ciudad){
        this.ciudadSeleccionada = ciudad;
    }



}

