import Banco.Menu;
import Banco.Banco;
import Banco.utils.SolicitudTarjetaCredito;
import Banco.utils.TipoTarjetaCredito;
import Usuarios.Cliente;
import Usuarios.Inversionista;
import Usuarios.utils.Rol;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {


        Banco bancoMadero = new Banco();
        Banco bancoAcueducto = new Banco();
        Inversionista inversionista=new Inversionista("Alejandro","Montejano","Diaz","Morelia","Michoacan","MODA133545","Calle desconocida",2004, LocalDate.now(),"Moda904803","Mario bros","1234");
        bancoMadero.personas.get(Rol.INVERSIONISTA).add(inversionista);
        Cliente cliente=new Cliente("Alejandro","Montejano","Diaz","Morelia","Michoacan","MODA133545","Calle desconocida",2004, LocalDate.now(),"Moda904803","Elpro","1234");
        bancoMadero.personas.get(Rol.CLIENTE).add(cliente);
        SolicitudTarjetaCredito solicitudTarjetaCredito=new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Simplicity);
        bancoMadero.solicitudes.add(solicitudTarjetaCredito);
        Inversionista inversionista1=new Inversionista("Andrea","Duran","Martinez","Morelia","Michoacan","DUMA238732545","Calle desconocida",2004, LocalDate.now(),"DUMA857345","Canelita","1234");
        bancoAcueducto.personas.get(Rol.INVERSIONISTA).add(inversionista1);
        Cliente cliente1=new Cliente("Andrea","Duran","Martinez","Morelia","Michoacan","DUMA238732545","Calle desconocida",2004, LocalDate.now(),"DUMA857345","Bioshinin","1234");
        bancoAcueducto.personas.get(Rol.CLIENTE).add(cliente1);
        SolicitudTarjetaCredito solicitudTarjetaCredito1=new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Simplicity);
        bancoAcueducto.solicitudes.add(solicitudTarjetaCredito1);
        bancoAcueducto.mostrarClientes();
        bancoMadero.mostrarClientes();
    }
}