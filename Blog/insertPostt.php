<?php
include "functionTestBBDD.php";
session_start();

if (!isset($_SESSION['usuario'])) {
	header("Location: login.php?redirigido=true");
}


if ($_SERVER["REQUEST_METHOD"] == "POST") {

	insertPost();

	$fechaActual = date('d-m-Y H:i:s');

	if (!isset($_COOKIE[$_SESSION['usuario']])) {
		setcookie($_SESSION['usuario'], $fechaActual, time() + 9999, "login.php");
	}
}

?>

<!DOCTYPE html>
<html lang="es">

<head>
	<title>Insertar Post</title>
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
			padding-right: 10%;
			padding-left: 10%;

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
				<center>Crear Post</center>
			</h2>
		</p>
		<form class="form-horizontal" action="" method="POST" enctype="multipart/form-data">
			<div class="form-group">
				<label class="control-label col-sm-2" for="email">Título de Post</label>
				<div class="col-sm-10">

					<input class="form-control" id="usuario" name="titulo" type="text" required>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Contenido</label>
				<div class="col-sm-10">

					<textarea class="form-control" id="clave" name="contenido" type="textfield" required></textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Imagen</label>
				<div class="col-sm-10">

				<input name="userImage" type="file" class="inputFile" />
				</div>
			</div>
			
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-primary">Aceptar</button>

			</div>

			<br>
			
			<a href="user.php"> Volver Atrás</a>
		</form>
	</div>


</body>

</html>