package co.uniquindio.unicine.entidades;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.swing.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@ToString
public class AdministradorTeatro implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private Integer id;

    @Length(max = 10)
    @Column(nullable = false, unique = true, length = 10)
    private String password;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @ToString.Exclude
    @OneToMany(mappedBy = "administradorTeatro")
    private List<Teatro> teatros;


    @Builder
    public AdministradorTeatro(Integer id, String password, String email) {
        this.id = id;
        this.password = password;
        this.email = email;
    }
}
