<?php
$ibn;
$total=0;
$db=new mysqli("localhost","u990586664_kinkbank","Eren_652652","u990586664_kingbank");
if(isset($_POST["email"])){
    $email=$_POST["email"];
    $sorgu=$db->query("select Iban from user where email='$email'");
    if($sorgu->num_rows>0){
        while($item=$sorgu->fetch_assoc()){
            $ibn=$item["Iban"];
        }
    }
    $sorgu2=$db->query("select * from money2 where iban='$ibn'");
    if($sorgu->num_rows>0){
        while($item2=$sorgu2->fetch_assoc()){
           $total+=intval($item2["GetMoney"]);
        }
        echo "{$total}";
    }
}




?>