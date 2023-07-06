package co.uniquindio.unicine.entidades;


import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cliente implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @Positive
    private Integer cedula;
    @Column(nullable = false, length = 200)
    private String nombreCompleto;

    @Email
    @Column(nullable = false, length = 200,unique = true)
    private String email;

    @Column(nullable = false)
    private String urlFoto;

    @Column(nullable = false, length = 200, unique = true)
    @ToString.Exclude
    @Length(max = 10)
    private String contrasenia;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Estado estado = Estado.NO_REGISTRADO;

    //relaciones

    @ToString.Exclude
    @OneToOne(mappedBy = "cliente")
    private EncuestaSatisfaccion encuesta;

    @ElementCollection
    private List<String> telefonos;

    @ToString.Exclude
    @OneToMany(mappedBy = "cliente")
    private List<Compra> compras;


    @ToString.Exclude
    @OneToMany(mappedBy = "cliente")
    private List<CuponCliente> cuponClientes;

    @Builder
    public Cliente(Integer cedula, String nombreCompleto, String email, String urlFoto, String contrasenia) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.urlFoto = urlFoto;
        this.contrasenia = contrasenia;

    }



}
