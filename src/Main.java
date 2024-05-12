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
        Menu.iniciarObjetos();
        Menu.seleccionarBanco();
    }
}