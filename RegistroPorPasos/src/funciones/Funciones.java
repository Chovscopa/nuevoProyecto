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
			String password = "";

			String url = "jdbc:mysql://localhost/registro_pasos";
			conn = DriverManager.getConnection(url, userName, password);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return conn;
	}
	
	public static void insertarRegistro(Connection conn,String usuario, String apellido, String fecha, String departamento, String salario, String comentarios, String cuenta2) throws SQLException{
		Statement stmt = null;
		//boolean sw = false;
		stmt = conn.createStatement();
		//INSERT INTO usuarios VALUES(usuario,apellido,fecha,departamento,salario,cuenta);
		String sqlStr = "INSERT INTO usuarios VALUES('" + usuario + "',"+ "'" + apellido + "',"+ "'" + fecha + "',"+ "'" +departamento+ "',"+ "'" +salario+ "',"+ "'" +comentarios+ "',"+ "'" +cuenta2+ "')";
		//String sqlStr = "INSERT INTO usuarios VALUES(" + usuario + ","+  apellido+  ","+  fecha + ","+ departamento+ ","+  salario+ ","+ comentarios+ ","+ cuenta2+ ")";
		stmt.executeUpdate(sqlStr);
		
		

	}
}
