<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="" method="post">
	Has cerrado sesion
		<%HttpSession sesion=request.getSession(true);
			sesion.invalidate(); %>
			<a href="login">Volver al login</a>
	</form>
</body>
</html>