<?php
	session_start();    // unirse a la sesión
						
	$_SESSION = array();
	
	setcookie(session_name(), 123, time() - 1000); // eliminar la cookie
	session_destroy();	// eliminar la sesion
	
	header("Location: index.php");
?>