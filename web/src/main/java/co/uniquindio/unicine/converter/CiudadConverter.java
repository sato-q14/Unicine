package co.uniquindio.unicine.converter;

import co.uniquindio.unicine.entidades.Ciudad;
import co.uniquindio.unicine.repositorio.CiudadRepositorio;
import co.uniquindio.unicine.servicios.AdministradorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;


@Component
public class CiudadConverter implements Converter<Ciudad> {

    @Autowired
    private AdministradorServicio administradorServicio;

    @Override
    public Ciudad getAsObject(FacesContext arg0, UIComponent arg1, String value) {

        Ciudad ciudad = null;
        try {
             ciudad = administradorServicio.obtenerCiudad(Integer.parseInt(value));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ciudad;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Ciudad value) {
        if (value != null){
            return ""+value.getId();
        }
        return "";
    }

}
