package Banco.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Generador {
    private long tarjetas;

    public static void generarTarjeta() {

    }

    public static boolean validarTarjeta() {
        return false;
    }

    public static String generarRFC(String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento) {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("YYMMdd"); //Formato para la fecha de nacimiento
        String fechaNacimientoS = formatoFecha.format(fechaNacimiento);
        Random r = new Random(); //Variable para los números aleatorios.
        int numero1 = r.nextInt(26) + 65; //Rango del 65 al 90 para las letras mayúsculas según código ASCII
        char caracter1 = (char) numero1; //Convertir a caracter
        String letra1 = Character.toString(caracter1); //Convertir a cadena
        int numero2 = r.nextInt(26) + 65;
        char caracter2 = (char) numero2;
        String letra2 = Character.toString(caracter2);
        int caracter3 = r.nextInt(10);
        String letra3 = caracter3 + "";
        String cadena = "" + apellidoPaterno.charAt(0) + apellidoPaterno.charAt(1) + apellidoMaterno.charAt(0) + nombre.charAt(0) + fechaNacimientoS + letra1 + letra2 + letra3;
        String rfc = cadena.toUpperCase();
        return rfc;
    }

    public static String generarCURP(String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento) {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("YYMMdd");
        String fechaNacimientoS = formatoFecha.format(fechaNacimiento);
        String cadena = "" + apellidoPaterno.charAt(0) + apellidoPaterno.charAt(1) + apellidoMaterno.charAt(0) + nombre.charAt(0) + fechaNacimientoS;
        String curp = cadena.toUpperCase();
        return curp;
    }
}
