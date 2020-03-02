package generacionDinamica;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Datos {

	
	public static String[] datosCuentas = new String[7] ;
	
	public static String[] datosCuentasDestino = new String[7] ;
	static String n;

	public static void cargarDatosOrigen() {
		Connection conn = null;
		Statement stmt = null;
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();

			String userName = "root";
			String password = "admin";

			String url = "jdbc:mysql://localhost/examenservidor";
			conn = DriverManager.getConnection(url, userName, password);

			stmt = conn.createStatement();

			String sqlStr = "select * from cuentas";
			ResultSet rset = stmt.executeQuery(sqlStr);
			
			int i = 0;
			
			while (rset.next()) {
				datosCuentas[i] =(String) rset.getString("ncuenta");
				i++;
			}

			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	

	public static LinkedHashMap<String,String> arrayCuentas = new LinkedHashMap<String,String>();

	static {
		cargarDatosOrigen();
			for(int i=0;i<datosCuentas.length;i++) {
				
				arrayCuentas.put(datosCuentas[i], datosCuentas[i]);
				
			}

	}
	
	public static LinkedHashMap<String,String> arrayCuentasDestino;

	static {
		arrayCuentasDestino = new LinkedHashMap<String,String>();
	   
			for(int i=0;i<datosCuentas.length;i++) {
				arrayCuentasDestino.put(datosCuentas[i], datosCuentas[i]);
			}

	}
	public static BigDecimal saldo1(String ncuentaParam) {
		BigDecimal saldo=new BigDecimal("0.0");
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String userName = "root";
			String password = "admin";
			String url = "jdbc:mysql://localhost/examenservidor";
			conn = DriverManager.getConnection(url, userName, password);
			stmt = conn.createStatement();
			String sqlStr = "select * from cuentas where ncuenta='"+ncuentaParam+ "'";
			
			ResultSet rset = stmt.executeQuery(sqlStr);			
			if (rset.next()) {	
				saldo= rset.getBigDecimal("saldo");
				
				 
			}
			saldo.toString();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return saldo;
	}
	
	public static int transferenciaRestar(Connection conn,String nc1,String cantidad) {
		int sw=0;
		
		Statement stmt = null;
		try {
			
			stmt = conn.createStatement();
			
			String sqlStr = "update cuentas set saldo=saldo-'" +cantidad +"'where ncuenta='"+nc1+ "'";
			
			int rset = stmt.executeUpdate(sqlStr);			
			
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return sw;
	}
	
	public static int transferenciaSumar(Connection conn, String nc2,String cantidad) {
		int sw=0;
		
		Statement stmt = null;
		try {
			
			stmt = conn.createStatement();
			
			String sqlStr = "update cuentas set saldo=saldo+'" +cantidad +"'where ncuenta='"+nc2+ "'";
			
			int rset = stmt.executeUpdate(sqlStr);			
			
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return sw;
	}
	
	
}


























/*
public static String saldo1(String ncuentaParam) {
		double saldo=0.00;
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String userName = "root";
			String password = "admin";
			String url = "jdbc:mysql://localhost/examenservidor";
			conn = DriverManager.getConnection(url, userName, password);
			stmt = conn.createStatement();
			String sqlStr = "select * from cuentas where ncuenta='"+ncuentaParam+ "'";
			
			ResultSet rset = stmt.executeQuery(sqlStr);			
			if (rset.next()) {	
				saldo= rset.getDouble("saldo");
	 
			}

			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		String saldo2 = String.valueOf(saldo);
		return saldo2;
	}
*/