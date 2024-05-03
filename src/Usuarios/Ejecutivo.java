package Usuarios;

import java.time.LocalDate;

public class Ejecutivo extends Empleado{

public Ejecutivo(String nombre, String apellidoPaterno, String apellidoMaterno, String ciudad, String estado, String curp, String direccion, int anioNacimiento, String RFC, String contrasena, LocalDate fechaInicio, double salario){
    super(nombre, apellidoPaterno, apellidoMaterno, ciudad, estado, curp, direccion, anioNacimiento, RFC, contrasena, fechaInicio, salario);
}

    @Override
    public String toString(){
        return String.format("ID: %d,Nombre: %s, Apellido: %s %s, Ciudad: %s, Estado: %s,Curp: %s, Direccion: %s, Año nacimiento: %d, RFC: %s, Fecha de inicio: , salario: %f", super.toString()); //me falta la fecha en formato
    }

    public static void darDeAltaCliente(){

    }
    public static void autorizarTarjeta(){

    }
    public static void registrarEjecutivo(){

    }
    public static void modificarEjecutivo(){

    }
    public static void eliminarEjecutivo(){

    }
}
