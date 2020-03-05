package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import pojo.Movimiento;

public class MovimientoDAOImpl implements MovimientoDAO{

	
	

	@Override
	public void llenarArrayMov(ArrayList<Movimiento> ar, String nc1, String nc2, String cantidad) {
		
		java.sql.Date fecha=new java.sql.Date(Calendar.getInstance().getTime().getTime());
		
		ar.add(new Movimiento(nc1, nc2, cantidad, fecha));
		
	}

	@Override
	public void volcarArrayMov(ArrayList<Movimiento> ar, Connection conn) {
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
