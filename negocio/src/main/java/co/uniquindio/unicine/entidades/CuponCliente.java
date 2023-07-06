package co.uniquindio.unicine.entidades;


import co.uniquindio.unicine.entidades.Cliente;
import co.uniquindio.unicine.entidades.Compra;
import co.uniquindio.unicine.entidades.Cupon;
import co.uniquindio.unicine.entidades.EstadoCupon;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CuponCliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Integer id;

    @Column(nullable = false)
    private EstadoCupon estado;

    @ManyToOne
    private Cupon cupon;

    @ManyToOne
    private Cliente cliente;

    @ToString.Exclude
    @OneToOne(mappedBy = "cuponCliente")
    private Compra compra;


    @Builder

    public CuponCliente(EstadoCupon estado) {
        this.estado = estado;
    }
}
