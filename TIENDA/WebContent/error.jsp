<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p><h1><b>No hay stock disponible</b></h1></p>
	<%
		request.getSession().invalidate();
	%>
	<a href="order.jsp">Volver</a>
</body>
</html>