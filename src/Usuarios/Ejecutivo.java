package Usuarios;

import Banco.Banco;
import Banco.utils.Generador;
import Usuarios.utils.DatosComun;
import Usuarios.utils.Rol;
import utils.UsuarioEnSesion;

import java.time.LocalDate;
import java.util.*;

public class Ejecutivo extends Empleado{
    private LocalDate fechaInicio;
    private String contrasenaSistema = "toychiquito";

private String contrasenaSecreta;

public Ejecutivo(String nombre, String apellidoPaterno, String apellidoMaterno, String ciudad, String estado, String curp, String direccion, int anioNacimiento, LocalDate fechaNacimiento, String RFC, String nombreUsuario, String contrasena, double salario){
    super(nombre, apellidoPaterno, apellidoMaterno, ciudad, estado, curp, direccion, anioNacimiento, fechaNacimiento, Rol.EJECUTIVO, RFC, nombreUsuario, contrasena, salario);
    fechaInicio = getFechaInicio();

}

    @Override
    public String toString(){
        return String.format("%s, Fecha inicio: %s", super.toString(),fechaInicio);
    }

    public static void registrarEjecutivo(Banco banco){
        Scanner sc = new Scanner(System.in);
        ArrayList<String> datosComun = DatosComun.registrarDatosComun(Rol.EJECUTIVO,banco);
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

        System.out.println("Ingrese salario");
        double salario = DatosComun.pedirValorDouble();
        //ocupo volver int el año de nacimiento y LocalDate la fecha de nacimiento
        int anioNacimientoint = Integer.parseInt(anioNacimiento);
        LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoCadena);

