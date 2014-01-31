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
 
foreach($thepost as $key => $value)
{
	echo "<p>$key | $value</p>";
	$pathdata[$x]=$value;
	$x++;
}
mysql_query("INSERT INTO walks (title, shortDesc, longDesc) VALUES ('$pathdata[0]', '$pathdata[1]', '$pathdata[2]')");
$getpathID = mysql_query("SELECT * FROM walks WHERE title = '$pathdata[0]'");
$fetchpathID = mysql_fetch_array($getpathID);
$pathID = $fetchpathID['id'];
echo "ARRAY ACCESS";

$pointMarkers = json_decode(file_get_contents("post_data.json"));
$y=0;
foreach($thepost->locations as $mypoints)
{
	foreach($mypoints as $key => $value)
	{
		echo "<p>$key | $value</p>";
		$pointdata[$y]=$value;
		$y++;
	}
	mysql_query("INSERT INTO location (walkID, latitude, longitude, timestamp) VALUES ('$pathID','$pointdata[2]', '$pointdata[3]', '$pointdata[4]')");
	
	$getlocationID = mysql_query("SELECT * FROM location WHERE latitude = '$pointdata[2]'");
	$fetchlocationID = mysql_fetch_array($getlocationID);
	$locationID = $fetchlocationID['ID'];
	
	mysql_query("INSERT INTO placedesc (locationID, name, description) VALUES ('$locationID', '$pointdata[0]', '$pointdata[1]')");
	
	
	mysql_query("INSERT INTO photos (placeID, photoName) VALUES ('$locationID','$pointdata[5]')");
	$y = 0;
}
$a = 0;
$max = 0;
foreach($thepost->waypoint_long as $interlongs)
{
	foreach($interlongs as $value)
	{
		echo "<p>$value</p>";
		$wayptlong[$a]=$value;
		$a++;
		$max = a;
	}
}
$a = 0;
foreach($thepost->waypoint_lat as $interlats)
{
	foreach($interlats as $value)
	{
		echo "<p>$value</p>";
		$wayptlong[$a]=$value;
		$a++;
	}
}
for(a=0; a<$max; a++)
{
	mysql_query("INSERT INTO location (latitude, longitude) VALUES ('$wayptlong[a]', '$wayptlat[a]')");
	a++;
}

mysql_close($con);

?>

</body>
</html>
