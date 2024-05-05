package Banco.utils;

import Banco.Tarjetas.TarjetaCredito;

import java.time.LocalDate;

public class SolicitudTarjetaCredito {

    //Cliente clienteSolicitante;
    private LocalDate fechaSolicitud;
    private String  tipoTarjeta;
    private String status;
    int idCliente;
//    public SolicitudTarjetaCredito(Cliente cliente, String tipoTarjeta){
//        this.cliente=cliente;
//        status= "En espera";
//        this.tipoTarjeta=tipoTarjeta;
//        idCliente=cliente.getId();
//    }
    public void aprobarTarjeta(){
        status="Solicitud Aprobada";
        Generador.generarTarjeta(5579);
        //añadir tarjeta a arraycliente
        //quitar de la lista de solicitudes
    }
    public void rechazarTarjeta(){
        status="Solicitud Rechazada";
        //quitar de la lista de solicitudes
    }
    public String toString(){
        return String.format("Cliente solicitante: ,Fecha de solicitud: %s , Tipo de tarjeta: %s",fechaSolicitud,tipoTarjeta);
    }
}
