import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/PasoJava1")
public class PasoJava1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public PasoJava1() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		embudo(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		embudo(request, response);
	}
	
	protected void embudo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		if (request.getSession()== null) {
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/datosPersonales.jsp");
			dispatcher.forward(request, response);
		}else {
			//pasar parametros por request (....)
			request.getSession().getAttribute("user");
			request.getSession().getAttribute("Apellidos");
			request.getSession().getAttribute("fecha");
			
			
			
			
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/datosPersonales.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
