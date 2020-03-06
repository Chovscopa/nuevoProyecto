<%@page import="utilidades.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="pojo.*"%>
<%@page import="dao.*"%>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

ArrayList<Movimiento> ar = (ArrayList<Movimiento>) request.getSession().getAttribute("arM");
MovimientoDAOImpl mov=new MovimientoDAOImpl();
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>Transeferencias: <br><%= mov.verArrayMov2(ar)%> </p>
<a href="FormularioInicial.jsp">Volver</a>

</body>
</html>