<!DOCTYPE html>
<html>
<head>

</head>

<body>

<p>Original name: <span id="origname"></span></p>
<p>New name: <span id="newname"></span></p>

<script>
var employees = [
{ "firstName" : "John" , "lastName" : "Doe" }, 
{ "firstName" : "Anna" , "lastName" : "Smith" }, 
{ "firstName" : "Peter" , "lastName" : "Jones" }, ];

document.getElementById("origname").innerHTML=employees[0].firstName + " " + employees[0].lastName;

// Set new name
employees[0].firstName="Gilbert";
document.getElementById("newname").innerHTML=employees[0].firstName + " " + employees[0].lastName;
</script>


<?php
if(!function_exists('json_decode')) die('JSON not supported.');
else 
echo("JSON Supported.</br>");
$json = 
'{
		"name":"A tour",
		"shortDesc": "Some kind of tour. I am not sure how long this should be.",
		"longDesc":"I do not know what to say right here.",
		"locations":
		[
			{
				"name": "Point One",
				"latitude": 0.23223232,
				"longitude": -1.23232,
				"time": 1390832912.171089,
				"image": "AB8FD927E8B72CB7223..."
			}
		]
	}';
var_dump(json_decode($json));
echo ("</br>");
/*
//create a DB connection
$con = mysql_connect("localhost","root","");
if (!$con)
{
	die('Could not connect: ' . mysql_error());
}
mysql_select_db("pathdb", $con);

$result = json_decode($json);
while($result)
{
    if($value)
	{
		//how to use json array to insert data in Database
        mysql_query("INSERT INTO `path` (title, shortDesc, longDesc) VALUES (`$value->name`, `$value->shortDesc`, `$value->longDesc`)");
    }
}
*/
?>
</body>
</html>
