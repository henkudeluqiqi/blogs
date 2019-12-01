?php
// EDIT THE 2 LINES BELOW AS REQUIRED
$send_email_to = "mshahbazsaleem@gmail.com";
$email_subject = "Your email subject line";

//GET FIELDS FROM YOUR HTML FORM, THE NAME PROPERTY IS PASSED IN THE $_POST ARRAY
//E.G IF YOU HAVE A FIELD WITH name="email" YOU'LL GET THE VALUE IN PHP SCRIPT
//LIKE BELOW
$email = $_POST['email'];
 
$name = $_POST['name'];
$phone = $_POST['phone'];
 
$headers = "MIME-Version: 1.0" . "\r\n";
$headers .= "Content-type:text/html;charset=iso-8859-1" . "\r\n";
$headers .= "From: ".$email. "\r\n";

/*Customize your message here*/  
$message = "<strong>Email = </strong>".$email."<br>";
$message .= "<strong>Name = </strong>".$name."<br>";  

if(!empty($phone))
    $message .= "<strong>Phone = </strong>".$phone."<br>";
 
$message .= "<strong>Message = </strong>Formify Sample Message!<br>";

@mail($send_email_to, $email_subject, $message,$headers);
 
header('Content-type: text/json');
$return_array['success'] = '1';
echo json_encode($return_array);
die();
?>

