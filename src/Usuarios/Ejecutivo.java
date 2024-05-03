package Usuarios;

import java.time.LocalDate;

public class Ejecutivo extends Empleado{

public Ejecutivo(String nombre, String apellidoPaterno, String apellidoMaterno, String ciudad, String estado, String curp, String direccion, int anioNacimiento, String RFC, String contrasena, LocalDate fechaInicio, double salario){
    super(nombre, apellidoPaterno, apellidoMaterno, ciudad, estado, curp, direccion, anioNacimiento, RFC, contrasena, fechaInicio, salario);
}

    public void darDeAltaCliente(){

    }
    public void autorizarTarjeta(){

    }
    public void registrarEjecutivo(){

    }
    public void modificarEjecutivo(){

    }
    public void eliminarEjecutivo(){

    }
}
