<?php

$db=new mysqli("localhost","u990586664_kinkbank","Eren_652652","u990586664_kingbank");
$sayac=0;
if(isset($_POST["email"])&&isset($_POST["limit"])&&isset($_POST["name"])&&isset($_POST["creation"])&&isset($_POST["discard"])){
    $email=$_POST["email"];$name=$_POST["name"];$limit=$_POST["limit"];$creation=$_POST["creation"];$discard=$_POST["discard"];
    $words=str_split($name);
    foreach($words as $word){
        if($word==" ")
            $sayac++;
    }
    if($sayac==1){
        $one=rand(1000,9999);$two=rand(1000,9999);$three=rand(1000,9999);$four=rand(1000,9999);
    $card_num=$one." ".$two." ".$three." ".$four;$cvc=rand(100,999);
    $sorgu=$db->query("insert into cards(email,name,card_number,cvc,limits,discard_date,creation_time) values('$email','$name','$card_num','$cvc','$limit','$discard','$creation')");
    echo "successfully";
    }else
        echo "Failed";
    
}



?>