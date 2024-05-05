package Banco;

import Banco.utils.Generador;

import java.time.LocalDate;
import java.util.Random;

public abstract class Tarjeta {
    private String numeroTarjeta;
    private String cvv;
    private LocalDate fechaCreacion;
    private String clabeInterbancaria;
    private double saldo;
    private LocalDate fechaVencimiento;
    private LocalDate fechaHoraUltimoMov;

    private String generarTarjeta(int clave) {
        this.numeroTarjeta = Generador.generarTarjeta(clave);
        return numeroTarjeta;
    }
    //Método para generar cvv con números aleatorios.
    private String generarCvv() {
        Random r = new Random();
        int codigo = r.nextInt(100,999);
        this.cvv = Integer.toString(codigo);
        return cvv;
    }

    private boolean simplicity() {
        return false;
    }

    private boolean platino() {
        return false;
    }

    private boolean oro() {
        return false;
    }
}
