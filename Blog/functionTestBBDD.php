<?php


function conexion(){
	$server = "localhost";
	$user = "blg";
	$pass = "blg";
	$db = "blg";

	$conn2 = new mysqli($server, $user, $pass, $db);

	if ($conn2->connect_error) {
		$conn2->close();
		$conn2 = null;
	}
	return $conn2;
}



function checkUsuario($usuario, $pass){
	$conn2 = conexion();
	$data = false;
	if ($conn2 != null) {
		$stmt = $conn2->prepare("SELECT * FROM usuarios WHERE nombre=?");
		$stmt->bind_param('s', $usuario);
		$stmt->execute();
		$result = $stmt->get_result();
		try {
			$row = $result->fetch_assoc();
		} finally {

			if ($result->num_rows > 0) {
				if (password_verify($pass, $row['pass'])) {
					$data = true;
				}
			} else {
				//echo "Revise usuario y contraseña";
				$sw = false;
				return $sw;
			}
			$stmt->close();
			$conn2->close();
		}
	}
	return $data;
}



function insertUser(){
	$usuario = $_POST['usuario'];
	$passHash = password_hash($_POST['clave'], PASSWORD_DEFAULT);
	$conn2 = conexion();

	$stmt = $conn2->prepare("INSERT INTO usuarios VALUES (?, ?)");
	$stmt->bind_param('ss', $usuario, $passHash);
	if ($stmt->execute() === true) {

		echo "<div class=\"error\">";
		echo "<center>Usuario insertado</center>";
		echo "</div>";
	} else {
		echo $conn2->connect_error;
		echo "Error en la insercion en la Tabla";
		header("Location: login.php");

		$conn2 = null;
	}
	$stmt->close();
	$conn2->close();
}

function insertPost(){ 

	if (is_uploaded_file($_FILES['userImage']['tmp_name'])) {
		if ($_FILES['userImage']['size'] <= 1000000) {
			if (($_FILES["userImage"]["type"] == "image/gif") || ($_FILES["userImage"]["type"] == "image/jpeg") || ($_FILES["userImage"]["type"] == "image/jpg") || ($_FILES["userImage"]["type"] == "image/png")) {
				$autor = $_SESSION['usuario'];
				$conn2 = conexion();
				$titulo = $_POST['titulo'];
				$contenido = $_POST['contenido'];

				$imgData = addslashes(file_get_contents($_FILES['userImage']['tmp_name']));


				$sql = "INSERT INTO post VALUES(null, '{$autor}','{$titulo}','{$contenido}',null,'{$imgData}')";
				if (mysqli_query($conn2, $sql)) {
					echo "<div class=\"error\">";
					echo "<center>Post Insertado</center>";
					echo "</div>";
				} else {
					echo "<div class=\"error\">";
					echo "<center>Error en la insercion</center>" . mysqli_error($conn2);
					echo "</div>";
				}

				$conn2->close();
			} else {
				echo "<div class=\"error\">";
				echo "<center>Formato no válido (gif, png, jpeg, jpg)</center>";
				echo "</div>";
			}
		} else {
			echo "<div class=\"error\">";
			echo "<center>Tamaño excedido (máximo 1MB)</center>";
			echo "</div>";
		}
	} else {
		echo "<div class=\"error\">";
		echo "<center>No ha seleccionado ninguna imagen</center>";
		echo "</div>";
	}
	
}

function updatePost($id){
	if (is_uploaded_file($_FILES['userImage']['tmp_name'])) {
		if ($_FILES['userImage']['size'] <= 1000000) {
			if (($_FILES["userImage"]["type"] == "image/gif") || ($_FILES["userImage"]["type"] == "image/jpeg") || ($_FILES["userImage"]["type"] == "image/jpg") || ($_FILES["userImage"]["type"] == "image/png")) {
				$conn2 = conexion();
				$titulo = $_POST['titulo'];
				$contenido = $_POST['contenido'];

				$imgData = addslashes(file_get_contents($_FILES['userImage']['tmp_name']));


				$sql = "UPDATE post SET title='{$titulo}', content='{$contenido}', imagen='{$imgData}' WHERE id='{$id}'";
				if (mysqli_query($conn2, $sql)) {
					echo "<div class=\"error\">";
					echo "<center>Post Modificado</center>";
					echo "</div>";
					echo '<script language="javascript">confirm("Post Modificado");</script>';
				} else {
					echo "<div class=\"error\">";
					echo "<center>Error en la insercion</center>" . mysqli_error($conn2);
					echo "</div>";
					
				}


				$conn2->close();
			} else {
				echo "<div class=\"error\">";
				echo "<center>Formato no válido (gif, png, jpeg, jpg)</center>";
				echo "</div>";
			}
		} else {
			echo "<div class=\"error\">";
			echo "<center>Tamaño excedido (máximo 1MB)</center>";
			echo "</div>";
		}
	} else {
		echo "<div class=\"error\">";
		echo "<center>No ha seleccionado ninguna imagen</center>";
		echo "</div>";
	}
	
}

function deletePost($id){
	$conn2 = conexion();

	$stmt = $conn2->prepare("DELETE FROM post WHERE id = ?");
	$stmt->bind_param('i', $id);

	if ($stmt->execute() === true) {

		echo "<div class=\"error\">";
		echo "<center>Post Eliminado</center>";
		echo "</div>";
	} else {

		echo "<div class=\"error\">";
		echo "<center>Error en la eliminacion</center>";
		echo "</div>";

		$conn2 = null;
	}
	$stmt->close();
	$conn2->close();
}


function deleteUser($nombre){
	$conn2 = conexion();

	$stmt = $conn2->prepare("DELETE FROM usuarios WHERE nombre = ?");
	$stmt->bind_param('s', $nombre);

	if ($stmt->execute() === true) {

		echo "<div class=\"error\">";
		echo "<center>Usuario Eliminado</center>";
		echo "</div>";
	} else {

		echo "<div class=\"error\">";
		echo "<center>Error en la eliminacion de Usuario</center>";
		echo "</div>";

		$conn2 = null;
	}
	$stmt->close();
	$conn2->close();
}
