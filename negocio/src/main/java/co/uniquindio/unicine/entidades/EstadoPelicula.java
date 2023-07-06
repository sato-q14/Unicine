package co.uniquindio.unicine.entidades;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public enum EstadoPelicula {

    PREVENTA,
    EN_CARTELERA;

    public EstadoPelicula[] getValues() {
        return EstadoPelicula.values();
    }

    @Column(name = "estado", length = 6)
    @Enumerated(EnumType.STRING)
    private EstadoPelicula estadoPelicula;

    @Override
    public String toString() {
        return "EstadoPelicula{" +
                "estadoPelicula=" + estadoPelicula +
                '}';
    }
}
