package Usuarios;

import Usuarios.utils.Inversion;

import java.util.ArrayList;

public class Inversionista extends Persona{
    private ArrayList<Inversion> inversiones = new ArrayList<>();

    public Inversionista(String nombre, String apellidoPaterno, String apellidoMaterno, String ciudad, String estado, String curp, String direccion, int anioNacimiento, String RFC, String contrasena) {
        super(nombre, apellidoPaterno, apellidoMaterno, ciudad, estado, curp, direccion, anioNacimiento, RFC, contrasena);
    }

    public static void registrarInversionista(){

    }
    public static void modificarInversionista(){

    }
    public static void eliminarInversionista(){

    }
    public static void realizarInversion(){

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
