<!DOCTYPE html>
<html>
<head>
<title>JSON Parsing Tool</title>
</head>

<body>

<?php
if(!function_exists('json_decode')) die('JSON not supported.');
else 
echo("JSON Supported.</br>");



//-------------------------------------------------------------------------------
//create a DB connection
$con = mysql_connect("localhost","root","group12");
if (!$con)
{
	die('Could not connect: ' . mysql_error());
}
else
{
	echo('Connection Established.</br></br>');
}
mysql_select_db("pathdb", $con);
//-------------------------------------------------------------------------------

$thepost=json_decode(file_get_contents('php://input'), true);
var_dump(json_decode(file_get_contents("php://input")));
echo "</br>";

$file = 'output.txt';
$current = $thepost;
file_put_contents($file, $current);


$jsonDecode=json_decode(file_get_contents("post_data.json"));
print_r($jsonDecode);
echo("</br>");
$result=array();
 
foreach($jsonDecode as $key => $value)
{
	echo "<p>$key | $value</p>";
}

?>

</body>
</html>
