<?php require_once '../model.php'; ?>

<?php $post = getPostById($_GET['id']); ?>


<!DOCTYPE html>
<html lang="es">
<head>

	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
	<script src="../bootstrap/js/jquery.min.js"></script>
	<script src="../bootstrap/js/bootstrap.min.js"></script>
	<link rel="icon" type="image/png" href="images/blgIcono.png">

	<style>
		body {
			background-image: url('../images/Logo1.png');
			background-repeat: repeat-y;
			width: 100%;
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
		img{
			max-width:500px;
          	max-height:500px;
		}
		.der{
      		width: 75%;
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
				<a class="navbar-brand" href="../index.php">Blg, Blg, Blg</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav navbar-right">

					<li><a href="../login.php"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">

		<h1><?php echo $post['title'] ?></h1>
		<div><?php echo $post['date'] ?></div>
		<div><?php echo $post['author'] ?></div>
		<br>
		<div>
			<?php echo $post['content'] ?>
		</div><br>
		<div>
			<img src="data:image/;charset=utf8;base64,<?php echo base64_encode($post['imagen']); ?>" class="der"/> 
		</div>
		
		<div>
			
			<br>
			<a href="javascript:history.back()"> Volver Atrás</a>

		</div>
	</div>
</body>

</html>