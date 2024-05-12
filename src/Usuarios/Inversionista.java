package Usuarios;

import Banco.Banco;
import Banco.utils.Generador;
import Usuarios.utils.DatosComun;
import Usuarios.utils.Inversion;
import Usuarios.utils.Rol;
import utils.UsuarioEnSesion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class Inversionista extends Persona{
    private ArrayList<Inversion> inversiones;

    public Inversionista(String nombre, String apellidoPaterno, String apellidoMaterno, String ciudad, String estado, String curp, String direccion, int anioNacimiento, LocalDate fechaNacimiento, String RFC, String nombreUsuario, String contrasena) {
        super(nombre, apellidoPaterno, apellidoMaterno, ciudad, estado, curp, direccion, anioNacimiento, fechaNacimiento, Rol.INVERSIONISTA, RFC, nombreUsuario, contrasena);
        inversiones = new ArrayList<>();

    }
    public ArrayList<Inversion> getInversiones() {
        return inversiones;
    }
    public static void registrarInversionista(Banco banco){
        ArrayList<String> datosComun = DatosComun.registrarDatosComun(Rol.INVERSIONISTA,banco);
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
        String nombreUsuario=datosComun.get(10);
        String contrasena = datosComun.get(11);

        //ocupo volver int el año de nacimiento y LocalDate la fecha de nacimiento
        int anioNacimientoint = Integer.parseInt(anioNacimiento);
        LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoCadena);

        Inversionista inversionista = new Inversionista(nombre, apellidoPaterno, apellidoMaterno, ciudad, estado, CURP, direccion, anioNacimientoint, fechaNacimiento, RFC, nombreUsuario, contrasena);

        banco.personas.get(Rol.INVERSIONISTA).add(inversionista);
        System.out.println(">Inversionista registrado<");
    }
    public static void modificarInversionista(Banco banco){
        Scanner sc=new Scanner(System.in);
        mostrarInversionistas(banco);
        System.out.println("Selecciona el INVERSIONISTA: ");
        int numInversionista=pedirInversionista(banco);
        int opcion=10 ;
        do{
            System.out.println("¿Qué información deseas editar?");
            System.out.println("1) Nombre\n2) Apellidos \n3) Ciudad\n4) Estado\n5) Dirección\n6) Fecha de nacimiento \n7)Contraseña\0 0)Atrás");
            opcion=sc.nextInt();
            Inversionista inversionista=(Inversionista) banco.personas.get(Rol.INVERSIONISTA).get(numInversionista-1);

            switch (opcion){
                case 1:
                    System.out.println("Ingrese el nuevo nombre: ");
                    inversionista.setNombre(DatosComun.pedirDatoString());
                    banco.personas.get(Rol.INVERSIONISTA).set(numInversionista-1,inversionista);
                    String curpAntigua = inversionista.getCurp();
                    char sexo = curpAntigua.charAt(10);
                    String nuevacurp= Generador.generarCURP(inversionista.getNombre(), inversionista.getApellidoPaterno(), inversionista.getApellidoMaterno(), inversionista.getFechaNacimiento(), sexo, inversionista.getEstado());
                    String nuevorfc = Generador.generarRFC(inversionista.getNombre(), inversionista.getApellidoPaterno(), inversionista.getApellidoMaterno(), inversionista.getFechaNacimiento());
                    inversionista.setRFC(nuevorfc);
                    inversionista.setCurp(nuevacurp);
                    System.out.println("Nombre modificado");
                    break;
                case 2:
                    System.out.println("Ingrese el nuevo apellido Paterno: ");
                    inversionista.setApellidoPaterno(DatosComun.pedirDatoString());
                    System.out.println("Ingrese el nuevo apellido Materno: ");
                    inversionista.setApellidoMaterno(DatosComun.pedirDatoString());
                    String curpAntigua1 = inversionista.getCurp();
                    char sexo1 = curpAntigua1.charAt(10);
                   String nuevacurp1= Generador.generarCURP(inversionista.getNombre(), inversionista.getApellidoPaterno(), inversionista.getApellidoMaterno(), inversionista.getFechaNacimiento(), sexo1, inversionista.getEstado());
                    String nuevorfc1 = Generador.generarRFC(inversionista.getNombre(), inversionista.getApellidoPaterno(), inversionista.getApellidoMaterno(), inversionista.getFechaNacimiento());
                    inversionista.setRFC(nuevorfc1);
                    inversionista.setCurp(nuevacurp1);
                    banco.personas.get(Rol.INVERSIONISTA).set(numInversionista-1,inversionista);
                    System.out.println("Apellido modificado");
                    break;
                case 3:
                    System.out.println("Ingrese nueva ciudad: ");
                    inversionista.setCiudad(DatosComun.pedirDatoString());
                    banco.personas.get(Rol.INVERSIONISTA).set(numInversionista-1,inversionista);
                    System.out.println("Ciudad actualizada");
                    break;
                case 4:
                    System.out.println("Ingrese nuevo estado: ");
                    inversionista.setEstado(DatosComun.pedirDatoString());
                    banco.personas.get(Rol.INVERSIONISTA).set(numInversionista-1,inversionista);
                    System.out.println("Estado actualizado");
                    break;
                case 5:
                    System.out.println("Ingrese nueva direccion: ");
                    inversionista.setDireccion(DatosComun.pedirDireccion());
                    banco.personas.get(Rol.INVERSIONISTA).set(numInversionista-1,inversionista);
                    System.out.println("Dirección actualizada");
                    break;
                case 6 :
                    System.out.println("Fecha de nacimiento");
                    LocalDate nuevaFechaNacimiento = DatosComun.obtenerFechaNacimiento();
                    inversionista.setFechaNacimiento(nuevaFechaNacimiento);
                    int anioNacimiento = DatosComun.obtenerFechaNacimiento().getYear();
                    inversionista.setAnioNacimiento(anioNacimiento);
                    String curpAntigua2 = inversionista.getCurp();
                    char sexo2 = curpAntigua2.charAt(10);
                    String nuevaCurp= Generador.generarCURP(inversionista.getNombre(), inversionista.getApellidoPaterno(), inversionista.getApellidoMaterno(), inversionista.getFechaNacimiento(), sexo2, inversionista.getEstado());
                    String RFCNuevo = Generador.generarRFC(inversionista.getNombre(), inversionista.getApellidoPaterno(), inversionista.getApellidoMaterno(), inversionista.getFechaNacimiento());
                    inversionista.setRFC(RFCNuevo);
                    inversionista.setCurp(nuevaCurp);
                    System.out.println("Fecha Nacimiento Actualizada");
                    break;
                case 7 :
                    System.out.println("Ingrese nueva contraseña");
                    String nuevaContrasena = sc.nextLine();
                    inversionista.setContrasena(nuevaContrasena);
                    System.out.println("Contrasena Actualizada");
                    break;
                case 0 :
                    System.out.println("Tenemos que poner un método mostrarMenuEmpleado");
                    UsuarioEnSesion.getInstancia().cerrarSesion();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + opcion);

            }

        }while(opcion!=0);


    }
    public static void eliminarInversionista(Banco banco){
        Scanner sc=new Scanner(System.in);
        mostrarInversionistas(banco);
        int numInversionista=0;
        boolean band;
        do {
            try {
                band=false;
                System.out.println("Selecciona el inversionista que deseas eliminar");
                numInversionista = sc.nextInt();
                banco.personas.get(Rol.INVERSIONISTA).get(numInversionista - 1);
            } catch (IndexOutOfBoundsException | InputMismatchException error) {
                System.out.println("Opcion no valida");
                band=true;
            }
            finally {
                sc.nextLine();
            }
        }while(band);
        Inversionista inversionista=(Inversionista)banco.personas.get(Rol.INVERSIONISTA).get(numInversionista - 1);
        if(inversionista.getInversiones().isEmpty()) {
            System.out.println("Seleccionaste a: ");
            System.out.println(banco.personas.get(Rol.INVERSIONISTA).get(numInversionista - 1).toString());
            int opcion = 0;
            boolean bandera;
            do {
                bandera = false;
                try {
                    System.out.println("¿Deseas eliminarlo? 1) Si, Otro numero) Cancelar");
                    opcion = sc.nextInt();
                } catch (InputMismatchException error) {
                    System.out.println("Opcion no valida");
                    bandera = true;
                    sc.nextLine();
                }
            } while (bandera);
            if (opcion == 1) {
                banco.personas.get(Rol.INVERSIONISTA).remove(numInversionista - 1);
                System.out.println("Inversionista eliminado");
            }
            if (opcion != 1) {
                System.out.println("Se cancelo la eliminacion");
            }
        }
        else{
            System.out.println("No se puede eliminar este inversionista");
        }
    }
