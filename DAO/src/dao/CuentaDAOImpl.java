package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import pojo.Cuenta;
import utilidades.Funciones;

public class CuentaDAOImpl implements CuentaDAO{

	

	@Override
	public int transferencia(Connection conn,String nc1,String nc2,String cantidad) {
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

	@Override
	public BigDecimal saldo1(Connection conn, String ncuentaParam) {
		BigDecimal saldo=new BigDecimal("0.0");
		 
		Statement stmt = null;
		try {
			
			stmt = conn.createStatement();
			String sqlStr = "select * from cuentas where ncuenta='"+ncuentaParam+ "'";
			
			ResultSet rset = stmt.executeQuery(sqlStr);			
			if (rset.next()) {	
				saldo= rset.getBigDecimal("saldo");
				
				 
			}
			
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return saldo;
	}

	@Override
	public boolean saldoInsuficiente(Connection conn, String nc1, String cantidad) {
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
