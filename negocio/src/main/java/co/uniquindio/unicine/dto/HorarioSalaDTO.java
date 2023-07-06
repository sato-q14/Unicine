package co.uniquindio.unicine.dto;

import co.uniquindio.unicine.entidades.Horario;
import co.uniquindio.unicine.entidades.Pelicula;
import co.uniquindio.unicine.entidades.Sala;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@Setter
public class HorarioSalaDTO {

    private Horario horario;
    private Sala sala;



}
