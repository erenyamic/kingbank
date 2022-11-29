<?php


$db=new mysqli("localhost","u990586664_kinkbank","Eren_652652","u990586664_kingbank");
if(isset($_POST["email"])){
    $email=$_POST["email"];$password=$_POST["password"];
    $iban=$_POST["iban"];$resetCode=$_POST["resetCode"];
    $sorgu1=$db->query("select * from user where email='$email'");
    if($sorgu1->num_rows==0){
        $sorgu2=$db->query("insert into user(email,password,Iban,resetCode,balance) values('$email','$password','$iban','$resetCode','100000')");
        echo "Successfully";
    }else
        echo "Email is already registered";
}


?>