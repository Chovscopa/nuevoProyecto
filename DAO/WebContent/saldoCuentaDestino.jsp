<%@page import="utilidades.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

String v2 = (String) request.getSession().getAttribute("varC2");

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>Saldo: <%= Datos.saldo1(Funciones.conexion(),v2)%> </p>

</body>
</html>