<?php
include "functionTestBBDD.php";
session_start();

if ($_SESSION['usuario']!='admin' || $_SESSION['usuario']== null ){
	header("Location: login.php");
}


?>

<!DOCTYPE html>
<html lang="es">

<head>
	<title>Administrador</title>
	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">

	<script src="bootstrap/js/jquery.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<meta charset="UTF-8">
	<link rel="icon" type="image/png" href="images/blgIcono.png">
	<style type="text/css">
		body {

			

			background-image: url('images/Logo1.png');
			background-repeat: repeat-y;
			width: 100%;
		}

		h2 {

			color: grey;
		}

		label {
			color: grey;
		}

		.error {
			background-color: #F08335;
			color: black;
			border-radius: 4px;
			border: 1px solid grey;
		}

		.container {
			margin-top: 5%;
			width: 48%;
			background-color: #f2f2f2;
			padding-top: 5%;
			padding-bottom: 5%;
			padding-right: 5%;
			padding-left: 5%;

			border-style: solid;
			border-width: 1px;
			border-color: #d9d9d9;
		}

		.btn-primary {
			background-color: #d3d3d3;
			color: #357ebd;
		}

		.navbar-inverse {
			background-color: #d3d3d3;
			border-color: #3b173da8;
		}

		.navbar-inverse .navbar-brand {
			color: white;
		}

		a:hover {
			color: #50546d;
		}

		.navbar-inverse .navbar-nav {
			color: white;
		}
	</style>
</head>

<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.php">Blg, Blg, Blg</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav navbar-right">

					<li><a href="logout.php"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">
		<p>
			<h2>
				<center>Acciones Administrador</center>
			</h2>
		</p>
		<a href="insertPosttAdmin.php"><button class="btn btn-primary">Crear Post</button></a>
		<a href="editarPostAdmin.php"><button class="btn btn-primary">Editar Posts</button></a>
		<a href="eliminarPostAdmin.php"><button class="btn btn-primary">Eliminar Post</button></a>
		<a href="eliminarUsuario.php"><button class="btn btn-primary">Eliminar Usuario</button></a>
		<a href="home.php"><button class="btn btn-primary">Chat</button></a>


	</div>


</body>

</html>




		