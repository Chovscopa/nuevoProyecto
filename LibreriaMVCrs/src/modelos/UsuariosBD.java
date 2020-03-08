package modelos;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.sql.DataSource;

import org.apache.tomcat.util.codec.binary.Base64;

public class UsuariosBD {
	
	
	private static final int MAX_SIZE=30;
	private static String[] usuarios=new String[MAX_SIZE];
	private static String[] pass=new String[MAX_SIZE];
	
private static DataSource pool;
	
	public UsuariosBD(DataSource pool) {
		this.pool=pool;
	}

/*
	public static Connection conexion() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String userName="tienda";
			String password="tienda";
			String url="jdbc:mysql://localhost/tiendalibros";
			conn = DriverManager.getConnection(url, userName, password);
		} catch (Exception ex){
			ex.printStackTrace();
			System.out.println("ErrorC");
		}
		return conn;
	}*/
	
	public static boolean comprobarUsuario(String usuario, String pass) {

	try {
		Connection conn=pool.getConnection();
		PreparedStatement pst=conn.prepareStatement("SELECT * FROM usuarios where usuario=? and password=?");
		pst.setString(1, usuario);
		pst.setString(2, pass);

		ResultSet rs=pst.executeQuery();

		//Si encuentra un resultado devuelve true, sino false
		if(rs.absolute(1)) {
			return true;
		}
		else return false;

		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error");
			return false;
		}
	}
	
	
	
	public static void cargarUsuarios(){
		Connection conn = null;
		Statement stmt = null;
		try {

			conn=pool.getConnection();
			stmt=conn.createStatement();
		
			String sqlStr = "select * from usuarios;";
			ResultSet rset = stmt.executeQuery(sqlStr);
			// Paso 5: Procesar el conjunto de registros resultante utilizando ResultSet
			int count = 0;
			while(rset.next()) {
				usuarios[count]= rset.getString("usuario");
				pass[count]=rset.getString("password");
				count++;
			}

			// Cerramos el resto de recursos
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static void modificarUsuario(String user, String pass){
		Connection conn = null;
		Statement stmt = null;
		try {

			conn = pool.getConnection();
			stmt = conn.createStatement();
			
			String sqlStr = "UPDATE usuarios SET usuario = "+user+" where password="+pass+";";
			stmt.executeUpdate(sqlStr);
			
			// Cerramos el resto de recursos
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		} catch (Exception ex){
			ex.printStackTrace();
		} 
	}
	
	public static void modificarContra(String user, String pass){
		Connection conn = null;
		Statement stmt = null;
		try {

			conn = pool.getConnection();
			stmt = conn.createStatement();
			
			String sqlStr = "UPDATE usuarios SET password = "+pass+" where usuario="+user+";";
			stmt.executeUpdate(sqlStr);
			
			// Cerramos el resto de recursos
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		} catch (Exception ex){
			ex.printStackTrace();
		} 
	}
	
	
	public static void recuperarContra(String pass, String pass2){
		Connection conn = null;
		Statement stmt = null;
		try {

			conn = pool.getConnection();
			stmt = conn.createStatement();
			
			String sqlStr = "UPDATE usuarios SET password = '"+pass2+"' where password='"+pass+"';";
			stmt.executeUpdate(sqlStr);
			
			// Cerramos el resto de recursos
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		} catch (Exception ex){
			ex.printStackTrace();
		} 
	}
	
	
	public static boolean recuperarUsu(String user){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rt=null;
		String usu="";
		try {

			conn = pool.getConnection();
			stmt = conn.createStatement();
			
			String sqlStr = "select usuario from usuarios where usuario='"+user+"'";
			rt=stmt.executeQuery(sqlStr);
			
			if(rt.next()) {
				usu=rt.getString("usuario");
			}
			
			// Cerramos el resto de recursos
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
			
			if(usu!="") {
				return true;
			}else {
				return false;
			}
			
		} catch (Exception ex){
			ex.printStackTrace();
			return false;
		} 

	}
	
	public static int tamanyo() {
		return usuarios.length;
	}
	/** Devuelve el tulo del libro idLibro*/
	public static String getUsuario(int id) {
		return usuarios[id];
	}
	/** Devuelve el autor del libro idLibro */
	public static String getPass(int id) {
		return pass[id];
	}

 }
