package co.uniquindio.unicine.bean;

import co.uniquindio.unicine.entidades.Confiteria;
import co.uniquindio.unicine.entidades.EstadoPelicula;
import co.uniquindio.unicine.entidades.Genero;
import co.uniquindio.unicine.entidades.Pelicula;
import co.uniquindio.unicine.servicios.AdministradorServicio;
import co.uniquindio.unicine.servicios.CloudinaryServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;



@Component
@ViewScoped
public class ConfiteriaBean {

    @Setter
    @Getter
    private Confiteria confiteria;
    @Autowired
    private AdministradorServicio adminServicio;


    private Map<String, String> imagenes;
    @Autowired
    private CloudinaryServicio cloudinaryServicio;

    @Getter @Setter
    private List<Confiteria> confitesSeleccionados;

    @Getter @Setter
    private List<Confiteria> confiterias;
    public boolean editar;

    @PostConstruct
    public void init(){
        confiteria = new Confiteria();
        imagenes=new HashMap<>();
        confiterias = adminServicio.listarConfiteria();
        confitesSeleccionados = new ArrayList<>();

    }

    public void crearConfiteria(){
        try {

            if (!editar) {
                if (!imagenes.isEmpty()) {
                    confiteria.setImagenes(imagenes);
                    Confiteria registro = adminServicio.crearConfiteria(confiteria);
                    confiterias.add(registro);
                    confiteria = new Confiteria();
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Confiteria creada con exito");
                    FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
                } else {
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Es necesario subir una imagen");
                    FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
                }
            }else{

                adminServicio.actualizarConfiteria(confiteria);

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Confiteria actualizada Exitosamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }

    public void subirImagenes(FileUploadEvent event){
        try {
            UploadedFile imagen = event.getFile();
            File imagenFile = convertirUploadedFile(imagen);
            Map resultado = cloudinaryServicio.subirImagen(imagenFile, "confites");
            imagenes.put(resultado.get("public_id").toString(), resultado.get("url").toString());
        }catch (Exception e){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }

    public File convertirUploadedFile(UploadedFile imagen) throws IOException {
        File file = new File(imagen.getFileName());
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagen.getContent());
        fos.close();
        return file;

    }

    public String getMensajeCrear(){
        if (editar){
            return "Actualizar Confiteria";
        }
        return "Crear Confiteria";
    }

    public void crearConfiteriaDialogo(){
        this.confiteria= new Confiteria();
        editar=false;
    }

    public String getTextoBorrar(){
        if (confitesSeleccionados.isEmpty()){
            return "Eliminar";
        }
        return confitesSeleccionados.size() == 1 ? " eliminar 1 elemento" : "Eliminar " + confitesSeleccionados.size() + " elementos";
    }

    public void eliminarConfiteria(){

        for (Confiteria c: confitesSeleccionados) {

            try {
                adminServicio.eliminarConfiteria(c.getId());
                confiterias.remove(c);

            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }

        }
        confitesSeleccionados.clear();

    }

    public void seleccionarConfiteria(Confiteria confiteriaSeleccionada){
        this.confiteria = confiteriaSeleccionada;
        editar = true;

    }


}
