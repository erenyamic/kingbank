<?php
$db=new mysqli("localhost","u990586664_kinkbank","Eren_652652","u990586664_kingbank");

if(isset($_POST["log_email"]) && isset($_POST["log_pass"])){
    $log_email=$_POST["log_email"];
    $log_pass=$_POST["log_pass"];
    $var_mi=$db->query("select * from user where email='$log_email'");
    if($var_mi->num_rows>0){
        while($item=$var_mi->fetch_assoc()){
            if($item["password"]==$log_pass)
                echo "Successfully";
            else
                echo "Wrong Password";
        }
        
        
    }else
        echo "Failed";
}
?>