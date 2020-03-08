package interfaz;

import java.util.ArrayList;
import java.util.Hashtable;

import modelos.*;

public interface InterfazUsuario {

	public void aniadirUsuario(String user, String pass);
	public void borrarUsuario(String user, String pass);
	//public String parsearContrasena(String contrasena);
	public void modificarUsuario(String user, String pass);
}
