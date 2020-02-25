
import java.io.IOException;
import funciones.Funciones;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/HasOlvidado")
public class HasOlvidado extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		embudo(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		embudo(request, response);
	}

	protected void embudo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		PrintWriter out2 = response.getWriter();
		String usuario=(String) request.getSession().getAttribute("nombre");
		try {
			//////////////// FORMULARIO ///////////////
			if (request.getParameter("enviar") == null) {
				out.println(usuario);
				Connection conn = null;
				conn = Funciones.conexion();
				String cc=Funciones.mostarPregunta(conn, usuario);
				formulario(out,cc);

			} else {

				String errores = "";
				Connection conn = null;
				conn = Funciones.conexion();
				Connection conn2 = null;
				conn2 = Funciones.conexion();
				String cc=Funciones.mostarPregunta(conn2, usuario);
				

				if (Funciones.checkPregunta(conn,usuario, request.getParameter("respuesta"))) {
					out.println("<p>Respuesta acertada, " + usuario + "</p>");

					out.println("<p><a href='CambioSeguridad'>Cambio de contraseña</a></p>");
					
				} else {

					
					if (request.getParameter("respuesta") == "") {
						errores += "Debes introducir respuesta" + "<br>";
					}

					// REPINTADO //
					repintado(out, errores,cc);

					try {

						if (conn != null)
							conn.close();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}

			}
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			out.close();
			out2.close();

		}

	}

	private void repintado(PrintWriter out2, String errores,String cc) {
		out2.println("<html>");
		out2.println("<head>");
		out2.println("<meta charset=\"UTF-8\">");
		out2.println("</head>");
		out2.println("<body>");
		out2.println("<fieldset>\n" + "<legend>Has olvidado?</legend><br/>");
		out2.println("<form action=\"HasOlvidado\" method=\"post\" ");
		out2.println("<p style=\"color:red\"/>"+errores+"<br>");
		out2.println("Pregunta de seguridad: "+cc+"?<br/>");
		
		out2.println("<label for=\"clave\">Respuesta</label> <input type=\"password\" name=\"respuesta\" />");

		out2.println("<input type=\"submit\" value=\"Enviar\" name=\"enviar\" />");

		out2.println("</form>\n" + "</fieldset>");
		out2.println("</body>");
		out2.println("</html>");
	}

	private void formulario(PrintWriter out2,String cc) {
		out2.println("<html>");
		out2.println("<head>");
		out2.println("<meta charset=\"UTF-8\">");
		out2.println("</head>");
		out2.println("<body>");
		out2.println("<fieldset>\n" + "<legend>Has olvidado?" + "" + "</legend><br/>");
		out2.println("<form action=\"HasOlvidado\" method=\"post\" ");
	
		out2.println("<p>Pregunta de seguridad: "+cc+"</p><br/>");

		out2.println("<label for=\"clave\">Respuesta</label> <input type=\"password\" name=\"respuesta\" /><br/>");

		out2.println("<input type=\"submit\" value=\"Enviar\" name=\"enviar\" />");

		out2.println("</form>\n" + "</fieldset>");
		out2.println("</body>");
		out2.println("</html>");
	}

	

	

}
