<?php
$db=new mysqli("localhost","u990586664_kinkbank","Eren_652652","u990586664_kingbank");
if(isset($_POST["email"])){
    $email=$_POST["email"];
    $sorgu=$db->query("select * from money where email='$email' order by Id desc");
    if($sorgu->num_rows>0){
        while($item=$sorgu->fetch_assoc()){
            echo "\nSender: ".$item["email"]."\nRecipient Iban: ".$item["iban"]."\nQuantity: ".$item["SendMoney"]." $"."\nExplanation: ".$item["commentt"]."\nDate: ".$item["Date"]."\n---\n";
        }
    }
}





?>