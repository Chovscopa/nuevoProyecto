<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
    	String errores=(String) request.getSession().getAttribute("errores");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="PasoJava4" method="post">
		<fieldset><legend>DATOS BANCARIOS</legend>
		<p style="color:red"><%=errores %></p>
			Cuenta Corriente <input type="text" name="cuenta"><br>
			<input type="submit" name="enviar" value="Grabar informacion e ir al resumen final">
		</fieldset>
		
	</form>
</body>
</html>
