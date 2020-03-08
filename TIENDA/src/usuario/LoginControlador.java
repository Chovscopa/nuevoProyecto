package usuario;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginControlador
 */
@WebServlet("/LoginControlador")
public class LoginControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
    public LoginControlador() {
        super();
        UserBD.cargarUsuarios();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Si el usuario ha cargado el formulario por primera vez, comprobamos si ya hay datos en la sesion:
		
		if(request.getParameter("log")==null) {
			recuperarDatosFormulario(request,response);
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
			
		}else if(request.getParameter("log").equals("user")){
			
			recuperarDatosFormulario(request,response);
			
			//guardamos los datos en dos variables
			
			String nombre_usuario=(String)request.getAttribute("usuario"); 
			String password_usuario = (String)request.getAttribute("password");
			int rol_usuario=comprobarUsuario(nombre_usuario,password_usuario);
			if(rol_usuario!=-1) {
				User usuario = new User(nombre_usuario,password_usuario,rol_usuario); 
					
				if(usuario.getRol()==1) {
						PrintWriter out = response.getWriter();
						out.println("Admin");
				}else if(usuario.getRol()==0){
					
					RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/order.jsp");
					dispatcher.forward(request, response);
				}else {
					RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/index.jsp");
					dispatcher.forward(request, response);
					
				}
			
			}else {
				RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
		
	
	protected void recuperarDatosFormulario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession(false);
		if(sesion!=null) {
			sesion.invalidate();
		}
		sesion=request.getSession();
		if(request.getParameter("usuario")!=null) {		
			sesion.setAttribute("usuario", request.getParameter("usuario")); 
			request.setAttribute("usuario",sesion.getAttribute("usuario"));
			
		}else {
			sesion.setAttribute("usuario"," ");
			request.setAttribute("usuario",sesion.getAttribute("usuario"));
		}
		if(request.getParameter("password")!=null) {
			sesion.setAttribute("password", request.getParameter("password"));
			request.setAttribute("password",sesion.getAttribute("password"));
		}else {
			sesion.setAttribute("password", "");
			request.setAttribute("password",sesion.getAttribute("password"));
		}
	}
	
	protected int comprobarUsuario(String nombre_usuario, String nombre_password) {
		int existe=-1;
		nombre_password=UserBD.hashearPass(nombre_password);
		String [] bbddUsuarios = UserBD.getUsuario();  String [] bbddPassword = UserBD.getPassword();
		for (int i=0 ; i < bbddUsuarios.length ; i++) {
			if(nombre_usuario.equals(bbddUsuarios[i]) && nombre_password.equals(bbddPassword[i])) {
				
				existe=obtenerRolDeUsuario(i);
			}
		}
		return existe;
	}
	
	protected int obtenerRolDeUsuario(int indice) {
		int [] bbddRoles = UserBD.getRol();
		return bbddRoles[indice];
	}


}
