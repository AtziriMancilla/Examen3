package Usuarios;


import Banco.Banco;
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
import java.util.*;

public class Cliente extends Persona {
    private static int num = 1;
    private int id;
    private LocalDate fechaRegistro;
    private TarjetaDebito tarjetaDebito;
    private ArrayList<TarjetaCredito> tarjetasCredito=new ArrayList<>();
    private int numeroSolicitudesEnProceso;

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
                            cliente.setNumeroSolicitudesEnProceso(1);//Contador que indica que tiene una numeroSolicitudesEnProceso pendiente.
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
                                cliente.setNumeroSolicitudesEnProceso(1);
                                System.out.println("Solicitud de tarjeta Simplicity realizada");
                                band=false;
                                break;
                            case 2:
                                solicitud = new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Platino);
                                Banco.solicitudes.add(solicitud);
                                cliente.setNumeroSolicitudesEnProceso(1);
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
                                    cliente.setNumeroSolicitudesEnProceso(1);
                                    System.out.println("Solicitud de tarjeta Simplicity realizada");
                                    band=false;
                                    break;
                                case 2:
                                    solicitud = new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Platino);
                                    Banco.solicitudes.add(solicitud);
                                    cliente.setNumeroSolicitudesEnProceso(1);
                                    System.out.println("Solicitud de tarjeta Platino realizada");
                                    band=false;
                                    break;
                                case 3:
                                    solicitud = new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Oro);
                                    Banco.solicitudes.add(solicitud);
                                    cliente.setNumeroSolicitudesEnProceso(1);
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

        public static void registrarCliente (Banco banco) {
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
            banco.personas.get(Rol.CLIENTE).add(cliente);
            System.out.println(">Cliente registrado<");
        }
        public static void mostrarClientes () {
            System.out.println("\nClientes en el banco\n");
            if (((ArrayList) banco.personas.get(Rol.CLIENTE)).isEmpty()) {
                System.out.println("No hay clientes registrados");
            } else {
                int i = 1;

                for (Iterator var1 = ((ArrayList) banco.personas.get(Rol.CLIENTE)).iterator(); var1.hasNext(); ++i) {
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
            Scanner sc=new Scanner(System.in);
            System.out.println("Ingrese el ID del cliente");
            int id=DatosComun.pedirNumero();
            boolean existe=false;
            for (Persona persona : Banco.personas.get(Rol.CLIENTE)) {
                Cliente cliente = (Cliente) persona;
                    if(cliente.getId()==id){
                        System.out.println(cliente.toString());
                        existe=true;
                    }
            }
        if (!existe)
            System.out.println("No se encontro este Cliente");
    }
    public int getId() {
        return id;
    }

    public int getNumeroSolicitudesEnProceso() {
        return numeroSolicitudesEnProceso;
    }

    public void setNumeroSolicitudesEnProceso(int numeroSolicitudesEnProceso) {
        this.numeroSolicitudesEnProceso = numeroSolicitudesEnProceso;
    }

    public static void consultarCuentaDebito(TarjetaDebito tarjetaDebito) {
        System.out.println("Saldo de la cuenta: " + tarjetaDebito.getSaldo());
        System.out.println(tarjetaDebito.toString());//Obtener el resto de los datos de la tarjeta
    }

    public static void consultarCuentasCredito(Cliente cliente) {
        int card = 1;
        if(cliente.getTarjetasCredito()!=null) {
            for(TarjetaCredito tarjetaCredito: cliente.getTarjetasCredito()){
                System.out.printf("\n%d) %s\n", card, tarjetaCredito.toString());
                card++;
            }
        }
        if(cliente.getTarjetasCredito()==null) System.out.println("No tienes tarjetas de crédito.");
    }

    public static void solicitudTarjetaCredito(Cliente cliente) {
        System.out.println("\tBienvenido\n");
        if (cliente.getTarjetasCredito().size()<3) {//Validación de la cantidad de tarjetas del cliente antes de realizar solicitud.
            if(cliente.getNumeroSolicitudesEnProceso()==0) Cliente.solicitarTarjetaCredito(cliente);
            if(cliente.getNumeroSolicitudesEnProceso()==1) System.out.println("Ya tienes una solicitud en curso. Debes esperar a que termine el proceso para poder hacer una nueva solicitud.");
        }
        if (cliente.getTarjetasCredito().size()==3) System.out.println("No puedes solicitar más tarjetas. Límite máximo alcanzado.");
    }

    public static void revisarStatusSolicitud(Cliente cliente) {
        System.out.println("Solicitud en curso:");//El ciclo buscará la solicitud hecha por el cliente y le mostrará el status.
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
    }

    public static void realizarCompraCredito(Cliente cliente) {
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
    }

    public static void realizarPagoCredito(Cliente cliente) {
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
    }
}

