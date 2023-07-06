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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString

public class Sala implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Integer id;

    @Column(nullable = false)
    private String nombreSala;

    @ToString.Exclude
    @OneToMany(mappedBy = "sala")
    private List<Funcion> funciones;

    @ManyToOne
    private Teatro teatro;

    @ManyToOne
    private DistribucionSillas distribucionSillas;

    @Builder
    public Sala( String nombreSala, Teatro teatro, DistribucionSillas distribucionSillas) {
        this.nombreSala = nombreSala;
        this.teatro = teatro;
        this.distribucionSillas = distribucionSillas;
    }
}
