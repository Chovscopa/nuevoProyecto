import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/PasoJava4")
public class PasoJava4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PasoJava4() {
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
		String cuenta=request.getParameter("cuenta");
		
		//asignacion de los campos del formalario a variables de sesion
		request.getSession().setAttribute("cuenta", cuenta);
		
		String errores="";
		
		if(fallos(cuenta,errores,request)) {
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/datosBancarios.jsp");
			dispatcher.forward(request, response);
		}else {
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/datosFinales.jsp");
			dispatcher.forward(request, response);
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

}
