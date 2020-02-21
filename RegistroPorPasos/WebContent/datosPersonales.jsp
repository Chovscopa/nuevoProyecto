<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String usuario=(String) request.getSession().getAttribute("user");
	String apellido=(String) request.getSession().getAttribute("Apellidos");
	String fecha=(String) request.getSession().getAttribute("Fecha");
	String errores=(String) request.getSession().getAttribute("errores"); 
	if(errores==null){
		errores="";
	}
	if(usuario==null){
		usuario="";
	}
	if(apellido==null){
		apellido="";
	}
	if(fecha==null){
		fecha="";
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="PasoJava2" method="post">
		<fieldset><legend>DATOS PERSONALES</legend>
		<p style="color:red"><%=errores %></p>
		
			Nombre<input type="text" name="user" value="<%= usuario %>">
			Apellidos<input type="text" name="Apellidos" value="<%= apellido %>"><br>
			Fecha de Nacimiento <input type="date" name="Fecha" value="<%= fecha %>"><br>
		
			<input type="submit" name="enviar" value="Grabar informacion e ir al paso 2 - Datos profesionales">
		</fieldset>
		
	</form>
</body>
</html>
