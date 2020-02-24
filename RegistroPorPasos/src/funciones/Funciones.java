package funciones;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Funciones {
	public static Connection conexion() {
		Connection conn = null;
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();

			String userName = "root";
			String password = "admin";

			String url = "jdbc:mysql://localhost/registro_pasos";
			conn = DriverManager.getConnection(url, userName, password);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return conn;
	}
	
	public static void insertarRegistro(Connection conn,String usuario, String apellido, String fecha, String departamento, String salario, String comentarios, String cuenta2, String genero, String exo, String cosLimpio,String hijLimpio ) throws SQLException{
		Statement stmt = null;
		
		stmt = conn.createStatement();
		
		String sqlStr = "INSERT INTO usuarios VALUES('" + usuario + "',"+ "'" + apellido + "',"+ "'" + fecha + "',"+ "'" +departamento+ "',"+ "'" +salario+ "',"+ "'" +comentarios+ "',"+ "'" +cuenta2+ "',"+ "'" +genero+ "',"+ "'" +exo+ "',"+ "'" +cosLimpio+ "',"+ "'" +hijLimpio+ "')";
		
		stmt.executeUpdate(sqlStr);
		
		

	}
}
