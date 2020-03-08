package controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import DAO.DAO_Libro;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;
import modelos.*;


@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {
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
       
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		LibrosBD.cargarDatos();
	}
	
	
    public ServletControlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// Recupera la sesion actual o crea una nueva si no existe.
		HttpSession session = request.getSession(true);
		// Recupera el carrito de la sesion actual
		List<ElementoPedido> elCarrito = (ArrayList<ElementoPedido>) session.getAttribute("carrito");
		// Determina a que pagina jsp redirigira
		String nextPage = "";
		String todo = request.getParameter("todo");
		if (todo == null) {
			nextPage = "/order.jsp";
		} else if (todo.equals("add")) {
			// Mandado por order.jsp con los parametros idLibro y cantidad.
			// crea un elementoPedido y lo a~nade al carrito
			int cant=Integer.parseInt(request.getParameter("cantidad"));	
			int id=Integer.parseInt(request.getParameter("idLibro"));
			session.setAttribute("canti", cant);
			session.setAttribute("id", id);
			LibrosBD.actualizarDatos(cant, id);
			ElementoPedido nuevoElementoPedido = new ElementoPedido(id, cant);
			
			
			if (elCarrito == null) { // el carrito esta vaco
				elCarrito = new ArrayList<>();
				elCarrito.add(nuevoElementoPedido);
				// enlaza el carrito con la sesion
				session.setAttribute("carrito", elCarrito);
			} else {

				// si lo esta actualiza la cantidad
				// si no lo a~nade
				boolean encontrado = false;
				Iterator iter = elCarrito.iterator();
				while (!encontrado && iter.hasNext()) {
		
					ElementoPedido unElementoPedido = (ElementoPedido)iter.next();
						if (unElementoPedido.getIdLibro() == nuevoElementoPedido.getIdLibro())
						{
							unElementoPedido.setCantidad(unElementoPedido.getCantidad()+ nuevoElementoPedido.getCantidad());
							encontrado = true;
							
						}
				}
				if (!encontrado) { // Lo a~nade al carrito
					elCarrito.add(nuevoElementoPedido);
				}
		}
			LibrosBD.cargarDatos();
		// Vuelve a order.jsp para mas pedidos
			nextPage = "/order.jsp";
		} else if (todo.equals("remove")) {
			// Enviado por order.jsp con el parametro indiceElemento
			// Borra el elemento indiceElemento del carrito
			int cant=Integer.parseInt(request.getParameter("cant"));
			int id=Integer.parseInt(request.getParameter("idLib"));
			int indiceCarrito = Integer.parseInt(request.getParameter("indiceElemento"));
			LibrosBD.restaurarDatos(cant, id);
			elCarrito.remove(indiceCarrito);
			// Vuelve a order.jsp para mas pedidos
			LibrosBD.cargarDatos();
			nextPage = "/order.jsp";
		} else if (todo.equals("checkout")) {
			// Enviado por order.jsp.
			// Calcula el precio total de todos los elementos del carrito
			float precioTotal = 0;
			int cantidadTotalOrdenada = 0;
			for (ElementoPedido item: elCarrito) {
				float precio = item.getPrecio();
				int cantidadOrdenada = item.getCantidad();
				precioTotal += precio * cantidadOrdenada;
				cantidadTotalOrdenada += cantidadOrdenada;
			}
			// Formatea el precio con dos decimales
			StringBuilder sb = new StringBuilder();
			Formatter formatter = new Formatter(sb);
			formatter.format("%.2f", precioTotal);
			// Coloca el precioTotal y la cantidadTotal en el request
			request.setAttribute("precioTotal", sb.toString());
			request.setAttribute("cantidadTotal", cantidadTotalOrdenada + "");
			// Redirige a checkout.jsp
			nextPage = "/checkout.jsp";
		}
		request.setAttribute("usuario", (String)session.getAttribute("usuario"));
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher =servletContext.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}

	

}
