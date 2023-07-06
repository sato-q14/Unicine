package co.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Horario implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @EqualsAndHashCode.Include
        @Column(name = "id")
        private Integer id;
        @Column(nullable = false)
        private int dia;
        @Column(nullable = false)
        private float hora;

        private LocalDate inicio;
        private LocalDate fin;


        @ToString.Exclude
        @OneToMany(mappedBy = "horario")
        private List<Funcion> funciones;


        @Builder
        public Horario(int dia, float hora, LocalDate inicio, LocalDate fin) {
                this.dia = dia;
                this.hora = hora;
                this.inicio = inicio;
                this.fin = fin;
        }
}
