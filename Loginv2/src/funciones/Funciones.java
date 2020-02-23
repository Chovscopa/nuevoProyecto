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

			String url = "jdbc:mysql://localhost/tienda4";
			conn = DriverManager.getConnection(url, userName, password);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return conn;
	}

	public static boolean checkUsuario(Connection conn, String nombre, String passss) throws SQLException {
		Statement stmt = null;
		boolean sw = false;
		stmt = conn.createStatement();

		String sqlStr = "select * from usuarios where nombre='" + nombre + "'" + "AND pass='" + passss + "'";
		ResultSet rset = stmt.executeQuery(sqlStr);
	
		sw = rset.next();
		
		return sw;
	}

	public static boolean checkPregunta(Connection conn, String nombre, String respuesta) throws SQLException {
		Statement stmt = null;
		boolean sw = false;
		stmt = conn.createStatement();

		String sqlStr = "select * from usuarios where nombre='" + nombre + "'" + "AND respuesta='" + respuesta + "'";

		ResultSet rset = stmt.executeQuery(sqlStr);
			
		sw = rset.next();

		return sw;
	}

	public static String mostarPregunta(Connection conn, String nombre) throws SQLException {
		Statement stmt = null;

		stmt = conn.createStatement();

		String sqlStr = "select * from usuarios where nombre='" + nombre + "'";
		String cc = null;
		ResultSet rset = stmt.executeQuery(sqlStr);

		if(rset.next()) {
			cc = (String) rset.getString("pregunta");
		}

		return cc;
	}

	
	public static boolean updateRegistro(Connection conn, String nombre, String clavenueva, String confirmarclavenueva)	throws SQLException {
		Statement stmt = null;
		boolean sw = false;
		stmt = conn.createStatement();

		String sqlStr = "UPDATE usuarios SET pass = '" + confirmarclavenueva + "'" + "WHERE nombre ='" + nombre + "'";
		if (clavenueva.equals(confirmarclavenueva)) {
			int rset = stmt.executeUpdate(sqlStr);

			sw = true;
		}
		return sw;
	}

	public static boolean checkUsuario1(Connection conn, String nombre, String passss) throws SQLException {
		Statement stmt = null;
		boolean sw = false;
		stmt = conn.createStatement();


		String sqlStr = "select * from usuarios where nombre='" + nombre + "'" + "AND pass='" + passss + "'";
		ResultSet rset = stmt.executeQuery(sqlStr);
		
		sw = rset.next();

		return sw;
	}

	public static boolean checkUsuario2(PrintWriter out, Connection conn, String nombre, String passss,
			String clavenueva, String confirmarclavenueva) throws SQLException {
		Statement stmt = null;
		boolean sw = false;
		stmt = conn.createStatement();

		String sqlStr = "select * from usuarios where nombre='" + nombre + "'" + "AND pass='" + passss + "'";

		ResultSet rset = stmt.executeQuery(sqlStr);
		
		sw = rset.next();

		return sw;
	}
}
/*public static String mostarPregunta(Connection conn, String nombre) throws SQLException {
		Statement stmt = null;

		try
		{
		stmt = conn.createStatement();

		String sqlStr = "select * from usuarios where nombre='" + nombre + "'";
		String cc = null;
		ResultSet rset = stmt.executeQuery(sqlStr);

		while (rset.next()) {
			cc = (String) rset.getString("pregunta");

		}
		}catch(SQLException e) {e.printStackTrace();}
		finally
		{
			try
			{
				if(stmt!=null) {stmt.close();}
				if(conn!=null) {conn.close();}
			}catch(SQLException e) {e.printStackTrace();}
		}

		return cc;
	}
	*/
