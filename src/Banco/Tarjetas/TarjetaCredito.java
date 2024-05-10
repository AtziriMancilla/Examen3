package Banco.Tarjetas;

import Banco.utils.TipoTarjetaCredito;
import Usuarios.utils.DatosComun;

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
        return String.format("%s\nCrédito Máximo: %.2f\nTipo de Tarjeta: %s", super.toString(), creditoMaximo, tipoDeTarjeta);
    }

    public void pagarTarjeta() {
        boolean flag = true;
        double pago;
        do {
            System.out.println("\nIngrese el depósito de la Tarjeta:");
            System.out.println("Saldo Pendiente: "+saldoPendiente);
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
            System.out.println("Saldo Disponible: "+creditoActual);
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
}
