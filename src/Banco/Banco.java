package Banco;

import Banco.utils.SolicitudTarjetaCredito;
import Banco.utils.TipoTarjetaCredito;
import Usuarios.*;
import Usuarios.utils.Inversion;
import Usuarios.utils.Rol;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Banco {
    public static final HashMap<Rol, ArrayList<Persona>> personas = new HashMap<>();
    public static final ArrayList<SolicitudTarjetaCredito> solicitudes = new ArrayList<>();
    public static final ArrayList<SolicitudTarjetaCredito> inversiones = new ArrayList<>();
    private String contrasenaSecreta = "uwu";
    public static final ArrayList<Inversion> inversion = new ArrayList<>();

    public Banco () {
        inicializarHashmap();
    }
    public void inicializarHashmap(){
        personas.put(Rol.GERENTE, new ArrayList<Persona>());
        personas.put(Rol.CLIENTE, new ArrayList<Persona>());
        personas.put(Rol.CAPTURISTA, new ArrayList<Persona>());
        personas.put(Rol.EJECUTIVO, new ArrayList<Persona>());
        personas.put(Rol.INVERSIONISTA, new ArrayList<Persona>());
    }
    public void agregarPersona(Persona persona, Rol rol){
        personas.put(rol, new ArrayList<Persona>());
        personas.get(rol).add(persona);
    }

    public Persona verificarInicioSesion(String usuario, String contrasena) {

        for (Map.Entry<Rol, ArrayList<Persona> > entry : personas.entrySet()){
            ArrayList<Persona> listaPersonas = entry.getValue();
            for(Persona personaActual : listaPersonas){
                if(personaActual.getNombre().equals(usuario)&& personaActual.getContrasena().equals(contrasena)){
                    return personaActual;
                }
            }
        }
        return null;
    }


    public static boolean verificarContrasenaSecreta(){

        Scanner sc = new Scanner(System.in);
        boolean contrasenaCoincide = false;
        String contrasenaIngresada = "";
        int contador =0;
        do{
            System.out.println("Ingrese contraseña secreta");
            contrasenaIngresada = sc.nextLine();
            if (contrasenaIngresada.equals("uwu")){
                contrasenaCoincide = true;
            }
            else {
                contador ++;
            }
            if (contador>=3){
                System.out.println("Alcanzó máximo de intentos");
                break;
            }
        }while(!contrasenaCoincide);
        return contrasenaCoincide;
    }

    //Crear personas
    public static void registrarCapturista(){
        Capturista.registrarCapturista();}
    public static void registrarCliente(){
        Cliente.registrarCliente();
    }
    //Nota: quizá borrarlo definitivamente//public void registrarGerente(){Gerente.registrarGerente();}
    public static void registrarEjecutivo(){Ejecutivo.registrarEjecutivo();}
    public static void registrarInversionista(){Inversionista.registrarInversionista();}

    //mostrar
    public static void mostrarCapturistas() {
        Capturista.mostrarCapturistas();
    }
    public static void mostrarClientes() {
        Cliente.mostrarClientes();
    }
    public static void mostrarEjecutivos() {Ejecutivo.mostrarEjecutivos();}

    public void mostrarGerente(){
        Gerente.mostrarGerente();
    }
    public static void mostrarInversionistas() {Inversionista.mostrarInversionistas();}

    //Eliminar
    public static void borrarCapturista(){
        Capturista.borrarCapturista();
    }
    public static void borrarCliente(){Cliente.borrarCliente();}
    public static void eliminarEjecutivo(){
        Ejecutivo.eliminarEjecutivo();
    }
    public static void eliminarInversionista(){
        Inversionista.eliminarInversionista();
    }

    //modificar
    public static void modificarCapturista(){Capturista.modificarCapturista();}
    public static void modificarCliente(){
        Cliente.modificarCliente();}
    public static void modificarEjecutivo(){Ejecutivo.modificarEjecutivo();}
    public void modificarGerente(){Gerente.modificarGerente();}
    public static void modificarInversionista(){Inversionista.modificarInversionista();}

    //Me imagino que también los metodos para las inversiones
    public void realizarInversion(Inversionista inversionista){Inversion.realizarInversion(inversionista);
    }
    public static void mostrarInversiones(){Inversion.mostrarInversiones();
    }
    //buscar
    public static void buscarEjecutivo() {
        Ejecutivo.buscarEjecutivo();
    }
    public static void buscarCliente() {
        Ejecutivo.buscarEjecutivo();
    }

}
