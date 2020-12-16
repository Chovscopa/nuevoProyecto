package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import DAO.DAO_Libro;
import DAO.DAO_Usuario;
import modelos.LibrosBD;
import modelos.UsuariosBD;



@WebServlet("/checkLogin")
public class checkLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DAO_Usuario du;
	private UsuariosBD ubd;
	@Resource(name="jdbc/tiendalibros")
	private DataSource pool;
	
	
    
	@Override
	public void init() throws ServletException {
	
		super.init();
		
		//Iniciamos la conexion
		try {
			ubd=new UsuariosBD(pool);
			du=new DAO_Usuario(pool);
		}catch(Exception e) {
			throw new ServletException(e);
		}
	}
	
	
    public checkLogin() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String usuario=request.getParameter("usuario");
		String pass=request.getParameter("pass");
		HttpSession sesion=request.getSession();
		String ruta="";
		
		if(UsuariosBD.recuperarUsu(usuario)) {
			if(UsuariosBD.comprobarUsuario(usuario, pass)) {
				if(usuario.equals("admin")) {
					ruta="/controladorAdmin";
				}else {
					ruta="/shopping";
				}
				sesion.setAttribute("usuario", usuario);
				request.setAttribute("usuario", usuario);
			}else {

				ruta="/login";		
			}
		}else {
			request.setAttribute("noExiste", "<p>No existe el usuario, por favor, registrate</p>");
			ruta="/login";
		}
		
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher(ruta);
		dispatcher.forward(request,response);
	}

}
