package co.uniquindio.unicine.bean;

import co.uniquindio.unicine.entidades.*;
import co.uniquindio.unicine.servicios.AdministradorServicio;
import co.uniquindio.unicine.servicios.CloudinaryServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FilesUploadEvent;
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
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

@Component
@ViewScoped
public class PeliculaBean implements Serializable {

    @Setter @Getter
    private Pelicula pelicula;

    @Setter @Getter
    private List<Genero> generos;

    @Autowired
    private AdministradorServicio adminServicio;

    private Map<String, String> imagenes;
    @Autowired
    private CloudinaryServicio cloudinaryServicio;

    @Getter @Setter
    private List<Pelicula> peliculasSeleccionados;

    @Getter @Setter
    private List<Pelicula> peliculas;

    @Getter @Setter
    private List<EstadoPelicula> estadoPelicula;

    @Getter @Setter
    private EstadoPelicula estadoPeliculaSeleccionado;

    @Getter @Setter
    private List<Genero> generosSeleccionados;


    public boolean editar;

    @PostConstruct
    public void init(){
        pelicula=new Pelicula();
        generos = Arrays.asList( Genero.values());
        estadoPelicula = Arrays.asList(EstadoPelicula.values());
        imagenes=new HashMap<>();
        peliculas = adminServicio.listarPeliculas();
        peliculasSeleccionados = new ArrayList<>();
        generosSeleccionados = new ArrayList<>();
    }

    public void crearPelicula(){
        System.out.println(editar);
        try {

            if (!editar) {
                if (!imagenes.isEmpty()) {
                    pelicula.setImagenes(imagenes);
                    pelicula.setGeneros(generosSeleccionados);
                    pelicula.setEstadoPelicula(estadoPeliculaSeleccionado);
                    Pelicula registro = adminServicio.crearPelicula(pelicula);
                    peliculas.add(registro);
                    pelicula = new Pelicula();
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Pelicula Creada Exitosamente");
                    FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
                } else {
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Es necesario subir una imagen");
                    FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
                }
            }else{
                System.out.println("ACTUALIZAR");
                pelicula.setEstadoPelicula(estadoPeliculaSeleccionado);
                pelicula.setGeneros(generosSeleccionados);
                adminServicio.actualizarPelicula(pelicula);

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Pelicula actualizada Exitosamente");
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
            Map resultado = cloudinaryServicio.subirImagen(imagenFile, "peliculas");
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
            return "Actualizar Pelicula";
        }
        return "Crear Pelicula";
    }

    public void crearPeliculaDialogo(){
        this.pelicula = new Pelicula();
        generosSeleccionados.clear();
        editar=false;
    }

    public String getTextoBorrar(){
        if (peliculasSeleccionados.isEmpty()){
            return "Eliminar";
        }
        return peliculasSeleccionados.size() == 1 ? " eliminar 1 elemento" : "Eliminar " + peliculasSeleccionados.size() + " elementos";
    }

    public void eliminarPeliculas(){

            for (Pelicula p: peliculasSeleccionados) {

                try {
                    adminServicio.eliminarPelicula(p.getId());
                    peliculas.remove(p);

                } catch (Exception e) {
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                    FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
                }

            }
            peliculasSeleccionados.clear();

    }

    public void seleccionarPelicula(Pelicula peliculaSeleccionada){
        this.pelicula = peliculaSeleccionada;
        generosSeleccionados.clear();
        generosSeleccionados.addAll( peliculaSeleccionada.getGeneros() );
        editar = true;
        System.out.println(peliculaSeleccionada);
    }

}
