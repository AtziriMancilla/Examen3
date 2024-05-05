package Banco.Tarjetas;

public class TarjetaDebito extends Tarjeta {
    private double saldo;

    public TarjetaDebito(int clave) {
        super(clave);
        this.saldo = 0;
    }

    private void depositar(double deposito) {
        this.saldo += deposito;
    }

    private void retirar(double retiro) {
        this.saldo -= retiro;
    }

    public double getSaldo() {
        return saldo;
    }
}
