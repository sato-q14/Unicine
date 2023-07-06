package co.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import javax.swing.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Confiteria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Integer id;

    @Column(nullable = false)
    private Float precio;

    @ElementCollection
    @Column(nullable = false)
    private Map<String, String> imagenes;

    @Column(nullable = false)
    private  String nombreAlimento;


    @ToString.Exclude
    @OneToMany(mappedBy = "confiteria")
    private List<CompraConfiteria> confiterias;


    @Builder
    public Confiteria(Float precio, String nombreAlimento) {
        this.precio = precio;
        this.nombreAlimento = nombreAlimento;
    }

    public String getImagenPrincipal(){

        if (!imagenes.isEmpty()){
            String primera = imagenes.keySet().toArray()[0].toString();
            return imagenes.get(primera);
        }

        return "";

    }


}
