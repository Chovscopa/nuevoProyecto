<?php

include "sqlscript.php";

require_once 'model.php'; 

$posts = getPosts();

require 'templates/list.php';

?>
