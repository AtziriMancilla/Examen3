package Usuarios;


import java.time.LocalDate;

public class Cliente extends Persona{
    private int id;
    private LocalDate fechaRegistro;
    //private TarjetaDebito tarjetaDebito;
    //private ArrayList<TarjetaCredito> tarjetasCredito=new ArrayList<>();

    public Cliente(String nombre, String apellidoPaterno, String apellidoMaterno, String ciudad, String estado, String curp, String direccion, int anioNacimiento, String RFC, String contrasena) {
        super(nombre, apellidoPaterno, apellidoMaterno, ciudad, estado, curp, direccion, anioNacimiento, RFC, contrasena);
        fechaRegistro=LocalDate.now();
        //generar tarjeta debito
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
