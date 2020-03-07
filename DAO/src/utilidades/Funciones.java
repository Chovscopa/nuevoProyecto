package utilidades;


import java.sql.*;

import javax.activation.DataSource;

import org.apache.tomcat.*;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;




public class Funciones {
	/*
	private static BasicDataSource ds=null;
	

	
	public static BasicDataSource getDS() {
		if(ds==null) {
			ds=new BasicDataSource();
			ds.setDriverClassName("com.mysql.jdbc.Driver");
			ds.setUsername("root");
			ds.setPassword("admin");
			ds.setUrl("jdbc:mysql://localhost/examenservidor?useServerPrepStmts=true");
			ds.setInitialSize(50);
			ds.setMaxIdle(10);
			ds.setMaxTotal(20);
			ds.setMaxWaitMillis(5000);
		}
		return ds;
	}
	public static Connection conexion(){
		Connection conn=null;
		try {
			conn= getDS().getConnection();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return conn;
	}
	*/
	
	public static Connection conexion() {
		Connection conn = null;
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();

			String userName = "root";
			String password = "admin";

			String url = "jdbc:mysql://localhost/examenservidor?useServerPrepStmts=true";
			conn = DriverManager.getConnection(url, userName, password);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return conn;
	}
	
	
}
