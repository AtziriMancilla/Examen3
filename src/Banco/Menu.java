package Banco;

import Banco.Tarjetas.TarjetaDebito;
import Usuarios.Cliente;
import Usuarios.Persona;
import Usuarios.utils.DatosComun;
import Usuarios.utils.Rol;
import utils.UsuarioEnSesion;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
            //case CAPTURISTA -> mostrarMenuC();
            case CLIENTE -> mostrarMenuCliente();
            //case EJECUTIVO -> mostrarMenuEjecutivo();
            //case GERENTE -> mostrarMenuGerente();
            //case INVERSIONISTA -> mostrarMenuInversionista();
        }
    }

    private static void mostrarMenuCliente() {
        boolean flag = true;
        Scanner sc = new Scanner(System.in);
        int opcion;
        Cliente cliente = (Cliente)UsuarioEnSesion.getInstancia().getUsuarioActual();//Para obtener al cliente de la sesión.
        TarjetaDebito tarjetaDebito = cliente.getTarjetaDebito();//Como sólo es una cuenta de débito, se obtiene la tarjeta antes de la selección del usuario.
        System.out.println("\tMenú Cliente");
        System.out.println("\nBienvenido. Seleccione una opción:\n");
        System.out.println("1) Consultar cuenta de débito");
        System.out.println("2) Depositar a cuenta de débito");
        System.out.println("3) Retirar dinero");
        System.out.println("4) Consultar cuentas de crédito");
        System.out.println("5) Realizar solicitud de tarjeta de crédito");
        System.out.println("6) Revisar estatus de solicitud de tarjeta");
        System.out.println("7) Realizar compra con tarjeta de crédito");
        System.out.println("8) Realizar pago a tarjeta de crédito");
        System.out.println("9) Cerrar Sesión");
        do {
            try {
                System.out.print("Opción: ");
                opcion = sc.nextInt();
                sc.nextLine();
                switch (opcion) {
                    case 1:
                        System.out.println("\t Bienvenido a su cuenta");
                        System.out.println("Saldo de la cuenta: "+ tarjetaDebito.getSaldo());
                        System.out.println(tarjetaDebito.toString());//Obtener el resto de los datos de la tarjeta
                        break;
                    case 2:
                        cliente.depositarDebito();
                        break;
                    case 3:
                        cliente.retirarDebito(tarjetaDebito);
                        break;
                    case 4:
                        System.out.println("Este será el menú consulta crédito");
                        break;
                    case 5:
                        System.out.println("\tBienvenido\n");
                        Cliente.solicitarTarjetaCredito(cliente);
                        break;
                    case 6:
                        System.out.println("Este será el menú status de solicitud de tarjeta");
                        break;
                    case 7:
                        System.out.println("Este será el menú realizar compra con tarjeta");
                        break;
                    case 8:
                        System.out.println("Este será el menú realizar pago a tarjeta");
                        break;
                    case 9:
                        System.out.println("Cerrando Sesión...");
                        flag = false;
                        break;
                    default:
                        System.out.println("Error. Selecciona una opción válida\n");
                        break;
                }
            } catch (InputMismatchException error) {
                System.out.println("\nDatos no válidos. Intenta de nuevo.\n");
                sc.next();
            } catch (NullPointerException error) {
                System.out.println(error.getMessage());
                sc.next();
            } catch (Exception error) {
                System.out.println("Entrada inválida. Intenta de nuevo.");
                sc.next();
            }
        }while(flag);
    }

}
