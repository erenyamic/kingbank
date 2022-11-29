<?php

$db=new mysqli("localhost","u990586664_kinkbank","Eren_652652","u990586664_kingbank");
if(isset($_POST["email"])){
    $email=$_POST["email"];
    $sorgu1=$db->query("delete from user where email='$email'");
    $sorgu2=$db->query("delete from actions where email='$email'");
    $sorgu3=$db->query("delete from cards where email='$email'");
    $sorgu4=$db->query("update money set email=' ' where email='$email'");
    $sorgu5=$db->query("update money2 set email='$email !!!' where email='$email'");
    
    echo "Deleted";
    
    
        
}else
    echo "Failed";


?>