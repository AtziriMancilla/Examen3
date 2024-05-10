package Usuarios;

import Banco.Banco;
import Banco.utils.Generador;
import Usuarios.utils.DatosComun;
import Usuarios.utils.Rol;
import utils.UsuarioEnSesion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Gerente extends Empleado{
    public String contrasenaSistema;

    private LocalDate fechaInicio;
    public Gerente(String nombre, String apellidoPaterno, String apellidoMaterno, String ciudad, String estado, String curp, String direccion, int anioNacimiento, LocalDate fechaNacimiento, String RFC,String nombreUsuario, String contrasena,  double salario, String contrasenaSistema){
            super(nombre, apellidoPaterno, apellidoMaterno, ciudad, estado, curp, direccion, anioNacimiento, fechaNacimiento, Rol.GERENTE, RFC, nombreUsuario, contrasena, salario);
            this.contrasenaSistema= contrasenaSistema;
             fechaInicio = getFechaInicio();
    }

    @Override
    public String toString(){
        return String.format("%s, Fecha Inicio: %s", super.toString(), String.valueOf(fechaInicio)); //me falta la fecha en formato
    }

    public static void modificarGerente(){
        Scanner sc=new Scanner(System.in);
        mostrarGerente();
        System.out.println("Selecciona el gerente: ");
        int numGerente=pedirGerente();

        int opt =10;
        do{
            System.out.println("¿Qué información deseas editar?");
            System.out.println("1) Nombre\n2) Apellidos \n3) Ciudad\n4) Estado\n5) Dirección\n6) Fecha de nacimiento \n7)Contraseña \n0)Atrás");
             opt=DatosComun.pedirNumero();
            Gerente gerente=(Gerente) Banco.personas.get(Rol.GERENTE).get(numGerente-1);
            switch (opt){
                case 1:
                    System.out.println("Ingrese el nuevo nombre: ");
                    gerente.setNombre(sc.nextLine());
                    Banco.personas.get(Rol.GERENTE).set(numGerente-1,gerente);
                    String curpAntigua = gerente.getCurp();
                    char sexo = curpAntigua.charAt(10);
                    Generador.generarCURP(gerente.getNombre(), gerente.getApellidoPaterno(), gerente.getApellidoMaterno(), gerente.getFechaNacimiento(), sexo, gerente.getEstado());
                    String nuevorfc = Generador.generarRFC(gerente.getNombre(), gerente.getApellidoPaterno(), gerente.getApellidoMaterno(), gerente.getFechaNacimiento());
                    gerente.setRFC(nuevorfc);
                    System.out.println("Nombre modificado");
                    break;
                case 2:
                    System.out.println("Ingrese el nuevo apellido Paterno: ");
                    gerente.setApellidoPaterno(sc.nextLine());
                    System.out.println("Ingrese el nuevo apellido Materno: ");
                    gerente.setApellidoMaterno(sc.nextLine());
                    String curpAntigua1 = gerente.getCurp();
                    char sexo1 = curpAntigua1.charAt(10);
                    Generador.generarCURP(gerente.getNombre(), gerente.getApellidoPaterno(), gerente.getApellidoMaterno(), gerente.getFechaNacimiento(), sexo1, gerente.getEstado());
                    String nuevorfc1 = Generador.generarRFC(gerente.getNombre(), gerente.getApellidoPaterno(), gerente.getApellidoMaterno(), gerente.getFechaNacimiento());
                    gerente.setRFC(nuevorfc1);
                    Banco.personas.get(Rol.GERENTE).set(numGerente-1,gerente);
                    System.out.println("Apellido modificado");
                    break;
                case 3:
                    System.out.println("Ingrese nueva ciudad: ");
                    gerente.setCiudad(sc.nextLine());
                    Banco.personas.get(Rol.GERENTE).set(numGerente-1,gerente);
                    System.out.println("Ciudad actualizada");
                    break;
                case 4:
                    System.out.println("Ingrese nuevo estado: ");
                    gerente.setEstado(sc.nextLine());
                    Banco.personas.get(Rol.GERENTE).set(numGerente-1,gerente);
                    System.out.println("Estado actualizado");
                    break;
                case 5:
                    System.out.println("Ingrese nueva direccion: ");
                    gerente.setDireccion(DatosComun.pedirDireccion());
                    Banco.personas.get(Rol.GERENTE).set(numGerente-1,gerente);
                    System.out.println("Dirección actualizada");
                    break;
                case 6 :
                    System.out.println("Fecha de nacimiento");
                    LocalDate nuevaFechaNacimiento = DatosComun.obtenerFechaNacimiento();
                    gerente.setFechaNacimiento(nuevaFechaNacimiento);
                    int anioNacimiento = DatosComun.obtenerFechaNacimiento().getYear();
                    gerente.setAnioNacimiento(anioNacimiento);
                    String curpAntigua2 = gerente.getCurp();
                    char sexo2 = curpAntigua2.charAt(10);
                    String nuevaCurp2 =Generador.generarCURP(gerente.getNombre(), gerente.getApellidoPaterno(), gerente.getApellidoMaterno(), gerente.getFechaNacimiento(), sexo2, gerente.getEstado());
                    String RFCNuevo = Generador.generarRFC(gerente.getNombre(), gerente.getApellidoPaterno(), gerente.getApellidoMaterno(), gerente.getFechaNacimiento());
                    gerente.setRFC(RFCNuevo);
                    gerente.setCurp(nuevaCurp2);
                    System.out.println("Fecha Nacimiento Actualizada");
                    break;
                case 7 :
                    System.out.println("Ingrese nueva contraseña");
                    String nuevaContrasena = sc.nextLine();
                    gerente.setContrasena(nuevaContrasena);
                    System.out.println("Contrasena Actualizada");
                    break;
                case 0 :
                    System.out.println("Tenemos que poner un método mostrarMenuEmpleado");
                    UsuarioEnSesion.getInstancia().cerrarSesion();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + opt);

            }
        }while(opt!=0);


    }

    public static void mostrarGerente(){
        System.out.println("\nGerente en el banco\n"+ Banco.personas.get(Rol.GERENTE).getFirst().toString());
    }
    private static int pedirGerente(){
        Scanner sc = new Scanner(System.in);
        boolean confirmacion = false;
        int numGerente=0;
        do{
            confirmacion = false;

            try{
                System.out.println("Selecciona el gerente: ");
                numGerente=DatosComun.pedirNumero();

                if(numGerente<1||numGerente>Banco.personas.get(Rol.GERENTE).size()){
                    throw new IndexOutOfBoundsException("El dato ingresado está fuera del tamaño de la lista");
                }
                else{
                    return  numGerente;
                }

            }catch(IndexOutOfBoundsException error){
                System.out.println("Error: "+ error.getMessage());

            }
        }while(!confirmacion);
        sc.nextLine();
        return  numGerente;
    }
}
