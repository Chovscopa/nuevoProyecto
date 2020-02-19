import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/PasoJava2")
public class PasoJava2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PasoJava2() {
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
		
		//captar los valores de los campos del fomulario
		String nombre=request.getParameter("user");	//faltan todo el resto de paramentros
		String apellido=request.getParameter("Apellidos");
		String fecha=request.getParameter("Fecha");
		
		String errores="";
		
		//asignacion de los campos del formalario a variables de sesion
		request.getSession().setAttribute("user", nombre);   //session.setAttribute("user", usuario);
		request.getSession().setAttribute("Apellidos", apellido);
		request.getSession().setAttribute("Fecha", fecha);
		request.getSession().setAttribute("errores", errores);
		
		
		
		//Errores
		if(fallos(nombre, apellido, fecha,errores,request)) {
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/datosPersonales.jsp");
			dispatcher.forward(request, response);
		}else {
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/datosProfesionales.jsp");
			dispatcher.forward(request, response);
		}
		
	}
	
	
	protected boolean fallos(String nombre,String apellido,String fecha,String errores, HttpServletRequest request) {
		boolean sw=false;
		if (nombre.equals("")) {
			errores += "No ha introducido nombre<br>";
			sw=true;
		}
		if (apellido.equals("")) {
			errores += "No ha introducido apellidos<br>";
			sw=true;
		}
		if (fecha.equals("")) {
			errores += "No ha introducido fecha<br>";
			sw=true;
		}
		request.getSession().setAttribute("errores", errores);
		return sw;
		
	}

}
