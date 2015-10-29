<?php
	
 if (isset($_POST["name"])) {
    
	// user node
	$response["result"] = array();
	$name = $_POST['name'];
	//$pid='karthi';
		if($name!=null){
			
			$reply = array();
			$reply["reply"] = 'hai '.$name;
			array_push($response["result"], $reply);
			$response["success"] = 1;
			echo json_encode($response);
		}else{
			$response["success"] = 0;
	        $response["message"] = "Required field(s) is missing";
 
               // echoing JSON response
             echo json_encode($response);
			}
        
    
} else {
    // required field is missing
	
	$response["message"] = "Required field(s) is missing";
    $response["success"] = 0;
    // echoing JSON response
    echo json_encode($response);
}
?>
