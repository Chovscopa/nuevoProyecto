package dao;

import java.sql.Connection;
import java.sql.Statement;

import pojo.Cuenta;

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

}
