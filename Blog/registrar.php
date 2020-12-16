<?php
include "functionTestBBDD.php";

if ($_SERVER["REQUEST_METHOD"] == "POST") {

	session_start();

	insertUser();
}

?>
<!DOCTYPE html>
<html lang="es">

<head>
	<title>Formulario de Registro</title>

	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">

	<script src="bootstrap/js/jquery.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
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
					<li><a href="registrar.php"><span class="glyphicon glyphicon-user"></span> Registro</a></li>
					<li><a href="login.php"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">

		<p>
			<h2>
				<center>Registro</center>
			</h2>
		</p>
		<form class="form-horizontal" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="POST">
			<div class="form-group">
				<label class="control-label col-sm-2" for="email">Usuario</label>
				<div class="col-sm-10">

					<input class="form-control" value="<?php if (isset($usuario)) echo $usuario; ?>" id="usuario" name="usuario" type="text" required>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Clave</label>
				<div class="col-sm-10">

					<input class="form-control" id="clave" name="clave" type="password" required>

				</div>
			</div>
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-primary">Aceptar</button>

			</div>

			<br>
			<a href="login.php">Login</a>
		</form>
	</div>
</body>

</html>