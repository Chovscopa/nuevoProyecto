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
	<p>AGREGAR LIBRO</p>
	<label for="titulo">TITULO: </label><input type="text" name="titulo"><br><br>
	<label for="autor">AUTOR: </label><input type="text" name="autor"><br><br>
	<label for="precio">PRECIO: </label><input type="number" name="precio"><br><br>
	<label for="cant">CANTIDAD: </label><input type="number" name="cant"><br><br>
	<input type="submit" value="Confirmar" name="accion">
	<input type="hidden" name="c" value="a">
	<br>
	</form>
</body>
</html>