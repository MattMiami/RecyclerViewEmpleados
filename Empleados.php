<?php

$server = "localhost";
$user = "root";
$pass = "clave";
$bd = "BDantoniomateos";

//Creamos la conexión
$conexion = mysqli_connect($server, $user, $pass,$bd)
or die("Ha sucedido un error inexperado en la conexion de la base de datos");

//generamos la consulta
$sql = "SELECT * FROM Empleados";
mysqli_set_charset($conexion, "utf8"); //formato de datos utf8

if(!$result = mysqli_query($conexion, $sql)) die();

$empleados = array(); //creamos un array

while($row = mysqli_fetch_array($result))
{
    $nombre=$row['Nombre'];
    $apellido=$row['Apellido'];
    $dni=$row['DNI'];
    $telefono=$row['Telefono'];
    $direccion=$row['Direccion'];
    $foto=$row['Foto'];


    $empleados[] = array('Nombre'=> $nombre, 'Apellido'=> $apellido, 'DNI'=> $dni, 'Telefono'=> $telefono, 'Direccion'=> $direccion, 'Foto'=> $foto);

}

//desconectamos la base de datos
$close = mysqli_close($conexion)
or die("Ha sucedido un error inexperado en la desconexion de la base de datos");


//Creamos el JSON

$json_string = json_encode($empleados);

echo $json_string;

//Si queremos crear un archivo json, sería de esta forma:
/*
$file = 'empleados.json';
file_put_contents($file, $json_string);
*/


?>