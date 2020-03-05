package pojo;

public class Cuenta {
	String ncuenta;
	double saldo;
	
	public Cuenta(String ncuenta, double saldo) {
		super();
		this.ncuenta = ncuenta;
		this.saldo = saldo;
	}
	public String getNcuenta() {
		return ncuenta;
	}
	public void setNcuenta(String ncuenta) {
		this.ncuenta = ncuenta;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	
}
