package dao;

import java.math.BigDecimal;


import java.sql.*;




import utilidades.Funciones;

public class CuentaDAOImpl implements CuentaDAO{

	

	@Override
	public int transferencia(Connection conn,String nc1,String nc2,String cantidad) {
		int sw=0;

		try {
			String sqlStr = "update cuentas set saldo=saldo - ? where ncuenta = ? ";
			PreparedStatement pstmt=conn.prepareStatement(sqlStr);
			
			pstmt.setString(1,cantidad);
			pstmt.setString(2,nc1);
			
			int rset = pstmt.executeUpdate();			
			
			if (pstmt != null)
				pstmt.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		///
		
		try {
			String sqlStr2 = "update cuentas set saldo=saldo + ? where ncuenta = ? ";
			PreparedStatement pstmt2 = conn.prepareStatement(sqlStr2);
			
			pstmt2.setString(1,cantidad);
			pstmt2.setString(2,nc2);
		
			int rset2 = pstmt2.executeUpdate();			
			
			if (pstmt2 != null)
				pstmt2.close();
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
		 
		
		try {
			String sqlStr = "select * from cuentas where ncuenta= ? ";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			
			pstmt.setString(1, ncuentaParam);
			ResultSet rset = pstmt.executeQuery();			
			if (rset.next()) {	
				saldo= rset.getBigDecimal("saldo");
				
				 
			}
			
			if (pstmt != null)
				pstmt.close();
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
