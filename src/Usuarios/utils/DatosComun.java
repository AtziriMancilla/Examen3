package Usuarios.utils;

import Banco.utils.Generador;
import Usuarios.Persona;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DatosComun {

    public static ArrayList<String> registrarDatosComun(Rol rol) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> datosComun = new ArrayList<>();

        String rolActual = rol == Rol.CLIENTE ? "Cliente" : rol == Rol.CAPTURISTA ? "Capturista" : rol == Rol.EJECUTIVO ? "Ejecutivo" : "Inversionista";
        System.out.printf("\nRegistrar %s\n", rolActual);
        System.out.println("Ingrese Nombre ");
        String nombre = pedirDatoString();

        System.out.println("Ingrese apellido Paterno ");
        String apellidoPaterno = pedirDatoString();

        System.out.println("Ingrese apellido Materno");
        String apellidoMaterno = pedirDatoString();

        System.out.println("Ingrese Ciudad");
        String ciudad = pedirDatoString();

        System.out.println("Ingrese Estado");
        String estado = pedirDatoString();

        System.out.println("Ingrese Dirección");
        String direccion = pedirDireccion();

        System.out.println("Ingrese fecha de Nacimiento");
        LocalDate fechaNacimiento = obtenerFechaNacimiento();
        //anioNacimiento
        int anioNacimiento = obteneranioNacimiento(fechaNacimiento);
        //obtener el sexo para la curp
        char sexo = obtenerSexo();

        String curp = Generador.generarCURP(nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, sexo, estado);
        //Aquí debería ir el RFC
        String RFC = Generador.generarRFC(nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento);
        System.out.print("Ingresa el nombre de usuario: ");
        String nombreUsuario=sc.nextLine();
        System.out.println("Ingresa la contraseña");
        String contrasena = sc.nextLine(); //aqui no se si vamos a meter excepciones de algun tipo

        datosComun.addAll(Arrays.asList(nombre, apellidoPaterno, apellidoMaterno, ciudad, estado, curp, direccion, String.valueOf(anioNacimiento), String.valueOf(fechaNacimiento), RFC, nombreUsuario, contrasena));//falta meter sucursal

        return datosComun;

    }

    public static LocalDate obtenerFechaNacimiento() {
        Scanner scanner = new Scanner(System.in);
        int dia, mes, anio;
        do {
            System.out.println("Ingrese el día(1-31)");
            dia = pedirNumero();
            if (dia < 1 || dia > 31)
                System.out.println("Dia no valido");
        }
        while (dia < 1 || dia > 31);
        do {
            System.out.println("Ingrese el mes(1-12)");
            mes = pedirNumero();
            if (mes < 1 || mes > 12)
                System.out.println("Mes no valido");
        }
        while (mes < 1 || mes > 12);
        do {
            System.out.println("Ingrese el año");
            anio = pedirNumero();
            if (anio < 1900 || anio > 2024)
                System.out.println("Año no valido");
        }
        while (anio < 1900 || anio > 2024);
        return LocalDate.of(anio, mes, dia);
    }

    private static String FechaMostrar(LocalDate fechaNacimiento) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        String fechaFormateada = fechaNacimiento.format(pattern);
        return fechaFormateada;
    }

    private static int obteneranioNacimiento(LocalDate fechaNacimiento) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("YYYY");
        String anioFormateado = fechaNacimiento.format(pattern);
        int anioNacimientoEntero = Integer.parseInt(anioFormateado);
        return anioNacimientoEntero;
    }

    private static char obtenerSexo() {
        Scanner sc = new Scanner(System.in);
        char sexo = 'a';
        boolean validacion = false;

        do {
            System.out.println("Ingrese Sexo ('H'/'M'): ");
            sexo = sc.next().charAt(0);
            sexo = Character.toUpperCase(sexo);
            validacion = validarSexo(sexo);
        }
        while (!validacion);

        return sexo;
    }

    private static boolean validarSexo(char sexo) {
        boolean band = false;
        if (sexo == 'H' || sexo == 'M') {
            band = true;
            return band;
        } else {
            System.out.println("Información no válida. Intente de nuevo.");
            return band;
        }
    }

    public static String pedirDatoString() { //No permite ni signos .,%. Acepta minimo una letra. No acepta numeros
        Scanner sc = new Scanner(System.in);
        String dato = "a";

        boolean error = false;

        do {
            error = false;

            try {
                System.out.print("Ingrese dato: ");
                dato = sc.nextLine();

                if (dato == null || dato.trim().isEmpty()) {
                    throw new IllegalArgumentException("El dato no puede estar vacío");
                }

                if (!dato.matches("[a-ñ-zA-Ñ-Z]+")) {
                    throw new IllegalArgumentException("El dato solo puede contener letras.");
                }

                if (dato.length()<2){
                    throw new IllegalArgumentException("Error: solo ingresaste un carácter");
                }
                else{
                    return dato;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                error = true;


            } catch (Exception e) {
                System.out.println("Error. Intente de nuevo. ");
                error = true;

            }

        } while (error);
        sc.nextLine();
        return dato;
    }
    public static String pedirDireccion(){
        Scanner sc = new Scanner(System.in);
        String direccion ="";
        System.out.println("Ingrese calle");
        String calle = pedirDatoString();
        System.out.println("Ingrese numero");
        int numero = pedirNumero();
        String numeroLegible = String.valueOf(numero);
        System.out.println("Ingrese colonia");
        String colonia =pedirDatoString();
        System.out.println("Ingrese código postal");
        int codigoPostal = pedirNumero();
        String codigoPostalLegible = String.valueOf(codigoPostal);
        direccion = "Calle: "+calle+" No."+numeroLegible+colonia+" Col."+colonia+" C.P. "+codigoPostalLegible;
        return direccion;
    }

    public static int pedirNumero(){ //para valores enteros
        Scanner sc = new Scanner(System.in);
        int numero=-1;
        boolean comprobacion = false;
        do {
            comprobacion = false;

            try {
                System.out.print("Ingresa dato: ");
                String input = sc.nextLine();

                if (input.isEmpty()) {
                    throw new IllegalArgumentException("No ha ingresado ningún número.");
                }

                numero = Integer.parseInt(input);

                if(numero<0){
                    throw new NumberFormatException("No puedes ingresar un valor negativo");
                }
                else{
                    return numero;
                }

            } catch(IllegalArgumentException error){
                System.out.println("Error: "+error.getMessage());
                comprobacion=true;
            }
            catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número entero.");
                comprobacion=true;
            }
            catch (Exception error) {
                System.out.println("Error. Intente de nuevo. ");
                comprobacion=true;
            }

        } while (comprobacion);
        sc.nextLine();
        return numero;
    }

    public static double pedirValorDouble(){
        Scanner sc = new Scanner(System.in);
        double valorDouble=-1;
        boolean comprobacion = false;
        do {
            comprobacion = false;
            try {
                System.out.print("Ingresa dato: ");
                String input = sc.nextLine();
                if (input.isEmpty()) {
                    throw new IllegalArgumentException("No ha ingresado ningún número.");
                }

                valorDouble = Double.parseDouble(input);

                if(valorDouble<0){
                    throw new NumberFormatException("No puedes ingresar un valor negativo");
                }
                else{
                    return valorDouble;
                }

            } catch(IllegalArgumentException error){
                System.out.println("Error: "+error.getMessage());
                comprobacion=true;
            }
            catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número válido.");
                comprobacion=true;
            }
            catch (Exception error) {
                System.out.println("Error. Intente de nuevo. ");
                comprobacion=true;
            }

        } while (comprobacion);
        sc.nextLine();
        return valorDouble;
    }
    public static void pedirOpcionValida(ArrayList<Persona> listaUsuarios){

    }
}

