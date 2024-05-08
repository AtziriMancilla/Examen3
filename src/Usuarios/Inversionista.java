package Usuarios;

import Usuarios.utils.DatosComun;
import Usuarios.utils.Inversion;
import Usuarios.utils.Rol;
import utils.UsuarioEnSesion;

import java.time.LocalDate;
import java.util.ArrayList;

public class Inversionista extends Persona{
    private ArrayList<Inversion> inversiones = new ArrayList<>();

    public Inversionista(String nombre, String apellidoPaterno, String apellidoMaterno, String ciudad, String estado, String curp, String direccion, LocalDate fechaNacimiento, Rol rol,String RFC, String contrasena) {
        super(nombre, apellidoPaterno, apellidoMaterno, ciudad, estado, curp, direccion, fechaNacimiento, rol,RFC, contrasena);
    }
    public ArrayList<Inversion> getInversiones() {
        return inversiones;
    }
    public static void registrarInversionista(){
        ArrayList<String> datosComun = DatosComun.registrarDatosComun(Rol.INVERSIONISTA);
        String nombre = datosComun.get(0);
        String apellidoPaterno = datosComun.get(1);
        String apellidoMaterno = datosComun.get(2);
        String ciudad = datosComun.get(3);
        String estado = datosComun.get(4);
        String CURP = datosComun.get(5);
        String direccion = datosComun.get(6);
        String fechaNacimientoCadena= datosComun.get(7);
        String RFC = datosComun.get(8);
        String contrasena = datosComun.get(9);

        ///fecha nacimiento cadena viene en formato ("dd/MM/YYYY")
        String diacadena = fechaNacimientoCadena.substring(0,2);
        String mescadena = fechaNacimientoCadena.substring(3,5);
        String aniocadena = fechaNacimientoCadena.substring (6,10);
        int dia = Integer.parseInt(diacadena);
        int mes = Integer.parseInt(mescadena);
        int anio = Integer.parseInt(aniocadena);
        //conversion a LocalDate
        LocalDate fechaNacimiento = LocalDate.of(anio,mes, dia);
        Rol rol = Rol.INVERSIONISTA;
        Inversionista inversionista = new Inversionista(nombre, apellidoPaterno, apellidoMaterno, ciudad, estado, CURP, direccion, fechaNacimiento, rol, RFC, contrasena);
        //falta agregar comando para añadir a la lista hashmap de su correspondiente sucursal.
    }
    public static void modificarInversionista(){

    }
    public static void eliminarInversionista(){

    }
    public static void realizarInversion(){
        Persona persona = UsuarioEnSesion.getInstancia().getUsuarioActual();
        Inversionista inversionista = (Inversionista) persona;
        inversionista.realizarInversion();
    }

    public void agregarInversion(Inversion inversion){
        inversiones.add(inversion);
    }

    public static void mostrarInversionistas(){}
    @Override
    public String toString() {
        return super.toString();
    }
}
