package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import modelos.LibrosBD;
import modelos.Libro;

public class DAO_Libro implements interfaz.InterfazLibro{
	
	@Resource(name="jdbc/tiendalibros")
	private DataSource pool;
	
	public DAO_Libro(DataSource pool) {
		this.pool=pool;
	}

	/*public DAO_Libro() throws ServletException{
		try {
			ml=new LibrosBD(pool);
		}catch(Exception e) {
			throw new ServletException(e);
		}
	}*/
	
	@Override
	public ArrayList<Libro> getLibros() {
		ArrayList<Libro> librosConsultados = new ArrayList<Libro>();
		Connection con = null;
		Statement st = null;
		try {
			con=pool.getConnection();
			String sql = "SELECT id,titulo,autor,precio,cantidad FROM libros;";
			st=con.createStatement();
			ResultSet resultado = st.executeQuery(sql);
			while(resultado.next()) {
				librosConsultados.add(new Libro(resultado.getInt("id"),resultado.getString("titulo"),resultado.getString("autor"),resultado.getFloat("precio"),resultado.getInt("cantidad")));
			}
			if (st != null)
				st.close();
			if (con != null)
				con.close();
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return librosConsultados;
	}


/*
	public static void agregarLibro(int id,String titulo, String autor, float precio, int c){
		Connection conn = null;
		Statement stmt = null;
		try {

			conn = pool.getConnection();
			stmt = conn.createStatement();
			
			String sqlStr = "INSERT INTO libros (id, titulo, autor, precio, cantidad) VALUES ("+id+", '"+titulo+"','"+autor+"',"+precio+","+c+");";
			stmt.execute(sqlStr);
			
			// Cerramos el resto de recursos
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		} catch (Exception ex){
			ex.printStackTrace();
			System.out.println("Error al insertar");
		} 
	}
	
	
		public static void borrarLibro(int id){
		Connection conn = null;
		Statement stmt = null;
		try {

			conn = pool.getConnection();
			stmt = conn.createStatement();
			
			String sqlStr = "DELETE FROM libros WHERE id="+id;
			stmt.executeUpdate(sqlStr);
			
			// Cerramos el resto de recursos
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		} catch (Exception ex){
			ex.printStackTrace();
		} 
	}
	*
	*/
	
	
	@Override
	public void borrarLibro(int id) {
			Connection con = null;
			PreparedStatement pst = null;
			try {

				con = pool.getConnection();
				
				String sql = "DELETE FROM libros WHERE id=?";
				pst = con.prepareStatement(sql);
				pst.setInt(1, id);
				pst.execute();
				
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception ex){
				ex.printStackTrace();
			} 
		
	}
	
	
	@Override
	public void modificarLibro(int id, String titulo, String autor, float precio, int c){
		Connection conn = null;
		Statement stmt = null;
		try {

			conn = pool.getConnection();
			stmt = conn.createStatement();
			
			String sqlStr = "UPDATE libros SET titulo='"+titulo+"', autor='"+autor+"', precio="+precio+", cantidad="+c+" where id="+id;
			stmt.executeUpdate(sqlStr);
			
			// Cerramos el resto de recursos
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		} catch (Exception ex){
			ex.printStackTrace();
		} 
	}

	@Override
	public void agregarLibro(int id,String titulo, String autor, float precio, int c) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pst = null;
		try {
			
			con = pool.getConnection();
		
			String sql = "INSERT INTO libros VALUES (?,?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setString(2, titulo);
			pst.setString(3, autor);
			pst.setFloat(4, precio);
			pst.setInt(5, c);
			pst.execute();
			
			
			//String sqlStr = "INSERT INTO libros (id, titulo, autor, precio, cantidad) VALUES ("+id+", '"+titulo+"','"+autor+"',"+precio+","+c+");";
			//stmt.execute(sqlStr);
			
			// Cerramos el resto de recursos
			if (pst != null) pst.close();
			if (con != null) con.close();
		} catch (Exception ex){
			ex.printStackTrace();
			System.out.println("Error al insertar");
		} 
	}

}
