package co.uniquindio.unicine.dto;

import co.uniquindio.unicine.entidades.Funcion;
import co.uniquindio.unicine.entidades.Pelicula;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
public  class PeliculaFuncion {

    private Pelicula p;
    private Funcion f;


}
