package co.uniquindio.unicine.entidades;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;


@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Administrador {

    @EqualsAndHashCode.Include
    @Id
    private Integer id;

    @Email
    @Column(unique = true,nullable = false)
    private String correo;

    @ToString.Exclude
    @Length(max = 10)
    @Column(nullable = false)
    private String password;


    @Builder
    public Administrador(Integer id, String correo, String password) {
        this.id = id;
        this.correo = correo;
        this.password = password;
    }
}
