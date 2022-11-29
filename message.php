<?php
$db=new mysqli("localhost","u990586664_kinkbank","Eren_652652","u990586664_kingbank");
if(isset($_POST["email"])&&isset($_POST["message"])){
    $email=$_POST["email"];
    $message=$_POST["message"];
    $sorgu=$db->query("insert into support(email,message) values('$email','$message')");
    echo "Successfully";
}





?>