import Banco.Tarjetas.TarjetaCredito;
import Banco.Tarjetas.TarjetaDebito;
import Banco.utils.Generador;

import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String RFC;
        String nombre, apellidoPaterno, apellidoMaterno;
        nombre = "Jafet";
        apellidoPaterno = "Santoyo";
        apellidoMaterno = "Benites";
        LocalDate fechaNacimiento = LocalDate.of(1997, 1, 30);
        char sexo = 'H';
        String estado = "Michoacán";
        RFC = Generador.generarRFC(nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento);
        System.out.println("RFC: " + RFC);
        System.out.println();
        String CURP = Generador.generarCURP(nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento,sexo,estado);
        System.out.println("CURP: " + CURP);
        System.out.println();
        int claveDebito = 5073;
        int claveCredito = 4072;
        TarjetaDebito tarjetaDebito = new TarjetaDebito(claveDebito);
        System.out.println("\tTarjeta de débito:");
        System.out.println(tarjetaDebito.toString());
        System.out.println();
        System.out.println("\tTarjetas de Crédito:");
        TarjetaCredito tarjeta = new TarjetaCredito(claveCredito,60000);
        System.out.println(tarjeta.toString());
        System.out.println();
        TarjetaCredito tarjeta2 = new TarjetaCredito(claveCredito,150000);
        System.out.println(tarjeta2.toString());
        System.out.println();
        TarjetaCredito tarjeta3 = new TarjetaCredito(claveCredito,400000);
        System.out.println(tarjeta3.toString());
    }
}