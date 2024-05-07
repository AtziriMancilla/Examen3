package Usuarios;

import Banco.Banco;
import Banco.utils.Generador;
import Usuarios.utils.DatosComun;
import Usuarios.utils.Rol;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Gerente extends Empleado{
    public String contrasenaSistema;

    private LocalDate fechaInicio;
    public Gerente(String nombre, String apellidoPaterno, String apellidoMaterno, String ciudad, String estado, String curp, String direccion, int anioNacimiento, LocalDate fechaNacimiento, String RFC, String contrasena,  double salario, String contrasenaSistema){
            super(nombre, apellidoPaterno, apellidoMaterno, ciudad, estado, curp, direccion, anioNacimiento, fechaNacimiento, Rol.GERENTE, RFC, contrasena, salario);
            this.contrasenaSistema= contrasenaSistema;
             fechaInicio = LocalDate.now();
    }

    @Override
    public String toString(){
        return String.format("ID: %d,Nombre: %s, Apellido: %s %s, Ciudad: %s, Estado: %s,Curp: %s, Direccion: %s, Año nacimiento: %d, RFC: %s, Fecha de inicio: , salario: %f", super.toString()); //me falta la fecha en formato
    }

    public static void modificarGerente(){
        Scanner sc=new Scanner(System.in);
        mostrarGerente();
        System.out.println("Selecciona el gerente: ");
        int numGerente=sc.nextInt();
        System.out.println("¿Qué información deseas editar?");
        System.out.println("1) Nombre\n2) Apellidos \n3) Ciudad\n4) Estado\n5) Dirección\n6) Fecha de nacimiento 7)Contraseña");
        int opcion=sc.nextInt();
        Gerente gerente=(Gerente) Banco.personas.get(Rol.GERENTE).get(numGerente-1);
        switch (opcion){
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
                gerente.setDireccion(sc.nextLine());
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
                Generador.generarCURP(gerente.getNombre(), gerente.getApellidoPaterno(), gerente.getApellidoMaterno(), gerente.getFechaNacimiento(), sexo2, gerente.getEstado());
                String RFCNuevo = Generador.generarRFC(gerente.getNombre(), gerente.getApellidoPaterno(), gerente.getApellidoMaterno(), gerente.getFechaNacimiento());
                gerente.setRFC(RFCNuevo);
                System.out.println("Fecha Nacimiento Actualizada");
                break;
            case 7 :
                System.out.println("Ingrese nueva contraseña");
                String nuevaContrasena = sc.nextLine();
                gerente.setContrasena(nuevaContrasena);
                System.out.println("Contrasena Actualizada");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + opcion);

        }

    }

    public static void mostrarGerente(){
        System.out.println("\nGerente en el banco\n"+ Banco.personas.get(Rol.GERENTE).getFirst().toString());
    }
}
