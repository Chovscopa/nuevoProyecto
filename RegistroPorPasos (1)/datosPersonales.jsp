<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="PasoJava2" method="post">
		<fieldset><legend>DATOS PERSONALES</legend>
			Nombre<input type="text" name="user">
			Apellidos<input type="text" name="Apellidos"><br>
			Fecha de Nacimiento <input type="date" name="Fecha"><br>
		
			<input type="submit" name="enviar" value="Grabar informacion e ir al paso 2 - Datos profesionales">
		</fieldset>
		
	</form>
</body>
</html>
