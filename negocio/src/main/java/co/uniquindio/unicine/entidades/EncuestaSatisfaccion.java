package co.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class EncuestaSatisfaccion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Integer id;

    @Column(nullable = false)
    private Integer calificacion;

    @OneToOne
    private Cliente cliente;

    @Builder
    public EncuestaSatisfaccion(Integer calificacion, Cliente cliente) {
        this.calificacion = calificacion;
        this.cliente = cliente;
    }
}
