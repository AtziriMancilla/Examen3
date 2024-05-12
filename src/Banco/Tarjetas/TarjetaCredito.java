package Banco.Tarjetas;

import Banco.Banco;
import Banco.utils.SolicitudTarjetaCredito;
import Banco.utils.TipoTarjetaCredito;
import Usuarios.Cliente;
import Usuarios.utils.DatosComun;

import java.util.ArrayList;
import java.util.List;

public class TarjetaCredito extends Tarjeta {
    private TipoTarjetaCredito tipoCredito;
    //credito que tiene dependiendo el tipo de tarjeta
    private double creditoMaximo;
    //credito que le queda disponible al cliente
    private double creditoActual;
    private double saldoPendiente;

    public TarjetaCredito(int clave,TipoTarjetaCredito tipo) {
        super(clave);
        tipoCredito=tipo;
        creditoMaximo=asignarCreditoMaximo(tipo);
        creditoActual=creditoMaximo;
        saldoPendiente=0;
    }
    public static int asignarCreditoMaximo(TipoTarjetaCredito tipo){
        int cantidad=0;
        if(tipo == TipoTarjetaCredito.Simplicity){
            cantidad=60000;
        }
        if(tipo==TipoTarjetaCredito.Platino){
            cantidad=150000;
        }
        else{
            cantidad=400000;
        }
        return cantidad;
    }


    @Override
    public String toString() {
        String tipoDeTarjeta = "" + tipoCredito; //Convierte a string el dato que indica el tipo de tarjeta.
        return String.format("\n%s\nCrédito Máximo: %.2f\nTipo de Tarjeta: %s", super.toString(), creditoMaximo, tipoDeTarjeta);
    }

    public void pagarTarjeta() {
        boolean flag = true;
        double pago;
        do {
            System.out.println("\nIngrese el depósito de la Tarjeta:");
            System.out.println("Saldo Pendiente: %.2f"+saldoPendiente);
            pago = DatosComun.pedirValorDouble();
            if (pago <= saldoPendiente) {
                System.out.println("Realizando pago...");
                saldoPendiente -= pago;
                System.out.println("Actualizando saldo y crédito disponible...");
                creditoActual += pago;
                System.out.println("Pago realizado.");
                flag = false;
            }
            if (pago > saldoPendiente) {
                System.out.println("Error. No puedes ingresar un monto mayor a la deuda actual.");
            }
            if (pago == 0) {
                System.out.println("Cancelando la operación...");
                flag = false;
            }
        } while(flag);
    }

    public void comprarCredito() {
        boolean flag = true;
        double importe;
        do {
            System.out.println("Saldo Disponible: %.2f"+creditoActual);
            System.out.println("\nIngrese el monto de la compra a crédito:");
            importe = DatosComun.pedirValorDouble();
            if (importe <= creditoActual) {
                System.out.println("Realizando pago...");
                creditoActual -= importe;
                System.out.println("Actualizando saldo y crédito disponible...");
                saldoPendiente += importe;
                System.out.println("Compra a crédito realizada.");
                flag = false;
            }
            if (importe > creditoActual) {
                System.out.println("No se puede realizar la compra. Límite de crédito alcanzado. Intenta de nuevo.");
            }
            if (importe == 0) {
                System.out.println("Cancelando la compra...");
                flag = false;
            }
        } while(flag);
    }

    public TipoTarjetaCredito getTipoCredito() {
        return tipoCredito;
    }

    public double getCreditoMaximo() {
        return creditoMaximo;
    }
    //Pendiente de uso.
    public double getCreditoActual() {
        return creditoActual;
    }

