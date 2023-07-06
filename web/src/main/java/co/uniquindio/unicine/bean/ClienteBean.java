package co.uniquindio.unicine.bean;

import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.net.SyslogOutputStream;
import co.uniquindio.unicine.entidades.Cliente;
import co.uniquindio.unicine.entidades.Estado;
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
import java.time.LocalDate;

@Component
@ViewScoped
public class ClienteBean implements Serializable {

    @Getter @Setter
    private Cliente cliente;

    @Getter @Setter
    private String confirmacionPassword;


    @Getter @Setter
    private Estado estado;
    @Autowired
    private ClienteServicio clienteServicio;

    @PostConstruct
    public void Init(){
        cliente=new Cliente();
    }

    public void registrarCliente() {
        try {
            if (cliente.getContrasenia().equals(confirmacionPassword)) {
                clienteServicio.registrarCliente(cliente);
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro exitoso");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
            }else{
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "las contraseñas no coinciden");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
            }
        }catch(Exception e){
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
        }
    }


    public void iniciarSesion(){
        try {
            if ( clienteServicio.comprobarAutenticacionCliente(cliente.getEmail(),cliente.getContrasenia())==null){
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta","credenciales no validas");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMsg);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
