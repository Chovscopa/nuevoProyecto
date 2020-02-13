package practica;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class ServletControlador
 */
@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		LibrosBD.cargarDatos();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);	// Recupera la sesion actual o crea una nueva si no existe.

		List<ElementoPedido> elCarrito = (ArrayList<ElementoPedido>) session.getAttribute("carrito");	// Recupera el carrito de la sesion actual

		String nextPage = "";	// Determina a que pagina jsp redirigira
		String todo = request.getParameter("todo");
		if (todo == null) {

			nextPage = "/order.jsp";	// Primer acceso - redireccion a order.jsp
		} 
		else if (todo.equals("add")) {
// Mandado por order.jsp con los parámetros idLibro y cantidad.
// crea un elementoPedido y lo añade al carrito
			ElementoPedido nuevoElementoPedido = new ElementoPedido(Integer.parseInt(request.getParameter("idLibro")),	Integer.parseInt(request.getParameter("cantidad")));
			if(  (Integer.parseInt(request.getParameter("idLibro"))) >= (nuevoElementoPedido.getCantidad()) ) {
				PrintWriter out = response.getWriter();
				out.println("no se puede");
			}
			if (elCarrito == null) { // el carrito esta vacío
				elCarrito = new ArrayList<>();
				elCarrito.add(nuevoElementoPedido);
// enlaza el carrito con la sesion
				session.setAttribute("carrito", elCarrito);
			} else {
// Comprueba si el libro esta en el carrito
// si lo está actualiza la cantidad
// si no lo añade
				boolean encontrado = false;
				Iterator iter = elCarrito.iterator();
				while (!encontrado && iter.hasNext()) {
					ElementoPedido unElementoPedido = (ElementoPedido) iter.next();
					if (unElementoPedido.getIdLibro() == nuevoElementoPedido.getIdLibro()) {
						unElementoPedido.setCantidad(unElementoPedido.getCantidad() + nuevoElementoPedido.getCantidad());
						encontrado = true;
					}
				}
				if (!encontrado) { // Lo a~nade al carrito
					elCarrito.add(nuevoElementoPedido);
				}
			}///////
			// Vuelve a order.jsp para mas pedidos
			nextPage = "/order.jsp";
		} else if (todo.equals("remove")) {
			// Enviado por order.jsp con el parametro indiceElemento
			// Borra el elemento indiceElemento del carrito
			int indiceCarrito = Integer.parseInt(request.getParameter("indiceElemento"));
			elCarrito.remove(indiceCarrito);
			// Vuelve a order.jsp para mas pedidos
			nextPage = "/order.jsp";
		} else if (todo.equals("checkout")) {
			// Enviado por order.jsp.
			// Calcula el precio total de todos los elementos del carrito
			float precioTotal = 0;
			int cantidadTotalOrdenada = 0;
			for (ElementoPedido item : elCarrito) {
				float precio = item.getPrecio();
				int cantidadOrdenada = item.getCantidad();
				precioTotal += precio * cantidadOrdenada;
				cantidadTotalOrdenada += cantidadOrdenada; //
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
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}
}