//    public static void realizarInversion(){
//        Persona persona = UsuarioEnSesion.getInstancia().getUsuarioActual();
//        Inversionista inversionista = (Inversionista) persona;
//        inversionista.realizarInversion();
//    }
//
//    public void agregarInversion(Inversion inversion){
//        inversiones.add(inversion);
//    }


    public static void mostrarInversionistas(Banco banco) {
        System.out.println("\nInversionistas en el banco\n");
        if ((banco.personas.get(Rol.INVERSIONISTA)).isEmpty()) {
            System.out.println("No hay inversionistas registrados");
        }
        else {
            int i = 1;

            for (Iterator<Persona> var1 = (banco.personas.get(Rol.INVERSIONISTA)).iterator(); var1.hasNext(); ++i) {
                Persona usuario = (Persona) var1.next();
                Inversionista inversionista = (Inversionista) usuario;
                System.out.println(i + ") " + inversionista.toString());
            }
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
    private static int pedirInversionista(Banco banco){
        Scanner sc = new Scanner(System.in);
        boolean confirmacion = false;
        int numInversionista=0;
        do{
            confirmacion = false;
            try{
                System.out.println("Selecciona el inversionista: ");
                numInversionista=DatosComun.pedirNumero();

                if(numInversionista<1||numInversionista>banco.personas.get(Rol.CAPTURISTA).size()){
                    throw new IndexOutOfBoundsException("El dato ingresado está fuera del tamaño de la liste");
                }
                else{
                    return  numInversionista;
                }

            }catch(IndexOutOfBoundsException error){
                System.out.println("Error: "+ error.getMessage());
                confirmacion=true;
            }
        }while(confirmacion);
        sc.nextLine();
        return  numInversionista;
    }
    public void realizarInversion(){
        Scanner sc=new Scanner(System.in);
        boolean cantidadValida = false;
        long monto;
        System.out.println("Ingrese un valor entero a invertir o escriba 0 para cancelar");
        do{
            cantidadValida=false;
            monto =DatosComun.pedirNumero();
            if (monto==0) {
                System.out.println("\nCancelando operación. Regresando al menú anterior...");
                cantidadValida = true;
            }
            if (monto>0){
                System.out.println("Realizando depósito y guardando registro...");
                Inversion inversion=new Inversion(monto);
                inversiones.add(inversion);
                System.out.println("Inversión realizada con éxito! Regresando al menú anterior...");

            }
        }while(cantidadValida);
    }
    public void mostrarMisInversiones(){
        if(inversiones.isEmpty()){
            System.out.println("No has realizado inversiones");
        }
        else{
            for(int i=0;i<inversiones.size();i++){
                System.out.println((i+1)+inversiones.get(i).mostrarInversion());
            }
        }

    }
}
