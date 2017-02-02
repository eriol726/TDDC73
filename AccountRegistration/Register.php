<?php
    $con = mysqli_connect("localhost", "id646804_eriol726", "abcd1234", "id646804_accountdb") or die('Unable to Connect');
    

    $name = $_POST["name"];
    $age = $_POST["age"];
    $username = $_POST["username"];
    $password = $_POST["password"];
    $passwordScoreString = $_POST["passwordScore"];
    $passwordScore = (int)$passwordScoreString;

    $sql = "SELECT * FROM user WHERE username='$username'";

    $res =  mysqli_query($con,$sql);
    //$resRes mysql_fetch_row($resRes);

    $response = array();
    $response["success"] = false;
    $response["usernameExist"] = false;
    $response["fieldEmpty"] = false;
    $response["weakPassword"] = false;
    if(mysqli_num_rows($res) > 0){
    //  echo '{"message":"userAlreadyExists"}';
        $response["usernameExist"] = true;  
    }
    else if($username == "" || $name == "" || $age == "" || $password == "" ){
        $response["fieldEmpty"] = true;
    }
    else if($passwordScore < 40){
        $response["weakPassword"] = true;
    }
    else{
   
        $statement = mysqli_prepare($con, "INSERT INTO user (name, username, age, password) VALUES (?, ?, ?, ?)");
        mysqli_stmt_bind_param($statement, "ssis", $name, $username, $age, $password);
        mysqli_stmt_execute($statement);
        $response["success"] = true;
    }
         
    echo json_encode($response); 

?>
    
?>