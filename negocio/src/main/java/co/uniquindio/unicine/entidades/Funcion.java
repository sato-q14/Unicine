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
public class Funcion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Integer id;

    @Column(nullable = false)
    private Float precio;

    @Column(nullable = false)
    private Float precioVip;

    @ToString.Exclude
    @OneToMany(mappedBy = "funcion")
    private List<Compra> compras;

    @ManyToOne
    private Horario horario;

    @ManyToOne
    private Sala sala;

    @ManyToOne
    private Pelicula pelicula;


    @Builder
    public Funcion(Float precio, Horario horario, Sala sala, Pelicula pelicula, Float precioVip) {
        this.precio = precio;
        this.horario = horario;
        this.sala = sala;
        this.pelicula = pelicula;
        this.precioVip=precioVip;
    }
}
