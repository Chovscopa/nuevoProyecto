package controladores;

import java.io.IOException;
import java.util.List;

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
import modelos.*;


@WebServlet("/controladorAdmin")
public class controladorAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAO_Libro dl;
	private LibrosBD lbd;
	@Resource(name="jdbc/tiendalibros")
	private DataSource pool;
	
	
    
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		//Iniciamos la conexion
		try {
			lbd=new LibrosBD(pool);
			dl=new DAO_Libro(pool);
		}catch(Exception e) {
			throw new ServletException(e);
		}
	}
	
	
    public controladorAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion=request.getSession();
		String admin=(String)sesion.getAttribute("usuario");
		request.setAttribute("usuario", admin);
		
		String ins=request.getParameter("accion");
		
		if(ins==null) ins="admin";
		String ruta="/admin.jsp";
		
		//GESTION LIBROS/////////
		
		switch(ins) {
		case "admin":
			listarLibros(request, response);
			ruta="/admin.jsp";
		break;
		
		case "Modificar": ruta="/modificarLibro.jsp";
		break;
		
		case "Agregar": ruta="/agregarLibro.jsp";
		break;
		
		case "Borrar": ruta="/borrarLibro.jsp";
		break;
		
		case "Confirmar": 
			String c=request.getParameter("c");
			if(c.equals("m")) {
				modificarLibro(request, response);
			}
			if(c.equals("b")) {
				borrarLibros(request, response);
			}
			if(c.equals("a")) {
				insertarLibros(request, response);
			}
			//ruta="/controladorAdmin?accion";
			break;
		
		default: System.out.println("Ha ocurrido un error");
		}
		
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher(ruta);
		dispatcher.forward(request,response);
		
		
		//GESTIONAR USUARIOS////
		
		
		
	}
	
	
	private void listarLibros(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		List<Libro> libro;

		try {

			libro=LibrosBD.ver();

			request.setAttribute("listaLibros", libro);
			
			//request.getRequestDispatcher("ver.jsp").forward(request, response);
			/*RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("ver.jsp");
			dispatcher.forward(request,response);*/
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void insertarLibros(HttpServletRequest request, HttpServletResponse response) {
			
		String titulo=request.getParameter("titulo");
		String autor=request.getParameter("autor");
		float precio=Float.parseFloat(request.getParameter("precio"));
		int c=Integer.parseInt(request.getParameter("cant"));
		//suma al id 1 y lo agrega
		int id=LibrosBD.actualizarId();
			
		dl.agregarLibro(id,titulo, autor, precio, c);
		listarLibros(request, response);
	}
	
	
	private void modificarLibro(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(request.getParameter("id"));
		String titulo=request.getParameter("titulo");
		String autor=request.getParameter("autor");
		float precio=Float.parseFloat(request.getParameter("precio"));
		int c=Integer.parseInt(request.getParameter("cant"));
			
		dl.modificarLibro(id, titulo, autor, precio, c);
		listarLibros(request, response);
	}
	
	
	private void borrarLibros(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(request.getParameter("idb"));
		
		dl.borrarLibro(id);
		listarLibros(request, response);
	}

	
}
