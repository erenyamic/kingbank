<?php

$db=new mysqli("localhost","u990586664_kinkbank","Eren_652652","u990586664_kingbank");
if(isset($_POST["email"])&&isset($_POST["date"])){
    $email=$_POST["email"];$date=$_POST["date"];
    $sorgu=$db->query("select * from actions where email='$email'");
    if($sorgu->num_rows>0){
        $sorgu2=$db->query("update actions set lastWrong='$date' where email='$email'");
    }
}






?>