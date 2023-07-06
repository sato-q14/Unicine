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

public class Teatro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Integer id;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private String direccion;

    @ManyToOne
    private AdministradorTeatro administradorTeatro;

    @ManyToOne
    private Ciudad ciudad;

    @ToString.Exclude
    @OneToMany(mappedBy = "teatro")
    private List<Sala> salas;


    @Builder
    public Teatro(String telefono, String direccion, AdministradorTeatro administradorTeatro, Ciudad ciudad) {
        this.telefono = telefono;
        this.direccion = direccion;
        this.administradorTeatro = administradorTeatro;
        this.ciudad = ciudad;
    }
}
