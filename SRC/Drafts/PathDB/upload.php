<!DOCTYPE html>
<html>
<head>
<title>JSON Parsing Tool</title>
</head>

<body>

<?php
error_log("Hi, dude");

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

error_log("sql works");

$thepost=json_decode(file_get_contents('php://input'));
var_dump(json_decode(file_get_contents("php://input")));
echo "</br>";

error_log("json decoding");

$file = 'output.txt';
$current = $thepost;
file_put_contents($file, $current);


$jsonDecode=json_decode(file_get_contents("post_data.json"));
print_r($jsonDecode);
echo("</br>");
$pathdata=array();
$short = "";
$name = "";
$long = "";
$x = 0;
 
foreach($thepost as $key => $value)
{
	echo "<p>$key | $value</p>";
	$pathdata[$x]=$value;
	if ($key == "name") {
		$name = $value;
	}
	if ($key == "short-description") {
		$short = $value;
	}
	if ($key == "long-description") {
		$long = $value;
	}
	$x++;
}
mysql_query("INSERT INTO walks (title, shortDesc, longDesc) VALUES ('$name', '$short', '$long')");
//$getpathID = mysql_query("SELECT * FROM walks WHERE title = '$name'");
//$fetchpathID = mysql_fetch_array($getpathID);
$pathID = mysql_insert_id();
error_log("inserted walk");

//$pointMarkers = json_decode(file_get_contents("post_data.json"));
//$y=0;

foreach($thepost->locations as $mypoints)
{
	 error_log("location");
	 $desc = "";
         $name = "";
         $lat = 0;
         $long = 0;
         $time = 0;
         $image = "";
         
	foreach($mypoints as $key => $value)
	{
		error_log("key: '$key'");
		echo "<p>$key | $value</p>";
		$pointdata[$y]=$value;
		$y++;
		
		if ($key == "description") {
			$desc = $value;
		}
		if ($key == "name") {
			$name = $value;
		}
		if ($key == "latitude") {
			$lat = $value;
		}
		if ($key == "longitude") {
			$long = $value;
		}
		if ($key == "time") {
			$time = $value;
		}
		if ($key == "image") {
			$image = $value;
		}
	}
	$q = "INSERT INTO location (walkID, latitude, longitude, timestamp) VALUES ('$pathID','$lat', '$long', '$time')"
	mysql_query($q);
	
	//$getlocationID = mysql_query("SELECT * FROM location WHERE time = '$time'");
	//$fetchlocationID = mysql_fetch_array($getlocationID);
	//$locationID = $fetchlocationID['ID'];
	$locationID = mysql_insert_id();
	
	mysql_query("INSERT INTO placedesc (locationID, name, description) VALUES ('$locationID', '$name', '$desc')");
	
	
	mysql_query("INSERT INTO photos (placeID, photoName) VALUES ('$locationID','$image')");
	$y = 0;
}
$a = 0;
$wayptlong = array();
foreach($thepost->waypoint_long as $value)
{
	echo "<p>$value</p>";
	$wayptlong[$a]=$value;
	$a++;
}

$a = 0;
$wayptlat = array();
foreach($thepost->waypoint_lat as $value)
{
	echo "<p>$value</p>";
	$wayptlat[$a]=$value;
	$a++;
}

for($b=0; $b<$a; $b++)
{
	$q = "INSERT INTO location (walkID, latitude, longitude) VALUES ('$pathID','$wayptlat[$b]', '$wayptlong[$b]')";
	error_log($q);
	mysql_query($q);
}

mysql_close($con);

?>

</body>
</html>
