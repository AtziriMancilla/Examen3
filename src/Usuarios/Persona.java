package Usuarios;

import java.time.LocalDate;

public class Persona {
    private int id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String ciudad;
    private String estado;
    private String curp;
    private String direccion;
    private LocalDate fechaNacimiento;
    private String RFC;
    //private Sucursal sucursal;
    private String contrasena;
    public String toString(){
        return String.format("ID: %d,Nombre: %s, Apellido: %s %s, Ciudad: %s, Estado: %s,Curp: %s, Direccion: %s, Fecha nacimiento: %s, RFC: %s",nombre,apellidoPaterno,apellidoMaterno,ciudad,estado,curp,direccion,fechaNacimiento,RFC);
    }
}
