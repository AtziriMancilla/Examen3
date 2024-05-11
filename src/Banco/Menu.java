package Banco;

import Banco.Tarjetas.TarjetaCredito;
import Banco.Tarjetas.TarjetaDebito;
import Banco.utils.SolicitudTarjetaCredito;
import Usuarios.*;
import Usuarios.utils.DatosComun;
import Usuarios.utils.Inversion;
import utils.UsuarioEnSesion;

import java.util.*;

public class Menu {

    private static final Banco bancoMadero = new Banco();
    private static final Banco bancoAcueducto = new Banco();
    public static void seleccionarBanco(){
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
            case CAPTURISTA -> mostrarMenuCapturista();
            case CLIENTE -> mostrarMenuCliente();
            case EJECUTIVO -> mostrarMenuEjecutivo();
            case GERENTE -> mostrarMenuGerente();
            case INVERSIONISTA -> mostrarMenuInversionista();
        }
    }

    private static void mostrarMenuCliente() {
        boolean flag = true;
        int opcion;
        Cliente cliente = (Cliente)UsuarioEnSesion.getInstancia().getUsuarioActual();//Para obtener al cliente de la sesión.
        TarjetaDebito tarjetaDebito = cliente.getTarjetaDebito();//Se obtiene la tarjeta de débito del usuario para sus futuras operaciones.
        System.out.println("\tMenú Cliente");
        System.out.println("\nBienvenido. Seleccione una opción:\n");
        System.out.println("1) Consultar cuenta de débito");
        System.out.println("2) Depositar a cuenta de débito");
        System.out.println("3) Retirar dinero");
        System.out.println("4) Comprar con tarjeta de débito");
        System.out.println("5) Consultar cuentas de crédito");
        System.out.println("6) Mostrar tarjetas de débito y crédito");
        System.out.println("7) Realizar solicitud de tarjeta de crédito");
        System.out.println("8) Revisar estatus de solicitud de tarjeta");
        System.out.println("9) Realizar compra con tarjeta de crédito");
        System.out.println("10) Realizar pago a tarjeta de crédito");
        System.out.println("0) Cerrar Sesión");
        do {
            System.out.print("Opción: ");
            opcion = DatosComun.pedirNumero();
            switch (opcion) {
                case 1:
                    System.out.println("Saldo de la cuenta: " + tarjetaDebito.getSaldo());
                    System.out.println(tarjetaDebito.toString());//Obtener el resto de los datos de la tarjeta
                    break;
                case 2:
                    tarjetaDebito.depositoDebito();
                    break;
                case 3:
                    tarjetaDebito.retirarDebito();
                    break;
                case 4:
                    tarjetaDebito.comprarDebito();
                    break;
                case 5:
                    int card = 1;
                    if(cliente.getTarjetasCredito()!=null) {
                        for(TarjetaCredito tarjetaCredito: cliente.getTarjetasCredito()){
                            System.out.printf("\n%d) %s\n", card, tarjetaCredito.toString());
                            card++;
                        }
                    }
                    if(cliente.getTarjetasCredito()==null) System.out.println("No tienes tarjetas de crédito.");
                    break;
                case 6:
                    cliente.verTodasLasTarjetas();
                    break;
                case 7:
                    System.out.println("\tBienvenido\n");
                    if (cliente.getTarjetasCredito().size()<3) {//Validación de la cantidad de tarjetas del cliente antes de realizar solicitud.
                        if(cliente.getNumeroSolicitudesEnProceso()==0) Cliente.solicitarTarjetaCredito(cliente);
                        if(cliente.getNumeroSolicitudesEnProceso()==1) System.out.println("Ya tienes una solicitud en curso. Debes esperar a que termine el proceso para poder hacer una nueva solicitud.");
                    }
                    if (cliente.getTarjetasCredito().size()==3) System.out.println("No puedes solicitar más tarjetas. Límite máximo alcanzado.");
                    break;
                case 8://El ciclo buscará la solicitud hecha por el cliente y le mostrará el status.
                    System.out.println("Solicitud en curso:");
                    boolean solicitudEncontrada = false;//Determina si se encontró una solicitud pendiente del cliente.
                    List<SolicitudTarjetaCredito> solicitudAEliminar = new ArrayList<>();//Lista que almacenará la solicitud a eliminar.
                    for(SolicitudTarjetaCredito solicitud : Banco.solicitudes) {
                        if (solicitud.getCliente() == cliente){
                            solicitudEncontrada = true;
                            System.out.println("Tarjeta solicitada: "+ solicitud.getTipoTarjeta());
                            System.out.println("Status: "+solicitud.getStatus());
                            if(solicitud.getStatus().equals("Solicitud Aprobada")||solicitud.getStatus().equals("Solicitud Rechazada")){//Revisión del status
                                System.out.println("Eliminando solicitud del historial...");
                                cliente.setNumeroSolicitudesEnProceso(0);//Una vez que el cliente consulte el estatus, si la solicitud ya fue atendida, su contador pasa a 0.
                                solicitudAEliminar.add(solicitud);//Añade la solicitud para luego eliminarla de la lista del Banco.
                            }
                        }
                    }
                    Banco.solicitudes.removeAll(solicitudAEliminar);//Una vez consultada y atendida la solicitud, se elimina del Banco.
                    if(!solicitudEncontrada) System.out.println("No tienes solicitudes pendientes.");
                case 9:
                    int i=1,seleccion,contadorTarjetas=0;//Contador i para las opciones
                    boolean select = true;
                    if (cliente.getTarjetasCredito()!=null) {
                        System.out.println("\tSelecciona la tarjeta a usar");
                        System.out.println("\nTarjetas de Crédito Disponibles");
                        for(TarjetaCredito tarjetaCredito:cliente.getTarjetasCredito()) {
                            if (tarjetaCredito.getSaldoPendiente() > 0) {
                                System.out.printf("\n%d) Tarjeta %s\n", i, tarjetaCredito.getTipoCredito());
                                System.out.printf("Crédito Máximo: %f\n", tarjetaCredito.getCreditoMaximo());
                                i++;
                            }//Este contador permite registrar cuántas tarjetas están sin saldo pendiente.
                            if (tarjetaCredito.getSaldoPendiente() == 0) {
                                contadorTarjetas++;
                            }
                        }
                        if (contadorTarjetas<cliente.getTarjetasCredito().size()) {
                            do {
                                seleccion = DatosComun.pedirNumero();
                                if (seleccion<1||seleccion>i) {
                                    System.out.println("Error. Selecciona una opción válida");
                                }else {
                                    seleccion-=1;
                                    TarjetaCredito tarjeta = cliente.getTarjetasCredito().get(seleccion);
                                    tarjeta.comprarCredito();
                                    select = false;
                                }
                            } while (select);
                        }
                        if (contadorTarjetas==cliente.getTarjetasCredito().size()) {
                            System.out.println("\nNo tienes tarjetas de crédito disponibles");
                        }
                    }
                    if (cliente.getTarjetasCredito()==null) System.out.println("\nNo tienes tarjetas de crédito");
                    break;
                case 10:
                    int k=1,opcionPago,contadorTc=0;//Contador i para las opciones
                    boolean pagoValido = true;
                    if (cliente.getTarjetasCredito()!=null) {
                        System.out.println("\tSelecciona la tarjeta a pagar");
                        System.out.println("\nTarjetas de Crédito");
                        for(TarjetaCredito tarjetaCredito:cliente.getTarjetasCredito()) {
                            if (tarjetaCredito.getSaldoPendiente() > 0) {
                                System.out.printf("\n%d) Tarjeta %s\n", k, tarjetaCredito.getTipoCredito());
                                System.out.printf("Saldo pendiente por pagar: %f", tarjetaCredito.getSaldoPendiente());
                                k++;
                            }//Este contador permite registrar cuántas tarjetas están sin saldo pendiente.
                            if (tarjetaCredito.getSaldoPendiente() == 0) {
                                contadorTc++;
                            }
                        }
                        if (contadorTc<cliente.getTarjetasCredito().size()) {
                            do {
                                opcionPago = DatosComun.pedirNumero();
                                if (opcionPago<1||opcionPago>k) {
                                    System.out.println("Error. Selecciona una opción válida");
                                }else {
                                    opcionPago-=1;
                                    TarjetaCredito tarjeta = cliente.getTarjetasCredito().get(opcionPago);
                                    tarjeta.pagarTarjeta();
                                    pagoValido = false;
                                }
                            } while (pagoValido);
                        }//Si no hay tarjetas pendientes de pago, se arroja el siguiente mensaje
                        if (contadorTc==cliente.getTarjetasCredito().size()) {
                            System.out.println("\nNo tienes tarjetas pendientes de pago");
                        }
                    }
                    if (cliente.getTarjetasCredito()==null) System.out.println("\nNo tienes tarjetas de crédito");
                    break;
                case 0:
                    System.out.println("Cerrando Sesión...");
                    flag = false;
                    break;
                default:
                    System.out.println("Error. Selecciona una opción válida\n");
                    break;
            }
        }while(flag);
    }

    private static void mostrarMenuCapturista(){
        Scanner scanner = new Scanner(System.in);
        int opcion = 10;

        do {
            System.out.println("\nMenú Capturista");
            System.out.println("Selecciona una opción para continuar");
            System.out.println("1. Registrar Ejecutivo de Cuenta");
            System.out.println("2. Modificar Ejecutivo de Cuenta");
            System.out.println("3. Ver Ejecutivos de Cuenta ");
            System.out.println("4. Eliminar Ejecutivo de Cuenta ");
            System.out.println("5. Buscar Ejecutivo de Cuenta ");//pero no se si se quede este o no
            System.out.println("0. Cerrar sesión ");
            opcion = DatosComun.pedirNumero();

            switch (opcion) {
                case 1:
                    Ejecutivo.registrarEjecutivo();
                    break;
                case 2:
                    Ejecutivo.modificarEjecutivo();
                    break;
                case 3:
                    Ejecutivo.mostrarEjecutivos();
                    break;
                case 4:
                    Ejecutivo.eliminarEjecutivo();
                    break;
                case 5:
                    Ejecutivo.buscarEjecutivo();
                    break;
                case 0:
                    UsuarioEnSesion.getInstancia().cerrarSesion();
                    seleccionarBanco();
                    break;
                default:
                    System.out.println("Ingresa una opción válida");
            }
        }
        while(opcion != 0);
    }
    private static void mostrarMenuEjecutivo(){
        Scanner scanner = new Scanner(System.in);
        int opcion = 10;

        do {
            System.out.println("\nMenú Ejecutivo");
            System.out.println("Selecciona una opción para continuar");
            System.out.println("1. Registrar Cliente ");
            System.out.println("2. Modificar Cliente ");
            System.out.println("3. Eliminar Cliente ");
            System.out.println("4. Ver Clientes ");
            System.out.println("5. Buscar Clientes ");
            System.out.println("6. Ver Solicitudes de Tarjetas");
            System.out.println("7. Procesar Solicitudes de Tarjeta");
            System.out.println("6. Ver Solicitudes de Tarjetas");//pedir contraseña
            //hacer atziri
            System.out.println("7. Procesar Solicitudes de Tarjeta");//pedir contraseña
            System.out.println("0. Cerrar sesión ");
            opcion = DatosComun.pedirNumero();

            switch (opcion) {
                case 1:
                    Cliente.registrarCliente();
                    break;
                case 2:
                    Cliente.modificarCliente();
                    break;
                case 3:
                    Cliente.borrarCliente();
                    break;
                case 4:
                    Cliente.mostrarClientes();
                    break;
                case 5:
                    Cliente.buscarCliente();
                    break;
                case 6:
                    //
                case 7:
                   //
                case 0:
                    UsuarioEnSesion.getInstancia().cerrarSesion();
                    seleccionarBanco();
                    break;
                default:
                    System.out.println("Ingresa una opción válida");
            }
        }
        while(opcion != 0);

    }
    private static void mostrarMenuInversionista() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 10;

        do {
            System.out.println("\nMenú Inversionista");
            System.out.println("Selecciona una opción para continuar");
            System.out.println("1. Realizar inversión");
            System.out.println("2. Ver mis propias inversiones");

            System.out.println("0. Cerrar sesión ");
            opcion = DatosComun.pedirNumero();

            switch (opcion) {
                case 1:
                    //
                    break;
                case 2:
                   //
                    break;
                case 0:
                    UsuarioEnSesion.getInstancia().cerrarSesion();
                    seleccionarBanco();
                    break;
                default:
                    System.out.println("Ingresa una opción válida");
            }
        }
        while(opcion != 0);
    }
    private static void mostrarMenuGerente (){
        Scanner scanner = new Scanner(System.in);
        int opcion = 10;

        do {
            System.out.println("\nMenú Gerente");
            System.out.println("Selecciona una opción para continuar");
            //todas la opciones
            System.out.println("1) >>En relación a Clientes<<");
            System.out.println("2) >>En relación a Tarjetas<<");
            System.out.println("3) >>En relación a Inversionistas e Inversiones<<");
            System.out.println("4) >>En relación a Ejecutivos<<");
            System.out.println("5) >>En relación a Capturistas<<");
            System.out.println("0) Cerrar sesión");

            opcion = DatosComun.pedirNumero();

            switch (opcion) {
                case 1:
                    mostrarMenuGerenteClientes();
                    break;
                case 2:
                    mostrarMenuGerenteTarjetas();
                    break;
                case 3:
                    mostrarMenuGerenteInversionistas();
                    break;
                case 4:
                    mostrarMenuGerenteEjecutivos();
                    break;
                case 5:
                    mostrarMenuGerenteCapturistas();
                case 0:
                    UsuarioEnSesion.getInstancia().cerrarSesion();
                    seleccionarBanco();
                    break;
                default:
                    System.out.println("Ingresa una opción válida");
            }
        }
        while(opcion != 0);
    }

    private static void mostrarMenuGerenteClientes() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 10;

        do {
            System.out.println(" >>En relación a Clientes<<");
            System.out.println("Selecciona una opción para continuar");
            System.out.println();
            System.out.println("1. Registrar Cliente");
            System.out.println("2. Modificar Cliente");
            System.out.println("3. Eliminar Cliente ");
            System.out.println("4. Ver Clientes ");
            System.out.println("5. Buscar Cliente"); //este viene en el file pero no se si ponerlo
            System.out.println("0. Atrás");
            opcion = DatosComun.pedirNumero();

            switch (opcion) {
                case 1:
                    Cliente.registrarCliente();
                    break;
                case 2:
                    Cliente.modificarCliente();
                    break;
                case 3:
                    Cliente.borrarCliente();
                    break;
                case 4:
                    Cliente.mostrarClientes();
                    break;
                case 5:
                    Cliente.buscarCliente();
                    break;
                case 0:
                   mostrarMenuGerente();
                   scanner.nextLine();
                    break;
                default:
                    System.out.println("Ingresa una opción válida");
            }
        }
        while(opcion != 0);
    }

    private static void mostrarMenuGerenteTarjetas() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 10;

        do {
            System.out.println(">>En relación a Tarjetas<<");
            System.out.println("Selecciona una opción para continuar");
            System.out.println();
            System.out.println("1. Ver Solicitudes de Tarjetas");
            System.out.println("2. Procesar Solicitudes de Tarjeta");
            System.out.println("0. Atrás");
            opcion = DatosComun.pedirNumero();

            switch (opcion) {
                case 1:
                    //
                    break;
                case 2:
                    //
                    break;
                case 0:
                    mostrarMenuGerente();
                    scanner.nextLine();
                    break;
                default:
                    System.out.println("Ingresa una opción válida");
            }
        }
        while(opcion != 0);
    }

    private static void mostrarMenuGerenteInversionistas() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 10;

        do {
            System.out.println(">>En relación a Inversionistas e Inversiones<<");
            System.out.println("Selecciona una opción para continuar");
            System.out.println();
            System.out.println("1. Registrar Inversionista ");
            System.out.println("2. Modificar Inversionista ");
            System.out.println("3. Eliminar Inversionista ");
            System.out.println("4. Ver Inversionistas ");
            System.out.println("5. Ver Inversiones");
            System.out.println("0. Atrás");

            opcion = DatosComun.pedirNumero();
            if(opcion==0){
                mostrarMenuGerente();
                scanner.nextLine();
            }
            else {
                boolean respuesta = Banco.verificarContrasenaSecreta();
                if (respuesta==false){System.out.println("Por seguridad se cerrrará la sesión");
                    UsuarioEnSesion.getInstancia().cerrarSesion();
                    seleccionarBanco();
                    break;
                }
                else {

            switch (opcion) {
                case 1:
                    Inversionista.registrarInversionista();
                    break;
                case 2:
                    Inversionista.modificarInversionista();
                    break;
                case 3:
                    Inversionista.eliminarInversionista();
                    break;
                case 4:
                    Inversionista.mostrarInversionistas();
                    break;
                case 5:
                    Inversion.mostrarInversiones();
                    break;
                case 0:
                    mostrarMenuGerente();
                    scanner.nextLine();
                    break;
                default:
                    System.out.println("Ingresa una opción válida");
            }
        }
            }
        }
        while(opcion != 0);
    }
    private static void mostrarMenuGerenteEjecutivos() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 10;

        do {
            System.out.println(" >>En relación a Ejecutivos<<");
            System.out.println("Selecciona una opción para continuar");
            System.out.println();
            System.out.println("1. Registrar Ejecutivo ");
            System.out.println("2. Modificar Ejecutivo ");
            System.out.println("3. Eliminar Ejecutivo ");
            System.out.println("4. Ver Ejecutivos ");
            System.out.println("0. Atrás");

            opcion = DatosComun.pedirNumero();

            switch (opcion) {
                case 1:
                    Ejecutivo.registrarEjecutivo();
                    break;
                case 2:
                    Ejecutivo.modificarEjecutivo();
                    break;
                case 3:
                    Ejecutivo.eliminarEjecutivo();
                    break;
                case 4:
                    Ejecutivo.mostrarEjecutivos();
                    break;
                case 0:
                    mostrarMenuGerente();
                    scanner.nextLine();
                    break;
                default:
                    System.out.println("Ingresa una opción válida");
            }
        }
        while(opcion != 0);
    }

    private static void mostrarMenuGerenteCapturistas() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 10;

        do {
            System.out.println(" >>En relación a Capturistas<<");
            System.out.println("Selecciona una opción para continuar");
            System.out.println();
            System.out.println("1. Registrar Capturista ");
            System.out.println("2. Modificar Capturista ");
            System.out.println("3. Eliminar Capturista ");
            System.out.println("4. Ver Capturistas ");
            System.out.println("0. Atrás");

            opcion = DatosComun.pedirNumero();

            switch (opcion) {
                case 1:
                    Capturista.registrarCapturista();
                    break;
                case 2:
                    Capturista.modificarCapturista();
                    break;
                case 3:
                    Capturista.borrarCapturista();
                case 4:
                    Capturista.mostrarCapturistas();
                case 0:
                    mostrarMenuGerente();
                    scanner.nextLine();
                    break;
                default:
                    System.out.println("Ingresa una opción válida");
            }
        }
        while(opcion != 0);
    }

}
