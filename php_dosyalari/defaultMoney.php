<?php
$db=new mysqli("localhost","u990586664_kinkbank","Eren_652652","u990586664_kingbank");

//$sorgu1=$db->query("select email from actions order by Id desc LIMIT 1");

$email1;

if(isset($_POST["cmail"])){
    $email1=$_POST["cmail"];
    $sorgu1=$db->query("select balance from user where email='$email1'");
    if($sorgu1->num_rows>0){
        while($item = $sorgu1->fetch_assoc()){
            echo $item["balance"];
       
        }
    }
}



?>