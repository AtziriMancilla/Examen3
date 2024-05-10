package Banco.Tarjetas;

import Banco.utils.TipoTarjetaCredito;

public class TarjetaCredito extends Tarjeta {
    private TipoTarjetaCredito tipoCredito;
    //credito que tiene dependiendo el tipo de tarjeta
    private double creditoMaximo;
    //credito que le queda disponible al cliente
    private double creditoActual;

    public TarjetaCredito(int clave,TipoTarjetaCredito tipo) {
        super(clave);
        tipoCredito=tipo;
        creditoMaximo=asignarCreditoMaximo(tipo);
        creditoActual=creditoMaximo;
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

    private void pagarTarjeta(double deposito) {
        this.creditoActual += deposito;
    }

    private void comprar(double monto) {
        this.creditoActual -= monto;
    }

    public TipoTarjetaCredito getTipoCredito() {
        return tipoCredito;
    }

    public double getCreditoMaximo() {
        return creditoMaximo;
    }

    public double getCreditoActual() {
        return creditoActual;
    }
}
