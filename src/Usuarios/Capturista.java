package Usuarios;

import Banco.Banco;
import Banco.utils.Generador;
import Usuarios.utils.DatosComun;
import Usuarios.utils.Rol;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
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
        System.out.println("\nCapturistas en el banco\n");
        if (((ArrayList)Banco.personas.get(Rol.CAPTURISTA)).isEmpty()) {
            System.out.println("No hay capturistas registrados");
        } else {
            int i = 1;

            for(Iterator var1 = ((ArrayList)Banco.personas.get(Rol.CAPTURISTA)).iterator(); var1.hasNext(); ++i) {
                Persona usuario = (Persona)var1.next();
                Capturista capturista = (Capturista) usuario;
                System.out.println("" + i + ") " + capturista.toString());
            }
        }
    }
    public static void borrarCapturista(){
        Scanner sc=new Scanner(System.in);
        mostrarCapturistas();
        System.out.println("Selecciona el capturista que deseas eliminar");
        int numCapturista=sc.nextInt();
        System.out.println("Seleccionaste a: ");
        Banco.personas.get(Rol.CAPTURISTA).get(numCapturista-1).toString();
        System.out.println("¿Deseas eliminarlo? 1) Si 2) Cancelar");
        int opcion= sc.nextInt();
        if(opcion==1){
            Banco.personas.get(Rol.CAPTURISTA).remove(numCapturista-1);
            System.out.println("Capturista eliminado");
        }
        else{
            System.out.println("Se cancelo la eliminacion");
        }
    }
    public static void modificarCapturista(){
        Scanner sc=new Scanner(System.in);
        mostrarCapturistas();
        System.out.println("Selecciona el capturista: ");
        int numCapturista=sc.nextInt();
        System.out.println("¿Qué información deseas editar?");
        System.out.println("1) Nombre\n2) Apellidos \n3) Ciudad\n4) Estado\n5) Dirección\n6) Fecha de nacimiento 7)Contraseña");
        int opcion=sc.nextInt();
        Capturista capturista=(Capturista) Banco.personas.get(Rol.CAPTURISTA).get(numCapturista-1);
        switch (opcion){
            case 1:
                System.out.println("Ingrese el nuevo nombre: ");
                capturista.setNombre(sc.nextLine());
                Banco.personas.get(Rol.CAPTURISTA).set(numCapturista-1,capturista);
                String curpAntigua = capturista.getCurp();
                char sexo = curpAntigua.charAt(10);
                Generador.generarCURP(capturista.getNombre(), capturista.getApellidoPaterno(), capturista.getApellidoMaterno(), capturista.getFechaNacimiento(), sexo, capturista.getEstado());
                String nuevorfc = Generador.generarRFC(capturista.getNombre(), capturista.getApellidoPaterno(), capturista.getApellidoMaterno(), capturista.getFechaNacimiento());
                capturista.setRFC(nuevorfc);
                System.out.println("Nombre modificado");
                break;
            case 2:
                System.out.println("Ingrese el nuevo apellido Paterno: ");
                capturista.setApellidoPaterno(sc.nextLine());
                System.out.println("Ingrese el nuevo apellido Materno: ");
                capturista.setApellidoMaterno(sc.nextLine());
                String curpAntigua1 = capturista.getCurp();
                char sexo1 = curpAntigua1.charAt(10);
                Generador.generarCURP(capturista.getNombre(), capturista.getApellidoPaterno(), capturista.getApellidoMaterno(), capturista.getFechaNacimiento(), sexo1, capturista.getEstado());
                String nuevorfc1 = Generador.generarRFC(capturista.getNombre(), capturista.getApellidoPaterno(), capturista.getApellidoMaterno(), capturista.getFechaNacimiento());
                capturista.setRFC(nuevorfc1);
                Banco.personas.get(Rol.CLIENTE).set(numCapturista-1,capturista);
                System.out.println("Apellido modificado");
                break;
            case 3:
                System.out.println("Ingrese nueva ciudad: ");
                capturista.setCiudad(sc.nextLine());
                Banco.personas.get(Rol.CLIENTE).set(numCapturista-1,capturista);
                System.out.println("Ciudad actualizada");
                break;
            case 4:
                System.out.println("Ingrese nuevo estado: ");
                capturista.setEstado(sc.nextLine());
                Banco.personas.get(Rol.CLIENTE).set(numCapturista-1,capturista);
                System.out.println("Estado actualizado");
                break;
            case 5:
                System.out.println("Ingrese nueva direccion: ");
                capturista.setDireccion(sc.nextLine());
                Banco.personas.get(Rol.CLIENTE).set(numCapturista-1,capturista);
                System.out.println("Dirección actualizada");
                break;
            case 6 :
                System.out.println("Fecha de nacimiento");
                LocalDate nuevaFechaNacimiento = DatosComun.obtenerFechaNacimiento();
                capturista.setFechaNacimiento(nuevaFechaNacimiento);
                int anioNacimiento = DatosComun.obtenerFechaNacimiento().getYear();
                capturista.setAnioNacimiento(anioNacimiento);
                String curpAntigua2 = capturista.getCurp();
                char sexo2 = curpAntigua2.charAt(10);
                Generador.generarCURP(capturista.getNombre(), capturista.getApellidoPaterno(), capturista.getApellidoMaterno(), capturista.getFechaNacimiento(), sexo2, capturista.getEstado());
                String RFCNuevo = Generador.generarRFC(capturista.getNombre(), capturista.getApellidoPaterno(), capturista.getApellidoMaterno(), capturista.getFechaNacimiento());
                capturista.setRFC(RFCNuevo);
                System.out.println("Fecha Nacimiento Actualizada");
                break;
            case 7 :
                System.out.println("Ingrese nueva contraseña");
                String nuevaContrasena = sc.nextLine();
                capturista.setContrasena(nuevaContrasena);
                System.out.println("Contrasena Actualizada");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + opcion);

        }
    }

}
