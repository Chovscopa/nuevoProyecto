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
	
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
	
	<link rel="icon" type="image/png" href="images/blgIcono.png">
	<style type="text/css">
		body {

			
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
			
			width:100%;
			
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
		#cajas{
			border: solid 1px;
			border-radius: 4px;
			box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);

		}
		img{
			max-width: 40%;
		}
		.col{
			text-align: center;
			border: solid 1px grey;
			border-radius: 4px;
			box-shadow: 4px 4px 2px 1px rgba(0, 0, 0, 0.2);
			
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
		<!---
		<div class="" id=form>
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
		<div class="row">
			<div class="col-sm">
				
				<img src="https://www.futbolemotion.com/imagesarticulos/142335/750/camiseta-adidas-flamengo-primera-equipacion-2020-2021-black-power-red-0.jpg">
				
			</div>
		<div class="col-sm">
			
				<img src="https://www.futbolemotion.com/imagesarticulos/142335/750/camiseta-adidas-flamengo-primera-equipacion-2020-2021-black-power-red-0.jpg">
			
		</div>
		<div class="col-sm">
			
				<img src="https://www.futbolemotion.com/imagesarticulos/142335/750/camiseta-adidas-flamengo-primera-equipacion-2020-2021-black-power-red-0.jpg">
		
		</div>
  		</div>
		-->
		
		
			<div class="row row-cols-1 row-cols-sm-2 row-cols-md-4">
				<div class="col">				
					<img src="https://www.futbolemotion.com/imagesarticulos/142335/750/camiseta-adidas-flamengo-primera-equipacion-2020-2021-black-power-red-0.jpg">
					<button type="button" class="btn btn-lg btn-danger" data-toggle="popover"data-placement="top" title="Popover title" data-content="And here's some amazing content. It's very engaging. Right?">Click to toggle popover</button>
				</div>
				<div class="col">				
					<img src="https://www.futbolemotion.com/imagesarticulos/142335/750/camiseta-adidas-flamengo-primera-equipacion-2020-2021-black-power-red-0.jpg">
				</div>
				<div class="col">				
					<img src="https://www.futbolemotion.com/imagesarticulos/142335/750/camiseta-adidas-flamengo-primera-equipacion-2020-2021-black-power-red-0.jpg">
				</div>
				<div class="col">				
					<img src="https://www.futbolemotion.com/imagesarticulos/142335/750/camiseta-adidas-flamengo-primera-equipacion-2020-2021-black-power-red-0.jpg">
				</div>
			</div>
		

		
		
	</div>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="/docs/4.5/assets/js/vendor/jquery.slim.min.js"><\/script>')</script><script src="/docs/4.5/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script><script src="https://cdn.jsdelivr.net/npm/docsearch.js@2/dist/cdn/docsearch.min.js"></script><script src="/docs/4.5/assets/js/docs.min.js"></script>
</body>

</html>