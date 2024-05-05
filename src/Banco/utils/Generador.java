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
        String letra3 = Integer.toString(caracter3); //Conversión de entero a String.
        String cadena = "" + apellidoPaterno.charAt(0) + apellidoPaterno.charAt(1) + apellidoMaterno.charAt(0) + nombre.charAt(0) + fechaNacimientoS + letra1 + letra2 + letra3;//Concatenando los valores de todas las variables para crear una sola cadena String.
        String rfc = cadena.toUpperCase();//Conversión de minúsculas a Mayúsculas.
        return rfc;
    }

    public static String generarCURP(String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento, char sexo, String estado) {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("YYMMdd");
        Random r = new Random();
        String fechaNacimientoS = formatoFecha.format(fechaNacimiento);
        int letra = estado.length() - 1; //Obtiene el largo de la palabra y se le resta 1 para obtener el último index correspondiente a la última letra
        int numero1 = r.nextInt(10);//Número aleatorio para el dígito 1
        int numero2 = r.nextInt(10);//Número aleatorio para el dígito 2
        String digito1 = Integer.toString(numero1);//Conversión a String. Luego se añaden los dos dígitos para completar la CURP
        String digito2 = Integer.toString(numero2);
        String cadena = "" + apellidoPaterno.charAt(0) + apellidoPaterno.charAt(1) + apellidoMaterno.charAt(0) + nombre.charAt(0) + fechaNacimientoS + sexo + estado.charAt(0) + estado.charAt(letra) + apellidoPaterno.charAt(2) + apellidoMaterno.charAt(2) + nombre.charAt(2) + digito1 + digito2;
        String curp = cadena.toUpperCase();
        return curp;
    }
}
