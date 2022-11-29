<?php
$db=new mysqli("localhost","u990586664_kinkbank","Eren_652652","u990586664_kingbank");

if(isset($_POST["email"])){
    $email=$_POST["email"];
    $sorgu=$db->query("select * from cards where email='$email'");
    if($sorgu->num_rows<2)
        echo "yes";
    else
        echo "no";
}

?>