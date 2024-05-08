package Banco.Tarjetas;

import Usuarios.Cliente;

public class TarjetaDebito extends Tarjeta {
    private double saldo;
    Cliente cliente;

    public TarjetaDebito(int clave) {
        super(clave);
        this.saldo = 0;
    }

    private void depositar(double deposito) {
        this.saldo += deposito;
        cliente.movimiento();
    }

    private void retirar(double retiro) {
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
}
