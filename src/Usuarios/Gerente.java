package Usuarios;

import Banco.Banco;
import Usuarios.utils.Rol;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class Gerente extends Empleado{
    public String contrasenaSistema;

    private LocalDate fechaInicio;
    public Gerente(String nombre, String apellidoPaterno, String apellidoMaterno, String ciudad, String estado, String curp, String direccion, int anioNacimiento, LocalDate fechaNacimiento, String RFC, String contrasena,  double salario, String contrasenaSistema){
            super(nombre, apellidoPaterno, apellidoMaterno, ciudad, estado, curp, direccion, anioNacimiento, fechaNacimiento, Rol.GERENTE, RFC, contrasena, salario);
            this.contrasenaSistema= contrasenaSistema;
             fechaInicio = LocalDate.now();
    }

    @Override
    public String toString(){
        return String.format("ID: %d,Nombre: %s, Apellido: %s %s, Ciudad: %s, Estado: %s,Curp: %s, Direccion: %s, Año nacimiento: %d, RFC: %s, Fecha de inicio: , salario: %f", super.toString()); //me falta la fecha en formato
    }
    public static void verInversiones (){

    }
    public static void modificarGerente(){

    }

    public static void mostrarGerente(){
        System.out.println("\nGerente en el banco\n"+ Banco.personas.get(Rol.GERENTE).getFirst().toString());
    }
}
