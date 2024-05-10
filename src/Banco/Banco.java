package Banco;

import Banco.utils.SolicitudTarjetaCredito;
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

    public Banco (){
        inicializarHashmap();
        Inversionista inversionista=new Inversionista("Alejandro","Montejano","Diaz","Morelia","Michoacan","MODA133545","Calle desconocida",2004, LocalDate.now(),"Moda904803","Mario bros","1234");
        personas.get(Rol.INVERSIONISTA).add(inversionista);
        Cliente cliente=new Cliente("Alejandro","Montejano","Diaz","Morelia","Michoacan","MODA133545","Calle desconocida",2004, LocalDate.now(),"Moda904803","Mario bros","1234");
        personas.get(Rol.CLIENTE).add(cliente);
        //cliente.realizarDeposito(600000);
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
    public void registrarCapturista(){
        Capturista.registrarCapturista();}
    public void registrarCliente(){
        Cliente.registrarCliente();
    }
    //Nota: quizá borrarlo definitivamente//public void registrarGerente(){Gerente.registrarGerente();}
    public void registrarEjecutivo(){Ejecutivo.registrarEjecutivo();}
    public void registrarInversionista(){Inversionista.registrarInversionista();}

    //mostrar
    public void mostrarCapturistas() {
        Capturista.mostrarCapturistas();
    }
    public void mostrarClientes() {
        Cliente.mostrarClientes();
    }
    public void mostrarEjecutivos() {Ejecutivo.mostrarEjecutivos();}

    public void mostrarGerente(){
        Gerente.mostrarGerente();
    }
    public void mostrarInversionistas() {Inversionista.mostrarInversionistas();}

    //Eliminar
    public void borrarCapturista(){
        Capturista.borrarCapturista();
    }
    public void borrarCliente(){Cliente.borrarCliente();}
    public void borrarEjecutivo(){
        Ejecutivo.eliminarEjecutivo();
    }
    public void borrarInversionista(){
        Inversionista.eliminarInversionista();
    }

    //modificar
    public void modificarCapturista(){Capturista.modificarCapturista();}
    public void modificarCliente(){
        Cliente.modificarCliente();}
    public void modificarEjecutivo(){Ejecutivo.modificarEjecutivo();}
    public void modificarGerente(){Gerente.modificarGerente();}
    public void modificarInversionista(){Inversionista.modificarInversionista();}

    //Me imagino que también los metodos para las inversiones
    public void realizarInversion(Inversionista inversionista){Inversion.realizarInversion(inversionista);
    }
    public void mostrarInversiones(){Inversion.mostrarInversiones();
    }

}
