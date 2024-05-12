package Usuarios.utils;

import Banco.Banco;
import Usuarios.Inversionista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Inversion {
    private long monto;
    private LocalDate fecha;
    public Inversion(long monto){
        this.monto=monto;
        fecha=LocalDate.now();
    }
    public String mostrarInversion(){
        return String.format("Monto: %d, \nFecha de realizacion: %s ",monto,fecha);
    }
    public static void mostrarInversiones(Banco banco){
        if(banco.personas.get(Rol.INVERSIONISTA).isEmpty())
            System.out.println("No hay inversiones");
        else {
            boolean cont=true;
            for (int i = 0; i < banco.personas.get(Rol.INVERSIONISTA).size(); i++) {
                Inversionista inversionista = (Inversionista) banco.personas.get(Rol.INVERSIONISTA).get(i);
                if (!inversionista.getInversiones().isEmpty()) {
                    System.out.println(inversionista.toString() + "\nInversiones realizadas:\n");
                    for (int j = 0; j < inversionista.getInversiones().size(); j++) {
                        System.out.println(inversionista.getInversiones().get(j).mostrarInversion());
                        cont=false;
                    }
                }
            }
            if(cont){
                System.out.println("No hay inversiones realizadas");
            }
        }
    }
}
