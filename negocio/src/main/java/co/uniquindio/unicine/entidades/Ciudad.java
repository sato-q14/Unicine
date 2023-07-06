package co.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Ciudad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Integer id;

    @Column(length = 200, nullable = false)
    private String nombre;

    @ToString.Exclude
    @OneToMany(mappedBy = "ciudad")
    private List<Teatro> teatros;

    @Builder
    public Ciudad(String nombre) {
        this.nombre = nombre;
    }
}
