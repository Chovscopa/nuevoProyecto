package utilidades;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.Statement;
import java.util.ArrayList;

import java.util.LinkedHashMap;


import utilidades.Funciones;

public class Datos {

	
	public static String[] datosCuentas = new String[7] ;
	//public static List<String> dC=new ArrayList<String>();
	
	public static String[] datosCuentasDestino = new String[7] ;
	//public static List<String> dCD=new ArrayList<String>();
	
	
	
	public static void cargarDatosOrigen(Connection conn) {
		
		Statement stmt = null;
		try {

			
			stmt = conn.createStatement();

			String sqlStr = "select * from cuentas";
			ResultSet rset = stmt.executeQuery(sqlStr);
			
			int i = 0;
			
			while (rset.next()) {
				datosCuentas[i] =(String) rset.getString("ncuenta");
				//dC.add(rset.getString("ncuenta"));
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
			cargarDatosOrigen(Funciones.conexion());
			for(int i=0;i<datosCuentas.length;i++) {
				
				arrayCuentas.put(datosCuentas[i], datosCuentas[i]);
				//arrayCuentas.put(dC.get(i), dC.get(i));
				
			}

	}
	
	public static LinkedHashMap<String,String> arrayCuentasDestino;

		static {
		arrayCuentasDestino = new LinkedHashMap<String,String>();
	   
			for(int i=0;i<datosCuentas.length;i++) {
				arrayCuentasDestino.put(datosCuentas[i], datosCuentas[i]);
				//arrayCuentasDestino.put(dC.get(i), dC.get(i));
			}

	}
		
	public static BigDecimal saldo1(Connection conn,String ncuentaParam) {
		BigDecimal saldo=new BigDecimal("0.0");
		 
		Statement stmt = null;
		try {
			
			stmt = conn.createStatement();
			String sqlStr = "select * from cuentas where ncuenta='"+ncuentaParam+ "'";
			
			ResultSet rset = stmt.executeQuery(sqlStr);			
			if (rset.next()) {	
				saldo= rset.getBigDecimal("saldo");
				
				 
			}
			//saldo.toString();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return saldo;
	}
	
	public static int transferencia(Connection conn,String nc1,String nc2,String cantidad) {
		int sw=0;
		
		Statement stmt = null;
		try {
			
			stmt = conn.createStatement();
			
			String sqlStr = "update cuentas set saldo=saldo-'" +cantidad +"'where ncuenta='"+nc1+ "'";
			
			int rset = stmt.executeUpdate(sqlStr);			
			
			if (stmt != null)
				stmt.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		///
		Statement stmt2 = null;
		try {
			
			stmt2 = conn.createStatement();
			
			String sqlStr = "update cuentas set saldo=saldo+'" +cantidad +"'where ncuenta='"+nc2+ "'";
			
			int rset = stmt2.executeUpdate(sqlStr);			
			
			if (stmt2 != null)
				stmt2.close();
			if (conn != null)
				conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return sw;
	}
	
	
	public static boolean saldoInsuficiente(Connection conn,String nc1,String cantidad) {
		boolean sw=false;
		
		BigDecimal saldo=saldo1(Funciones.conexion(),nc1);
		if(cantidad=="") {
			cantidad="0";
		}
        BigDecimal num = new BigDecimal(cantidad);
        
		
		if(saldo.compareTo(num)==-1) {
			sw=true;
		}
		
		return sw;
	}
	
	
	
	
	
	
	
	    
	
	   
	
}