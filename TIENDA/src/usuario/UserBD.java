package usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

public class UserBD {
	private static final int MAX_SIZE = 2;
	private static String[] usuarios = new String[MAX_SIZE];
	private static String[] contrasen = new String[MAX_SIZE];
	private static int [] roles = new int [MAX_SIZE];
	public static void cargarUsuarios() {
		Connection conn = null;
		Statement stmt = null;
		try {
//Paso 1: Cargar el driver JDBC.
			Class.forName("com.mysql.jdbc.Driver").newInstance();
// Paso 2: Conectarse a la Base de Datos utilizando la clase Connection
			String userName = "root";
			String password = "admin";
//URL de la base de datos(equipo, puerto, base de datos)
			String url = "jdbc:mysql://localhost/tiendalibros";
			conn = DriverManager.getConnection(url, userName, password);
// Paso 3: Crear sentencias SQL, utilizando objetos de tipo Statement
			stmt = conn.createStatement();
// Paso 4: Ejecutar las sentencias SQL a trav´es de los objetos Statement
			String sqlStr = "select usuario, password, rol from usuarios;";
			ResultSet rset = stmt.executeQuery(sqlStr);
// Paso 5: Procesar el conjunto de registros resultante utilizando ResultSet
			int count = 0;
			while (rset.next()) {
				usuarios[count] = rset.getString("usuario");
				contrasen[count] = rset.getString("password");
				roles[count] =  rset.getInt("rol");
				count++;
			}
// Cerramos el resto de recursos
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	protected static String hashearPass(String password) {
		boolean correcto=true;
		String url="jdbc:mysql://localhost/tiendalibros";
		String user="root";
		String pass="admin";
		String hashPash="";
		Connection conn=null;
		Statement stmt = null;
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
					conn = DriverManager.getConnection(url, user, pass);
						stmt = conn.createStatement();
						String queryHash = "SELECT password('"+password+"') AS hash";

						ResultSet result=stmt.executeQuery(queryHash);
						while(result.next()) {
							hashPash=result.getString("hash");
						}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		return hashPash;
	}
	
	protected static int obtenerRolDeUsuario(String usuario,String password) {
		int rol=-1;
		String url="jdbc:mysql://localhost/tiendalibros";
		String user="root";
		String pass="";
		Connection conn=null;
		Statement stmt = null;
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
					conn = DriverManager.getConnection(url, user, pass);
						stmt = conn.createStatement();
						String queryHash = "SELECT password('"+password+"') AS hash";
						String hashPash="";
						ResultSet result=stmt.executeQuery(queryHash);
						while(result.next()) {
							hashPash=result.getString("hash");
						}
						String sqlQuery = "SELECT rol FROM usuarios WHERE (usuario='"+usuario+"') AND (pass='"+hashPash+"');";
						ResultSet rs = stmt.executeQuery(sqlQuery);
						while(rs.next()) {
							rol=rs.getInt("rol");
						}
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally {
				try {
					conn.close();
					stmt.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
				
			}
		return rol;
	}
	
	public static String [] getUsuario() {
		return usuarios;
	}

	public void setUsuario(String usuario) {
		//this.usuario = usuario;
	}

	public static String [] getPassword() {
		return contrasen;
	}

	public void setPassword(String pass) {
		//this.pass = pass;
	}

	public void setRol(int rol) {
		//this.rol=rol;
	}

	public static int [] getRol() {
		return roles;
	}
}
