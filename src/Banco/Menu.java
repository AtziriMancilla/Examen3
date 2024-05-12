package Banco;
import Banco.Tarjetas.TarjetaDebito;
import Banco.utils.SolicitudTarjetaCredito;
import Banco.utils.TipoTarjetaCredito;
import Usuarios.*;
import Usuarios.utils.DatosComun;
import Usuarios.utils.Rol;
import utils.UsuarioEnSesion;

import java.time.LocalDate;
import java.util.*;

public class Menu {

    private static final Banco bancoMadero = new Banco();
    private static final Banco bancoAcueducto = new Banco();

    public static void iniciarObjetos() {
        pruebasMadero();
        pruebasAcueducto();
    }

    public static void seleccionarBanco(){
        int sucursal;
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
               iniciarSesion(bancoAcueducto);
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
                System.out.println();
                seleccionarMenu(banco);
            } else {

                System.out.println("Usuario o contrasena incorrectos. Intenta de nuevo.");
            }
        } while (!datosCorrectos);
    }
    private static void seleccionarMenu(Banco banco) {
        switch (UsuarioEnSesion.getInstancia().getUsuarioActual().getRol()) {
            case CAPTURISTA -> mostrarMenuCapturista(banco);
            case CLIENTE -> mostrarMenuCliente(banco);
            case EJECUTIVO -> mostrarMenuEjecutivo(banco);
            case GERENTE -> mostrarMenuGerente(banco);
            case INVERSIONISTA -> mostrarMenuInversionista(banco);
        }
    }

    private static void mostrarMenuCliente(Banco banco) {
        int opcion;
        Cliente cliente = (Cliente)UsuarioEnSesion.getInstancia().getUsuarioActual();//Para obtener al cliente de la sesión.
        TarjetaDebito tarjetaDebito = cliente.getTarjetaDebito();//Se obtiene la tarjeta de débito del usuario para sus futuras operaciones.
        System.out.println("\tMenú Cliente");

        if(cliente.getTarjetasCredito().isEmpty()){//Caso 1: El cliente no tiene tarjetas de crédito.
            if(tarjetaDebito.getSaldo()<50000){//Si el cliente no cumple con saldo mínimo para solicitar tarjeta.
                do{//Menú básico.
                    System.out.println("\nBienvenido. Seleccione una opción:\n");
                    System.out.println("1) Consultar cuenta de débito");
                    System.out.println("2) Depositar a cuenta de débito");
                    System.out.println("3) Retirar dinero");
                    System.out.println("4) Comprar con tarjeta de débito");
                    System.out.println("0) Cerrar Sesión");
                    opcion = DatosComun.pedirNumero();
                    switch (opcion) {
                        case 1:
                            banco.consultarCuentaDebito(tarjetaDebito);
                            break;
                        case 2:
                            banco.depositarDebito(tarjetaDebito);
                            mostrarMenuCliente(banco);
                            break;
                        case 3:
                            banco.retirarDebito(tarjetaDebito);
                            mostrarMenuCliente(banco);
                            break;
                        case 4:
                            banco.comprarDebito(tarjetaDebito);
                            mostrarMenuCliente(banco);
                            break;
                        case 0:
                            UsuarioEnSesion.getInstancia().cerrarSesion();
                            seleccionarBanco();
                            break;
                        default:
                            System.out.println("Error. Selecciona una opción válida\n");
                            break;
                    }
                } while(opcion!=0);
            }
            if(tarjetaDebito.getSaldo()>=50000){//El cliente tiene saldo para solicitar una tarjeta de crédito.
                do{//Menú con opción para solicitar tarjeta de crédito.
                    System.out.println("\nBienvenido. Seleccione una opción:\n");
                    System.out.println("1) Consultar cuenta de débito");
                    System.out.println("2) Depositar a cuenta de débito");
                    System.out.println("3) Retirar dinero");
                    System.out.println("4) Comprar con tarjeta de débito");
                    System.out.println("5) Realizar solicitud de tarjeta de crédito");
                    System.out.println("6) Revisar estatus de solicitud de tarjeta");
                    System.out.println("0) Cerrar Sesión");
                    opcion = DatosComun.pedirNumero();
                    switch (opcion) {
                        case 1:
                            banco.consultarCuentaDebito(tarjetaDebito);
                            break;
                        case 2:
                            banco.depositarDebito(tarjetaDebito);
                            mostrarMenuCliente(banco);
                            break;
                        case 3:
                            banco.retirarDebito(tarjetaDebito);
                            mostrarMenuCliente(banco);
                            break;
                        case 4:
                            banco.comprarDebito(tarjetaDebito);
                            mostrarMenuCliente(banco);
                            break;
                        case 5:
                            banco.solicitudDeTarjetaCredito(cliente);
                            break;
                        case 6:
                            banco.revisarSolicitudCredito(cliente);
                            break;
                        case 0:
                            UsuarioEnSesion.getInstancia().cerrarSesion();
                            seleccionarBanco();
                            break;
                    }
                }while(opcion!=0);
            }
        }
        if(!cliente.getTarjetasCredito().isEmpty()){//Caso 2. El cliente ya cuenta con tarjetas de crédito.
            do {//Menú Completo.
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
                opcion = DatosComun.pedirNumero();
                switch (opcion) {
                    case 1:
                        banco.consultarCuentaDebito(tarjetaDebito);
                        break;
                    case 2:
                        banco.depositarDebito(tarjetaDebito);
                        mostrarMenuCliente(banco);
                        break;
                    case 3:
                        banco.retirarDebito(tarjetaDebito);
                        mostrarMenuCliente(banco);
                        break;
                    case 4:
                        banco.comprarDebito(tarjetaDebito);
                        mostrarMenuCliente(banco);
                        break;
                    case 5:
                        banco.consultarCuentasCredito(cliente);
                        break;
                    case 6:
                        banco.verTarjetas(cliente);
                        break;
                    case 7:
                        banco.solicitudDeTarjetaCredito(cliente);
                        break;
                    case 8:
                        banco.revisarSolicitudCredito(cliente);
                        break;
                    case 9:
                        banco.compraTarjetaCredito(cliente);
                        break;
                    case 10:
                        banco.pagoTarjetaCredito(cliente);
                        break;
                    case 0:
                        UsuarioEnSesion.getInstancia().cerrarSesion();
                        seleccionarBanco();
                        break;
                    default:
                        System.out.println("Error. Selecciona una opción válida\n");
                        break;
                }
            }while(opcion!=0);
        }
    }

    private static void mostrarMenuCapturista(Banco banco){
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
                    banco.registrarEjecutivo();
                    break;
                case 2:
                    banco.modificarEjecutivo();
                    break;
                case 3:
                    banco.mostrarEjecutivos();
                    break;
                case 4:
                    banco.eliminarEjecutivo();
                    break;
                case 5:
                    banco.buscarEjecutivo();
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
    private static void mostrarMenuEjecutivo(Banco banco){
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

            System.out.println("0. Cerrar sesión ");
            opcion = DatosComun.pedirNumero();

            switch (opcion) {
                case 1:
                    banco.registrarCliente();
                    break;
                case 2:
                    banco.modificarCliente();
                    break;
                case 3:
                    banco.borrarCliente();
                    break;
                case 4:
                    banco.mostrarClientes();
                    break;
                case 5:
                    banco.buscarCliente();
                    break;
                case 6:
                    banco.verSolicitudes();
                case 7:
                    banco.procesarSolicitudes();
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
    private static void mostrarMenuInversionista(Banco banco) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 10;

        do {
            System.out.println("\nMenú Inversionista");
            System.out.println("Selecciona una opción para continuar");
            System.out.println("1. Realizar inversión");
            System.out.println("2. Ver mis propias inversiones");

            System.out.println("0. Cerrar sesión ");
            opcion = DatosComun.pedirNumero();
            Inversionista inversionista=(Inversionista)UsuarioEnSesion.getInstancia().getUsuarioActual();
            switch (opcion) {
                case 1:
                    banco.realizarInversion(inversionista);
                    break;
                case 2:
                    banco.mostrarMisInversiones(inversionista);
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
    private static void mostrarMenuGerente (Banco banco){
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
                    mostrarMenuGerenteClientes(banco);
                    break;
                case 2:
                    mostrarMenuGerenteTarjetas(banco);
                    break;
                case 3:
                    mostrarMenuGerenteInversionistas(banco);
                    break;
                case 4:
                    mostrarMenuGerenteEjecutivos(banco);
                    break;
                case 5:
                    mostrarMenuGerenteCapturistas(banco);
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

    private static void mostrarMenuGerenteClientes(Banco banco) {
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
                    banco.registrarCliente();
                    break;
                case 2:
                    banco.modificarCliente();
                    break;
                case 3:
                    banco.borrarCliente();
                    break;
                case 4:
                    banco.mostrarClientes();
                    break;
                case 5:
                    banco.buscarCliente();
                    break;
                case 0:
                   mostrarMenuGerente(banco);
                   scanner.nextLine();
                    break;
                default:
                    System.out.println("Ingresa una opción válida");
            }
        }
        while(opcion != 0);
    }

    private static void mostrarMenuGerenteTarjetas(Banco banco) {
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
                    banco.verSolicitudes();
                    break;
                case 2:
                    banco.procesarSolicitudes();
                    break;
                case 0:
                    mostrarMenuGerente(banco);
                    scanner.nextLine();
                    break;
                default:
                    System.out.println("Ingresa una opción válida");
            }
        }
        while(opcion != 0);
    }

    private static void mostrarMenuGerenteInversionistas(Banco banco) {
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
                mostrarMenuGerente(banco);
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
                    banco.registrarInversionista();
                    break;
                case 2:
                    banco.modificarInversionista();
                    break;
                case 3:
                    banco.eliminarInversionista();
                    break;
                case 4:
                    banco.mostrarInversionistas();
                    break;
                case 5:
                    banco.mostrarInversiones();
                    break;
                case 0:
                    mostrarMenuGerente(banco);
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
    private static void mostrarMenuGerenteEjecutivos(Banco banco) {
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
                    banco.registrarEjecutivo();
                    break;
                case 2:
                    banco.modificarEjecutivo();
                    break;
                case 3:
                    banco.eliminarEjecutivo();
                    break;
                case 4:
                    banco.mostrarEjecutivos();
                    break;
                case 0:
                    mostrarMenuGerente(banco);
                    scanner.nextLine();
                    break;
                default:
                    System.out.println("Ingresa una opción válida");
            }
        }
        while(opcion != 0);
    }

    private static void mostrarMenuGerenteCapturistas(Banco banco) {
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
                    banco.registrarCapturista();
                    break;
                case 2:
                    banco.modificarCapturista();
                    break;
                case 3:
                    banco.borrarCapturista();
                case 4:
                    banco.mostrarCapturistas();
                case 0:
                    mostrarMenuGerente(banco);
                    scanner.nextLine();
                    break;
                default:
                    System.out.println("Ingresa una opción válida");
            }
        }
        while(opcion != 0);
    }
    public static void pruebasMadero(){
        Inversionista inversionista=new Inversionista("Alejandro","Montejano","Diaz","Morelia","Michoacan","MODA133545","Calle desconocida",2004, LocalDate.now(),"Moda904803","Mario bros","1234");
        bancoMadero.personas.get(Rol.INVERSIONISTA).add(inversionista);
        Cliente cliente=new Cliente("Alejandro","Montejano","Diaz","Morelia","Michoacan","MODA133545","Calle desconocida",2004, LocalDate.now(),"Moda904803","Elpro","1234");
        bancoMadero.personas.get(Rol.CLIENTE).add(cliente);
        SolicitudTarjetaCredito solicitudTarjetaCredito=new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Simplicity);
        bancoMadero.solicitudes.add(solicitudTarjetaCredito);
        Gerente gerente = new Gerente("Jafet","Santoyo","Benites","Morelia","Michoacán","SABJ970130HMNNNF09","Rey Ezequias #88",1997,LocalDate.of(1997,1,30),"SABJ970130HM1","jafiSan","uwu",35240.89,"1234");
        bancoMadero.personas.get(Rol.GERENTE).add(gerente);
    }
    public static void pruebasAcueducto(){
        Inversionista inversionista1=new Inversionista("Andrea","Duran","Martinez","Morelia","Michoacan","DUMA238732545","Calle desconocida",2004, LocalDate.now(),"DUMA857345","Canelita","1234");
        bancoAcueducto.personas.get(Rol.INVERSIONISTA).add(inversionista1);
        Cliente cliente1=new Cliente("Andrea","Duran","Martinez","Morelia","Michoacan","DUMA238732545","Calle desconocida",2004, LocalDate.now(),"DUMA857345","Bioshinin","1234");
        bancoAcueducto.personas.get(Rol.CLIENTE).add(cliente1);
        SolicitudTarjetaCredito solicitudTarjetaCredito1=new SolicitudTarjetaCredito(cliente1, TipoTarjetaCredito.Simplicity);
        bancoAcueducto.solicitudes.add(solicitudTarjetaCredito1);
        Gerente gerente2 = new Gerente("Andrés","Santoyo","Benites","Morelia","Michoacán","SABA030617HMNNNF09","Rey Ezequias #88",2003,LocalDate.of(2003,1,30),"SABJ970130HM1","andySan","uwu",35240.89,"1234");
        bancoAcueducto.personas.get(Rol.CLIENTE).add(gerente2);
    }

}
