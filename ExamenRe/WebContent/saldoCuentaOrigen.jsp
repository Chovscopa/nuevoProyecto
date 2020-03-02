<%@page import="generacionDinamica.generacionDinamica"%>
<%@page import="generacionDinamica.Datos"%>
<%@page import="funciones.Funciones"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

String v = (String) request.getSession().getAttribute("varC1");

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>Saldo: <%= Datos.saldo1(v)%> </p>

</body>
</html>