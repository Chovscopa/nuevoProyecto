<?php
session_start();
if (isset($_SESSION['usuario'])) {
  include "templates/header2.php";
  include "config.php";

  $sql = "SELECT * FROM `chat`";
  $conn = mysqli_connect(DBHOST, DBUSER, DBPWD, DBNAME);
  $query = mysqli_query($conn, $sql);
?>
  <style>
    h2 {
      color: #9A9898;
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
    }

    .display-chat {
      height: 300px;
      background-color: #d3d3d3;
      margin-bottom: 4%;
      overflow: auto;
      padding: 15px;

    }

    .message {
      background-color: #e3e3e3;
      color: #357ebd;
      border-radius: 5px;
      padding: 5px;
      margin-bottom: 3%;
    }
  </style>

  <meta http-equiv="refresh" content="10">

  <script>
    $(document).ready(function() {
      // Set trigger and container variables
      var trigger = $('.container .display-chat '),
        container = $('#content');

      //Scroll hacia abajo
      var objDiv = document.getElementById("display-chat");
      objDiv.scrollTop = objDiv.scrollHeight;

      // Fire on click
      trigger.on('click', function() {
        // Set $this for re-use. Set target from data attribute
        var $this = $(this),
          target = $this.data('target');

        // Load target page into container
        container.load(target + '.php');


        // Stop normal link behavior
        return false;
      });
    });

    function checkearTecla(e) {
      if (e.keyCode == 13) // 13 es el código de tecla del enter
        document.getElementById("formID").submit(); // envío el formulario

      return true; // Devuelvo true en caso de no ser el enter
    }
  </script>




  <div class="container">
    <center>
      <h2>Bienvenid@ <span style="color:#357ebd;"><?php echo $_SESSION['usuario']; ?> !</span></h2>
      <label>Aquí puedes chatear de forma segura</label>
    </center></br>
    <div class="display-chat" id="display-chat">
      <?php
      if (mysqli_num_rows($query) > 0) {
        while ($row = mysqli_fetch_assoc($query)) {
      ?>
          <div class="message">
            <p>
              <span><?php echo $row['name']; ?> :</span>
              <?php echo $row['message']; ?>
            </p>
          </div>
        <?php
        }
      } else {
        ?>
        <div class="message">
          <p>
            No hay ninguna conversación previa.
          </p>
        </div>
      <?php
      }
      ?>

    </div>



    <form class="form-horizontal" method="post" action="sendMessage.php" id="formID">
      <div class="form-group">
        <div class="col-sm-10">
          <textarea name="msg" class="form-control" placeholder="Introduce mensaje..." onkeypress="return checkearTecla(event)"></textarea>
        </div>

        <div class="col-sm-2">
          <button type="submit" class="btn btn-primary">Enviar</button>
        </div>

      </div>
    </form>
  </div>


  </body>

  </html>
<?php
} else {
  header('location:index.php');
}
?>