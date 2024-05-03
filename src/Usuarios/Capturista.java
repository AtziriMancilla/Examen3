package Usuarios;

import java.time.LocalDate;

public class Capturista extends Empleado{

    public Capturista(String nombre, String apellidoPaterno, String apellidoMaterno, String ciudad, String estado, String curp, String direccion, int anioNacimiento, String RFC, String contrasena, LocalDate fechaInicio, double salario){
        super(nombre, apellidoPaterno, apellidoMaterno, ciudad, estado, curp, direccion, anioNacimiento, RFC, contrasena, fechaInicio, salario);
    }

}
