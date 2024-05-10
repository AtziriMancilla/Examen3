package Usuarios;


import Banco.Banco;
import Banco.Tarjetas.Tarjeta;
import Banco.Tarjetas.TarjetaCredito;
import Banco.Tarjetas.TarjetaDebito;
import Banco.utils.Generador;
import Banco.utils.SolicitudTarjetaCredito;
import Banco.utils.TipoTarjetaCredito;
import Usuarios.utils.DatosComun;
import Usuarios.utils.Rol;
import utils.UsuarioEnSesion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class Cliente extends Persona {
    private static int num = 1;
    private int id;
    private LocalDate fechaRegistro;
    private TarjetaDebito tarjetaDebito;
    private ArrayList<TarjetaCredito> tarjetasCredito=new ArrayList<>();

    public Cliente(String nombre, String apellidoPaterno, String apellidoMaterno, String ciudad, String estado, String curp, String direccion,
        int anioNacimiento, LocalDate fechaNacimiento, String RFC, String nombreUsuario, String contrasena){
            super(nombre, apellidoPaterno, apellidoMaterno, ciudad, estado, curp, direccion, anioNacimiento, fechaNacimiento, Rol.CLIENTE, RFC, nombreUsuario, contrasena);
            fechaRegistro = LocalDate.now();
            id = num;
            num++;
            tarjetaDebito = new TarjetaDebito(1234);
        }
        public ArrayList<TarjetaCredito> getTarjetasCredito() {
            return tarjetasCredito;
        }

        @Override
        public String toString () {
            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/YYYY");
            String fechaFormateada = fechaRegistro.format(pattern);
            return String.format("ID: %d, %s Fecha registro %s ", id, super.toString(), fechaFormateada);
        }
        //json no depende del lenguaje
        public TarjetaDebito getTarjetaDebito() {
            return tarjetaDebito;
        }

        public static void solicitarTarjetaCredito (Cliente cliente) {
            int opciones=0,opcion;
            boolean band = true;
            SolicitudTarjetaCredito solicitud;
            TarjetaDebito tarjetaDebito = cliente.getTarjetaDebito();//Se obtiene la tarjeta de débito del cliente.
            if(tarjetaDebito.simplicity(tarjetaDebito.getSaldo())) {//Dependiendo del saldo, se motrarán las opciones disponibles.
                opciones=1;
                if(tarjetaDebito.platino(tarjetaDebito.getSaldo())) {
                    opciones=2;
                    if(tarjetaDebito.oro(tarjetaDebito.getSaldo())) {
                        opciones=3;
                    }
                }
            }
            switch(opciones) {
                case 1:
                    do{
                        System.out.println("Puedes seleccionar el tipo Simplicity");
                        System.out.println("1.Solicitar\n2. Salir");
                        opcion=DatosComun.pedirNumero();
                        if(opcion==1) {
                            solicitud = new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Simplicity);
                            Banco.solicitudes.add(solicitud);
                            System.out.println("Solicitud de tarjeta Simplicity realizada");
                            band=false;
                        }else System.out.println("Opción no válida");
                    } while(band);
                    break;
                case 2:
                    do{
                        System.out.println("Seleccione el tipo de tarjeta: ");
                        System.out.println("1. Simplicity\n2. Platino");
                        opcion = DatosComun.pedirNumero();
                        switch (opcion) {
                            case 1:
                                solicitud = new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Simplicity);
                                Banco.solicitudes.add(solicitud);
                                System.out.println("Solicitud de tarjeta Simplicity realizada");
                                band=false;
                                break;
                            case 2:
                                solicitud = new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Platino);
                                Banco.solicitudes.add(solicitud);
                                System.out.println("Solicitud de tarjeta Platino realizada");
                                band=false;
                                break;
                            default:
                                System.out.println("Opcion no valida");
                                break;
                        }
                    } while(band);
                    break;
                    case 3:
                        do{
                            System.out.println("Seleccione el tipo de tarjeta: ");
                            System.out.println("1. Simplicity\n2. Platino\n3. Oro");
                            opcion = DatosComun.pedirNumero();
                            switch (opcion) {
                                case 1:
                                    solicitud = new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Simplicity);
                                    Banco.solicitudes.add(solicitud);
                                    System.out.println("Solicitud de tarjeta Simplicity realizada");
                                    band=false;
                                    break;
                                case 2:
                                    solicitud = new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Platino);
                                    Banco.solicitudes.add(solicitud);
                                    System.out.println("Solicitud de tarjeta Platino realizada");
                                    band=false;
                                    break;
                                case 3:
                                    solicitud = new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Oro);
                                    Banco.solicitudes.add(solicitud);
                                    System.out.println("Solicitud de tarjeta Oro realizada");
                                    band=false;
                                    break;
                                default:
                                    System.out.println("Opción no válida");
                                    break;
                            }
                        } while(band);
                        break;
                default:
                    System.out.println("No tienes ofertas disponibles.");
                    break;
            }
        }
        public void verTodasLasTarjetas() {
            System.out.println("Tarjeta de debito: ");
            System.out.println(tarjetaDebito.toString());
            System.out.println("Tarjetas de credito: ");
            tarjetasCredito.forEach(tarjetaCredito -> System.out.println(tarjetaCredito.toString()));
        }
