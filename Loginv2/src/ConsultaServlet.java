import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import funciones.Funciones;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet("/ConsultaServlet")
public class ConsultaServlet extends HttpServlet {
	// El m etodo doGet() se ejecuta una vez por cada petici on HTTP GET.
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		embudo(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		embudo(request, response);
	}

	public void embudo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int counterS = 0;
		HttpSession sesion = request.getSession(false);

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		PrintWriter out2 = response.getWriter();

		String usuario = request.getParameter("Nombre");
		String clave = request.getParameter("Clave");

		if (sesion == null) {
			sesion = request.getSession();
			sesion.setAttribute(usuario, counterS);
		}
		counterS = (int) sesion.getAttribute(usuario);
		if (counterS > 2) {
			RequestDispatcher r = request.getRequestDispatcher("/Cambio");
			r.forward(request, response);
		} else {
			String errores = "";
			if (request.getParameter("Nombre") == "") {
				errores += "Debes introducir nombre" + "<br>";
			} else {
				sesion.setAttribute("nombre", request.getParameter("Nombre"));
			}
			if (request.getParameter("Clave") == "") {
				errores += "Debes introducir contraseña" + "<br>";
			}
			if (errores.length() != 0) {
				repintado(out, errores);
			} else {
				Connection conn = null;
				Statement stmt = null;
				try {

					
					conn = Funciones.conexion();

					if (Funciones.checkUsuario1(conn, request.getParameter("Nombre"), request.getParameter("Clave"))) {

						out.println("<p>Bienvenido " + request.getParameter("Nombre") + "</p>");

						sesion.setAttribute("nombre", request.getParameter("Nombre"));
						out.println("<p><a href='Bienvenido'>Bienvenido</a></p>");

					} else {
						counterS++;
						out.println("<p><a href='index.html'>Volver Login</a></p><br>");
						out.println("<h1>Error de usuario y contraseña<h1>");
						sesion.setAttribute(usuario, counterS);
						sesion.setAttribute("Nombre", usuario);
						 
					}

				} catch (Exception ex) {
					ex.printStackTrace();

				} finally {
					out.close();
					out2.close();
					try {

						if (stmt != null) {
							stmt.close();
						}
						if (conn != null) {
							conn.close();
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}

				}
			}
		}
	}

	private void repintado(PrintWriter out2, String errores) {
		out2.println("<html>");
		out2.println("<head>");
		out2.println("<meta charset=\"UTF-8\">");
		out2.println("</head>");
		out2.println("<body>");
		out2.println("<fieldset>\n" + "<legend>Formulario de login</legend><br/>");
		out2.println("<form action=\"ConsultaServlet\" method=\"post\" ");
		out2.println("<p style=\"color:red\"/>" + errores + "<br></p>");
		out2.println("<label for=\"nombre\">Nombre</label> <input type=\"text\" name=\"Nombre\" /> <br/> <br/>");
		out2.println("<label for=\"clave\">Clave</label> <input type=\"password\" name=\"Clave\" /> <br/> <br/>");
		out2.println("<p><a href='Cambio'>Cambio de contraseña?</a></p>");
		out2.println("<input type=\"submit\" value=\"Enviar\" name=\"enviar\" />");

		out2.println("</form>\n" + "</fieldset>");
		out2.println("</body>");
		out2.println("</html>");
	}/*
		 * private void formulario(PrintWriter out2) { out2.println("<html>");
		 * out2.println("<head>"); out2.println("<meta charset=\"UTF-8\">");
		 * out2.println("</head>"); out2.println("<body>"); out2.println("<fieldset>\n"
		 * + "<legend>Formulario de login</legend><br/>");
		 * out2.println("<form action=\"ConsultaServlet\" method=\"post\" ");
		 * 
		 * out2.
		 * println("<label for=\"nombre\">Nombre</label> <input type=\"text\" name=\"Nombre\" /> <br/> <br/>"
		 * ); out2.
		 * println("<label for=\"clave\">Clave</label> <input type=\"password\" name=\"Clave\" /> <br/> <br/>"
		 * ); out2.println("<p><a href='Cambio'>Cambio de contraseña?</a></p>");
		 * out2.println("<input type=\"submit\" value=\"Enviar\" name=\"enviar\" />");
		 * 
		 * out2.println("</form>\n" + "</fieldset>"); out2.println("</body>");
		 * out2.println("</html>"); }
		 */

}
/*
 * protected void LoginBD(HttpServletRequest request, HttpServletResponse
 * response) throws ServletException, IOException {
 * 
 * 
 * 
 * // Recepcion de Parametros int contadorAccesos=0; String usuario =
 * request.getParameter("Nombre"); String password =
 * request.getParameter("Clave"); HttpSession session =
 * request.getSession(false);
 * 
 * if(session==null) { session=request.getSession();
 * session.setAttribute(usuario, contadorAccesos); }
 * 
 * contadorAccesos=(int) session.getAttribute(usuario);
 * 
 * if ( contadorAccesos > 2 ) { RequestDispatcher rd =
 * request.getRequestDispatcher("CambiarContrasena"); rd.forward(request,
 * response);
 * 
 * ESTO VA JUSTO ANTES DEL CONTROL DE ERRORES DE CAMPOS VACIOS
 * 
 */
/*
 * // Iniciamos la conexion con la peticion a la base de datos conn =
 * conexion(); stmt = conn.
 * prepareStatement("SELECT * FROM usuarios WHERE nombre = ? AND pass = PASSWORD(?) "
 * ); stmt.setString(1, usuario); stmt.setString(2, password); ResultSet rset =
 * stmt.executeQuery();
 * 
 * //SI encuentra al usuario y contraseña en la base de datos if (rset.next()) {
 * 
 * // Crea una nueva sesion contadorAccesos++;
 * 
 * session.setAttribute("nombre",usuario);
 * 
 * // Usuario nuevo de la tabla en el login out.println("<p>Hola, " +
 * session.getAttribute("nombre") + "!</p>");
 * out.println("<p><a href=index.html>Log Out</a></p>");
 * 
 * }
 * 
 * else { //Si no encuentra el usuario o la contraseña es incorrecta se crea la
 * sesion
 * 
 * 
 * 
 * contadorAccesos++;
 * 
 * out.println("<a href=index.html>Volver al Login.</a>"); out.println("<br>");
 * out.println("<h1>Error en usuario o contraseña</h1>");
 * session.setAttribute(usuario,contadorAccesos);
 * session.setAttribute("nombre",usuario); } }
 * 
 * ESTO ES SI AMBOS CAMPOS ESTAN RELLENOS; POR TANTO NO HAY ERRORES DE CAMPOS
 * VACIOS
 */

/*
 * create database tienda5 CREATE TABLE usuarios( nombre VARCHAR(20) NOT NULL
 * PRIMARY KEY UNIQUE, pass VARCHAR(200) NOT NULL, pregunta VARCHAR(200),
 * respuesta VARCHAR(200)); INSERT INTO usuarios VALUES('Ovidio','1234','Nombre
 * de tu padre','Eduardo'); INSERT INTO usuarios VALUES('juan','1234','Nombre de
 * tu padre','Perico'); ; delete from usuarios where nombre='Ovidio';
 */