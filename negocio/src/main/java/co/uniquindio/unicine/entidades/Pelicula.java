package co.uniquindio.unicine.entidades;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.print.DocFlavor;
import javax.swing.*;
import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Pelicula implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @ElementCollection
    @Column(nullable = false)
    private Map<String, String> imagenes;


    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private EstadoPelicula estadoPelicula;

    @Lob
    @Column(nullable = false)
    private String reparto;

    @Column(nullable = false)
    private String urlTrailer;

    @Lob
    @Column(nullable = false)
    private String sinopsis;


    @ToString.Exclude
    @OneToMany(mappedBy = "pelicula")
    private List <Funcion> funciones;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Genero> generos;

    @Builder
    public Pelicula(String nombre, EstadoPelicula estadoPelicula, String reparto, String urlTrailer, String sinopsis) {
        this.nombre = nombre;
        this.estadoPelicula = estadoPelicula;
        this.reparto = reparto;
        this.urlTrailer = urlTrailer;
        this.sinopsis = sinopsis;
    }

    public String getImagenPrincipal(){

        if (!imagenes.isEmpty()){
            String primera = imagenes.keySet().toArray()[0].toString();
            return imagenes.get(primera);
        }

        return "";

    }










}
