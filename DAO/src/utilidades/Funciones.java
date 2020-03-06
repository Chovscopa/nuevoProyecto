package utilidades;


import java.sql.Connection;
import java.sql.DriverManager;


public class Funciones {
	public static Connection conexion() {
		Connection conn = null;
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();

			String userName = "root";
			String password = "";

			String url = "jdbc:mysql://localhost/examenservidor";
			conn = DriverManager.getConnection(url, userName, password);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return conn;
	}
	
	
}
