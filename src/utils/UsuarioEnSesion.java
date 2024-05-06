package utils;

import Usuarios.Persona;

public class UsuarioEnSesion {
    private static UsuarioEnSesion instancia;
    private Persona usuarioActual;
    private UsuarioEnSesion() {

    }
    public static UsuarioEnSesion getInstancia() {
        if (instancia == null) {
            instancia = new UsuarioEnSesion();
        }
        return instancia;
    }
    public Persona getUsuarioActual() {
        return usuarioActual;
    }
    public void setUsuario(Persona usuarioActual) {

        this.usuarioActual = usuarioActual;
    }
    public boolean hayUsuarioEnSesion() {
        return usuarioActual != null;
    }

    public void cerrarSesion() {
        instancia = null;
        usuarioActual = null;
    }
}
