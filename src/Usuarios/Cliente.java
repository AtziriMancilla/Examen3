package Usuarios;


import Banco.Tarjetas.TarjetaCredito;
import Banco.Tarjetas.TarjetaDebito;
import Banco.utils.Generador;
import Usuarios.utils.DatosComun;
import Usuarios.utils.Rol;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Cliente extends Persona{
    private int num=1;
    private int id;
    private LocalDate fechaRegistro;
    private TarjetaDebito tarjetaDebito;
    private ArrayList<TarjetaCredito> tarjetasCredito=new ArrayList<>();

    public Cliente(String nombre, String apellidoPaterno, String apellidoMaterno, String ciudad, String estado, String curp, String direccion, LocalDate fechaNacimiento, String RFC, String contrasena) {
        super(nombre, apellidoPaterno, apellidoMaterno, ciudad, estado, curp, direccion, fechaNacimiento, RFC, contrasena);
        fechaRegistro=LocalDate.now();
        id=num;
        num++;
        TarjetaDebito tarjetaDebito1 = new TarjetaDebito(1234);
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
        Cliente cliente = new Cliente(nombre, apellidoPaterno, apellidoMaterno, ciudad, estado, CURP, direccion, fechaNacimiento, RFC, contrasena);
    }
    public static void mostrarClientes(){

    }
    public static void modificarCliente(){

    }
    public static void borrarCliente(){

    }
    public static void buscarCliente(){

    }
}
