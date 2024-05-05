package Banco.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Generador {
    private static String tarjetas;
    //En este método, se solicita como parámetro la clave que usará cada sucursal para sus tarjetas, que conforma los primeros 4 dígitos del plástico. Ejemplo: clave = 5073.
    public static String generarTarjeta(int clave) {
        Random r = new Random();
        String campo1 = Integer.toString(clave);//Se convierte la clave a tipo String.
        String campo2 = Integer.toString(r.nextInt(1000,9999));//Se generan números aleatorios en grupos de 4 dígitos.
        String campo3 = Integer.toString(r.nextInt(1000,9999));
        String campo4 = Integer.toString(r.nextInt(1000,9999));
        tarjetas = campo1 + " " + campo2 + " " + campo3 + " " + campo4;//Se concatenan los datos y se agregan espacios para mejor organización del número.
        return tarjetas;
    }

    public static boolean validarTarjeta() {
        return false;
    }

    public static String generarRFC(String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento) {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("YYMMdd"); //Formato para la fecha de nacimiento
        String fechaNacimientoS = formatoFecha.format(fechaNacimiento);
        Random r = new Random(); //Variable para los números aleatorios.
        int numero1 = r.nextInt(65,91); //Rango del 65 al 90 para las letras mayúsculas según código ASCII
        char caracter1 = (char) numero1; //Convertir a caracter
        String letra1 = Character.toString(caracter1); //Convertir a cadena
        int numero2 = r.nextInt(65,91);
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
