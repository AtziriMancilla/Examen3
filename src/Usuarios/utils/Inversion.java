package Usuarios.utils;

import Usuarios.Inversionista;

import java.time.LocalDate;

public class Inversion {
    Inversionista inversionista;
    double monto;
    LocalDate fecha;
    public Inversion(Inversionista inversionista,double monto){
        this.inversionista=inversionista;
        this.monto=monto;
        fecha=LocalDate.now();
    }

}
