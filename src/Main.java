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
        System.out.println(RFC);
        String CURP = Generador.generarCURP(nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento,sexo,estado);
        System.out.println(CURP);
        int clave = 5073;
        String numero = Generador.generarTarjeta(clave);
        System.out.println(numero);
    }
}