<%
String usuario =(String) request.getAttribute("usuario");
String password =(String) request.getAttribute("password");
%>

<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h2>Login</h2>
<form method="LoginController2" action="LoginControlador">
<input type="hidden" name="log" value="user">
<table>
<tr>
<td>Introduce tu usuario:</td>
<td><input type="text" name="usuario" value="<%=usuario%>"/></td>
</tr>
<tr>

<td>Introduce tu contrase~na:</td>
<td><input type="password" name="password" value="<%=password%>"/></td>
</tr>
</table>
<br/>
<br><br>
<a href="order.jsp">¿No tienes cuenta? Entra como invitado </a>
<input type="submit" value="LOGIN" />
<input type="reset" value="LIMPIAR" />
</form>
</body>
</html>
