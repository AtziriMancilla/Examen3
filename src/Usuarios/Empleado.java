package Usuarios;

import Usuarios.utils.Rol;

import java.time.LocalDate;

public class Empleado extends Persona{
    private LocalDate fechaInicio;
    protected double salario;
    //private Rol rol;
    public Empleado(String nombre, String apellidoPaterno, String apellidoMaterno, String ciudad, String estado, String curp, String direccion, LocalDate fechaNacimiento, Rol rol, String RFC, String contrasena, double salario){
        super(nombre,apellidoPaterno, apellidoMaterno, ciudad, estado, curp, direccion, fechaNacimiento, rol, RFC, contrasena );
        this.fechaInicio = LocalDate.now();
        this.salario = salario;
        //this.rol = Empleado;
    }

    @Override
    public String toString(){
        return String.format("ID: %d,Nombre: %s, Apellido: %s %s, Ciudad: %s, Estado: %s,Curp: %s, Direccion: %s, Año nacimiento: %d, RFC: %s, Fecha de inicio: , salario: %f", super.toString(), salario); //me falta la fecha en formato
    }
    public static void mostrarEmpleados(){

    }


}
