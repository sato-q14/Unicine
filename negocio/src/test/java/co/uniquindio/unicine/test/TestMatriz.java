package co.uniquindio.unicine.test;

public class TestMatriz {

    public static void main(String[] args) {

        String esquemaSala="xxxxxxx";
        int filas = 6;
        int columnas = 4;

        String resultado = FormatMatrix(esquemaSala,filas,columnas);
        System.out.println(resultado);

    }

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
