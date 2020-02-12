import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/PasoJava3")
public class PasoJava3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PasoJava3() {
        super();
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		embudo(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		embudo(request, response);
	}
	
	protected void embudo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//captar los valores de los campos del fomulario
		String departamento=request.getParameter("departamento[]");
		String salario=request.getParameter("salario");
		String comentarios=request.getParameter("comentarios");
		//asignacion de los campos del formalario a variables de sesion
		request.getSession().setAttribute("departamento[]", departamento);
		request.getSession().setAttribute("salario", salario);
		request.getSession().setAttribute("comentarios", comentarios);
		
		String errores="";
		
		
		if(fallos(salario,errores,request)) {
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/datosProfesionales.jsp");
			dispatcher.forward(request, response);
		}else {
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/datosBancarios.jsp");
			dispatcher.forward(request, response);
		}
		/*
		
		*/
	}
	
	
	
	protected boolean fallos(String salario,String errores, HttpServletRequest request) {
		boolean sw=false;
		
		if (salario.equals("")) {
			errores += "No ha introducido salario<br>";
			sw=true;
		}
		
		request.getSession().setAttribute("errores", errores);
		return sw;
		
	}

}
