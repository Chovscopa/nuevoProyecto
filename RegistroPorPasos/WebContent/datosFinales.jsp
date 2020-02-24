<%@page import="generacionDinamica.generacionDinamica"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
String usuario=(String) request.getSession().getAttribute("user");
String apellido=(String) request.getSession().getAttribute("Apellidos");
String fecha=(String) request.getSession().getAttribute("Fecha");
String departamento=(String) request.getSession().getAttribute("departamento[]");
String salario=(String) request.getSession().getAttribute("salario");
String comentarios=(String) request.getSession().getAttribute("comentarios");
String cuenta=(String) request.getSession().getAttribute("cuenta");

String sex = (String) request.getSession().getAttribute("genero");

String[] pai = (String[]) request.getSession().getAttribute("paises[]");
String exo="";
for(int i=0;i<pai.length;i++){
	exo+=pai[i]+" ";
}

String cop= (String) request.getSession().getAttribute("casadoOpareja");
String copLimpio=generacionDinamica.limpiarNull1(cop);
String hij = (String) request.getSession().getAttribute("hijo");
String hijLimpio=generacionDinamica.limpiarNull1(hij);


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<fieldset>
		<legend>DATOS PERSONALES</legend>
			<p>Nombre: <b><%=usuario %></b></p>	
			<p>Apellidos: <b><%=apellido %></b></p>
			<p>Fecha: <b><%=fecha %></b></p>
			<p>Genero: <b><%=sex %></b></p>
			<p>Nacionalidad: <b><%= exo%></b></p>
			<p>¿Casado o pareja de hecho? <b><%= copLimpio%></b></p>
			<p>¿Hijos? <b><%= hijLimpio%></b></p>
			
	</fieldset>
	
	<fieldset>
		<legend>DATOS PROFESIONALES</legend>
			<p>Departamento: <b><%=departamento %></b></p>	
			<p>Salario: <b><%=salario %></b></p>
			<p>Cometarios: <b><%=comentarios %></b></p>
	</fieldset>
	
	<fieldset>
		<legend>DATOS BANCARIOS</legend>
			<p>Numero de cuenta: <b><%=cuenta %></b></p>	
		
	</fieldset>

<a href="datosPersonales.jsp">Volver al principio</a>	
</body>
</html>