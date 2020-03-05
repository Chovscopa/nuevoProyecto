<%@page import="utilidades.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

String[] cue = (String[]) request.getSession().getAttribute("cuentas[]");
String[] cue2 = (String[]) request.getSession().getAttribute("cuentas[]2");

String errores=(String) request.getSession().getAttribute("errores"); 
String c=(String) request.getSession().getAttribute("c"); 
if(c==null){
	c="";
}

if(errores==null){
	errores="";
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="S2" method="post">
		<%Datos.cargarDatosOrigen(Funciones.conexion()); %>
		<p style="color:red"><%= errores %></p>

			Cuenta origen<br>					
			<%=generacionDinamica.generaSelect1(Datos.arrayCuentas,cue)%>  <input type="submit" name="saldoN1" value="Saldo">	<br> 	<br> 
			Cantidad a transferir<br>	 
			<input type="text" name="cantidadT" value="<%= c %>"><br><br> 
			Cuenta destino<br>
			<%=generacionDinamica.generaSelect2(Datos.arrayCuentas,cue)%> 	<input type="submit" name="saldoN2" value="Saldo">	<br> 
			
			<input type="submit" name="Transferencia" value="Transferir"><br> 	<br> 
			
			<input type="submit" name="VerTransferencias" value="VerTransferencias"><br> 	<br> 
			<input type="submit" name="Salir" value="Salir">
		
	</form>
</body>
</html>