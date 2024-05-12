package Banco.utils;

import Banco.Banco;
import Banco.Tarjetas.TarjetaCredito;
import Usuarios.Cliente;
import Usuarios.utils.DatosComun;

import java.time.LocalDate;
import java.util.Scanner;

public class SolicitudTarjetaCredito {
    private Cliente cliente;
    private LocalDate fechaSolicitud;
    private TipoTarjetaCredito tipoTarjeta;
    private String status;
    int idCliente;

    public SolicitudTarjetaCredito(Cliente clienteSolicitante, TipoTarjetaCredito tipo) {
        cliente = clienteSolicitante;
        status = "En espera";
        tipoTarjeta = tipo;
        idCliente = cliente.getId();
        cliente.setNumeroSolicitudesEnProceso(1);
    }

    //método que aprueba la solicitud en curso y asigna una tarjeta de credito
    //en el menu verificar que el cliente no tenga 3 tarjetas de credito para mostrarle la opcion de solicitar
    public void aprobarTarjeta() {
        status = "Solicitud Aprobada";
        TarjetaCredito tarjetaCredito = new TarjetaCredito(5579, tipoTarjeta);
        cliente.getTarjetasCredito().add(tarjetaCredito);
    }

    public void rechazarTarjeta(Banco banco,int opcion) {
        status = "Solicitud Rechazada";
        banco.solicitudes.remove(opcion-1);
    }

    public String toString() {
        return String.format("Cliente solicitante: %s ,Fecha de solicitud: %s , Tipo de tarjeta: %s", cliente, fechaSolicitud, tipoTarjeta);
    }

    public String getStatus() {
        return status;
    }

    //Getter para buscar en la lista al cliente que solicita ver el estatus de su tarjeta.
    public Cliente getCliente() {
        return cliente;
    }

    //Getter para mostrarle al cliente la tarjeta que solicitó.
    public TipoTarjetaCredito getTipoTarjeta() {
        return tipoTarjeta;
    }

    public static void verSolicitudes(Banco banco) {
        if (banco.solicitudes.isEmpty()) {
            System.out.println("No hay solicitudes pendientes");
        } else {
            System.out.println("Solicitudes");
            for (int i = 0; i < banco.solicitudes.size(); i++) {
                System.out.println((i + 1) + ")" + banco.solicitudes.get(i).toString());
            }
        }
    }

    public static void procesarSolicitudes(Banco banco) {
        verSolicitudes(banco);
        if (!banco.solicitudes.isEmpty()) {
            Scanner sc = new Scanner(System.in);
            boolean band = false;
            int opcion = 0;
            do {
                try {
                    band = false;
                    System.out.println("Seleccione una solicitud");
                    opcion = DatosComun.pedirNumero();
                    System.out.println("Seleccionaste: ");
                    System.out.println(banco.solicitudes.get(opcion - 1).toString());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Opcion no valida");
                    sc.nextLine();
                    band = true;
                }
            } while (band);
            boolean bandera2=true;
            while(bandera2) {
                System.out.println("1) Aceptar solicitud\n2)Rechazar solicitud");
                int accion = DatosComun.pedirNumero();
                if (accion == 1) {
                    banco.solicitudes.get(opcion - 1).aprobarTarjeta();
                    System.out.println("Aprobaste la solicitud");
                    bandera2 = false;
                }
                if (opcion == 2) {
                    banco.solicitudes.get(opcion - 1).rechazarTarjeta(banco, opcion);
                    System.out.println("Rechazaste la solicitud");
                    bandera2 = false;
                } else {
                    System.out.println("Opcion no valida");
                }
            }
        }
    }
}
