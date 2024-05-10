package Banco.Tarjetas;

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
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        double deposito;
        do {
            try{
                System.out.print("\nIngresa el monto: ");
                deposito = sc.nextDouble();
                sc.nextLine();
                if (deposito > 0) {
                    System.out.println("Realizando depósito...");
                    saldo += deposito;
                    System.out.println("Depósito realizado con éxito.");
                    flag = false;
                }
                if (deposito < 0) {
                    System.out.println("Error. El monto no puede ser menor a 0.");
                }else if(deposito == 0) {
                    System.out.println("Cancelando operación...");
                    flag = false;
                }
            } catch (InputMismatchException error){
                System.out.println("Datos inválidos. Intenta de nuevo");
                sc.next();
            } catch (NullPointerException error){
                System.out.println(error.getMessage());
                sc.next();
            } catch (Exception error) {
                System.out.println("Entrada incorrecta. Intenta de nuevo");
                sc.next();
            }
        }while(flag);
    }

    public void retirarDebito(TarjetaDebito tarjeta) {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        double retiro;
        System.out.println("Saldo actual: "+ tarjeta.getSaldo());
        do {
            try {
                System.out.print("\nIngresa el monto: ");
                retiro = sc.nextDouble();
                sc.nextLine();
                if (retiro > 0) {
                    if (retiro <= saldo) {
                        System.out.println("Realizando retiro...");
                        saldo -= retiro;
                        System.out.println("Retiro realizado con éxito");
                        flag = false;
                    }
                    if (retiro > saldo) {
                        System.out.println("Error. Fondos insuficientes. Intenta de nuevo");
                    }
                }
                if (retiro < 0) {
                    System.out.println("Error. El monto no puede ser menor a 0.");
                }
                if (retiro == 0) {
                    System.out.println("Cancelando operacion...");
                    flag = false;
                }
            } catch (InputMismatchException error) {
                System.out.println("Datos inválidos. Intenta de nuevo");
                sc.next();
            } catch (NullPointerException error) {
                System.out.println(error.getMessage());
                sc.next();
            } catch (Exception error) {
                System.out.println("Entrada incorrecta. Intenta de nuevo");
                sc.next();
            }
        }while(flag);
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
