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

<p>Path details: <span id="newpath"></span></p>
<script>
var path =
[
	{
		"title":"Shops"
		"shortDesc":"Shopping"
		"longDesc":"I need more pizza."
	}
	document.getElementById("newpath").innerHTML=path[0].title + " " + path[0].shortDesc + " " + path[0].longDesc;
]
</script>

<script>
var points =
[
	{
		
	}
]
</script>

<?php
if(!function_exists('json_decode')) die('JSON not supported.');
else 
echo("JSON Supported.</br>");
//create a DB connection
$con = mysql_connect("localhost","root","group12");
if (!$con)
{
	die('Could not connect: ' . mysql_error());
}
else
{
	echo('Connection Established.</br>');
}
mysql_select_db("pathdb", $con);
?>

</body>
</html>
