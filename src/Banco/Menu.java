package Banco;

import Usuarios.Persona;
import Usuarios.utils.DatosComun;
import Usuarios.utils.Rol;
import utils.UsuarioEnSesion;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Menu {

    private final Banco bancoMadero = new Banco();
    private final Banco bancoAcueducto = new Banco();
    public void seleccionarBanco(){
        Scanner sc = new Scanner(System.in);
        int sucursal =0;
        boolean band;
        do{
            band=false;
            System.out.println(">>Bienvenido al Banco<<");
            System.out.println("Por favor elija la sucursal");
            System.out.println("1. Madero \t\t 2. Acueducto");
            sucursal = DatosComun.pedirNumero();
            if(sucursal==1){
                iniciarSesion(bancoMadero);
            }
            if(sucursal==2){
               iniciarSesion( bancoAcueducto);
            }
            else{
                System.out.println("Intenta de nuevo");
                band=true;
            }
        }while(band);
    }


    public static void iniciarSesion(Banco banco) {
        Scanner sc = new Scanner(System.in);
        boolean datosCorrectos = false;
        do {
            System.out.println("BIENVENIDO AL SISTEMA DEL BANC0");
            System.out.println("Inicia sesión para continuar");
            System.out.println("Ingresa tu usuario: ");
            String usuario = sc.nextLine();

            System.out.println("Ingresa tu contrasena: ");
            String contrasena = sc.nextLine();

            Persona personaActual=banco.verificarInicioSesion(usuario, contrasena);

            if (personaActual!= null) {
                UsuarioEnSesion.getInstancia().setUsuario(personaActual);
                datosCorrectos = true;
                seleccionarMenu();
            } else {

                System.out.println("Usuario o contrasena incorrectos. Intenta de nuevo.");
            }
        } while (!datosCorrectos);
    }
    private static void seleccionarMenu() {
        switch (UsuarioEnSesion.getInstancia().getUsuarioActual().getRol()) {
            //case CAPTURISTA -> mostrarMenuCliente();
            //case CLIENTE -> mostrarMenuTrabajador();
            //case EJECUTIVO -> mostrarMenuEjecutivo();
            //case GERENTE -> mostrarMenuGerente();
            //case INVERSIONISTA -> mostrarMenuInversionista();
        }
    }

//    private Banco bancoMadero = new Banco();
//    private Banco bancoAcueducto = new Banco();
//    public void discernirElBanco(){
//        do{
//            System.out.println(">>Bienvenido al Banco<<");
//            System.out.println("Por favor elija la sucursal");
//            System.out.println("1. Madero \t\t 2. Acueducto");
//            int sucursal =
//        }while();
//
//    }
//    public Persona verificarInicioSesion(String usuario, String contrasena) {
//        for (Map.Entry<Rol, ArrayList<Persona>> entry : usuario.ent()){
//            ArrayList<Persona> listaUsuarios = entry.getValue();
//            for(Persona usuarioActual : listaUsuarios){
//                if(usuarioActual.getNombre().equals(usuario)&& usuarioActual.getContrasena().equals(contrasena)){
//                    return usuarioActual;
//                }
//            }
//        }
//        return null;
//    }

}
