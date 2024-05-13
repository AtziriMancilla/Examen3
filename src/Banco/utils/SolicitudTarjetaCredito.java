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

    public void rechazarTarjeta() {
        status = "Solicitud Rechazada";
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
    public static void verSolicitudesPendientes(Banco banco) {
        if (banco.solicitudes.isEmpty() || contadorSolicitudesPendientes(banco) == 0)
            System.out.println("No hay solicitudes pendientes");
        if (!banco.solicitudes.isEmpty() && contadorSolicitudesPendientes(banco) != 0) {
            for (int i = 0; i < banco.solicitudes.size(); i++) {
                if (banco.solicitudes.get(i).status.equals("En espera")) {
                    System.out.println(banco.solicitudes.get(i).toString());
                }
            }
        }
    }
    public static int contadorSolicitudesPendientes(Banco banco){
        int cont=0;
        for (int i=0;i<banco.solicitudes.size();i++) {
            if (banco.solicitudes.get(i).status.equals("En espera")){
                cont++;
            }
        }
        return cont;
    }

    public static void procesarSolicitudes(Banco banco) {
        if(banco.solicitudes.isEmpty()||contadorSolicitudesPendientes(banco)==0)
            System.out.println("No hay solicitudes pendientes");
        if(!banco.solicitudes.isEmpty()&&contadorSolicitudesPendientes(banco)!=0){
            Scanner sc = new Scanner(System.in);
            boolean band = false;
            int id = 0;
            int posicion=-1;
            for (int i=0;i<banco.solicitudes.size();i++) {
                if (banco.solicitudes.get(i).status.equals("En espera")){
                    System.out.println(banco.solicitudes.get(i).toString());
                }
            }
            do {
                try {
                    posicion = -1;
                    band = false;
                    System.out.println("Seleccione el id de una solicitud: ");
                    id = DatosComun.pedirNumero();
                    for (int i = 0; i < banco.solicitudes.size(); i++) {
                        if (banco.solicitudes.get(i).getCliente().getId() == id) {
                            posicion = i;
                        }
                    }
                    if (banco.solicitudes.get(posicion).status.equals("Solicitud Aprobada")||banco.solicitudes.get(posicion).status.equals("Solicitud Rechazada")) {
                        throw new IllegalArgumentException("Id no válido");
                    }
                    System.out.println("Seleccionaste: ");
                    System.out.println(banco.solicitudes.get(posicion).toString());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Opción no valida");
                    band = true;
                }catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                    band = true;
                }
            } while (band);
            boolean bandera2=false;
            do{
                System.out.println("1) Aceptar solicitud\n2)Rechazar solicitud\n0)Salir");
                int accion = DatosComun.pedirNumero();
                if (accion == 1) {
                    banco.solicitudes.get(posicion).aprobarTarjeta();
                    System.out.println("Aprobaste la solicitud");
                    bandera2 = false;
                }
                if (accion == 2) {
                    banco.solicitudes.get(posicion).rechazarTarjeta();
                    System.out.println("Rechazaste la solicitud");
                    bandera2 = false;
                }
                if(accion==0)
                    bandera2=false;
                if(accion != 1 && accion != 2 && accion != 0) {
                    System.out.println("Opción no valida");
                    bandera2 = true;
                    sc.nextLine();
                }
            }while(bandera2);
        }
    }
}

