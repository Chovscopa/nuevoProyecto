package sa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	static Connection conectar = null;
	static final String url = "jdbc:mysql://localhost/prueba";
	static final String usuario = "root";
	static final String password = "";

	public static void abrirConexion(){
	    try{
	        conectar = DriverManager.getConnection(url, usuario, password);
	        System.out.println("Conexión Exitosa");
	    }catch(SQLException ex){
	        System.out.println("Error al abrir Conexión: " + ex.getMessage());
	    }
	}
}
