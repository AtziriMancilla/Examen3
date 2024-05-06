package Usuarios;

import java.time.LocalDate;

public class Capturista extends Empleado{



    public Capturista(String nombre, String apellidoPaterno, String apellidoMaterno, String ciudad, String estado, String curp, String direccion, int anioNacimiento, String RFC, String contrasena, LocalDate fechaInicio, double salario){
        super(nombre, apellidoPaterno, apellidoMaterno, ciudad, estado, curp, direccion, anioNacimiento, RFC, contrasena, fechaInicio, salario);
    }

    @Override
    public String toString(){
        return String.format("ID: %d,Nombre: %s, Apellido: %s %s, Ciudad: %s, Estado: %s,Curp: %s, Direccion: %s, Año nacimiento: %d, RFC: %s, Fecha de inicio: , salario: %f", super.toString()); //me falta la fecha en formato
    }
    public static void registrarCapturista(){

    }
    public static void mostrarCapturistas(){

    }
    public static void borrarCapturista(){

    }
    public static void modificarCapturista(){

    }

}
