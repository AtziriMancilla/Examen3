package Usuarios;

import java.time.LocalDate;

public class Empleado extends Persona{
    private LocalDate fechaInicio;
    private double salario;
    //private Rol rol;
    public Empleado(String nombre, String apellidoPaterno, String apellidoMaterno, String ciudad, String estado, String curp, String direccion, int anioNacimiento, String RFC, String contrasena, LocalDate fechaInicio, double salario){
        super(nombre,apellidoPaterno, apellidoMaterno, ciudad, estado, curp, direccion, anioNacimiento, RFC, contrasena );
        this.fechaInicio = LocalDate.now();
        this.salario = salario;
        //this.rol = Empleado;
    }


}
