<%@page import="utilidades.*"%>
<%@page import="dao.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

String v = (String) request.getSession().getAttribute("varC1");
CuentaDAOImpl cuentaImpl=new CuentaDAOImpl();
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>Saldo: <%= cuentaImpl.saldo1(Funciones.conexion(),v)%> </p>

</body>
</html>