
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogOut")
public class LogOut extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LogOut() {
		super();

	}

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

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			out.println("<html><head><title>Logout</title></head><body>");
			out.println("<h2>Logout</h2>");
			HttpSession session = request.getSession(false);
			if (session == null) {
				out.println("<h3>No has iniciado sesión</h3>");
			} else {
				session.invalidate();
				out.println("<p>Adios</p>");
				out.println("<p><a href='index.html'>Login</a></p>");
			}
			out.println("</body></html>");
		} finally {
			out.close();
		}
	}

}
