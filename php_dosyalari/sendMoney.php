<?php


$db=new mysqli("localhost","u990586664_kinkbank","Eren_652652","u990586664_kingbank");
if
(isset($_POST["ibn"])&&isset($_POST["send"])&&isset($_POST["email"])&&isset($_POST["comment"])&&isset($_POST["date"])){
    $ibn=$_POST["ibn"];$sendMoney=intval($_POST["send"]);$email=$_POST["email"];$comment=$_POST["comment"];
    $date=$_POST["date"];
    $sorgu=$db->query("select balance from user where email='$email'");
    if($sorgu->num_rows>0){
       while($item = $sorgu->fetch_assoc()){
            $money= intval($item["balance"]);
       
        }
        if($money>=0&&$sendMoney<=$money){
            $money-=$sendMoney;
            $sorgu2=$db->query("update user set balance='$money' where email='$email'");
            $sorgu3=$db->query("insert into money(email,iban,SendMoney,commentt,Date) values('$email','$ibn','$sendMoney','$comment','$date')");
            echo "successfully";
        }
    }
    
}




?>