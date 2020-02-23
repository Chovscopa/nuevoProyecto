import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import funciones.Funciones;


@WebServlet("/PasoJava4")
public class PasoJava4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PasoJava4() {
        super();
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			embudo(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			embudo(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	protected void embudo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		//captar los valores de los campos del fomulario
		String cuenta=request.getParameter("cuenta");
		
		//asignacion de los campos del formalario a variables de sesion
		request.getSession().setAttribute("cuenta", cuenta);
		
		//
		String usuario=(String) request.getSession().getAttribute("user");
		String apellido=(String) request.getSession().getAttribute("Apellidos");
		String fecha=(String) request.getSession().getAttribute("Fecha");
		String departamento=(String) request.getSession().getAttribute("departamento[]");
		String salario=(String) request.getSession().getAttribute("salario");
		String comentarios=(String) request.getSession().getAttribute("comentarios");
		String cuenta2=(String) request.getSession().getAttribute("cuenta");
		
		String sex = (String) request.getSession().getAttribute("genero");
		
		String[] pai = (String[]) request.getSession().getAttribute("paises[]");
		String exo="";
		for(int i=0;i<pai.length;i++){
			exo+=pai[i]+" ";
		}
		
		String errores="";
		
		if(fallos(cuenta,errores,request)) {
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/datosBancarios.jsp");
			dispatcher.forward(request, response);
		}else {
			
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/datosFinales.jsp");
			dispatcher.forward(request, response);
			
			Connection conn = null;
			conn = Funciones.conexion();
			Funciones.insertarRegistro(conn, usuario, apellido, fecha, departamento, salario, comentarios, cuenta2, sex, exo);
			
		}
				
	}
	
	
	protected boolean fallos(String cuenta,String errores,HttpServletRequest request) {
		boolean sw=false;
		if(cuenta.equals("")) {
			errores+="No ha introducido numero de cuenta";
			sw=true;
		}
		request.getSession().setAttribute("errores", errores);
		return sw;
	}

}/*
create database registro_pasos;
*/
