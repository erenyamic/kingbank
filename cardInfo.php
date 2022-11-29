<?php

$db=new mysqli("localhost","u990586664_kinkbank","Eren_652652","u990586664_kingbank");
if(isset($_POST["email"])){
    $email=$_POST["email"];
    $sorgu=$db->query("select * from cards where email='$email'");
    if($sorgu->num_rows>0){
        while($item=$sorgu->fetch_assoc()){
            echo $item["card_number"]." ".$item["cvc"]." ".$item["name"]." ".$item["discard_date"]." ".$item["limits"]." "."successfully"." ";
        }
    }else
        echo "Card is not exists . . . . . .";
}


?>