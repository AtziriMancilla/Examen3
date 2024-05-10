package Banco.Tarjetas;

public class TarjetaDebito extends Tarjeta {
    private double saldo;


    public TarjetaDebito(int clave) {
        super(clave);
        this.saldo = 0;
    }

    public void depositar(double deposito) {
        this.saldo += deposito;
    }

    public void retirar(double retiro) {
        this.saldo -= retiro;
    }

    public double getSaldo() {
        return saldo;
    }

    private boolean simplicity(double saldo) {
        if (saldo >= 60000) return true;
        return false;
    }

    private boolean platino(double saldo) {
        if (saldo >= 150000) return true;
        return false;
    }

    private boolean oro(double saldo) {
        if (saldo >= 400000) return true;
        return false;
    }
    @Override
    public String toString(){
        return String.format("%s, Saldo: %f",super.toString(),saldo);
    }
}
