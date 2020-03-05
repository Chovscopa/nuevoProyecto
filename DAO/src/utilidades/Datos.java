package utilidades;

import pojo.Movimiento;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
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
	
	public static void llenarArrayMov(ArrayList<Movimiento> ar, String nc1, String nc2,String cantidad) {
		
		
		java.sql.Date fecha=new java.sql.Date(Calendar.getInstance().getTime().getTime());
		
		ar.add(new Movimiento(nc1, nc2, cantidad, fecha));
		
			
	}
	
	


	public static void volcarArrayMov(ArrayList<Movimiento> ar, Connection conn) {
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			for(int i=0;i<ar.size();i++) {
				String sqlStr = "INSERT INTO movimientos VALUES(null, '" + ar.get(i).getFecha() + "',"+ "'" + ar.get(i).getCuentaOrigen() + "',"+ "'" + ar.get(i).getCuentaDestino() + "',"+ "'" +ar.get(i).getCantidad()+ "')";
				
				stmt.executeUpdate(sqlStr);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		

	}
	
	public static void verArrayMov(ArrayList<Movimiento> ar) {
		
		Iterator<Movimiento> itrMovimiento = ar.iterator();
		while (itrMovimiento.hasNext()) {
			Movimiento mov = itrMovimiento.next();
			System.out.println(mov.getCuentaOrigen() + "-" + mov.getCuentaDestino() + "-"+ mov.getCantidad() + "-" + mov.getFecha());
		}

	}
	
	public static String verArrayMov2(ArrayList<Movimiento> ar) {
		String r="";
		Iterator<Movimiento> itrMovimiento = ar.iterator();
		while (itrMovimiento.hasNext()) {
			Movimiento mov = itrMovimiento.next();
			r+=mov.getCuentaOrigen() + "-" + mov.getCuentaDestino() + "-"+ mov.getCantidad() + "-" + mov.getFecha()+"<br>";
		}
		return r;

	}	
	
	    
	
	   
	
}