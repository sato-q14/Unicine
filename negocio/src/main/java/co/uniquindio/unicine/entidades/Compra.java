package co.uniquindio.unicine.entidades;

import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Compra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Integer id;

    @Positive
    @Column(nullable = false)
    private Float valorTotal;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private MetodoPago metodoPago;

    @Column(nullable = false)
    private LocalDate fecha;


    //RELACIONES
    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Funcion funcion;


    @OneToOne
    private CuponCliente cuponCliente;

    @ToString.Exclude
    @OneToMany(mappedBy = "compra")
    private List<CompraConfiteria> confiterias;

    @ToString.Exclude
    @OneToMany(mappedBy = "compra")
    private List<Entrada> entradas;


    @Builder
    public Compra(Integer id, Float valorTotal, MetodoPago metodoPago, LocalDate fecha, Cliente cliente, Funcion funcion, CuponCliente cuponCliente) {
        this.id = id;
        this.valorTotal = valorTotal;
        this.metodoPago = metodoPago;
        this.fecha = fecha;
        this.cliente = cliente;
        this.funcion = funcion;
        this.cuponCliente = cuponCliente;
    }


}