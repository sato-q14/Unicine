package co.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = false)
@NoArgsConstructor
//@AllArgsConstructor
@ToString
public class Entrada implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Integer codigo;

    @Column(nullable = false)
    private Float precio;

    @Column(nullable = false)
    private Integer fila;

    @Column(nullable = false)
    private Integer columna;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private TipoSilla tipoSilla;

    @ManyToOne
    private Compra compra;



    @Builder
    public Entrada(Float precio, Integer fila, Integer columna, TipoSilla tipoSilla, Compra compra) {
        this.precio = precio;
        this.fila = fila;
        this.columna = columna;
        this.tipoSilla = tipoSilla;
        this.compra = compra;
    }
}
