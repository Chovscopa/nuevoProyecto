<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="controladorAdmin" method="post">
	<p>BORRAR LIBRO</p>
	<label for="id">ID: </label><input type="number" name="idb" value="<%=request.getParameter("id")%>"><br><br>
	<input type="submit" value="Confirmar" name="accion">
	<input type="hidden" name="c" value="b">
	<br><br>
	</form>
</body>
</html>