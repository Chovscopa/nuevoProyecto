<?php
include "functionTestBBDD.php";
require_once 'model.php';
session_start();


if ($_SESSION['usuario']!='admin' || $_SESSION['usuario']== null ){
	header("Location: login.php");
}


if ($_SERVER["REQUEST_METHOD"] == "POST") {

	deleteUser($_SESSION['nombre']);
} else {
	$_SESSION['nombre'] = $_GET['nombre'];

	$usuario = getPostsToUpdate($_SESSION['nombre']);
}




?>

<!DOCTYPE html>
<html lang="es">

<head>
	<title>Eliminar Usuario</title>
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

		.container {
			margin-top: 5%;
			width: 49%;
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

		.error {
			background-color: #F08335;
			color: black;
			border-radius: 4px;
			border: 1px solid grey;
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
				<center>Confirmar Eliminación Usuario</center>
			</h2>
		</p>

		<form class="form-horizontal" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="POST">
			<div class="form-group">
				<p>¿ESTAS SEGURO DE ELIMINAR EL POST?</p> <br>
				<p>¡SI HACES CLICK EN "ELIMINAR" NO SE PODRÁN RECUPERAR LOS DATOS!</p>
				<p>ASÍ MISMO SE ELIMINIRÁN TODOS LOS POST ASOCIADOS AL USUARIO.</p>
			</div>

			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-primary">Eliminar Usuario</button>

			</div>

			<br>

		</form>

		<a href="sesiones1_principal_admin.php"> Volver Atrás</a>
	</div>
</body>

</html>