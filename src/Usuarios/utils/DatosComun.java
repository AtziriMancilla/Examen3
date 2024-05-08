package Usuarios.utils;

import Banco.utils.Generador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DatosComun {

    public static ArrayList<String> registrarDatosComun(Rol rol){
        Scanner sc = new Scanner(System.in);
        ArrayList<String> datosComun = new ArrayList<>();

        String rolActual = rol == Rol.CLIENTE? "Cliente": rol == Rol.CAPTURISTA? "Capturista" : rol == Rol.EJECUTIVO? "Ejecutivo" :  "Inversionista";
        System.out.printf("\nRegistrar %s\n", rolActual);

        System.out.println("Ingrese nombre");
        String nombre = sc.nextLine();

        System.out.println("Ingrese apellido Paterno");
        String apellidoPaterno = sc.nextLine();

        System.out.println("Ingrese apellido Materno");
        String apellidoMaterno = sc.nextLine();

        System.out.println("Ingrese Ciudad");
        String ciudad = sc.nextLine();

        System.out.println("Ingrese Estado");
        String estado = sc.nextLine();

        /////Aqui debería ir curp


        System.out.println("Ingrese Dirección");
        String direccion = sc.nextLine();

        System.out.println("Ingrese fecha de Nacimiento");
        LocalDate fechaNacimiento = obtenerFechaNacimiento();
        String fechaNacimientoCadena = FechaMostrar(fechaNacimiento);
        //anioNacimiento
        int anioNacimiento = obteneranioNacimiento(fechaNacimiento);
        //obtener el sexo para la curp
        char sexo = obtenerSexo();
        boolean comprobacion = validarSexo(sexo);
        if (!comprobacion){
             obtenerSexo();
        }

            String curp = Generador.generarCURP(nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, sexo, estado);
            //Aquí debería ir el RFC
            String RFC = Generador.generarRFC(nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento);

            System.out.println("Ingresa la contraseña");
            String contrasena = sc.nextLine();

            datosComun.addAll(Arrays.asList(nombre, apellidoPaterno, apellidoMaterno, ciudad, estado, curp, direccion, String.valueOf(fechaNacimiento), RFC, contrasena, String.valueOf(anioNacimiento)));

            return datosComun;

    }
    public static LocalDate obtenerFechaNacimiento() {
        Scanner scanner = new Scanner(System.in);
        int dia, mes, anio;
        do {
            System.out.println("Ingrese el día");
            dia = scanner.nextInt();
            if(dia < 1 || dia > 31)
                System.out.println("Dia no valido");
        }
        while (dia < 1 || dia > 31) ;
        do {
            System.out.println("Ingrese el mes");
            mes = scanner.nextInt();
            if(mes < 1 || mes > 12)
                System.out.println("Mes no valido");
        }
        while (mes < 1 || mes > 12) ;
        do {
            System.out.println("Ingrese el año");
            anio = scanner.nextInt();
            if(anio < 1900 || anio > 2024)
                System.out.println("Año no valido");
        }
        while (anio < 1900 || anio > 2024) ;
        return LocalDate.of(anio,mes,dia);
    }

    private static String FechaMostrar (LocalDate fechaNacimiento){
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        String fechaFormateada = fechaNacimiento.format(pattern);
        return fechaFormateada;
    }
    private static int obteneranioNacimiento (LocalDate fechaNacimiento){
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("YYYY");
        String anioFormateado = fechaNacimiento.format(pattern);
        int anioNacimientoEntero = Integer.parseInt(anioFormateado);
        return anioNacimientoEntero;
    }
    private static char obtenerSexo (){
        Scanner sc = new Scanner(System.in);
        char sexo='a';
        System.out.println("Ingrese Sexo: ");
        System.out.println("1: hombre  2: mujer");
        int eleccion = sc.nextInt();
        if (eleccion ==1){
            sexo = 'H';
            return sexo;
        } else if (eleccion==2) {
            sexo = 'M';
            return sexo;
        }
        else System.out.println("entrada no válida");
        return sexo;
    }
    private static  boolean validarSexo(char sexo){
        boolean band = false;
        if(sexo=='H'|| sexo=='M'){
            band = true;
            return band;
        }
        else return band;
    }
}
