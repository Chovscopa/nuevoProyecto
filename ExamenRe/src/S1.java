import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import generacionDinamica.Datos;


@WebServlet("/S1")
public class S1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public S1() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		embudo(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		embudo(request, response);
	}
	
	protected void embudo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Datos.cargarDatosOrigen();
		request.getSession().setAttribute("cuentas[]", Datos.datosCuentas);
		
	
		
		
		if (request.getSession()== null) {
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/FormularioInical.jsp");
			dispatcher.forward(request, response);
		}else {
			
			request.getSession().getAttribute("cuentas[]");
			request.getSession().getAttribute("cantidadT");

			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/FormularioInicial.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
