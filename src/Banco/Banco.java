package Banco;

import Banco.utils.SolicitudTarjetaCredito;
import Usuarios.*;
import Usuarios.utils.Inversion;
import Usuarios.utils.Rol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Banco {
    public static final HashMap<Rol, ArrayList<Persona>> personas = new HashMap<>();
    public static final ArrayList<SolicitudTarjetaCredito> solicitudes = new ArrayList<>();

    public Banco (){

    }
    public void agregarPersona(Persona persona, Rol rol){
        personas.put(rol, new ArrayList<Persona>());
        personas.get(rol).add(persona);
    }

    public Persona verificarInicioSesion(String usuario, String contrasena) {

        for (Map.Entry<Rol, ArrayList<Persona> > entry : personas.entrySet()){
            ArrayList<Persona> listaPersonas = entry.getValue();
            for(Persona personaActual : listaPersonas){
                if(personaActual.getNombre().equals(usuario)&& personaActual.getContrasena().equals(contrasena)){
                    return personaActual;
                }
            }
        }
        return null;
    }

    //Crear personas
    public void registrarCapturista(){
        Capturista.registrarCapturista();}
    public void registrarCliente(){
        Cliente.registrarCliente();
    }
    //Nota: quizá borrarlo definitivamente//public void registrarGerente(){Gerente.registrarGerente();}
    public void registrarEjecutivo(){Ejecutivo.registrarEjecutivo();}
    public void registrarInversionista(){Inversionista.registrarInversionista();}

    //mostrar
    public void mostrarCapturistas() {
        Capturista.mostrarCapturistas();
    }
    public void mostrarClientes() {
        Cliente.mostrarClientes();
    }
    public void mostrarEjecutivos() {Ejecutivo.mostrarEjecutivos();}
    public void mostrarEmpleados(){Empleado.mostrarEmpleados();}
    public void mostrarGerente(){
        Gerente.mostrarGerente();
    }
    public void mostrarInversionistas() {Inversionista.mostrarInversionistas();}

    //Eliminar
    public void borrarCapturista(){
        Capturista.borrarCapturista();
    }
    public void borrarCliente(){Cliente.borrarCliente();}
    public void borrarEjecutivo(){
        Ejecutivo.eliminarEjecutivo();
    }
    public void borrarGerente(){
        Gerente.eliminarGerente();
    }
    public void borrarInversionista(){
        Inversionista.eliminarInversionista();
    }

    //modificar
    public void modificarCapturista(){Capturista.modificarCapturista();}
    public void modificarCliente(){
        Cliente.modificarCliente();}
    public void modificarEjecutivo(){Ejecutivo.modificarEjecutivo();}
    public void modificarGerente(){Gerente.modificarGerente();}
    public void modificarInversionista(){Inversionista.modificarInversionista();}

    //Me imagino que también los metodos para las inversiones
    public void realizarInversion(){Inversion.realizarInversion();
    }
    public void mostrarInversiones(){Inversion.mostrarInversiones();
    }
    public void eliminarInversión(){Inversion.eliminarInversion();}
}
