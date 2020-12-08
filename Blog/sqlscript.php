<?php

	////////////////////////////////// CREACION CONEXION ////////////////////////////////////
    $server="localhost";
    $user="root";
    $pass="";
    

    $conn = new mysqli($server,$user,$pass);

    if($conn->connect_error){
    	$conn->close();
    	$conn=null;
	}else{

		////////////////////////////////// CREACION DASE DE DATOS ////////////////////////////////////

		$stmt = $conn->prepare("CREATE DATABASE IF NOT EXISTS blg CHARACTER SET utf8 COLLATE utf8_general_ci");
	
	
		if($stmt->execute()===true){ 
			
		}else{
			//echo "Error en la creacion de la BBDD";
			$conn->close();
			
		}

		////////////////////////////////// CREACION USUARIO BLG ////////////////////////////////////
		$nuevoUsuario="CREATE USER IF NOT EXISTS 'blg'@'localhost' IDENTIFIED BY 'blg'";

		if($conn->query($nuevoUsuario)===true){
		//echo "Usuario creado"."<br>";
		}else{
			//echo "Error en la creacion del USUARIO";
			$conn->close();
			
		}
		$conn->close();

		////////////////////////////////// ASIGNACION PERMISOS USUARIO TIENDA 4 ////////////////////////////////////

		$priv="GRANT ALL PRIVILEGES on blg.* to 'blg'@'localhost' ";

		$conn = new mysqli($server,$user,$pass);
			
		if($conn->query($priv)===true){
		//echo "Permisos asignados"."<br>";
		}else{
			//echo "Error en la ASIGNACION DE PERMISOS";
			$conn->close();
			
		}
		$conn->close();
		/////

	}

	////////////////////////////// CREACION TABLA USUARIOS ///////////////////////////////////

	$server="localhost";
    $user="root";
    $pass="";
    $db="blg";
    $conn = new mysqli($server,$user,$pass,$db);
	$sql="CREATE TABLE IF NOT EXISTS usuarios(
		nombre VARCHAR(20) NOT NULL PRIMARY KEY UNIQUE,
		pass VARCHAR(200) NOT NULL
		
	)";

	if($conn->query($sql)===true){
		//echo "Tabla creada"."<br>";
	}else{
		echo $conn->connect_error;
		//echo "Error en la creacion de la Tabla usuarios"."<br>";
		$conn->close();
		$conn=null;
	}
	$conn->close();

	////////////////////////////////// INSERCION USUARIOS ////////////////////////////////////

	
	$server="localhost";
    $user="root";
    $pass="";
    $db="blg";

    $conn = new mysqli($server,$user,$pass,$db);

   
	$userr5="admin";
	$userr1="usr";


	$passhash5=password_hash('1234qwer', PASSWORD_DEFAULT);
	$passhash1=password_hash(1234, PASSWORD_DEFAULT);

    

	$sql="INSERT INTO usuarios VALUES('$userr5','$passhash5')";
	if($conn->query($sql)===true){
		//echo "Registro insertado";
	}else{
		echo $conn->connect_error;
		//echo "Error en la insercion en la Tabla";
		$conn=null;
	}
	

	$conn = new mysqli($server,$user,$pass,$db);

	$sql="INSERT INTO usuarios VALUES('$userr1','$passhash1')";
	if($conn->query($sql)===true){
		//echo "Registro insertado";
	}else{
		echo $conn->connect_error;
		//echo "Error en la insercion en la Tabla";
		$conn=null;
	}
	

	////////////////////////////// CREACION TABLA POST ///////////////////////////////////

	$server="localhost";
    $user="root";
    $pass="";
    $db="blg";
    $conn = new mysqli($server,$user,$pass,$db);
	$sql="CREATE TABLE IF NOT EXISTS post(
		id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
		author varchar(30) NOT NULL,
		title varchar(200) NOT NULL,
		content varchar(20000) NOT NULL,
		date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
		imagen mediumblob NULL,
		CONSTRAINT fk_posts_users FOREIGN KEY(author) REFERENCES usuarios(nombre) ON DELETE CASCADE ON UPDATE CASCADE
	)";

	if($conn->query($sql)===true){
		//echo "Tabla creada"."<br>";
	}else{
		echo $conn->connect_error;
		//echo "Error en la creacion de la Tabla usuarios"."<br>";
		$conn->close();
		$conn=null;
	}
	$conn->close();
	
	////////////////////////////////// INSERCION POSTS ////////////////////////////////////
	
	$server="localhost";
    $user="root";
    $pass="";
    $db="blg";
	
    $conn = new mysqli($server,$user,$pass,$db);

	$sql="INSERT INTO post VALUES(1, 'usr', 'Titulo 1', 'Contenido del post 1', CURDATE(), null),
							(2, 'usr', 'Titulo 2', 'Contenido del post 2', CURDATE(), null),
							(3, 'usr', 'Titulo 3', 'Contenido del post 3', CURDATE(), null),
							(4, 'usr', 'Titulo 4', 'Contenido del post 4', CURDATE(), null),
							(5, 'usr', 'Titulo 5', 'Contenido del post 5', CURDATE(), null)";
	if($conn->query($sql)===true){
		//echo "Registro insertado";
	}else{
		echo $conn->connect_error;
		//echo "Error en la insercion en la Tabla";
		$conn=null;
	}


	////////////////////////////////// CREACION TABLA CHAT ////////////////////////////////////
	
	$conn = new mysqli($server,$user,$pass,$db);

	
	$sql="CREATE TABLE IF NOT EXISTS `chat` (
		`id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
		`name` varchar(255) DEFAULT NULL,
		`message` text,
		`created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
		
	)";

	if($conn->query($sql)===true){
		//echo "Tabla creada"."<br>";
	}else{
		echo $conn->connect_error;
		//echo "Error en la creacion de la Tabla chat"."<br>";
		$conn->close();
		$conn=null;
	}
	$conn->close();
	

	