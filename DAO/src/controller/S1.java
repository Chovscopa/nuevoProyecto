package controller;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.*;
import javax.*;

import utilidades.*;
import dao.*;

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
		Datos.cargarDatosOrigen(Funciones.conexion());
		request.getSession().setAttribute("cuentas[]", Datos.datosCuentas);
		
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/FormularioInicial.jsp");
			dispatcher.forward(request, response);
	
	}

}
