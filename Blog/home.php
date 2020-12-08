<?php
session_start();
if (isset($_SESSION['usuario'])) {
	include "templates/header2.php";

?>
	<style>
		h2 {
			color:#9A9898;
		}

		label {
			color: grey;	
		}

		span {
			color: grey;
			font-weight: bold;
		}

		.container {
			margin-top: 3%;
			width: 60%;
			background-color: #f2f2f2;
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

		
	</style>

	<div class="container">
		<center>
			<h2>Bienvenid@ <span style="color:#357ebd;"><?php echo $_SESSION['usuario']; ?> !</span></h2>
			<br><br>
			<label>Clic abajo para entrar en el chat</label><br>
			<br><br>
			<a href="chat.php" class="btn btn-primary">Abre el chat</a>

	</div>

	</body>

	</html>
<?php
} else {
	header('location:index.php');
}
?>