package Usuarios.utils;

import Usuarios.Inversionista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Inversion {
    private Inversionista inversionista;
    private long monto;
    private LocalDate fecha;
    public Inversion(Inversionista inversionista,long monto){
        this.inversionista=inversionista;
        this.monto=monto;
        fecha=LocalDate.now();
    }
    public String mostrarInversion(){
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/YY hh:mm");
        String fechaFormateada = fecha.format(pattern);
        return String.format("Inversionista: %s Monto: %.2f Fecha: %s ",inversionista.toString(),monto,fechaFormateada);
    }
    public static void realizarInversion(Inversionista inversionista){
        Scanner sc=new Scanner(System.in);
        boolean cantidadValida = false;
        long monto;
        System.out.println("\tBienvenido. Ingrese el mondo a invertir o escriba 0 para cancelar");
        do{
            System.out.print("\nIngrese el monto: ");
            monto =sc.nextLong();
            sc.nextLine();
            if (monto <0) {
                System.out.println("Error. Monto no válido! Ingrese números válidos o 0 para cancelar la operación.");
            }
            if (monto==0) {
                System.out.println("\nCancelando operación. Regresando al menú anterior...");
                cantidadValida = true;
            }
            if (monto>0){
                System.out.println("Realizando depósito y guardando registro...");
                Inversion inversionNueva = new Inversion(inversionista,monto);
                //inversionista.realizarInversion(inversionNueva);
                System.out.println("Inversión hecha con éxito! Regresando al menú anterior...");
                cantidadValida = true;
            }
        }while(!cantidadValida);
    }
    public static void mostrarInversiones(){

    }

}
