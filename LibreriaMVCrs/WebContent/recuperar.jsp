<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="controladorRecuperar" method="post">
		Contraseña actual <input type="password" name="actual"><br><br>
		Nueva contraseña <input type="password" name="nueva1"><br><br>
		Repite nueva contraseña <input type="password" name="nueva2"><br><br>
		<input type="submit" name="cambiar" value="Cambiar">
	<br><br>
	<%if(request.getAttribute("recuperar")!=null) {
		out.println(request.getAttribute("recuperar"));
	}%>
	<br><br>
	<a href="login">Volver al login</a>
	</form>
	
	
</body>
</html>