//        public static void realizarCompra () {
//
//        }
//        public static void pagarTarjeta () {
//
//        }
        //Método para objetos precargados del sistema.
        /*public void realizarDeposito(double monto){
            tarjetaDebito.depositar(monto);
        }*/
        //Método para depósitos ingresados por el Usuario en Sesión.
        /*public void depositarDebito() {
            tarjetaDebito.depositoDebito();
        }*/

        /*public void retirarDebito(TarjetaDebito tarjeta) {
            tarjetaDebito.retirar(tarjeta);
        }*/

        public static void registrarCliente () {
            ArrayList<String> datosComun = DatosComun.registrarDatosComun(Rol.CLIENTE);
            String nombre = datosComun.get(0);
            String apellidoPaterno = datosComun.get(1);
            String apellidoMaterno = datosComun.get(2);
            String ciudad = datosComun.get(3);
            String estado = datosComun.get(4);
            String CURP = datosComun.get(5);
            String direccion = datosComun.get(6);
            String anioNacimiento = datosComun.get(7);
            String fechaNacimientoCadena = datosComun.get(8);
            String RFC = datosComun.get(9);
            String nombreUsuario = datosComun.get(10);
            String contrasena = datosComun.get(11);

            //ocupo volver int el año de nacimiento y LocalDate la fecha de nacimiento
            int anioNacimientoint = Integer.parseInt(anioNacimiento);
            LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoCadena);

            Cliente cliente = new Cliente(nombre, apellidoPaterno, apellidoMaterno, ciudad, estado, CURP, direccion, anioNacimientoint, fechaNacimiento, RFC, nombreUsuario, contrasena);
            if (!Banco.personas.containsKey(Rol.CLIENTE)) {
                Banco.personas.put(Rol.CLIENTE, new ArrayList<Persona>());
            }
            Banco.personas.get(Rol.CLIENTE).add(cliente);
            System.out.println(">Cliente registrado<");
        }
        public static void mostrarClientes () {
            System.out.println("\nClientes en el banco\n");
            if (((ArrayList) Banco.personas.get(Rol.CLIENTE)).isEmpty()) {
                System.out.println("No hay clientes registrados");
            } else {
                int i = 1;

                for (Iterator var1 = ((ArrayList) Banco.personas.get(Rol.CLIENTE)).iterator(); var1.hasNext(); ++i) {
                    Persona usuario = (Persona) var1.next();
                    Cliente cliente = (Cliente) usuario;
                    System.out.println("" + i + ") " + cliente.toString());
                }
            }
        }
        public static void modificarCliente () {
            Scanner sc = new Scanner(System.in);
            mostrarClientes();
            int numCliente = pedirCliente();
            int opt = 10;

            do {
                System.out.println("¿Qué información deseas editar?");
                System.out.println("1) Nombre\n2) Apellidos \n3) Ciudad\n4) Estado\n5) Dirección\n6) Fecha de nacimiento \n7)Contraseña \n0)Regresar");
                opt = DatosComun.pedirNumero();

                Cliente cliente = (Cliente) Banco.personas.get(Rol.CLIENTE).get(numCliente - 1);

                switch (opt) {
                    case 1:
                        System.out.println("Ingrese el nuevo nombre: ");
                        String nuevoNombre = DatosComun.pedirDatoString();
                        cliente.setNombre(nuevoNombre);
                        Banco.personas.get(Rol.CLIENTE).set(numCliente - 1, cliente);
                        String curpAntigua = cliente.getCurp();
                        char sexo = curpAntigua.charAt(10);
                        String nuevaCurp = Generador.generarCURP(cliente.getNombre(), cliente.getApellidoPaterno(), cliente.getApellidoMaterno(), cliente.getFechaNacimiento(), sexo, cliente.getEstado());
                        String nuevorfc = Generador.generarRFC(cliente.getNombre(), cliente.getApellidoPaterno(), cliente.getApellidoMaterno(), cliente.getFechaNacimiento());
                        cliente.setRFC(nuevorfc);
                        cliente.setCurp(nuevaCurp);
                        System.out.println("Nombre modificado");
                        break;

                    case 2:
                        System.out.println("Ingrese el nuevo apellido Paterno: ");
                        String nuevoApellidoPaterno = DatosComun.pedirDatoString();
                        cliente.setApellidoPaterno(nuevoApellidoPaterno);

                        System.out.println("Ingrese el nuevo apellido Materno: ");
                        String nuevoApellidoMaterno = DatosComun.pedirDatoString();
                        cliente.setApellidoMaterno(nuevoApellidoMaterno);

                        String curpAntigua1 = cliente.getCurp();
                        char sexo1 = curpAntigua1.charAt(10);
                        String nuevaCurp1 = Generador.generarCURP(cliente.getNombre(), cliente.getApellidoPaterno(), cliente.getApellidoMaterno(), cliente.getFechaNacimiento(), sexo1, cliente.getEstado());
                        String nuevorfc1 = Generador.generarRFC(cliente.getNombre(), cliente.getApellidoPaterno(), cliente.getApellidoMaterno(), cliente.getFechaNacimiento());
                        cliente.setRFC(nuevorfc1);
                        cliente.setCurp(nuevaCurp1);
                        Banco.personas.get(Rol.CLIENTE).set(numCliente - 1, cliente);
                        System.out.println("Apellido modificado");
                        break;

                    case 3:
                        System.out.println("Ingrese nueva ciudad: ");
                        String nuevaCiudad = DatosComun.pedirDatoString();
                        cliente.setCiudad(nuevaCiudad);
                        Banco.personas.get(Rol.CLIENTE).set(numCliente - 1, cliente);
                        System.out.println("Ciudad actualizada");
                        break;
                    case 4:
                        System.out.println("Ingrese nuevo estado: ");
                        cliente.setEstado(DatosComun.pedirDatoString());
                        Banco.personas.get(Rol.CLIENTE).set(numCliente - 1, cliente);
                        System.out.println("Estado actualizado");
                        break;
                    case 5:
                        System.out.println("Ingrese nueva direccion: ");
                        cliente.setDireccion(DatosComun.pedirDireccion());
                        Banco.personas.get(Rol.CLIENTE).set(numCliente - 1, cliente);
                        System.out.println("Dirección actualizada");
                        break;
                    case 6:
                        System.out.println("Fecha de nacimiento");
                        LocalDate nuevaFechaNacimiento = DatosComun.obtenerFechaNacimiento();
                        cliente.setFechaNacimiento(nuevaFechaNacimiento);
                        int anioNacimiento = DatosComun.obtenerFechaNacimiento().getYear();
                        cliente.setAnioNacimiento(anioNacimiento);
                        String curpAntigua2 = cliente.getCurp();
                        char sexo2 = curpAntigua2.charAt(10);
                        String CURPNuevo = Generador.generarCURP(cliente.getNombre(), cliente.getApellidoPaterno(), cliente.getApellidoMaterno(), cliente.getFechaNacimiento(), sexo2, cliente.getEstado());
                        String RFCNuevo = Generador.generarRFC(cliente.getNombre(), cliente.getApellidoPaterno(), cliente.getApellidoMaterno(), cliente.getFechaNacimiento());
                        cliente.setRFC(RFCNuevo);
                        cliente.setCurp(CURPNuevo);
                        System.out.println("Fecha Nacimiento Actualizada");
                        break;
                    case 7:
                        System.out.println("Ingrese nueva contraseña");
                        String nuevaContrasena = sc.nextLine();
                        cliente.setContrasena(nuevaContrasena);
                        System.out.println("Contrasena Actualizada");
                        break;

                    case 0:
                        System.out.println("Tenemos que poner un método mostrarMenuEmpleado");
                        UsuarioEnSesion.getInstancia().cerrarSesion();
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + opt);

                }

            } while (opt != 0);


        }
        public static void borrarCliente () {
            Scanner sc=new Scanner(System.in);
            mostrarClientes();
            int numCliente=0;
            boolean band;
            do {
                try {
                    band=false;
                    System.out.println("Selecciona el cliente que deseas eliminar");
                    numCliente = sc.nextInt();
                    Banco.personas.get(Rol.CLIENTE).get(numCliente - 1);
                } catch (IndexOutOfBoundsException | InputMismatchException error) {
                    System.out.println("Opcion no valida");
                    band=true;
                }
                finally {
                    sc.nextLine();
                }
            }while(band);
            Cliente cliente=(Cliente) Banco.personas.get(Rol.CLIENTE).get(numCliente - 1);
            if(cliente.getTarjetaDebito().getSaldo()==0) {
                System.out.println("Seleccionaste a: ");
                System.out.println(Banco.personas.get(Rol.CLIENTE).get(numCliente - 1).toString());
                int opcion = 0;
                boolean bandera;
                do {
                    bandera = false;
                    try {
                        System.out.println("¿Deseas eliminarlo? 1) Sí, Otro número) Cancelar");
                        opcion = sc.nextInt();
                    } catch (InputMismatchException error) {
                        System.out.println("Opción no valida");
                        bandera = true;
                        sc.nextLine();
                    }
                } while (bandera);
                if (opcion == 1) {
                    Banco.personas.get(Rol.CLIENTE).remove(numCliente - 1);
                    System.out.println("Cliente eliminado");
                }
                if (opcion != 1) {
                    System.out.println("Se cancelo la eliminación");
                }
            }
            else{
                System.out.println("No se puede eliminar a este Cliente");
            }
        }

        private static int pedirCliente () {
            Scanner sc = new Scanner(System.in);
            boolean confirmacion = false;
            int numCliente = 0;
            do {
                confirmacion = false;

                try {
                    System.out.println("Selecciona el cliente: ");
                    numCliente = DatosComun.pedirNumero();
                    if (numCliente < 1 || numCliente > Banco.personas.get(Rol.CLIENTE).size()) {
                        throw new IndexOutOfBoundsException("El dato ingresado está fuera del tamaño de la lista");
                    }
                    else{
                        return  numCliente;
                    }
                } catch (IndexOutOfBoundsException error) {
                    System.out.println("Error: " + error.getMessage());

                }
            } while (confirmacion);
            sc.nextLine();
            return numCliente;
    }
    public static void buscarCliente(){

    }
}

