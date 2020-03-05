import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletTestBD")

public class ServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Embudo(request, response);
	}

	protected void Embudo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Recepcion de paramteros
		String usu = request.getParameter("Nombre");
		String[] pre = request.getParameterValues("preguntas[]");

		// Creacion del formulario
		String submit = request.getParameter("Enviar");
		if (submit == null) {

			response.setContentType("text/html;charset=UTF-8");
			try (PrintWriter out = response.getWriter()) {

				out.println("<html>");
				out.println("<head>");
				out.println("<meta charset=\"UTF-8\">");
				out.println("</head>");
				out.println("<body>");
				out.println("<fieldset>\n" + "<legend>EXAMEN</legend><br/>");
				out.println("<form action=\"ServletTestBD\" method=\"post\" >");

				out.println("<label for=\"nombre\">Nombre</label> <input type=\"text\" name=\"Nombre\" /> <br/> <br/>");
				for (int i = 1; i < 21; i++) {
					out.println("<br>");
					out.println(generarBotonesdeCheckboxRepintado(((Integer)i).toString(),request.getParameterValues(((Integer)i).toString())));
				}
				out.println("<br><br>");
				out.println("<input type=\"submit\" value=\"Enviar\" name=\"Enviar\" />");
				out.println("</form>\n" + "</fieldset>");
				out.println("</body>");
				out.println("</html>");

			}
//asas//
		} else {

			String errores = "";
			boolean sw = true;
			// Tratamiento de errores
			if (usu.equals("")) {
				errores += "Introduzca nombre <br/>";
			}

			for (int i = 0; i < 20; i++) {
				String[] respuesta = request.getParameterValues(Integer.toString(i));
				if (respuesta != null) {
					if (respuesta.length > 1) {
						sw = false;
					}
				}

			}
			if (!sw) {
				errores += "Hay preguntas con mas de una respuesta seleccionada";
			}

			// FORMULARIO CON ERRORES REPINTADO
			if (errores.length() != 0) {
				response.setContentType("text/html;charset=UTF-8");
				try (PrintWriter out = response.getWriter()) {

					out.println("<html>");
					out.println("<head>");
					out.println("<meta charset=\"UTF-8\">");
					out.println("</head>");
					out.println("<body>");
					out.println("<fieldset>\n" + "<legend>EXAMEN</legend><br/>");
					out.println("<p style=\"color:red\">" + errores + "</p>");
					out.println("<form action=\"ServletTestBD\" method=\"post\" >");
					out.println("<label for=\"nombre\">Nombre</label> <input type=\"text\" name=\"Nombre\" value=\""
							+ usu + "\" /> <br/> <br/>");
					for (int i = 1; i < 21; i++) {
						out.println("<br>");
						out.println(generarBotonesdeCheckboxRepintado(((Integer)i).toString(),request.getParameterValues(((Integer)i).toString())));
					}
					out.println("<br><br>");
					out.println("<input type=\"submit\" value=\"Enviar\" name=\"Enviar\" />");

					out.println("</form>\n" + "</fieldset>");
					out.println("</body>");
					out.println("</html>");

				}
			} else {

				// Establecemos el tipo MIME del mensaje de respuesta
				response.setContentType("text/html;charset=UTF-8");

				// Creamos un objeto para poder escribir la respuesta
				PrintWriter out = response.getWriter();
				Connection conn = null;
				Statement stmt = null;

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
		}
	}

	private Connection conexion()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection conn = null;

		Class.forName("com.mysql.jdbc.Driver").newInstance(); // Paso 1: Cargar el driver JDBC.

		String userName = "root"; // Paso 2: Conectarse a la Base de Datos utilizando la clase Connection
		String password = "admin";

		String url = "jdbc:mysql://localhost/TestExamen"; // URL de la base de datos(equipo, puerto, base de datos)
		conn = DriverManager.getConnection(url, userName, password);
		return conn;
	}

	private String generarBotonesdeCheckbox(int numeroPregunta) {
		String radios = numeroPregunta + ". " + "A<input type='checkbox' name='" + numeroPregunta + "' value='A'>"
				+ " B<input type='checkbox' name='" + numeroPregunta + "' value='B'>"
				+ " C<input type='checkbox' name='" + numeroPregunta + "' value='C'>"
				+ " D<input type='checkbox' name='" + numeroPregunta + "' value='D'>";
		return radios;
	}
	
	private String generarBotonesdeCheckboxRepintado(String numeroPregunta,String[] respuestas) {
		String radios = numeroPregunta + ". " 
				+ " A<input type='checkbox' name='" + numeroPregunta + "' value='A'"+(respuestas!=null&&contiene(respuestas,"A")?"checked":"")+">"
				+ " B<input type='checkbox' name='" + numeroPregunta + "' value='B'"+(respuestas!=null&&contiene(respuestas,"B")?"checked":"")+">"
				+ " C<input type='checkbox' name='" + numeroPregunta + "' value='C'"+(respuestas!=null&&contiene(respuestas,"C")?"checked":"")+">"
				+ " D<input type='checkbox' name='" + numeroPregunta + "' value='D'"+(respuestas!=null&&contiene(respuestas,"D")?"checked":"")+">";
		return radios;
	}
	private boolean contiene(String[] array,String elem){
		int i=0;
		while(i<array.length&&!array[i].equalsIgnoreCase(elem)){i++;}
		return i<array.length;
		}
}

/*
create database TestExamen

use TestExamen

create table respuestas (
pregunta int,
respuesta varchar(50),
primary key (pregunta));

0 insert into respuestas values (1, 'A');
1 insert into respuestas values (2, 'B');
2 insert into respuestas values (3, 'A');
3 insert into respuestas values (4, 'A');
4 insert into respuestas values (5, 'C');
5 insert into respuestas values (6, 'B');
6 insert into respuestas values (7, 'B');
7 insert into respuestas values (8, 'B');
8 insert into respuestas values (9, 'A');
9 insert into respuestas values (10, 'A');
10 insert into respuestas values (11, 'B');
11 insert into respuestas values (12, 'B');
12 insert into respuestas values (13, 'B');
13 insert into respuestas values (14, 'C');
14 insert into respuestas values (15, 'D');
15 insert into respuestas values (16, 'D');
16 insert into respuestas values (17, 'D');
17 insert into respuestas values (18, 'D');
18 insert into respuestas values (19, 'D');
19 insert into respuestas values (20, 'D');
*/
