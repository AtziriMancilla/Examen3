package Usuarios;

import Banco.Banco;
import Usuarios.utils.DatosComun;
import Usuarios.utils.Rol;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Ejecutivo extends Empleado{
private LocalDate fechaInicio;

public Ejecutivo(String nombre, String apellidoPaterno, String apellidoMaterno, String ciudad, String estado, String curp, String direccion, int anioNacimiento, LocalDate fechaNacimiento, String RFC, String contrasena, double salario){
    super(nombre, apellidoPaterno, apellidoMaterno, ciudad, estado, curp, direccion, anioNacimiento, fechaNacimiento, Rol.EJECUTIVO, RFC, contrasena, salario);
    fechaInicio = LocalDate.now();

}

    @Override
    public String toString(){
        return String.format("ID: %d,Nombre: %s, Apellido: %s %s, Ciudad: %s, Estado: %s,Curp: %s, Direccion: %s, Año nacimiento: %d, RFC: %s, Fecha de inicio: , salario: %f", super.toString()); //me falta la fecha en formato
    }

    public static void registrarEjecutivo(){
        Scanner sc = new Scanner(System.in);
        ArrayList<String> datosComun = DatosComun.registrarDatosComun(Rol.EJECUTIVO);
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
        //
        System.out.println("Ingrese salario");
        double salario = sc.nextDouble();
        Ejecutivo ejecutivo = new Ejecutivo(nombre, apellidoPaterno, apellidoMaterno, ciudad, estado, CURP, direccion, anio, fechaNacimiento, RFC, contrasena, salario);
        if(!Banco.personas.containsKey(Rol.EJECUTIVO)){
            Banco.personas.put(Rol.EJECUTIVO, new ArrayList<Persona>());
        }
        Banco.personas.get(Rol.EJECUTIVO).add(ejecutivo);
        System.out.println(">Ejecutivo registrado<");
    }
    public static void mostrarEjecutivos(){
        System.out.println("\nEjecutivos en el banco\n");
        if (((ArrayList)Banco.personas.get(Rol.EJECUTIVO)).isEmpty()) {
            System.out.println("No hay ejecutivos registrados");
        } else {
            int i = 1;

            for(Iterator var1 = ((ArrayList)Banco.personas.get(Rol.EJECUTIVO)).iterator(); var1.hasNext(); ++i) {
                Persona usuario = (Persona)var1.next();
                Ejecutivo ejecutivo = (Ejecutivo) usuario;
                System.out.println("" + i + ") " + ejecutivo.toString());
            }
        }
    }
    public static void modificarEjecutivo(){

    }
    public static void eliminarEjecutivo(){
        Scanner sc=new Scanner(System.in);
        mostrarEjecutivos();
        System.out.println("Selecciona el ejecutivo que deseas eliminar");
        int numEjecutivo=sc.nextInt();
        System.out.println("Seleccionaste a: ");
        Banco.personas.get(Rol.EJECUTIVO).get(numEjecutivo-1).toString();
        System.out.println("¿Deseas eliminarlo? 1) Si 2) Cancelar");
        int opcion= sc.nextInt();
        if(opcion==1){
            Banco.personas.get(Rol.EJECUTIVO).remove(numEjecutivo-1);
            System.out.println("Ejecutivo eliminado");
        }
        else{
            System.out.println("Se cancelo la eliminacion");
        }
    }
}
