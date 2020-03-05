package pojo;
import java.sql.Date;

public class Movimiento {
	String cuentaOrigen;
	String cuentaDestino;
	String cantidad;
	java.sql.Date fecha;
	
	public Movimiento(String cuentaOrigen, String cuentaDestino, String cantidad, Date fecha) {
		super();
		this.cuentaOrigen = cuentaOrigen;
		this.cuentaDestino = cuentaDestino;
		this.cantidad = cantidad;
		this.fecha = fecha;
	}
	public String getCuentaOrigen() {
		return cuentaOrigen;
	}
	public void setCuentaOrigen(String cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}
	public String getCuentaDestino() {
		return cuentaDestino;
	}
	public void setCuentaDestino(String cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	public java.sql.Date getFecha() {
		return fecha;
	}
	public void setFecha(java.sql.Date fecha) {
		this.fecha = fecha;
	}
	
	
}
