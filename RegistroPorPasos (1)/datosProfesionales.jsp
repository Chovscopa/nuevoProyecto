<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="PasoJava3" method="post" enctype="multipart/from-data">
		<fieldset><legend>DATOS PROFESIONALES</legend>
			Departamento <select name="departamento[]">
							<option value="marqueting">Marqueting</option>
							<option value="diseño">Diseño</option>
							<option value="ventas">Ventas</option>
						</select>
			<br>
			Salario <input type="text" name="salario" /><br>
			Comentarios <textarea name="comentarios" rows="5" cols="30" placeholder="Cometarios..."></textarea><br>
		
			<input type="submit" name="enviar" value="Grabar informacion e ir al paso 3 - Datos bancarios">
		</fieldset>
		
		
	</form>
</body>
</html>
