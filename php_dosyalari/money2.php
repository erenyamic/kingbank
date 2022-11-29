<?php
$ibn;
$db=new mysqli("localhost","u990586664_kinkbank","Eren_652652","u990586664_kingbank");
if(isset($_POST["email"])){
    $email=$_POST["email"];
    $sorgu=$db->query("select Iban from user where email='$email'");
    if($sorgu->num_rows>0){
        while($item=$sorgu->fetch_assoc()){
            $ibn=$item["Iban"];
        }
    }
    $sorgu2=$db->query("select * from money2 where iban='$ibn' order by Id desc");
    if($sorgu->num_rows>0){
        while($item2=$sorgu2->fetch_assoc()){
            echo "\nSender: ".$item2["email"]."\nRecipient Iban: ".$item2["iban"]."\nQuantity: ".$item2["GetMoney"]." $"."\nExplanation: ".$item2["commentt"]."\nDate: ".$item2["Date"]."\n---\n";
        }
    }
}




?>