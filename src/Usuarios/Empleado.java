package Usuarios;

import Usuarios.utils.Rol;

import java.time.LocalDate;

public class Empleado extends Persona{

    protected double salario;
    private Rol rol;
    private LocalDate fechaInicio;
    public Empleado(String nombre, String apellidoPaterno, String apellidoMaterno, String ciudad, String estado, String curp, String direccion, int anioNacimiento,  LocalDate fechaNacimiento, Rol rol, String RFC, String contrasena, double salario){
        super(nombre,apellidoPaterno, apellidoMaterno, ciudad, estado, curp, direccion, Persona.getAnioNacimiento(),  fechaNacimiento, rol, RFC, contrasena );
        this.salario = salario;
        this.rol = rol;
        this.fechaInicio = LocalDate.now();
    }

    @Override
    public String toString(){
        return String.format("%s, Salario: %d, Fecha Inicio: %s", super.toString(), salario, String.valueOf(fechaInicio));
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
}
