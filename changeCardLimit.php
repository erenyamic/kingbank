<?php

$db=new mysqli("localhost","u990586664_kinkbank","Eren_652652","u990586664_kingbank");
$sayac=0;
if(isset($_POST["email"])&&isset($_POST["limit"])&&isset($_POST["cardno"])){
    $email=$_POST["email"];
    $limit=$_POST["limit"];
    $cardno=$_POST["cardno"];
    
    $sorgu2=$db->query("select * from cards where email='$email' and card_number='$cardno'");
    if($sorgu2->num_rows>0){
        $sorgu=$db->query("update cards set limits='$limit' where email='$email' and card_number='$cardno'");
        echo "Successfully";
    }else
        echo "Failed";
   
    
}



?>