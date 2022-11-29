<?php

$db=new mysqli("localhost","u990586664_kinkbank","Eren_652652","u990586664_kingbank");
if(isset($_POST["email"])&&isset($_POST["cardno"])){
    $email=$_POST["email"];$cardNo=$_POST["cardno"];
    $sorgu=$db->query("select * from cards where email='$email' and card_number='$cardNo'");
    if($sorgu->num_rows>0){
        $sorgu2=$db->query("delete from cards where card_number='$cardNo' and email='$email'");
        echo "Successfully";
    }
    else
        echo "Failed";
    
        
}


?>