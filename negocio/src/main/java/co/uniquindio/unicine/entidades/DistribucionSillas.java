package co.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class DistribucionSillas implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Integer id;

    @Column(nullable = false)
    private int totalSillasNormales;

    @Column(nullable = false)
    private int totalSillasVip;

    @Column(nullable = false)
    private int columnas;

    @Column(nullable = false)
    private int filas;

    @Column(nullable = false)
    private String esquema;

    @ToString.Exclude
    @OneToMany(mappedBy = "distribucionSillas")
    private List<Sala> salas;


    @Builder
    public DistribucionSillas(int totalSillasNormales, int totalSillasVip, int columnas, int filas, String esquema) {
        this.totalSillasNormales = totalSillasNormales;
        this.totalSillasVip = totalSillasVip;
        this.columnas = columnas;
        this.filas = filas;
        this.esquema = esquema;
    }
}
