package Banco;

import Banco.Tarjetas.TarjetaDebito;
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
    public HashMap<Rol, ArrayList<Persona>> personas;
    public ArrayList<SolicitudTarjetaCredito> solicitudes;


    public Banco () {
        personas = new HashMap<>();
        solicitudes = new ArrayList<>();
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
                if(personaActual.getNombreUsuario().equals(usuario)&& personaActual.getContrasena().equals(contrasena)){
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
        Capturista.registrarCapturista(this);}
    public void registrarCliente(){
        Cliente.registrarCliente(this);
    }
    //Nota: quizá borrarlo definitivamente//public void registrarGerente(){Gerente.registrarGerente();}
    public void registrarEjecutivo(){Ejecutivo.registrarEjecutivo(this);}
    public void registrarInversionista(){Inversionista.registrarInversionista(this);}

    //mostrar
    public void mostrarCapturistas() {
        Capturista.mostrarCapturistas(this);
    }
    public void mostrarClientes() {
        Cliente.mostrarClientes(this);
    }

    public void mostrarEjecutivos() {Ejecutivo.mostrarEjecutivos(this);}

    public void mostrarGerente(){
        Gerente.mostrarGerente(this);
    }
    public void mostrarInversionistas() {Inversionista.mostrarInversionistas(this);}

    //Eliminar
    public void borrarCapturista(){
        Capturista.borrarCapturista(this);
    }
    public void borrarCliente(){Cliente.borrarCliente(this);}
    public void eliminarEjecutivo(){
        Ejecutivo.eliminarEjecutivo(this);
    }
    public void eliminarInversionista(){
        Inversionista.eliminarInversionista(this);
    }

    //modificar
    public void modificarCapturista(){Capturista.modificarCapturista(this);}
    public void modificarCliente(){
        Cliente.modificarCliente(this);}
    public void modificarEjecutivo(){Ejecutivo.modificarEjecutivo(this);}
    public void modificarGerente(){Gerente.modificarGerente(this);}
    public void modificarInversionista(){Inversionista.modificarInversionista(this);}

    //Me imagino que también los metodos para las inversiones
    public void realizarInversion(Inversionista inversionista){inversionista.realizarInversion();
    }
    public void mostrarMisInversiones(Inversionista inversionista){inversionista.mostrarMisInversiones();
    }
    //buscar
    public void buscarEjecutivo() {
        Ejecutivo.buscarEjecutivo(this);
    }
    public void buscarCliente() {
        Ejecutivo.buscarEjecutivo(this);
    }
    public void verSolicitudes(){
        SolicitudTarjetaCredito.verSolicitudes(this);
    }
    public void procesarSolicitudes(){
        SolicitudTarjetaCredito.procesarSolicitudes(this);
    }
    public void mostrarInversiones() {
        Inversion.mostrarInversiones(this);
    }
    //Métodos Jafet
    public void consultarCuentaDebito (TarjetaDebito tarjetaDebito) {
        tarjetaDebito.consultarCuenta();
    }
    public void depositarDebito(TarjetaDebito tarjetaDebito) {
        tarjetaDebito.depositoDebito();
    }
    public void retirarDebito(TarjetaDebito tarjetaDebito) {
        tarjetaDebito.retiroDebito();
    }
    public void comprarDebito(TarjetaDebito tarjetaDebito) {
        tarjetaDebito.compraDebito();
    }
    public void consultarCuentasCredito(Cliente cliente) {
        cliente.consultarCuentasCredito();
    }
    public void verTarjetas(Cliente cliente) {
        cliente.verTodasLasTarjetas();
    }
    public void solicitudDeTarjetaCredito(Cliente cliente) {
        cliente.solicitudTarjetaCredito(this);
    }
    public void revisarSolicitudCredito(Cliente cliente) {
        cliente.revisarStatusSolicitud(this);
    }
    public void compraTarjetaCredito(Cliente cliente) {
        cliente.realizarCompraCredito();
    }
    public void pagoTarjetaCredito(Cliente cliente) {
        cliente.realizarPagoCredito();
    }
}
