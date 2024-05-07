package Usuarios;


import Banco.Banco;
import Banco.Tarjetas.Tarjeta;
import Banco.Tarjetas.TarjetaCredito;
import Banco.Tarjetas.TarjetaDebito;
import Banco.utils.Generador;
import Usuarios.utils.DatosComun;
import Usuarios.utils.Rol;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

public class Cliente extends Persona{
    private int num=1;
    private int id;
    private LocalDate fechaRegistro;
    private TarjetaDebito tarjetaDebito;
    private ArrayList<TarjetaCredito> tarjetasCredito;
    public Cliente(String nombre, String apellidoPaterno, String apellidoMaterno, String ciudad, String estado, String curp, String direccion, int anioNacimiento, LocalDate fechaNacimiento, String RFC, String contrasena) {
        super(nombre, apellidoPaterno, apellidoMaterno, ciudad, estado, curp, direccion, anioNacimiento, fechaNacimiento, Rol.CLIENTE, RFC, contrasena);
        fechaRegistro=LocalDate.now();
        id=num;
        num++;
        tarjetaDebito= new TarjetaDebito(1234);
        tarjetasCredito= new ArrayList<>();
    }

    @Override
    public String toString() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        String fechaFormateada = fechaRegistro.format(pattern);
        return String.format("ID: %d, %s Fecha registro %s ",id,super.toString(),fechaFormateada);
    }

    public static void solicitarTarjeta(){

    }
    public static void verEstatusSolicitud(){

    }
    public static void verTarjetas(){

    }
    public static void realizarCompra(){

    }
    public static void pagarTarjeta(){

    }
    public static void registrarCliente(){
        ArrayList<String> datosComun = DatosComun.registrarDatosComun(Rol.CLIENTE);
        String nombre = datosComun.get(0);
        String apellidoPaterno = datosComun.get(1);
        String apellidoMaterno = datosComun.get(2);
        String ciudad = datosComun.get(3);
        String estado = datosComun.get(4);
        String CURP = datosComun.get(5);
        String direccion = datosComun.get(6);
        String fechaNacimientoCadena= datosComun.get(7);
        String RFC = datosComun.get(8);
        String contrasena = datosComun.get(9);

        ///fecha nacimiento cadena viene en formato ("dd/MM/YYYY")
        String diacadena = fechaNacimientoCadena.substring(0,2);
        String mescadena = fechaNacimientoCadena.substring(3,5);
        String aniocadena = fechaNacimientoCadena.substring (6,10);
        int dia = Integer.parseInt(diacadena);
        int mes = Integer.parseInt(mescadena);
        int anio = Integer.parseInt(aniocadena);
        //conversion a LocalDate
        LocalDate fechaNacimiento = LocalDate.of(anio,mes, dia);
        Cliente cliente = new Cliente(nombre, apellidoPaterno, apellidoMaterno, ciudad, estado, CURP, direccion, anio, fechaNacimiento, RFC, contrasena);
        if(!Banco.personas.containsKey(Rol.CLIENTE)){
            Banco.personas.put(Rol.CLIENTE, new ArrayList<Persona>());
        }
        Banco.personas.get(Rol.CLIENTE).add(cliente);
        System.out.println(">Cliente registrado<");
    }
    public static void mostrarClientes(){
        System.out.println("\nClientes en el banco\n");
        if (((ArrayList)Banco.personas.get(Rol.CLIENTE)).isEmpty()) {
            System.out.println("No hay clientes registrados");
        } else {
            int i = 1;

            for(Iterator var1 = ((ArrayList)Banco.personas.get(Rol.CLIENTE)).iterator(); var1.hasNext(); ++i) {
                Persona usuario = (Persona)var1.next();
                Cliente cliente = (Cliente)usuario;
                System.out.println("" + i + ") " + cliente.toString());
            }
        }
    }
    public static void modificarCliente(){

    }
    public static void borrarCliente(){

    }
    public static void buscarCliente(){

    }
}
