package interfaz;

import java.util.ArrayList;

import modelos.Libro;

public interface InterfazLibro {
	public ArrayList<Libro> getLibros();
	public void agregarLibro(int id,String titulo, String autor, float precio, int c);
	public void borrarLibro(int id);
	public void modificarLibro(int id,String titulo, String autor, float precio, int c);
}
