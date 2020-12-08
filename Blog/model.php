<?php

 
 require_once 'config.php'; 

 
function openConex(){
	$conex=new mysqli(DBHOST, DBUSER, DBPWD, DBNAME); 
	
    return $conex;
}    

function getPosts(){	
	$mysqli = openConex();
	
	$result = $mysqli->query('SELECT id, title FROM post ORDER BY id desc');	

	return $result;	
}

function getPostById($id){
	$mysqli = openConex();
 
	$result = $mysqli->query('SELECT date, title, content, author, imagen FROM post WHERE id ='.$id);
	$row = mysqli_fetch_assoc($result);
	
    return $row;
}
function getPostsByAuthor($autor){
	$mysqli = openConex();
 
	$result = $mysqli->query('SELECT id, title FROM post WHERE author ="'.$autor.'"');
	
	
    return $result;
}

function getPostsToUpdate($id){
	$mysqli = openConex();
 
	$result = $mysqli->query('SELECT title, content, imagen FROM post WHERE id ="'.$id.'"');
	
	
    $row = mysqli_fetch_assoc($result);
	
    return $row;
}

function getUsuarios(){	
	$mysqli = openConex();
	
	$result = $mysqli->query("SELECT nombre FROM usuarios WHERE nombre <> 'admin' AND nombre <> 'usr'");	

	return $result;	
}


?>
   

