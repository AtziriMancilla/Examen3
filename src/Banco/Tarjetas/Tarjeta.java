package Banco.Tarjetas;

import Banco.utils.Generador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Tarjeta {
    private String numeroTarjeta;
    private String cvv;
    private LocalDate fechaCreacion;
    private String clabeInterbancaria;
    private LocalDate fechaVencimiento;
    private LocalDate fechaHoraUltimoMov;

    public Tarjeta(int clave) {
        this.numeroTarjeta = generarTarjeta(clave);
        this.cvv = generarCvv();
        this.fechaCreacion = LocalDate.now();
        this.clabeInterbancaria = generarClabe();
        LocalDate fecha = LocalDate.now();
        int anio = fecha.getYear() + 5; //Para tener el año de vencimiento de la tarjeta.
        this.fechaVencimiento = LocalDate.of(anio, fecha.getMonth(), fecha.getDayOfMonth());//Se asigna la fecha de vencimiento al atributo.
        this.fechaHoraUltimoMov = null;
    }

    private String generarTarjeta(int clave) {
        this.numeroTarjeta = Generador.generarTarjeta(clave);
        return numeroTarjeta;
    }
    //Método para generar cvv con números aleatorios.
    private String generarCvv() {
        Random r = new Random();
        this.cvv = Integer.toString(r.nextInt(100,999));
        return cvv;
    }
    //Método para generar clabe interbancaria de la tarjeta.
    private String generarClabe() {
        Random r = new Random();
        String campo1 = Integer.toString(r.nextInt(100,999));
        String campo2 = Integer.toString(r.nextInt(100,999));
        String campo3 = Integer.toString(r.nextInt(100,999));
        String campo4 = Integer.toString(r.nextInt(100,999));
        String campo5 = Integer.toString(r.nextInt(100,999));
        String campo6 = Integer.toString(r.nextInt(100,999));
        this.clabeInterbancaria = campo1 + " " + campo2 + " " + campo3 + " " + campo4 + " " + campo5 + " " + campo6;
        return clabeInterbancaria;
    }
    //Getters y setters
    private LocalDate getFechaHoraUltimoMov() {
        return fechaHoraUltimoMov;
    }

    private void setFechaHoraUltimoMov(LocalDate fechaHoraUltimoMov) {
        this.fechaHoraUltimoMov = fechaHoraUltimoMov;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public String getCvv() {
        return cvv;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public String getClabeInterbancaria() {
        return clabeInterbancaria;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatoFechaVen = DateTimeFormatter.ofPattern("MM/YY");
        DateTimeFormatter formatoFechaCreacion = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        String fechaC = formatoFechaCreacion.format(fechaCreacion);
        String fechaVen = formatoFechaVen.format(fechaVencimiento);
        return String.format("Núm.Tarjeta: %s\nFecha de Creación: %s\nFecha de Vencimiento: %s",numeroTarjeta,fechaC,fechaVen);
    }

    private boolean simplicity(double saldo) {
        return false;
    }

    private boolean platino() {
        return false;
    }

    private boolean oro() {
        return false;
    }
}
