package Usuarios;

import Banco.Banco;
import Usuarios.utils.DatosComun;
import Usuarios.utils.Rol;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Capturista extends Empleado{

    LocalDate fechaInicio;

    public Capturista(String nombre, String apellidoPaterno, String apellidoMaterno, String ciudad, String estado, String curp, String direccion, int anioNacimiento, LocalDate fechaNacimiento, String RFC, String contrasena, double salario){
        super(nombre, apellidoPaterno, apellidoMaterno, ciudad, estado, curp, direccion, getAnioNacimiento(), fechaNacimiento, Rol.CAPTURISTA, RFC, contrasena,  salario);
        fechaInicio = LocalDate.now();
    }

    @Override
    public String toString(){
        return String.format("ID: %d,Nombre: %s, Apellido: %s %s, Ciudad: %s, Estado: %s,Curp: %s, Direccion: %s, Año nacimiento: %d, RFC: %s, Fecha de inicio: , salario: %f", super.toString()); //me falta la fecha en formato
    }
    public static void registrarCapturista(){
        Scanner sc = new Scanner(System.in);
        ArrayList<String> datosComun = DatosComun.registrarDatosComun(Rol.CAPTURISTA);
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

        System.out.println("Ingrese salario");
        double salario = sc.nextDouble();
        ///fecha nacimiento cadena viene en formato ("dd/MM/YYYY")
        String diacadena = fechaNacimientoCadena.substring(0,2);
        String mescadena = fechaNacimientoCadena.substring(3,5);
        String aniocadena = fechaNacimientoCadena.substring (6,10);
        int dia = Integer.parseInt(diacadena);
        int mes = Integer.parseInt(mescadena);
        int anio = Integer.parseInt(aniocadena);
        //conversion a LocalDate
        LocalDate fechaNacimiento = LocalDate.of(anio,mes, dia);
        ////

        Capturista capturista = new Capturista(nombre, apellidoPaterno, apellidoMaterno, ciudad, estado, CURP, direccion, anio, fechaNacimiento, RFC, contrasena, salario);
        if(!Banco.personas.containsKey(Rol.CAPTURISTA)){
            Banco.personas.put(Rol.CAPTURISTA, new ArrayList<Persona>());
        }
        Banco.personas.get(Rol.CAPTURISTA).add(capturista);
        System.out.println(">Capturista registrado<");

    }
    public static void mostrarCapturistas(){

    }
    public static void borrarCapturista(){

    }
    public static void modificarCapturista(){

    }

}
