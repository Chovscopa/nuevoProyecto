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
import javax.servlet.http.HttpSession;

import javax.servlet.RequestDispatcher;

@WebServlet("/Servlet1")

public class Servlet1 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {

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
				out.println("<form action=\"Servlet1\" method=\"post\" >");
				out.println("<label for=\"nombre\">Nombre</label> <input type=\"text\" name=\"Nombre\" /> <br/> <br/>");

				for (int i = 1; i < 21; i++) {

					out.println("<br>");
					//out.println(generarBotonesdeCheckboxRepintado(((Integer) i).toString(),request.getParameterValues(((Integer) i).toString())));
					out.println(generarBotonesdeCheckboxRepintado(((Integer) i).toString(),request.getParameterValues(((Integer) i).toString()),letras1));

				}

				out.println("<br><br>");
				out.println("<input type=\"submit\" value=\"Enviar\" name=\"Enviar\" />");
				out.println("</form>\n" + "</fieldset>");
				out.println("</body>");
				out.println("</html>");

			}

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
					out.println("<form action=\"Servlet1\" method=\"post\" >");
					out.println("<label for=\"nombre\">Nombre</label> <input type=\"text\" name=\"Nombre\" value=\""

							+ usu + "\" /> <br/> <br/>");

					for (int i = 1; i < 21; i++) {

						out.println("<br>");
						out.println(generarBotonesdeCheckboxRepintado(((Integer) i).toString(),request.getParameterValues(((Integer) i).toString()),letras1));

					}

					out.println("<br><br>");
					out.println("<input type=\"submit\" value=\"Enviar\" name=\"Enviar\" />");
					out.println("</form>\n" + "</fieldset>");
					out.println("</body>");
					out.println("</html>");

				}

			} else {

				

				response.setContentType("text/html;charset=UTF-8");
				///////////// Todo el bloque de código que había aquí en el proyecto original se pasa al segundo servlet que será la parte de la corrección con los datos de la sesione /////////
				
				HttpSession session=request.getSession(false);		//Invalidar sesion (a true o vacio se crearía una sesión)
				if(session != null) {
					session.invalidate();
				}
				session=request.getSession(true);
				synchronized (session) {
					session.setAttribute("usuario", usu);
					RequestDispatcher rd=request.getRequestDispatcher("Servlet2");
					rd.forward(request, response);
				}
				

			}

		}

	}

	private String generarBotonesdeCheckboxRepintado(String numeroPregunta, String[] respuestas,String[] letras1) {
		
		String checkboxses = numeroPregunta+". ";
	    
		for(int i=0;i<letras1.length;i++) {
			checkboxses+= letras1[i]+"<input type='checkbox' name='" + numeroPregunta + "' value='"+letras1[i]+"'"
						+ (respuestas != null && contiene(respuestas, letras1[i]) ? "checked" : "") + ">";
		}		

		return checkboxses;

	}

	private boolean contiene(String[] array, String elem) {

		int i = 0;
		while (i < array.length && !array[i].equalsIgnoreCase(elem)) {
			i++;
		}
		return i < array.length;

	}
	String[] letras1= {"A","B","C","D"};

}

