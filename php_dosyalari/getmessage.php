<?php
$db=new mysqli("localhost","u990586664_kinkbank","Eren_652652","u990586664_kingbank");

if(isset($_POST["email"])){
    $email1=$_POST["email"];
    $sorgu1=$db->query("select * from support");
    if($sorgu1->num_rows>0){
        while($item = $sorgu1->fetch_assoc()){
            $name=explode("@",$item["email"]);
            echo "Sender: ".$name[0]."\nMessage: ".$item["message"]."\n---\n";
       
        }
    }
}

?>