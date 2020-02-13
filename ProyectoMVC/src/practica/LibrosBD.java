package practica;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * LibrosBD Encapsula la comunicacion con la base de datos Almacena ttulos,
 * autores y precios en tres arrays
 */
public class LibrosBD {
	private static final int MAX_SIZE = 6;
	private static String[] titulos = new String[MAX_SIZE];
	private static String[] autores = new String[MAX_SIZE];
	private static float[] precios = new float[MAX_SIZE];
	private static int[] stock = new int[MAX_SIZE];

	public static void cargarDatos() {
		Connection conn = null;
		Statement stmt = null;
		try {
//Paso 1: Cargar el driver JDBC.
			Class.forName("com.mysql.jdbc.Driver").newInstance();
// Paso 2: Conectarse a la Base de Datos utilizando la clase Connection
			String userName = "root";
			String password = "";
//URL de la base de datos(equipo, puerto, base de datos)
			String url = "jdbc:mysql://localhost/tiendalibros";
			conn = DriverManager.getConnection(url, userName, password);
// Paso 3: Crear sentencias SQL, utilizando objetos de tipo Statement
			stmt = conn.createStatement();
// Paso 4: Ejecutar las sentencias SQL a traves de los objetos Statement
			String sqlStr = "select * from libros;";
			ResultSet rset = stmt.executeQuery(sqlStr);
// Paso 5: Procesar el conjunto de registros resultante utilizando ResultSet
			int count = 0;
			while (rset.next()) {
				titulos[count] = rset.getString("titulo");
				autores[count] = rset.getString("autor");
				precios[count] = (float) rset.getDouble("precio");
				stock[count]= rset.getInt("cantidad");
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

	/** Devuelve el numero de libros */
	public static int tamanyo() {
		return titulos.length;
	}

	/** Devuelve el ttulo del libro idLibro */
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
	
	public static int getStock(int idLibro) {
		return stock[idLibro];
	}
}