package Banco.Tarjetas;

import Usuarios.utils.DatosComun;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TarjetaDebito extends Tarjeta {
    private double saldo;


    public TarjetaDebito(int clave) {
        super(clave);
        this.saldo = 0;
    }
    //Método para depósitos iniciales de objetos precargados
    /*public void depositar(double deposito) {
        this.saldo += deposito;
    }*/
    //Método para depósitos mediante entradas del Usuario en Sesión
    public void depositoDebito() {
        boolean flag = true;
        double deposito;
        do {
            System.out.println("\nIngrese el monto a depositar:");
            deposito = DatosComun.pedirValorDouble();
            if (deposito > 0) {
                System.out.println("Realizando depósito...");
                saldo += deposito;
                System.out.println("Depósito realizado con éxito.\n");
                flag = false;
            }
            if (deposito < 0) {
                System.out.println("Error. El monto no puede ser menor a 0.");
            }else if(deposito == 0) {
                System.out.println("Cancelando operación...\n");
                flag = false;
            }
        }while(flag);
    }

    public void retiroDebito() {
        boolean flag = true;
        double retiro,saldoDisponible;
        do {
            System.out.printf("\nSaldo Disponible: %.2f",saldo);
            System.out.println("\nIngresa el monto:");
            retiro = DatosComun.pedirValorDouble();
            saldoDisponible = saldo;
            if (retiro <= saldoDisponible && retiro!=0) {
                System.out.println("Realizando retiro...");
                saldo -= retiro;
                System.out.println("Retiro realizado con éxito\n");
                flag = false;
            }
            if (retiro > saldoDisponible) {
                System.out.println("Error. Fondos insuficientes. Intenta de nuevo");
            }
            if (retiro == 0) {
                System.out.println("Cancelando la operación...\n");
                flag = false;
            }
        }while(flag);
    }

    public void compraDebito() {
        boolean flag = true;
        double compra, saldoDisponible;
        do {
            System.out.printf("\nSaldo Disponible: %.2f",saldo);
            System.out.println("\nIngresa el monto de la compra:");
            compra = DatosComun.pedirValorDouble();
            saldoDisponible = saldo;
            if (compra <= saldoDisponible && compra != 0) {
                System.out.println("Realizando compra...");
                saldo -= compra;
                System.out.println("Compra realizada.\n");
                flag = false;
            }
            if (compra > saldoDisponible) {
                System.out.println("Error. Fondos insuficientes. Intenta de nuevo");
            }
            if (compra == 0) {
                System.out.println("Cancelando la compra...\n");
                flag = false;
            }
        } while (flag);
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean simplicity(double saldo) {
        if (saldo >= 50000) return true;
        return false;
    }

    public boolean platino(double saldo) {
        if (saldo >= 100000) return true;
        return false;
    }

    public boolean oro(double saldo) {
        if (saldo >= 200000) return true;
        return false;
    }
    @Override
    public String toString(){
        return String.format("\nSaldo disponible: %.2f\n%s",saldo,super.toString());
    }
    //Métodos Jafet
    public void consultarCuenta() {
        System.out.println(this);//Imprimir los datos
    }

}
