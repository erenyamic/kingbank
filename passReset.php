<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <style>
        input{
            display:block;
            margin:5px;
        }
    </style>
</head>
<body>
    <form action="https://cryptoymc.com/passReset.php" method="post">
        <input type="text" name="rcode" placeholder="Reset Code">
        <input type="password" name="pass1" placeholder="password">
        <input type="password" name="pass2" placeholder="password again">
        <input type="submit" name="btn" value="Save">
    </form>
    
    <?php
        if(isset($_POST["btn"])){
            $db=new mysqli("localhost","u990586664_kinkbank","Eren_652652","u990586664_kingbank");
            if(isset($_POST["pass1"])&&isset($_POST["pass2"])&&isset($_POST["rcode"])){
            if($_POST["pass1"]==$_POST["pass2"]){
                $rcode=$_POST["rcode"];
                $var_mi=$db->query("select * from user where resetCode='$rcode'");
                if($var_mi->num_rows>0){
                    $newPass=$_POST["pass1"];
                    $updatePass=$db->query("update user set password='$newPass' where resetCode='$rcode'");
                }
            }else
                alert("Did not match");
        }
        }
        

?>
</body>
</html>