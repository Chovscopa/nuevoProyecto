<?php
include "functionTestBBDD.php";
require_once 'model.php';
session_start();
if ($_SESSION['usuario']!='admin' || $_SESSION['usuario']== null ){
	header("Location: login.php");
}
$usuarios = getUsuarios();

?>

<!DOCTYPE html>
<html lang="es">

<head>
    <title>Página principal</title>
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

        <center>
            <h2 style="color:grey; margin-top:20%">Blog, Blog, Blog!!!</h2>
        </center>
        <center><img src="images/Logo1.png" class="der"></a></center>

        <center>
            <h1>Usuarios</h1>
        </center>
        <ul>
            <?php require_once 'model.php';

            $usuarios = getUsuarios();

            foreach ($usuarios as $usuario) : ?>
                <li>
                    <h2>
                        <h3>
                            <?php echo $usuario['nombre'] ?>  <a href="verficacionEliminacionUsuario.php?nombre=<?php echo $usuario['nombre']?>"><button class="btn btn-primary">Eliminar Usuario</button></a>
            </h3>
                        
                    <h2>

                </li>
            <?php endforeach; ?>
        </ul>
        <a href="sesiones1_principal_admin.php"> Volver Atrás</a>
    </div>
</body>

</html>


