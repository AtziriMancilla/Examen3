import Banco.Banco;
import Banco.Tarjetas.TarjetaCredito;
import Banco.Tarjetas.TarjetaDebito;
import Banco.utils.Generador;
import Banco.utils.SolicitudTarjetaCredito;
import Usuarios.Cliente;
import Usuarios.Ejecutivo;
import Usuarios.Inversionista;
import Usuarios.utils.Rol;

import java.time.LocalDate;
import java.time.LocalDateTime;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        String RFC;
//        String nombre, apellidoPaterno, apellidoMaterno;
//        nombre = "Jafet";
//        apellidoPaterno = "Santoyo";
//        apellidoMaterno = "Benites";
//        LocalDate fechaNacimiento = LocalDate.of(1997, 1, 30);
//        char sexo = 'H';
//        String estado = "Michoacán";
//        RFC = Generador.generarRFC(nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento);
//        System.out.println("RFC: " + RFC);
//        System.out.println();
//        String CURP = Generador.generarCURP(nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento,sexo,estado);
//        System.out.println("CURP: " + CURP);
//        System.out.println();
//        int claveDebito = 5073;
//        int claveCredito = 4072;
//        TarjetaDebito tarjetaDebito = new TarjetaDebito(claveDebito);
//        System.out.println("\tTarjeta de débito:");
//        System.out.println(tarjetaDebito.toString());
//        LocalDateTime fechaDebito = LocalDateTime.now();
//        tarjetaDebito.setFechaHoraUltimoMov(fechaDebito);
//        System.out.println(tarjetaDebito.fechaUltimoMov());
//        System.out.println();
//        System.out.println("\tTarjetas de Crédito:");
//        TarjetaCredito tarjeta = new TarjetaCredito(claveCredito,60000);
//        System.out.println(tarjeta.toString());
//        LocalDateTime fecha1 = LocalDateTime.now();
//        tarjeta.setFechaHoraUltimoMov(fecha1);
//        System.out.println(tarjeta.fechaUltimoMov());
//        System.out.println();
//        TarjetaCredito tarjeta2 = new TarjetaCredito(claveCredito,150000);
//        System.out.println(tarjeta2.toString());
//        LocalDateTime fecha2 = LocalDateTime.now();
//        tarjeta.setFechaHoraUltimoMov(fecha2);
//        System.out.println(tarjeta.fechaUltimoMov());
//        System.out.println();
//        TarjetaCredito tarjeta3 = new TarjetaCredito(claveCredito,400000);
//        System.out.println(tarjeta3.toString());
//        LocalDateTime fecha3 = LocalDateTime.now();
//        tarjeta.setFechaHoraUltimoMov(fecha3);
//        System.out.println(tarjeta.fechaUltimoMov());
        Banco banco=new Banco();
//        //Cliente.solicitarTarjetaCredito((Cliente) Banco.personas.get(Rol.CLIENTE).getFirst());
//        //Cliente.buscarCliente();
//        Ejecutivo.buscarEjecutivo();
//        Cliente.registrarCliente();
        SolicitudTarjetaCredito.verSolicitudesTarjetas();
    }
}