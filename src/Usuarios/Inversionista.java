package Usuarios;

import Banco.Banco;
import Usuarios.utils.DatosComun;
import Usuarios.utils.Inversion;
import Usuarios.utils.Rol;

import java.time.LocalDate;
import java.util.ArrayList;

public class Inversionista extends Persona{
    private ArrayList<Inversion> inversiones;

    public Inversionista(String nombre, String apellidoPaterno, String apellidoMaterno, String ciudad, String estado, String curp, String direccion, int anioNacimiento, LocalDate fechaNacimiento, String RFC, String contrasena) {
        super(nombre, apellidoPaterno, apellidoMaterno, ciudad, estado, curp, direccion, anioNacimiento, fechaNacimiento, Rol.INVERSIONISTA, RFC, contrasena);
        inversiones = new ArrayList<>();
    }
    public ArrayList<Inversion> getInversiones() {
        return inversiones;
    }
    public static void registrarInversionista(){
        ArrayList<String> datosComun = DatosComun.registrarDatosComun(Rol.INVERSIONISTA);
        String nombre = datosComun.get(0);
        String apellidoPaterno = datosComun.get(1);
        String apellidoMaterno = datosComun.get(2);
        String ciudad = datosComun.get(3);
        String estado = datosComun.get(4);
        String CURP = datosComun.get(5);
        String direccion = datosComun.get(6);
        String fechaNacimientoCadena= datosComun.get(7);
        String RFC = datosComun.get(8);
        String contrasena = datosComun.get(9);

        ///fecha nacimiento cadena viene en formato ("dd/MM/YYYY")
        String diacadena = fechaNacimientoCadena.substring(0,2);
        String mescadena = fechaNacimientoCadena.substring(3,5);
        String aniocadena = fechaNacimientoCadena.substring (6,10);
        int dia = Integer.parseInt(diacadena);
        int mes = Integer.parseInt(mescadena);
        int anio = Integer.parseInt(aniocadena);
        //conversion a LocalDate
        LocalDate fechaNacimiento = LocalDate.of(anio,mes, dia);

        Inversionista inversionista = new Inversionista(nombre, apellidoPaterno, apellidoMaterno, ciudad, estado, CURP, direccion, anio, fechaNacimiento, RFC, contrasena);

        if(!Banco.personas.containsKey(Rol.INVERSIONISTA)){
            Banco.personas.put(Rol.INVERSIONISTA, new ArrayList<Persona>());
        }
        Banco.personas.get(Rol.INVERSIONISTA).add(inversionista);
        System.out.println(">Inversionista registrado<");
    }
    public static void modificarInversionista(){

    }
    public static void eliminarInversionista(){

    }
    public static void realizarInversion(){

    }
    public static void mostrarInversionistas(){}
    @Override
    public String toString() {
        return super.toString();
    }
}
