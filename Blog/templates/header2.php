<!DOCTYPE html>
<html lang="es">

<head>

  <title>Blg, Blg, Blg</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
  <script src="bootstrap/js/jquery.min.js"></script>
  <script src="bootstrap/js/bootstrap.min.js"></script>
  <link rel="icon" type="image/png" href="images/blgIcono.png">
  <style type="text/css">
    body {
      background-image: url('images/home.jpg');
      background-repeat: repeat;
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

    .navbar-inverse .navbar-nav>li>a {
      color: #999;
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
        <li><a href="javascript:history.back()"><span class="glyphicon glyphicon-arrow-left"></span> Volver</a></li>
          <li><a href="logout.php"><span class="glyphicon glyphicon-log-out"></span> Salir</a></li>
        </ul>
      </div>
    </div>
  </nav>