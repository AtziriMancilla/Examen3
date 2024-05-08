package Usuarios;

import Banco.Banco;
import Banco.utils.Generador;
import Usuarios.utils.DatosComun;
import Usuarios.utils.Rol;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import static javax.swing.UIManager.get;
//import static sun.jvm.hotspot.runtime.BasicObjectLock.size;

public class Capturista extends Empleado{

    private LocalDate fechaInicio;

    public Capturista(String nombre, String apellidoPaterno, String apellidoMaterno, String ciudad, String estado, String curp, String direccion, int anioNacimiento, LocalDate fechaNacimiento, String RFC, String contrasena, double salario){
        super(nombre, apellidoPaterno, apellidoMaterno, ciudad, estado, curp, direccion, anioNacimiento, fechaNacimiento, Rol.CAPTURISTA, RFC, contrasena,  salario);
        fechaInicio = getFechaInicio();
    }

    @Override
    public String toString(){
        return String.format("%s, Fecha Inicio: %s", super.toString(), String.valueOf(fechaInicio));
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
        String anioNacimiento = datosComun.get(7);
        String fechaNacimientoCadena= datosComun.get(8);
        String RFC = datosComun.get(9);
        String contrasena = datosComun.get(10);

        System.out.println("Ingrese salario");
        double salario = DatosComun.pedirValorDouble();
        //ocupo volver int el año de nacimiento y LocalDate la fecha de nacimiento
        int anioNacimientoint = Integer.parseInt(anioNacimiento);
        LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoCadena);

        Capturista capturista = new Capturista(nombre, apellidoPaterno, apellidoMaterno, ciudad, estado, CURP, direccion, anioNacimientoint, fechaNacimiento, RFC, contrasena, salario);
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

        int numCapturista=DatosComun.pedirNumero();
        //falta try catch para que no sobrepase un numero de la lista
        System.out.println("¿Qué información deseas editar?");
        System.out.println("1) Nombre\n2) Apellidos \n3) Ciudad\n4) Estado\n5) Dirección\n6) Fecha de nacimiento 7)Contraseña");
        int opcion=DatosComun.pedirNumero();

        Capturista capturista=(Capturista) Banco.personas.get(Rol.CAPTURISTA).get(numCapturista-1);
        switch (opcion){
            case 1:
                System.out.println("Ingrese el nuevo nombre: ");
                String nuevoNombre = DatosComun.pedirDatoString();
                capturista.setNombre(nuevoNombre);
                Banco.personas.get(Rol.CAPTURISTA).set(numCapturista-1,capturista);
                String curpAntigua = capturista.getCurp();
                char sexo = curpAntigua.charAt(10);
                String nuevaCurp= Generador.generarCURP(capturista.getNombre(), capturista.getApellidoPaterno(), capturista.getApellidoMaterno(), capturista.getFechaNacimiento(), sexo, capturista.getEstado());
                String nuevorfc = Generador.generarRFC(capturista.getNombre(), capturista.getApellidoPaterno(), capturista.getApellidoMaterno(), capturista.getFechaNacimiento());
                capturista.setRFC(nuevorfc);
                capturista.setCurp(nuevaCurp);
                System.out.println("Nombre modificado");
                break;
            case 2:
                System.out.println("Ingrese el nuevo apellido Paterno: ");
                String nuevoApellidoPaterno = DatosComun.pedirDatoString();
                capturista.setApellidoPaterno(nuevoApellidoPaterno);
                System.out.println("Ingrese el nuevo apellido Materno: ");
                String nuevoApellidoMaterno = DatosComun.pedirDatoString();
                capturista.setApellidoMaterno(nuevoApellidoMaterno);
                String curpAntigua1 = capturista.getCurp();
                char sexo1 = curpAntigua1.charAt(10);
                String nuevaCurp1 = Generador.generarCURP(capturista.getNombre(), capturista.getApellidoPaterno(), capturista.getApellidoMaterno(), capturista.getFechaNacimiento(), sexo1, capturista.getEstado());
                String nuevorfc1 = Generador.generarRFC(capturista.getNombre(), capturista.getApellidoPaterno(), capturista.getApellidoMaterno(), capturista.getFechaNacimiento());
                capturista.setRFC(nuevorfc1);
                capturista.setCurp(nuevaCurp1);
                Banco.personas.get(Rol.CLIENTE).set(numCapturista-1,capturista);
                System.out.println("Apellido modificado");
                break;
            case 3:
                System.out.println("Ingrese nueva ciudad: ");
                String nuevaCiudad = DatosComun.pedirDatoString();
                capturista.setCiudad(nuevaCiudad);
                Banco.personas.get(Rol.CLIENTE).set(numCapturista-1,capturista);
                System.out.println("Ciudad actualizada");
                break;
            case 4:
                System.out.println("Ingrese nuevo estado: ");
                String nuevoEstado = DatosComun.pedirDatoString();
                capturista.setEstado(nuevoEstado);
                Banco.personas.get(Rol.CLIENTE).set(numCapturista-1,capturista);
                System.out.println("Estado actualizado");
                break;
            case 5:
                System.out.println("Ingrese nueva direccion: ");
                String nuevaDireccion = DatosComun.pedirDireccion();
                capturista.setDireccion(nuevaDireccion);
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
                String nuevaCurp2 = Generador.generarCURP(capturista.getNombre(), capturista.getApellidoPaterno(), capturista.getApellidoMaterno(), capturista.getFechaNacimiento(), sexo2, capturista.getEstado());
                String RFCNuevo2 = Generador.generarRFC(capturista.getNombre(), capturista.getApellidoPaterno(), capturista.getApellidoMaterno(), capturista.getFechaNacimiento());
                capturista.setRFC(RFCNuevo2);
                capturista.setCurp(nuevaCurp2);
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
    /*private static int pedirNumeroDeLista(){
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Ingrese un índice para acceder al elemento de la lista: ");
            int indice = sc.nextInt();

            if (indice < 0 || indice >= Banco.personas.get(Rol.CAPTURISTA).size()) {
                throw new IndexOutOfBoundsException("El índice ingresado está fuera del rango válido.");
            }

            String elemento = Banco.personas.get(Rol.CAPTURISTA).get(i);
            System.out.println("El elemento en el índice " + indice + " es: " + elemento);
        } catch (InputMismatchException e) {
            System.out.println("Error: Debe ingresar un número entero.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
    }*/

}
