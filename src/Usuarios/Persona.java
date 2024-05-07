package Usuarios;

import Usuarios.utils.Rol;

import java.time.LocalDate;

public class Persona {
    private static int PERSONAS_REGISTRADAS = 1;
    private int id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String ciudad;
    private String estado;
    private String curp;
    private String direccion;
    private static int anioNacimiento;
    public LocalDate fechaNacimiento;
    private String RFC;
    //private Sucursal sucursal;
    private String contrasena;
    private Rol rol;

    public Persona(String nombre, String apellidoPaterno, String apellidoMaterno, String ciudad, String estado, String curp, String direccion, int anioNacimiento, LocalDate fechaNacimiento, Rol rol, String rfc, String contrasena) {
      this.id = PERSONAS_REGISTRADAS;
      PERSONAS_REGISTRADAS++;
      this.nombre = nombre;
      this.apellidoPaterno = apellidoPaterno;
      this.apellidoMaterno = apellidoMaterno;
      this.ciudad = ciudad;
      this.estado = estado;
      this.curp = curp;
      this.direccion = direccion;
      this.anioNacimiento = anioNacimiento;
      this.fechaNacimiento = fechaNacimiento;
      this.RFC = rfc;
      this.rol = rol;
      this.contrasena = contrasena;
      //this.sucursal = sucursal;
    }

    public String toString(){
        return String.format("ID: %d,Nombre: %s, Apellido: %s %s, Ciudad: %s, Estado: %s,Curp: %s, Direccion: %s, Fecha nacimiento: %s, RFC: %s",nombre,apellidoPaterno,apellidoMaterno,ciudad,estado,curp,direccion,fechaNacimiento,RFC);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public static int getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(int anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