        Ejecutivo ejecutivo = new Ejecutivo(nombre, apellidoPaterno, apellidoMaterno, ciudad, estado, CURP, direccion, anioNacimientoint, fechaNacimiento, RFC, nombreUsuario, contrasena, salario);
        banco.personas.get(Rol.EJECUTIVO).add(ejecutivo);
        System.out.println(">Ejecutivo registrado<");
    }
    public static void mostrarEjecutivos(Banco banco){
        System.out.println("\nEjecutivos en el banco\n");
        if (banco.personas.get(Rol.EJECUTIVO).isEmpty()) {
            System.out.println("No hay ejecutivos registrados");
        } else {
            for(int i=0;i<banco.personas.get(Rol.EJECUTIVO).size();i++){
                Ejecutivo ejecutivo=(Ejecutivo)banco.personas.get(Rol.EJECUTIVO).get(i);
                System.out.println(ejecutivo.toString());
            }
        }
    }
    public static void modificarEjecutivo(Banco banco){
        Scanner sc=new Scanner(System.in);
        mostrarEjecutivos(banco);
        System.out.println("Selecciona el ejecutivo: ");
        int numEjecutivo = pedirEjecutivo(banco);
        int opt =10;
        do{
            System.out.println("¿Qué información deseas editar?");
            System.out.println("1) Nombre\n2) Apellidos \n3) Ciudad\n4) Estado\n5) Dirección\n6) Fecha de nacimiento\n 7)Contraseña\n 0)Salir/Regresar");
            opt=DatosComun.pedirNumero();
            Ejecutivo ejecutivo=(Ejecutivo) banco.personas.get(Rol.EJECUTIVO).get(numEjecutivo-1);
            switch (opt){
                case 1:
                    System.out.println("Ingrese el nuevo nombre: ");
                    ejecutivo.setNombre(DatosComun.pedirDatoString());
                    banco.personas.get(Rol.EJECUTIVO).set(numEjecutivo-1,ejecutivo);
                    String curpAntigua = ejecutivo.getCurp();
                    char sexo = curpAntigua.charAt(10);
                    String nuevacurp= Generador.generarCURP(ejecutivo.getNombre(), ejecutivo.getApellidoPaterno(), ejecutivo.getApellidoMaterno(), ejecutivo.getFechaNacimiento(), sexo, ejecutivo.getEstado());
                    String nuevorfc = Generador.generarRFC(ejecutivo.getNombre(), ejecutivo.getApellidoPaterno(), ejecutivo.getApellidoMaterno(), ejecutivo.getFechaNacimiento());
                    ejecutivo.setRFC(nuevorfc);
                    ejecutivo.setCurp(nuevacurp);
                    System.out.println("Nombre modificado");
                    break;

                case 2:
                    System.out.println("Ingrese el nuevo apellido Paterno: ");
                    ejecutivo.setApellidoPaterno(DatosComun.pedirDatoString());
                    System.out.println("Ingrese el nuevo apellido Materno: ");
                    ejecutivo.setApellidoMaterno(DatosComun.pedirDatoString());
                    String curpAntigua1 = ejecutivo.getCurp();
                    char sexo1 = curpAntigua1.charAt(10);
                    String nuevaCurp= Generador.generarCURP(ejecutivo.getNombre(), ejecutivo.getApellidoPaterno(), ejecutivo.getApellidoMaterno(), ejecutivo.getFechaNacimiento(), sexo1, ejecutivo.getEstado());
                    String nuevorfc1 = Generador.generarRFC(ejecutivo.getNombre(), ejecutivo.getApellidoPaterno(), ejecutivo.getApellidoMaterno(), ejecutivo.getFechaNacimiento());
                    ejecutivo.setRFC(nuevorfc1);
                    ejecutivo.setCurp(nuevaCurp);
                    banco.personas.get(Rol.EJECUTIVO).set(numEjecutivo-1,ejecutivo);
                    System.out.println("Apellido modificado");
                    break;

                case 3:
                    System.out.println("Ingrese nueva ciudad: ");
                    ejecutivo.setCiudad(DatosComun.pedirDatoString());
                    banco.personas.get(Rol.EJECUTIVO).set(numEjecutivo-1,ejecutivo);
                    System.out.println("Ciudad actualizada");
                    break;

                case 4:
                    System.out.println("Ingrese nuevo estado: ");
                    ejecutivo.setEstado(DatosComun.pedirDatoString());
                    banco.personas.get(Rol.EJECUTIVO).set(numEjecutivo-1,ejecutivo);
                    System.out.println("Estado actualizado");
                    break;

                case 5:
                    System.out.println("Ingrese nueva direccion: ");
                    ejecutivo.setDireccion(DatosComun.pedirDireccion());
                    banco.personas.get(Rol.EJECUTIVO).set(numEjecutivo-1,ejecutivo);
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
                    String curpNueva2= Generador.generarCURP(ejecutivo.getNombre(), ejecutivo.getApellidoPaterno(), ejecutivo.getApellidoMaterno(), ejecutivo.getFechaNacimiento(), sexo2, ejecutivo.getEstado());
                    String RFCNuevo2 = Generador.generarRFC(ejecutivo.getNombre(), ejecutivo.getApellidoPaterno(), ejecutivo.getApellidoMaterno(), ejecutivo.getFechaNacimiento());
                    ejecutivo.setRFC(RFCNuevo2);
                    ejecutivo.setCurp(curpNueva2);
                    System.out.println("Fecha Nacimiento Actualizada");
                    break;

                case 7 :
                    System.out.println("Ingrese nueva contraseña");
                    String nuevaContrasena = sc.nextLine();
                    ejecutivo.setContrasena(nuevaContrasena);
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
    public static void eliminarEjecutivo(Banco banco){
        Scanner sc=new Scanner(System.in);
        mostrarEjecutivos(banco);
        int numEjecutivo=0;
        boolean band;
        do {
            try {
                band=false;
                System.out.println("Selecciona el Ejecutivo que deseas eliminar");
                numEjecutivo = sc.nextInt();
                banco.personas.get(Rol.EJECUTIVO).get(numEjecutivo - 1);
            } catch (IndexOutOfBoundsException | InputMismatchException error) {
                System.out.println("Opcion no valida");
                band=true;
            }
            finally {
                sc.nextLine();
            }
        }while(band);
        System.out.println("Seleccionaste a: ");
        System.out.println(banco.personas.get(Rol.EJECUTIVO).get(numEjecutivo - 1).toString());
        int opcion = 0;
        boolean bandera;
        do {
            bandera = false;
            try {
                System.out.println("¿Deseas eliminarlo? 1) Sí, Otro número) Cancelar");
                opcion = sc.nextInt();
            }catch (InputMismatchException error) {
                System.out.println("Opción no valida");
                bandera = true;
                sc.nextLine();
                }
            } while (bandera);
        if (opcion == 1) {
            banco.personas.get(Rol.EJECUTIVO).remove(numEjecutivo - 1);
            System.out.println("Ejecutivo eliminado");
        }
        if (opcion != 1) {
            System.out.println("Se cancelo la eliminación");
        }
    }
    private static int pedirEjecutivo(Banco banco){
        Scanner sc = new Scanner(System.in);
        boolean confirmacion = false;
        int numEjecutivo=0;

        do{
            confirmacion = false;
            try{
                System.out.println("Selecciona el ejecutivo: ");
                numEjecutivo=DatosComun.pedirNumero();

                if(numEjecutivo<1||numEjecutivo>banco.personas.get(Rol.CAPTURISTA).size()){
                    throw new IndexOutOfBoundsException("El dato ingresado está fuera del tamaño de la lista");
                }
                else{
                    return  numEjecutivo;
                }
            }catch(IndexOutOfBoundsException error){
                System.out.println("Error: "+ error.getMessage());

            }
        }while(confirmacion);
        sc.nextLine();
        return  numEjecutivo;
    }



    public static void buscarEjecutivo(Banco banco){
        Scanner sc=new Scanner(System.in);
        System.out.println("Ingrese el nombre de usuario del Ejecutivo");
        String nombreUsuario=DatosComun.pedirDatoUsuario();
        boolean existe=false;
        for (Persona persona : banco.personas.get(Rol.EJECUTIVO)) {
            Ejecutivo ejecutivo = (Ejecutivo) persona;
            if(Objects.equals(ejecutivo.getNombreUsuario(), nombreUsuario)){
                System.out.println(ejecutivo.toString());
                existe=true;
            }
        }
        if (!existe)
            System.out.println("No se encontró este Ejecutivo");
    }
}

