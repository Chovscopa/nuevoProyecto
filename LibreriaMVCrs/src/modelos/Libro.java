package modelos;

public class Libro {
	public int id=0;
	public String titulo="";
	public String autor="";
	public float precio=0;
	public int cantidad=0;
	
	
	public Libro(int id, String titulo, String autor, float precio, int cantidad) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.precio = precio;
		this.cantidad = cantidad;
	}


	public Libro(String titulo, String autor, float precio, int cantidad) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.precio = precio;
		this.cantidad = cantidad;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getAutor() {
		return autor;
	}


	public void setAutor(String autor) {
		this.autor = autor;
	}


	public float getPrecio() {
		return precio;
	}


	public void setPrecio(float precio) {
		this.precio = precio;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	@Override
	public String toString() {
		return "Libro id= " + id + ", titulo= " + titulo + ", autor= " + autor + ", precio= " + precio + ", cantidad= "
				+ cantidad ;
	}
	
	
	
}
