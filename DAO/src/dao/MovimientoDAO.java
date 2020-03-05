package dao;

import java.sql.Connection;
import java.util.ArrayList;

import pojo.Movimiento;


public interface MovimientoDAO {
	public void llenarArrayMov(ArrayList<Movimiento> ar, String nc1, String nc2,String cantidad);
	public void volcarArrayMov(ArrayList<Movimiento> ar, Connection conn);					
	public String verArrayMov2(ArrayList<Movimiento> ar);
}

