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
	/*
	private DataSource pool; // Pool de conexiones a la base de datos
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			// Crea un contexto para poder luego buscar el recurso DataSource
			InitialContext ctx = new InitialContext();
			// Busca el recurso DataSource en el contexto
			pool = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql_examenservidor");
			if (pool == null) {
				throw new ServletException("DataSource desconocida 'mysql_examenservidor'");
			}
		} catch (NamingException ex) {
			ex.printStackTrace();
		}
	}
	*/
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
