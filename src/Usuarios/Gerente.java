package Usuarios;

import java.time.LocalDate;

public class Gerente extends Empleado{
    public String contrasenaSistema;

    public Gerente(String nombre, String apellidoPaterno, String apellidoMaterno, String ciudad, String estado, String curp, String direccion, int anioNacimiento, String RFC, String contrasena, LocalDate fechaInicio, double salario, String contrasenaSistema){
            super(nombre, apellidoPaterno, apellidoMaterno, ciudad, estado, curp, direccion, anioNacimiento, RFC, contrasena, fechaInicio, salario);
            this.contrasenaSistema= contrasenaSistema;
    }

    @Override
    public String toString(){
        return String.format("ID: %d,Nombre: %s, Apellido: %s %s, Ciudad: %s, Estado: %s,Curp: %s, Direccion: %s, Año nacimiento: %d, RFC: %s, Fecha de inicio: , salario: %f", super.toString()); //me falta la fecha en formato
    }
    public static void verInversiones (){

    }
}
