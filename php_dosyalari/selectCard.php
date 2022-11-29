<?php
$db=new mysqli("localhost","u990586664_kinkbank","Eren_652652","u990586664_kingbank");
if(isset($_POST["email"])&&isset($_POST["card"])){
    $email=$_POST["email"];$card=$_POST["card"];
    if($card=="1"){
        $sorgu=$db->query("select * from cards where email='$email'");
        if($sorgu->num_rows>0){
            while($item=$sorgu->fetch_assoc()){
            echo $item["card_number"]." ".$item["cvc"]." ".$item["name"]." ".$item["discard_date"]." ".$item["limits"]." ";
            }
        
        }
    }else if($card=="2"){
        $sorgu2=$db->query("select * from cards where email='$email' order by Id desc");
        if($sorgu2->num_rows>0){
            while($item=$sorgu2->fetch_assoc()){
            echo $item["card_number"]." ".$item["cvc"]." ".$item["name"]." ".$item["discard_date"]." ".$item["limits"]." ";
            }
        
        }
    }
}

?>