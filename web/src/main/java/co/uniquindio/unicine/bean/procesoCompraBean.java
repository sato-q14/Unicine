package co.uniquindio.unicine.bean;


import co.uniquindio.unicine.entidades.CompraConfiteria;
import co.uniquindio.unicine.entidades.CuponCliente;
import co.uniquindio.unicine.entidades.Funcion;
import co.uniquindio.unicine.entidades.Sala;
import co.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import java.util.List;

@Component
@ViewScoped
public class procesoCompraBean {


    @Autowired
    private ClienteServicio clienteServicio;

    @Getter @Setter
    private Sala sala;

    @Getter @Setter
    private Funcion funcion;

    @Getter @Setter
    private CuponCliente cupon;

    @Getter @Setter
    private List<CompraConfiteria> confites;


    private String esquemaSala = sala.getDistribucionSillas().getEsquema();
    private int filas = sala.getDistribucionSillas().getFilas();
    private int columnas = sala.getDistribucionSillas().getColumnas();

    public static String FormatMatrix(String esquemaSala, int filas, int columnas) {
        try {
            String[][] matrix = new String[filas][columnas];
            String[] arr = esquemaSala.split("\\s*,\\s*");

            int k = 0;
            int s = arr.length;

            for (int i = 0; i < filas; ++i) {
                for (int j = 0; j < columnas; ++j) {
                    matrix[i][j] = (k < s) ? arr[k] : "*";
                    ++k;
                }
            }

            String append = "", result;

            for (int i = 0; i < filas; ++i) {
                append += "|\t";
                for (int j = 0; j < columnas; ++j) {
                    append += matrix[i][j] + "\t";
                }
                append += "|\n";
            }
            result = append;
            return result;

        } catch (Exception e) {
            return null;
        }
    }




}
