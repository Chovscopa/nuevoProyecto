import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.*;

@WebServlet("/Bienvenido")
	public class Bienvenido extends HttpServlet {
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
			embudo(request,response);
		}
		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
			embudo(request,response);
		}
		protected void embudo(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				try {
					out.println("<html><head><title>Bienvenido</title></head><body>");
					out.println("<h2>Bienvenido</h2>");
				
					// Recupera el nombre de usuario
					String usuario;
					HttpSession session = request.getSession(false);
					if (session == null) {
						out.println("<h3>No has iniciado sesión</h3>");
					} else {
						synchronized (session) {
						usuario = (String) session.getAttribute("nombre");
					}
					out.println("<table>");
					out.println("<tr>");
					out.println("<td>Usuario:</td>");
					out.println("<td>" + usuario + "</td></tr>");
					out.println("<tr>");
					out.println("</table>");
					out.println("<p><a href='LogOut'>Logout</a></p>");
					}
					out.println("</body></html>");
				} finally {
				out.close();
				}
			}
	}