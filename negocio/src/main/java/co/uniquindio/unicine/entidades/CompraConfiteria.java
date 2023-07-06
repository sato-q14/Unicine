package co.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CompraConfiteria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Integer id;

    private Float precio;

    private int unidades;

    @ManyToOne
    private Compra compra;

    @ManyToOne
    private Confiteria confiteria;


    @Builder
    public CompraConfiteria(Float precio, int unidades) {
        this.precio = precio;
        this.unidades = unidades;
    }
}
