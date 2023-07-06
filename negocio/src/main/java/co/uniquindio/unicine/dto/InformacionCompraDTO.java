package co.uniquindio.unicine.dto;

import co.uniquindio.unicine.entidades.Funcion;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@ToString
@AllArgsConstructor
public  class InformacionCompraDTO {

    private Float precioTotal;
    private LocalDate fecha;
    private Integer idFuncion;
    private Double precioEntrada;
    private Double precioConfiteria;



}
