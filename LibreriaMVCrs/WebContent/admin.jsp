<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page session="true" import="java.util.*,controladores.*" %>
    <%@page session="true" import="modelos.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
	<style>
		table, th, td {border-bottom:1px solid black;
		text-align:center;
		broder-collapse:collapse;
		padding:1px;}
		
	</style>
</head>
<body>
	

	<p>Hola <%=request.getAttribute("usuario") %> </p><a href="logout.jsp">Logout</a>
	<h3>LIBROS</h3>
	<p>LISTA DE LIBROS</p>
		<% ArrayList<Libro> libros=(ArrayList<Libro>)request.getAttribute("listaLibros");%>
		
		<table>
		<tr>
			<th>Id</th>
			<th>Titulo</th>
			<th>Autor</th>
			<th>Precio</th>
			<th>Cantidad</th>
			<th></th>
			<th></th>
		</tr>
		
		<%for(Libro l : libros) {%>
		<tr>
		<form action="controladorAdmin" method="post">
				<td><%=l.getId() %></td>
				<td><%=l.getTitulo() %></td>
				<td><%=l.getAutor() %></td>
				<td><%=l.getPrecio() %></td>
				<td><%=l.getCantidad() %></td>
				<td><input type="submit" value="Modificar" name="accion"></td>
				<td><input type="submit" value="Borrar" name="accion"></td>
				<input type="hidden" value="<%=l.getId() %>" name="id">
			</form>
			</tr>
		<%} %>
		</table>
	<br>
	<form action="controladorAdmin" method="post">
	<input type="submit" value="Agregar" name="accion">
</form>
	
	<h3>USUARIOS</h3>
	Introduzca nombre de usuario: <input type="text" name="user"><br><br>
	Introduzca la contraseña: <input type="password" name="passu">
	
	<p>MODIFICAR USUARIO</p>
	Introduzca el nuevo nombre de usuario: <input type="text" name="userm">
	
	<p>MODIFICAR CONTRASEÑA</p>
	Introduzca la nueva contraseña: <input type="password" name="passm"><br><br>
	<input type="submit" name="modificarUser" value="Modificar User">
	
	<p>BORRAR USUARIO</p>
	<input type="submit" name="borrarUser" value="Borrar User">
	
	<p>AGREGAR USUARIO</p>
	<input type="submit" name="agregarUser" value="Agregar User"><br>
	
	
	
	</form>
</body>
</html>