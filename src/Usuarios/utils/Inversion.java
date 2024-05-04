package Usuarios.utils;

import Usuarios.Inversionista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Inversion {
    private Inversionista inversionista;
    private double monto;
    private LocalDate fecha;
    public Inversion(Inversionista inversionista,double monto){
        this.inversionista=inversionista;
        this.monto=monto;
        fecha=LocalDate.now();
    }
    public String mostrarInversion(){
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/YY hh:mm");
        String fechaFormateada = fecha.format(pattern);
        return String.format("Inversionista: %s Monto: %f Fecha: %s ",inversionista.toString(),monto,fechaFormateada);
    }
}
