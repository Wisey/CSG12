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

$thepost=json_decode(file_get_contents('php://input'));
var_dump(json_decode(file_get_contents("php://input")));
echo "</br>";

$file = 'output.txt';
$current = $thepost;
file_put_contents($file, $current);


$jsonDecode=json_decode(file_get_contents("post_data.json"));
print_r($jsonDecode);
echo("</br>");
$pathdata=array();
$x = 0;
 
foreach($jsonDecode as $key => $value)
{
	echo "<p>$key | $value</p>";
	$pathdata[$x]=$value;
	$x++;
}
mysql_query("INSERT INTO walks (title, shortDesc, longDesc) VALUES ('$pathdata[0]', '$pathdata[1]', '$pathdata[2]')");

echo "ARRAY ACCESS";

$pointMarkers = json_decode(file_get_contents("post_data.json"));
$y=0;
foreach($jsonDecode->locations as $mypoints)
{
	foreach($mypoints as $key => $value)
	{
		echo "<p>$key | $value</p>";
		if($key="name")
		{
			$name = $value;
		}
		if($key="description")
		{
			$desc = $value;
		}
		if($key="latitude")
		{
			$lat = $value;
		}
		if($key="longitude")
		{
			$long = $value;
		}
		if($key="time")
		{
			$time = $value;
		}
		if($key="image")
		{
			$img = $value;
		}
		$pointdata[$y]=$value;
		$y++;
	}
	mysql_query("INSERT INTO location (latitude, longitude, timestamp) VALUES ('$pointdata[2]', '$pointdata[3]', '$pointdata[4]')");
	mysql_query("INSERT INTO placedesc (name, description) VALUES ('$pointdata[0]', '$pointdata[1]')");
	mysql_query("INSERT INTO photos (photoName) VALUES ('$pointdata[5]')");
	$y = 0;
}

mysql_close($con);

?>

</body>
</html>
