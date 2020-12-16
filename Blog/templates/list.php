<!DOCTYPE html>
<html lang="es">

<head>

  <title>Blg, Blg, Blg</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src="bootstrap/js/jquery.min.js"></script>
  <script src="bootstrap/js/bootstrap.min.js"></script>
  <link rel="icon" type="image/png" href="images/blgIcono.png">
  <style>
    body {
      background-image: url('images/Logo1.png');
      background-repeat: repeat-y;
      background-size: auto;
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
      margin-bottom: 5%;
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

    footer {
      background-color: #d3d3d3;
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
          <li><a href="registrar.php"><span class="glyphicon glyphicon-user"></span> Registro</a></li>
          <li><a href="login.php"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
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
      <h1>Lista de Posts</h1>
    </center>
    <ul>
      <?php foreach ($posts as $post) : ?>
        <li>
          <h2>
            <a href="templates/show.php?id=<?php echo $post['id'] ?>" >
              <?php echo $post['title'] ?>
            </a>
            <h2>

        </li>
      <?php endforeach; ?>
    </ul>

  </div>
</body>
<!-- Footer -->
<footer class="page-footer font-small blue pt-4">

  <!-- Footer Links -->
  <div class="container-fluid text-center text-md-left">

    <!-- Grid row -->
    <div class="row">

      <!-- Grid column -->
      <div class="col-md-6 mt-md-0 mt-3">

        <!-- Content -->
        <h5 class="text-uppercase">Blg, Blg, Blg!!!</h5>
        <p>Use este blog con responsabilidad.</p>

      </div>
      <!-- Grid column -->

      <hr class="clearfix w-100 d-md-none pb-3">

      <!-- Grid column -->
      <div class="col-md-3 mb-md-0 mb-3">

        <!-- Links -->
        <h5 class="text-uppercase">Internal Links</h5>

        <ul class="list-unstyled">
          <li>
            <a href="login.php">Login</a>
          </li>
          <li>
            <a href="registrar.php">Registro</a>
          </li>


        </ul>

      </div>
      <!-- Grid column -->

      <!-- Grid column -->
      <div class="col-md-3 mb-md-0 mb-3">

        <!-- Links -->
        <h5 class="text-uppercase">External Links</h5>

        <ul class="list-unstyled">
          <li>
          <a href="https://www.facebook.com/"><i class="fa fa-facebook"></i></a>
          </li>
          <li>
          <a href="https://www.twitter.com/"><i class="fa fa-twitter"></i></a>
          </li>


        </ul>

      </div>
      <!-- Grid column -->

    </div>
    <!-- Grid row -->

  </div>
  <!-- Footer Links -->

  <!-- Copyright -->
  <div class="footer-copyright text-center py-3">© 2020 Copyright:

    <a href="mailto:ovidiovermenouze@gmail.com">Ovidio Vermenouze Fernandez</a>
    
  </div>
  <!-- Copyright -->

</footer>
<!-- Footer -->

</html>