    public double getSaldoPendiente() {
        return saldoPendiente;
    }
    //Métodos Jafet
    public static void solicitarTarjeta (Banco banco, Cliente cliente) {
            int opciones = 0, opcion;
            boolean band = false;
            SolicitudTarjetaCredito solicitud;
            TarjetaDebito tarjetaDebito = cliente.getTarjetaDebito();//Se obtiene la tarjeta de débito del cliente.
            if (tarjetaDebito.simplicity(tarjetaDebito.getSaldo())) {//Dependiendo del saldo, se motrarán las opciones disponibles.
                opciones = 1;
                if (tarjetaDebito.platino(tarjetaDebito.getSaldo())) {
                    opciones = 2;
                    if (tarjetaDebito.oro(tarjetaDebito.getSaldo())) {
                        opciones = 3;
                    }
                }
            }
            switch (opciones) {
                case 1:
                    do {
                        System.out.println("Puedes seleccionar el tipo Simplicity");
                        System.out.println("1.Solicitar\n2. Salir");
                        opcion = DatosComun.pedirNumero();
                        if (opcion == 1) {
                            solicitud = new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Simplicity);
                            banco.solicitudes.add(solicitud);
                            System.out.println("Solicitud de tarjeta Simplicity realizada");
                            band = false;
                        } if(opcion==2) band = false;
                        else {
                            System.out.println("Opción no válida.");
                            band = true;
                        }
                    } while (band);
                    break;
                case 2:
                    do {
                        System.out.println("Seleccione el tipo de tarjeta: ");
                        System.out.println("1. Simplicity\n2. Platino\n3. Salir");
                        opcion = DatosComun.pedirNumero();
                        switch (opcion) {
                            case 1:
                                solicitud = new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Simplicity);
                                banco.solicitudes.add(solicitud);
                                System.out.println("Solicitud de tarjeta Simplicity realizada");
                                band = false;
                                break;
                            case 2:
                                solicitud = new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Platino);
                                banco.solicitudes.add(solicitud);
                                System.out.println("Solicitud de tarjeta Platino realizada");
                                band = false;
                                break;
                            case 3:
                                band = false;
                                break;
                            default:
                                System.out.println("Opcion no valida");
                                band = true;
                                break;
                        }
                    } while (band);
                    break;
                case 3:
                    do {
                        System.out.println("Seleccione el tipo de tarjeta: ");
                        System.out.println("1. Simplicity\n2. Platino\n3. Oro\4. Salir");
                        opcion = DatosComun.pedirNumero();
                        switch (opcion) {
                            case 1:
                                solicitud = new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Simplicity);
                                banco.solicitudes.add(solicitud);
                                System.out.println("Solicitud de tarjeta Simplicity realizada");
                                band = false;
                                break;
                            case 2:
                                solicitud = new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Platino);
                                banco.solicitudes.add(solicitud);
                                System.out.println("Solicitud de tarjeta Platino realizada");
                                band = false;
                                break;
                            case 3:
                                solicitud = new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Oro);
                                banco.solicitudes.add(solicitud);
                                System.out.println("Solicitud de tarjeta Oro realizada");
                                band = false;
                                break;
                            case 4:
                                band = false;
                                break;
                            default:
                                System.out.println("Opción no válida");
                                band = true;
                                break;
                        }
                    } while (band);
                    break;
                default:
                    System.out.println("No tienes ofertas disponibles.");
                    break;
            }
        }
    public static void revisarStatus(Banco banco, Cliente cliente) {
        System.out.println("Solicitud en curso:");//El ciclo buscará la solicitud hecha por el cliente y le mostrará el status.
        boolean solicitudEncontrada = false;//Determina si se encontró una solicitud pendiente del cliente.
        int indexSolicitud = 0;
        for(SolicitudTarjetaCredito solicitud : banco.solicitudes) {
            if (solicitud.getCliente() == cliente){
                solicitudEncontrada = true;
                System.out.println("Tarjeta solicitada: "+ solicitud.getTipoTarjeta());
                System.out.println("Status: "+solicitud.getStatus());
                if(solicitud.getStatus().equals("Solicitud Aprobada")||solicitud.getStatus().equals("Solicitud Rechazada")){//Revisión del status
                    System.out.println("Eliminando solicitud del historial...");
                    cliente.setNumeroSolicitudesEnProceso(0);//Una vez que el cliente consulte el estatus, si la solicitud ya fue atendida, su contador pasa a 0.
                    indexSolicitud = banco.solicitudes.indexOf(solicitud);
                }
            }
        }
        banco.solicitudes.remove(indexSolicitud);//Una vez consultada y atendida la solicitud, se elimina del Banco.
        if(!solicitudEncontrada) System.out.println("No tienes solicitudes pendientes.");
    }
}
