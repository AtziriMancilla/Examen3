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
            numeroSolicitudesEnProceso = 0;
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

        public void verTodasLasTarjetas() {
            System.out.println("\n"+"\tTarjeta de débito: ");
            System.out.println(tarjetaDebito.toString());
            System.out.println("\n"+"\tTarjetas de crédito: ");
            tarjetasCredito.forEach(tarjetaCredito -> System.out.println(tarjetaCredito.toString()));
        }

        public static void registrarCliente (Banco banco) {
            ArrayList<String> datosComun = DatosComun.registrarDatosComun(Rol.CLIENTE,banco);
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
        public static void mostrarClientes (Banco banco) {
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
        public static void modificarCliente (Banco banco) {
            Scanner sc = new Scanner(System.in);
            mostrarClientes(banco);
            int numCliente = pedirCliente(banco);
            int opt = 10;

            do {
                System.out.println("¿Qué información deseas editar?");
                System.out.println("1) Nombre\n2) Apellidos \n3) Ciudad\n4) Estado\n5) Dirección\n6) Fecha de nacimiento \n7)Contraseña \n0)Regresar");
                opt = DatosComun.pedirNumero();

                Cliente cliente = (Cliente) banco.personas.get(Rol.CLIENTE).get(numCliente - 1);

                switch (opt) {
                    case 1:
                        System.out.println("Ingrese el nuevo nombre: ");
                        String nuevoNombre = DatosComun.pedirDatoString();
                        cliente.setNombre(nuevoNombre);
                        banco.personas.get(Rol.CLIENTE).set(numCliente - 1, cliente);
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
                        banco.personas.get(Rol.CLIENTE).set(numCliente - 1, cliente);
                        System.out.println("Apellido modificado");
                        break;

                    case 3:
                        System.out.println("Ingrese nueva ciudad: ");
                        String nuevaCiudad = DatosComun.pedirDatoString();
                        cliente.setCiudad(nuevaCiudad);
                        banco.personas.get(Rol.CLIENTE).set(numCliente - 1, cliente);
                        System.out.println("Ciudad actualizada");
                        break;
                    case 4:
                        System.out.println("Ingrese nuevo estado: ");
                        cliente.setEstado(DatosComun.pedirDatoString());
                        banco.personas.get(Rol.CLIENTE).set(numCliente - 1, cliente);
                        System.out.println("Estado actualizado");
                        break;
                    case 5:
                        System.out.println("Ingrese nueva direccion: ");
                        cliente.setDireccion(DatosComun.pedirDireccion());
                        banco.personas.get(Rol.CLIENTE).set(numCliente - 1, cliente);
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
        public static void borrarCliente (Banco banco) {
            Scanner sc=new Scanner(System.in);
            mostrarClientes(banco);
            int numCliente=0;
            boolean band;
            do {
                try {
                    band=false;
                    System.out.println("Selecciona el cliente que deseas eliminar");
                    numCliente = sc.nextInt();
                    banco.personas.get(Rol.CLIENTE).get(numCliente - 1);
                } catch (IndexOutOfBoundsException | InputMismatchException error) {
                    System.out.println("Opcion no valida");
                    band=true;
                    //revisar sc.nextLine
                }
            }while(band);
            Cliente cliente=(Cliente) banco.personas.get(Rol.CLIENTE).get(numCliente - 1);
            if(cliente.getTarjetaDebito().getSaldo()==0) {
                System.out.println("Seleccionaste a: ");
                System.out.println(banco.personas.get(Rol.CLIENTE).get(numCliente - 1).toString());
                System.out.println("¿Deseas eliminarlo? 1) Sí, Otro número) Cancelar");
                int opcion = DatosComun.pedirNumero();
                if (opcion == 1) {
                    banco.personas.get(Rol.CLIENTE).remove(numCliente - 1);
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

        private static int pedirCliente (Banco banco) {
            Scanner sc = new Scanner(System.in);
            boolean confirmacion = false;
            int numCliente = 0;
            do {
                confirmacion = false;

                try {
                    System.out.println("Selecciona el cliente: ");
                    numCliente = DatosComun.pedirNumero();
                    if (numCliente < 1 || numCliente > banco.personas.get(Rol.CLIENTE).size()) {
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
    public static void buscarCliente(Banco banco){
            Scanner sc=new Scanner(System.in);
            System.out.println("Ingrese el ID del cliente");
            int id=DatosComun.pedirNumero();
            boolean existe=false;
            for (Persona persona : banco.personas.get(Rol.CLIENTE)) {
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

    public void consultarCuentasCredito() {
        if(tarjetasCredito == null) {
            System.out.println("No tienes tarjetas de crédito.");
        }
        if(tarjetasCredito!=null) {
            for(TarjetaCredito tarjetaCredito:tarjetasCredito){
                System.out.println(tarjetaCredito.toString());
            }
        }
    }

    public void solicitudTarjetaCredito(Banco banco) {
        System.out.println("\tBienvenido\n");//Ya no es necesario validar aquí si alcanzó el límite de tarjetas, porque si lo hizo, no tendrá acceso a la opción de solicitar en el menú.
        if(numeroSolicitudesEnProceso==0) {//Validar que no tiene una solicitud pendiente, antes de hacer otra.
            TarjetaCredito.solicitarTarjeta(banco, this);
        }else System.out.println("Ya tienes una solicitud en proceso. Espera a que sea antendida.");
    }

    public void revisarStatusSolicitud(Banco banco) {
        TarjetaCredito.revisarStatus(banco,this);
    }

    public void realizarCompraCredito() {
        int i=0,seleccion,tarjetasNoDisponibles=0;//Contador i para las opciones
        boolean select = true;
        if (tarjetasCredito!=null) {
            System.out.println("\tSelecciona la tarjeta a usar");
            System.out.println("\nTarjetas de Crédito Disponibles");
            for(TarjetaCredito tarjetaCredito:tarjetasCredito) {
                if (tarjetaCredito.getCreditoActual() > 0) {
                    i++;
                    System.out.printf("\n%d) Tarjeta %s\n", i, tarjetaCredito.getTipoCredito());
                    System.out.printf("Crédito Máximo: %.2f\n", tarjetaCredito.getCreditoMaximo());
                    System.out.printf("Crédito Disponible: %.2f\n", tarjetaCredito.getCreditoActual());
                }//Este contador permite registrar las tarjetas sin saldo disponible.
                if (tarjetaCredito.getCreditoActual() == 0) {
                    tarjetasNoDisponibles++;
                }
            }
            if (tarjetasNoDisponibles<tarjetasCredito.size()) {
                do {
                    seleccion = DatosComun.pedirNumero();
                    if (seleccion<1||seleccion>i) {
                        System.out.println("Error. Selecciona una opción válida");
                    }else {
                        seleccion-=1;
                        TarjetaCredito tarjeta = tarjetasCredito.get(seleccion);
                        tarjeta.comprarCredito();
                        select = false;
                    }
                } while (select);
            }
            if (tarjetasNoDisponibles==tarjetasCredito.size()) {
                System.out.println("\nNo tienes tarjetas de crédito disponibles");
            }
        }
        if (tarjetasCredito==null) System.out.println("\nNo tienes tarjetas de crédito");
    }

    public void realizarPagoCredito() {
        int k=0,opcionPago,tarjetasCreditoLiquidadas=0;//Contador i para las opciones
        boolean pagoValido = true;
        if (tarjetasCredito!=null) {
            System.out.println("\tSelecciona la tarjeta a pagar");
            System.out.println("\nTarjetas de Crédito");
            for(TarjetaCredito tarjetaCredito:tarjetasCredito) {
                if (tarjetaCredito.getSaldoPendiente() > 0) {
                    k++;
                    System.out.printf("\n%d) Tarjeta %s\n", k, tarjetaCredito.getTipoCredito());
                    System.out.printf("Saldo pendiente por pagar: %.2f\n", tarjetaCredito.getSaldoPendiente());
                }//Este contador permite registrar cuántas tarjetas están sin saldo pendiente.
                if (tarjetaCredito.getSaldoPendiente() == 0) {
                    tarjetasCreditoLiquidadas++;
                }
            }
            if (tarjetasCreditoLiquidadas<tarjetasCredito.size()) {
                do {
                    opcionPago = DatosComun.pedirNumero();
                    if (opcionPago<1||opcionPago>k) {
                        System.out.println("Error. Selecciona una opción válida");
                    }else {
                        opcionPago-=1;
                        TarjetaCredito tarjeta = tarjetasCredito.get(opcionPago);
                        tarjeta.pagarTarjeta();
                        pagoValido = false;
                    }
                } while (pagoValido);
            }//Si no hay tarjetas pendientes de pago, se arroja el siguiente mensaje
            if (tarjetasCreditoLiquidadas==tarjetasCredito.size()) {
                System.out.println("\nNo tienes tarjetas pendientes de pago\n");
            }
        }
        if (tarjetasCredito==null) System.out.println("\nNo tienes tarjetas de crédito");
    }
}

