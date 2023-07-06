package co.uniquindio.unicine.dto;

import co.uniquindio.unicine.entidades.EstadoPelicula;
import co.uniquindio.unicine.entidades.Horario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
@ToString

public class FuncionDTO {

    private String nombrePelicula;
    private EstadoPelicula estadoPelicula;
    private Map urrlImagen;
    private Integer idSala;
    private String direccionTeatro;
    private String nombreCiudad;
    private Horario horario;

}
