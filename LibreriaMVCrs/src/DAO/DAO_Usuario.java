package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.annotation.Resource;
import javax.sql.DataSource;

import interfaz.InterfazUsuario;
import modelos.*;


public class DAO_Usuario implements InterfazUsuario{

	@Resource(name="jdbc/tiendalibros")
	private DataSource pool;
	
	public DAO_Usuario(DataSource pool) {
		this.pool=pool;
	}

	@Override
	public void aniadirUsuario(String user, String pass) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			
			String sql = "INSERT INTO usuarios VALUES (?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, user);
			pst.setString(2, pass);
			//pst.setInt(3, usuario.getRol());
			//pst.setString(4, usuario.getPreguntaSecreta());
			//pst.setString(5, usuario.getRespuestaSecreta());
			pst.execute();
			
			if (pst != null)
				pst.close();
			if (con != null)
				con.close();		
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	

	@Override
	public void borrarUsuario(String user, String pass) {

		Connection conn = null;
		Statement stmt = null;
		try {

			conn = pool.getConnection();
			stmt = conn.createStatement();
			
			String sqlStr = "DELETE FROM usuarios WHERE usuario = "+user+" and password="+pass+";";
			stmt.executeUpdate(sqlStr);
			
			// Cerramos el resto de recursos
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		} catch (Exception ex){
			ex.printStackTrace();
		} 
		
	}

	@Override
	public void modificarUsuario(String user, String pass){
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



}
