<?php

include "config.php";
session_start();
if($_POST){
		$nombre=$_SESSION['usuario'];
	    $mensaje=$_POST['msg'];

		$sql="INSERT INTO `chat`(`name`, `message`) VALUES ('".$nombre."', '".$mensaje."')";
        $conn=mysqli_connect(DBHOST,DBUSER,DBPWD,DBNAME);
		$query = mysqli_query($conn,$sql);
		if($query){
			header('Location: chat.php');
		}
		else{
			echo "Algo salió mal";
		}

}
?>
