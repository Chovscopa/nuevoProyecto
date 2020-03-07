

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Servlet2")
public class Servlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Servlet2() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		embudo(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		embudo(request, response);
	}
	
	protected void embudo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// Creamos un objeto para poder escribir la respuesta

		PrintWriter out = response.getWriter();
		Connection conn = null;
		Statement stmt = null;
		
		String usu = ""; 
		
		HttpSession session=request.getSession(false);
		if(session==null) {
			
		}else {
			synchronized (session) {
				usu=(String) session.getAttribute("usuario");
			}
		}
		
		try {

			double nota = 0.0;
			conn = conexion();

			// Paso 3: Crear sentencias SQL, utilizando objetos de tipo Statement

			stmt = conn.createStatement();

			for (int indice = 1; indice < 21; indice++) {

				String[] respuesta = request.getParameterValues(Integer.toString(indice));

				if (respuesta != null) {

					String sqlStr1 = "select respuesta from respuestas where pregunta=" + indice + ";";
					ResultSet rset1 = stmt.executeQuery(sqlStr1);

					while (rset1.next()) {

						String solucion = rset1.getString("respuesta");

						// Respuesta correcta

						if (respuesta[0].equals(solucion)) {
							nota += 0.5;
						}

					}

				} else {
					nota -= 0.5;

				}

			}

			out.println(

					"<html><head><meta charset=\"UTF-8\"><title>Resultado de las preguntas</title></head><body>");

			out.println("Nota final de " + usu + ": " + nota);

			out.println("</body></html>");

		} catch (Exception ex) {

			ex.printStackTrace();

		} finally {

			out.close(); // Cerramos el flujo de escritura

			try {

				// Cerramos el resto de recursos

				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();

			} catch (SQLException ex) {

				ex.printStackTrace();

			}

		}
		

	}
	private Connection conexion() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		Connection conn = null;
		Class.forName("com.mysql.jdbc.Driver").newInstance(); // Paso 1: Cargar el driver JDBC.
		String userName = "root"; // Paso 2: Conectarse a la Base de Datos utilizando la clase Connection
		String password = "admin";
		String url = "jdbc:mysql://localhost/testexamen"; // URL de la base de datos(equipo, puerto, base de datos)
		conn = DriverManager.getConnection(url, userName, password);

		return conn;

	}
}


