package co.uniquindio.unicine.converter;


import co.uniquindio.unicine.entidades.Ciudad;
import co.uniquindio.unicine.entidades.Teatro;
import co.uniquindio.unicine.servicios.AdministradorServicio;
import co.uniquindio.unicine.servicios.AdministradorTeatroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@Component
public class TeatroConverter implements Converter<Teatro> {


    @Autowired
    private AdministradorTeatroServicio adminTeatroServicio;

    @Override
    public Teatro getAsObject(FacesContext context, UIComponent component, String value) {
        Teatro teatro = null;
        try {
            teatro = adminTeatroServicio.obtenerTeatro(Integer.parseInt(value));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return teatro;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Teatro value) {
        if (value != null){
            return ""+value.getId();
        }
        return "";
    }
}
