<?php
$total=0;
$db=new mysqli("localhost","u990586664_kinkbank","Eren_652652","u990586664_kingbank");
if(isset($_POST["email"])){
    $email=$_POST["email"];
    $sorgu=$db->query("select * from money where email='$email'");
    if($sorgu->num_rows>0){
        while($item=$sorgu->fetch_assoc()){
            $total+=intval($item["SendMoney"]);
        }
        echo "{$total}";
    }
}





?>