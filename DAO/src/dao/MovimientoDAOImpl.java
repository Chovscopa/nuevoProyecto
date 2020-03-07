package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import utilidades.*;
import pojo.Movimiento;

public class MovimientoDAOImpl implements MovimientoDAO{

	
	

	@Override
	public void llenarArrayMov(ArrayList<Movimiento> ar, String nc1, String nc2, String cantidad) {
		
		java.sql.Date fecha=new java.sql.Date(Calendar.getInstance().getTime().getTime());
		
		ar.add(new Movimiento(nc1, nc2, cantidad, fecha));
		
	}

	@Override
	
	public void volcarArrayMov(ArrayList<Movimiento> ar, Connection conn) {
		
		
		try {
			//stmt = conn.createStatement();
			for(int i=0;i<ar.size();i++) {
				//String sqlStr = "INSERT INTO movimientos VALUES(null, '" + ar.get(i).getFecha() + "',"+ "'" + ar.get(i).getCuentaOrigen() + "',"+ "'" + ar.get(i).getCuentaDestino() + "',"+ "'" +ar.get(i).getCantidad()+ "')";
				String sqlStr = "INSERT INTO movimientos VALUES(null, ? , ? , ? , ? )";

				PreparedStatement pstmt=conn.prepareStatement(sqlStr);
				pstmt.setDate(1, ar.get(i).getFecha());
				pstmt.setString(2, ar.get(i).getCuentaOrigen());
				pstmt.setString(3, ar.get(i).getCuentaDestino());
				pstmt.setString(4,ar.get(i).getCantidad());
				int rset = pstmt.executeUpdate();	
				if (pstmt != null)
					pstmt.close();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	@Override
	public String verArrayMov2(ArrayList<Movimiento> ar) {
		String r="";
		Iterator<Movimiento> itrMovimiento = ar.iterator();
		while (itrMovimiento.hasNext()) {
			Movimiento mov = itrMovimiento.next();
			r+=mov.getCuentaOrigen() + "-" + mov.getCuentaDestino() + "-"+ mov.getCantidad() + "-" + mov.getFecha()+"<br>";
		}
		return r;
	}
	
	
	
}
