<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page session="true" import="java.util.*,controladores.*" %>
    <%@page session="true" import="modelos.*" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<h2>Librera MVC</h2>
<p>Bienvenido <%=request.getAttribute("usuario") %></p>
<a href="logout.jsp">Logout</a>
<hr /><br />

<p><strong>Elige un libro y la cantidad:</strong></p>
<form name="AnyadirForm" action="shopping" method="POST">
		<input type="hidden" name="todo" value="add">
		Titulo: <select name="idLibro">
				<%
				//LibrosBD.cargarDatos();
				// Scriptlet 1: Carga los libros en el select.
				for (int i = 0; i < LibrosBD.tamanyo(); i++) {
					if(LibrosBD.getCantidades(i)>0) {
						out.println("<option value='" + i + "'>");
						out.println(LibrosBD.getTitulo(i) + " | " + LibrosBD.getAutor(i)
						+ " | " + LibrosBD.getPrecio(i)+" | "+LibrosBD.getCantidades(i));
						out.println("</option>");
					}else {
						out.println("<option value='" + i + "' disabled>");
						out.println(LibrosBD.getTitulo(i) + " | " + LibrosBD.getAutor(i)
						+ " | " + LibrosBD.getPrecio(i)+" | Agotado!");
						out.println("</option>");
					}
				}
				%>
		</select>
		Cantidad: <input type="text" name="cantidad" size="10" value="1">
		
		<input type="submit" value="Añadir a la cesta">
</form>


<br /><hr /><br />
<%
// Scriptlet 2: Chequea si la cesta esta vacia.

List<ElementoPedido> cesta =(List<ElementoPedido>) session.getAttribute("carrito");
if (cesta != null && cesta.size() > 0) {
%>
<p><strong>Tu cesta contiene:</strong></p>
<table border="1" cellspacing="0" cellpadding="5">
<tr>
<th>Titulo</th>
<th>Autor</th>
<th>Precio</th>
<th>Cantidad</th>
<th>&nbsp;</th>
</tr>
<%
		// Scriptlet 3: Muestra los libros del carrito.
		for (int i = 0; i < cesta.size(); i++) {
			ElementoPedido elementoPedido = cesta.get(i);
			%>
			<tr>
			<form name="borrarForm" action="shopping" method="POST">
				<input type="hidden" name="todo" value="remove">
				<input type="hidden" name="indiceElemento" value="<%= i %>">
				<input type="hidden" name="idLib" value="<%= elementoPedido.getIdLibro() %>">
				<input type="hidden" name="cant" value="<%= elementoPedido.getCantidad() %>">
				<td><%= elementoPedido.getTitulo() %></td>
				<td><%= elementoPedido.getAutor() %></td>
				<td align="right"><%= elementoPedido.getPrecio() %></td>
				<td align="right"><%= elementoPedido.getCantidad() %></td>
				<td><input type="submit" value="Eliminar de la cesta"></td>
			</form>
			</tr>
		<%
		}
		%>

</table><br />
<form name="checkoutForm" action="shopping" method="POST">
<input type="hidden" name="todo" value="checkout">
<input type="submit" value="Checkout">
</form>
<%
} // if
%>
</body>
</html>