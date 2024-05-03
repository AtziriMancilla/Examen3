package Usuarios;

import java.time.LocalDate;

public class Gerente extends Empleado{
    public String contrasenaSistema;

    public Gerente(String nombre, String apellidoPaterno, String apellidoMaterno, String ciudad, String estado, String curp, String direccion, int anioNacimiento, String RFC, String contrasena, LocalDate fechaInicio, double salario, String contrasenaSistema){
            super(nombre, apellidoPaterno, apellidoMaterno, ciudad, estado, curp, direccion, anioNacimiento, RFC, contrasena, fechaInicio, salario);
            this.contrasenaSistema= contrasenaSistema;
    }
    public void verInversiones (){

    }
}
