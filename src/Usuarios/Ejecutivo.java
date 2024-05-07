package Usuarios;

import Banco.Banco;
import Banco.utils.Generador;
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
        Scanner sc=new Scanner(System.in);
        mostrarEjecutivos();
        System.out.println("Selecciona el ejecutivo: ");
        int numEjecutivo=sc.nextInt();
        System.out.println("¿Qué información deseas editar?");
        System.out.println("1) Nombre\n2) Apellidos \n3) Ciudad\n4) Estado\n5) Dirección\n6) Fecha de nacimiento 7)Contraseña");
        int opcion=sc.nextInt();
        Ejecutivo ejecutivo=(Ejecutivo) Banco.personas.get(Rol.EJECUTIVO).get(numEjecutivo-1);
        switch (opcion){
            case 1:
                System.out.println("Ingrese el nuevo nombre: ");
                ejecutivo.setNombre(sc.nextLine());
                Banco.personas.get(Rol.EJECUTIVO).set(numEjecutivo-1,ejecutivo);
                String curpAntigua = ejecutivo.getCurp();
                char sexo = curpAntigua.charAt(10);
                Generador.generarCURP(ejecutivo.getNombre(), ejecutivo.getApellidoPaterno(), ejecutivo.getApellidoMaterno(), ejecutivo.getFechaNacimiento(), sexo, ejecutivo.getEstado());
                String nuevorfc = Generador.generarRFC(ejecutivo.getNombre(), ejecutivo.getApellidoPaterno(), ejecutivo.getApellidoMaterno(), ejecutivo.getFechaNacimiento());
                ejecutivo.setRFC(nuevorfc);
                System.out.println("Nombre modificado");
                break;
            case 2:
                System.out.println("Ingrese el nuevo apellido Paterno: ");
                ejecutivo.setApellidoPaterno(sc.nextLine());
                System.out.println("Ingrese el nuevo apellido Materno: ");
                ejecutivo.setApellidoMaterno(sc.nextLine());
                String curpAntigua1 = ejecutivo.getCurp();
                char sexo1 = curpAntigua1.charAt(10);
                Generador.generarCURP(ejecutivo.getNombre(), ejecutivo.getApellidoPaterno(), ejecutivo.getApellidoMaterno(), ejecutivo.getFechaNacimiento(), sexo1, ejecutivo.getEstado());
                String nuevorfc1 = Generador.generarRFC(ejecutivo.getNombre(), ejecutivo.getApellidoPaterno(), ejecutivo.getApellidoMaterno(), ejecutivo.getFechaNacimiento());
                ejecutivo.setRFC(nuevorfc1);
                Banco.personas.get(Rol.EJECUTIVO).set(numEjecutivo-1,ejecutivo);
                System.out.println("Apellido modificado");
                break;
            case 3:
                System.out.println("Ingrese nueva ciudad: ");
                ejecutivo.setCiudad(sc.nextLine());
                Banco.personas.get(Rol.EJECUTIVO).set(numEjecutivo-1,ejecutivo);
                System.out.println("Ciudad actualizada");
                break;
            case 4:
                System.out.println("Ingrese nuevo estado: ");
                ejecutivo.setEstado(sc.nextLine());
                Banco.personas.get(Rol.EJECUTIVO).set(numEjecutivo-1,ejecutivo);
                System.out.println("Estado actualizado");
                break;
            case 5:
                System.out.println("Ingrese nueva direccion: ");
                ejecutivo.setDireccion(sc.nextLine());
                Banco.personas.get(Rol.EJECUTIVO).set(numEjecutivo-1,ejecutivo);
                System.out.println("Dirección actualizada");
                break;
            case 6 :
                System.out.println("Fecha de nacimiento");
                LocalDate nuevaFechaNacimiento = DatosComun.obtenerFechaNacimiento();
                ejecutivo.setFechaNacimiento(nuevaFechaNacimiento);
                int anioNacimiento = DatosComun.obtenerFechaNacimiento().getYear();
                ejecutivo.setAnioNacimiento(anioNacimiento);
                String curpAntigua2 = ejecutivo.getCurp();
                char sexo2 = curpAntigua2.charAt(10);
                Generador.generarCURP(ejecutivo.getNombre(), ejecutivo.getApellidoPaterno(), ejecutivo.getApellidoMaterno(), ejecutivo.getFechaNacimiento(), sexo2, ejecutivo.getEstado());
                String RFCNuevo = Generador.generarRFC(ejecutivo.getNombre(), ejecutivo.getApellidoPaterno(), ejecutivo.getApellidoMaterno(), ejecutivo.getFechaNacimiento());
                ejecutivo.setRFC(RFCNuevo);
                System.out.println("Fecha Nacimiento Actualizada");
                break;
            case 7 :
                System.out.println("Ingrese nueva contraseña");
                String nuevaContrasena = sc.nextLine();
                ejecutivo.setContrasena(nuevaContrasena);
                System.out.println("Contrasena Actualizada");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + opcion);

        }
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
