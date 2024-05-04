package Usuarios;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Cliente extends Persona{
    private int num=1;
    private int id;
    private LocalDate fechaRegistro;
    //private TarjetaDebito tarjetaDebito;
    //private ArrayList<TarjetaCredito> tarjetasCredito=new ArrayList<>();

    public Cliente(String nombre, String apellidoPaterno, String apellidoMaterno, String ciudad, String estado, String curp, String direccion, int anioNacimiento, String RFC, String contrasena) {
        super(nombre, apellidoPaterno, apellidoMaterno, ciudad, estado, curp, direccion, anioNacimiento, RFC, contrasena);
        fechaRegistro=LocalDate.now();
        id=num;
        num++;
        //generar tarjeta debito
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
    public static void crearCliente(){

    }
    public static void editarCliente(){

    }
    public static void borrarCliente(){

    }
    public static void buscarCliente(){

    }
}
