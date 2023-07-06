package co.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cupon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Integer id;

    @Column(nullable = false)
    private LocalDate fechaExpiracion;

    @Column(nullable = false)
    private String concepto;

    @Column(nullable = false)
    private Float descuento;

    @Column(nullable = false)
    private String descripcion;


    @ToString.Exclude
    @OneToMany(mappedBy = "cupon")
    private List<CuponCliente> cuponClientes;


    @Builder
    public Cupon(LocalDate fechaExpiracion, String concepto, Float descuento, String descripcion) {
        this.fechaExpiracion = fechaExpiracion;
        this.concepto = concepto;
        this.descuento = descuento;
        this.descripcion = descripcion;
    }
}
