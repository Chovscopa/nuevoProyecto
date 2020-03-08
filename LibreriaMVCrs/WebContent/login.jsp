<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="checkLogin" method="post">
	Usuario <input type="text" name="usuario"><br><br>
	Contraseña <input type="password" name="pass"><br><br>
	<input type="submit" name="enviar" value="enviar">
	<%if(request.getAttribute("no")!=null) {
		out.println(request.getAttribute("no"));
	}
	if(request.getAttribute("noExiste")!=null) {
		out.println(request.getAttribute("noExiste"));
	}%>
	
	</form>
</body>
</html>