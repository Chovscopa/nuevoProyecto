<%@page import="generacionDinamica.generacionDinamica"%>
<%@page import="generacionDinamica.Datos"%>
<%@page import="funciones.Funciones"%>
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
	
	String sex = (String) request.getSession().getAttribute("genero");
	
	String[] pai = (String[]) request.getSession().getAttribute("paises[]");
	
	String cop= (String) request.getSession().getAttribute("casadoOpareja");
	
	String h= (String) request.getSession().getAttribute("hijo");
	
	
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
			
			<%=generacionDinamica.generarButton(sex) %><br>
			<%=generacionDinamica.generaSelectPaises(Datos.arrayPaises,pai) %><br>
			<%=generacionDinamica.generaChecboxCoP(cop) %>
			<%=generacionDinamica.generaChecboxH(h) %><br>
		
			<input type="submit" name="enviar" value="Grabar informacion e ir al paso 2 - Datos profesionales">
		</fieldset>
		
	</form>
</body>
</html>
