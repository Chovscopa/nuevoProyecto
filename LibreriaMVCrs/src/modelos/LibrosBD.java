package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import controladores.*;
/**
* LibrosBD
* Encapsula la comunicaci´on con la base de datos
* Almacena ttulos, autores y precios en tres arrays
*/


public class LibrosBD {


	private static final int MAX_SIZE=6;
	private static String[] titulos=new String[MAX_SIZE];
	private static String[] autores=new String[MAX_SIZE];
	private static float[] precios=new float[MAX_SIZE];
	private static int[] cantidades=new int[MAX_SIZE];
	
private static DataSource pool;
	
	public LibrosBD(DataSource pool) {
		this.pool=pool;
	}
	
	
	public static void cargarDatos(){
		Connection conn = null;
		Statement stmt = null;
		try {

			conn=pool.getConnection();
			stmt=conn.createStatement();
		
			String sqlStr = "select * from libros;";
			ResultSet rset = stmt.executeQuery(sqlStr);
			// Paso 5: Procesar el conjunto de registros resultante utilizando ResultSet
			int count = 0;
			while(rset.next()) {
				autores[count]= rset.getString("autor");
				titulos[count]=rset.getString("titulo");
				precios[count]=(float)rset.getDouble("precio");
				cantidades[count]=(int)rset.getInt("cantidad");
				count++;
			}

			// Cerramos el resto de recursos
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	public static List<Libro> ver(){
		Connection conn = null;
		Statement stmt = null;
		List<Libro> libros=new ArrayList<>();
		
		try {

			conn=pool.getConnection();
			stmt=conn.createStatement();
		
			String sqlStr = "select * from libros;";
			ResultSet rset = stmt.executeQuery(sqlStr);

			while(rset.next()) {
				int id=(int)rset.getInt("id");
				String autor= rset.getString("autor");
				String titulo=rset.getString("titulo");
				float precio=(float)rset.getDouble("precio");
				int cantidad=(int)rset.getInt("cantidad");
				
				Libro l=new Libro(id, titulo, autor, precio, cantidad);
				libros.add(l);
			}

			// Cerramos el resto de recursos
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		} catch (Exception ex){
			ex.printStackTrace();
		}
		return libros;
	}
	
	
	public static void actualizarDatos(int c, int id){
		Connection conn = null;
		Statement stmt = null;
		try {

			conn =pool.getConnection();
			stmt = conn.createStatement();
			
			String sqlStr = "UPDATE libros SET cantidad = cantidad - "+c+" where id="+id+";";
			stmt.executeUpdate(sqlStr);
			
			// Cerramos el resto de recursos
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		} catch (Exception ex){
			ex.printStackTrace();
		} 
	}
	
	
	public static int actualizarId(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rt=null;
		int i=0;
		try {

			conn =pool.getConnection();
			stmt = conn.createStatement();
			
			String sqlStr = "select count(id) from libros";
			rt=stmt.executeQuery(sqlStr);
			
			if(rt.next()) {
				i=rt.getInt(1);
			}
			
			// Cerramos el resto de recursos
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		} catch (Exception ex){
			ex.printStackTrace();
		} 
		return i;
	}
	
	public static void restaurarDatos(int c, int id){
		Connection conn = null;
		Statement stmt = null;
		try {

			conn = pool.getConnection();
			stmt = conn.createStatement();
			
			String sqlStr = "UPDATE libros SET cantidad = cantidad + "+c+" where id="+id+";";
			stmt.executeUpdate(sqlStr);
			
			// Cerramos el resto de recursos
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		} catch (Exception ex){
			ex.printStackTrace();
		} 
	}
	
		
	
	/** Devuelve el numero de libros */
	public static int tamanyo() {
		return titulos.length;
	}
	/** Devuelve el tulo del libro idLibro*/
	public static String getTitulo(int idLibro) {
		return titulos[idLibro];
	}
	/** Devuelve el autor del libro idLibro */
	public static String getAutor(int idLibro) {
		return autores[idLibro];
	}
	/** Devuelve el precio del libro idLibro */
	public static float getPrecio(int idLibro) {
		return precios[idLibro];
	}
	
	public static int getCantidades(int idLibro) {
		return cantidades[idLibro];
	}
	
}
