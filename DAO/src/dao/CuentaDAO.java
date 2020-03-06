package dao;

import java.sql.Connection;
import java.sql.Statement;

import pojo.Cuenta;

public interface CuentaDAO {
	
	public int transferencia(Connection conn,String nc1,String nc2,String cantidad);
	
}
