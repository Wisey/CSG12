<!DOCTYPE html>
<html>
<head>
</head>

<body>
<?php


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

$getphotos = mysql_query ("SELECT * FROM photos");
while($photograph = mysql_fetch_array($getphotos))
{
	$data = $a['photoName'];
	echo '<img src="data:image/jpg;base64,' . $data . '" />';
}

?>
</body>
