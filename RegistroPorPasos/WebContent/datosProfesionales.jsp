<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
	    String departamento=(String) request.getSession().getAttribute("departamento[]");
	    String salario=(String) request.getSession().getAttribute("salario");
	    String comentarios=(String) request.getSession().getAttribute("comentarios");
    	String errores=(String )request.getSession().getAttribute("errores");
    	if(departamento==null){
    		departamento="";
    	}
    	if(salario==null){
    		salario="";
    	}
    	if(comentarios==null){
    		comentarios="";
    	}
    	
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="PasoJava3" method="post">
		<fieldset><legend>DATOS PROFESIONALES</legend>
			<p style="color:red"><%=errores %></p>
			
			Departamento <select name="departamento[]">
							<option value="Marqueting">Marqueting</option>
							<option value="Diseño">Diseño</option>
							<option value="Ventas">Ventas</option>
						</select>
			<br>
			Salario <input type="text" name="salario" value="<%=salario %>"/><br>
			Comentarios <textarea name="comentarios" rows="5" cols="30" placeholder="Cometarios..." value="<%=comentarios %>"></textarea><br>
		
			<input type="submit" name="enviar" value="Grabar informacion e ir al paso 3 - Datos bancarios">
		</fieldset>
		
		
	</form>
</body>
</html>
