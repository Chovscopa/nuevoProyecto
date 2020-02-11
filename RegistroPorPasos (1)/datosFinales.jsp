<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
String usuario=(String) request.getSession().getAttribute("user");
String apellido=(String) request.getSession().getAttribute("Apellidos");
String fecha=(String) request.getSession().getAttribute("Fecha");
String departamento=(String) request.getSession().getAttribute("departamento[]");
String salario=(String) request.getSession().getAttribute("salario");
String comentarios=(String) request.getSession().getAttribute("comentarios");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<fieldset>
	<legend>DATOS PERSONALES</legend>
		<p>Nombre: </p> <%=usuario %>	
		<p>Apellidos: </p> <%=apellido %>
		<p>Fecha: </p> <%=fecha %>
</fieldset>

<fieldset>
	<legend>DATOS PROFESIONALES</legend>
		<p>Departamento: </p> <%=departamento %>	
		<p>Salario: </p> <%=salario %>
		<p>Cometarios</p> <%=comentarios %>	
</fieldset>

<fieldset>
	<legend>DATOS BANCARIOS</legend>
		<p>Nombre: </p> <%=usuario %>	
	
</fieldset>
	
</body>
</html>