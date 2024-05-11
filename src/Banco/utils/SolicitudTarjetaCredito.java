package Banco.utils;

import Banco.Banco;
import Banco.Tarjetas.TarjetaCredito;
import Usuarios.Cliente;

import java.time.LocalDate;

public class SolicitudTarjetaCredito {
    Cliente cliente;
    private LocalDate fechaSolicitud;
    private TipoTarjetaCredito  tipoTarjeta;
    private String status;
    int idCliente;
    public SolicitudTarjetaCredito(Cliente cliente, TipoTarjetaCredito tipo){
        this.cliente=cliente;
        status= "En espera";
        tipoTarjeta=tipo;
        idCliente=cliente.getId();
    }
    //método que aprueba la solicitud en curso y asigna una tarjeta de credito
    //en el menu verificar que el cliente no tenga 3 tarjetas de credito para mostrarle la opcion de solicitar
    public void aprobarTarjeta(){
        status="Solicitud Aprobada";
        TarjetaCredito tarjetaCredito=new TarjetaCredito(5579,tipoTarjeta);
        cliente.getTarjetasCredito().add(tarjetaCredito);

    }
    public void rechazarTarjeta(){
        status="Solicitud Rechazada";
        //quitar de la lista de solicitudes
    }
    public String toString(){
        return String.format("Cliente solicitante: %s ,Fecha de solicitud: %s , Tipo de tarjeta: %s",cliente,fechaSolicitud,tipoTarjeta);
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
}
