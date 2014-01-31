<?php
//CREATE DATABASE CONNECTION, WITH DEBUG.
$con = mysql_connect("localhost","root","group12");
if (!$con)
{
	die('Could not connect: ' . mysql_error());
}

//pathdb STORES ALL TABLES.
mysql_select_db("pathdb", $con);
?>

<!DOCTYPE html>
<html lang="en-us">
<head>
<title>Main Page</title>
<link rel="stylesheet" href="css/screen.css" type="text/css" media="screen" />
<link rel="stylesheet" href="css/lightbox.css" type="text/css" media="screen" />
<link href='http://fonts.googleapis.com/css?family=Fredoka+One|Open+Sans:400,700' rel='stylesheet' type='text/css'>
<style>
#map-canvas
{
	height: 450px;
	width:950px;
	margin: auto;
	padding: auto;
	box-shadow: 2px 2px 2px 2px #999;
}
</style>
<nav>
<?php
	$options = mysql_query("SELECT * FROM walks");
?>
<form action="index.php" method="post" style="height:15px; float:left;">
<select name="select1"  style="width:134px; float:left; margin-left:10px; margin-top:10px;">
<?php
	while($line = mysql_fetch_array($options))
	{
?>
		<option value="<?php echo $line['title'];?>"> <?php echo $line['title'];?> </option>
<?php
	}
?>
</select>
<input name = "submitbutton" type = "submit" value = "submit" />
</form>
</nav>
		
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCgXd8GzR2kAhJw-fnQqX_ZYpDnBxLLiRw&sensor=false">
</script>
	
<script type="text/javascript">
	var image = 'icon2.png';
	var directionsDisplay;
	var directionsService = new google.maps.DirectionsService();
	
	

	function initialize()
	{
		var mapOptions = {center: new google.maps.LatLng(52.413571,-4.073489), zoom: 14};
		var map = new google.maps.Map(document.getElementById("map-canvas"),mapOptions);
		var infowindow = new google.maps.InfoWindow();
		
		<?php
		$drop = $_POST['select1'];
		$result = mysql_query("SELECT * FROM walks WHERE title = '$drop'");
		$actual = mysql_fetch_array($result);
		$actualpath = $actual['id'];
		//$drop = 1;
		$points = mysql_query("SELECT * FROM location WHERE walkID = '$actualpath'");
		while($a = mysql_fetch_array($points))
		{
			$dataselector = $a['ID'];
			$data = mysql_query("SELECT * FROM placedesc WHERE locationID = '$dataselector'");
			$description = mysql_fetch_array($data);
			
			$getphotos = mysql_query ("SELECT * FROM photos WHERE placeID = '$dataselector'");
			$photograph = mysql_fetch_array($getphotos);
			$data = $photograph['photoName'];
		?>
			var LatLng = new google.maps.LatLng(<?=$a['latitude']?>,<?=$a['longitude']?>);
			var ContentString = "<b><?=$description['name']?></b></br> <?=$description['description']?></br> <img src='icon.2.png";
			//ContentString =
			var marker = new google.maps.Marker(
			{
				map:map,
				draggable:false,
				animation: google.maps.Animation.DROP,
				position: LatLng,
				icon: image
			});
			marker.content = ContentString;
			google.maps.event.addListener(marker, 'click', function(){
			infowindow.setContent(this.content);
			infowindow.open(this.getMap(),this);
			});
		<?php
		}
		?>
		
		var userroute = new Array();
		<?php
		$route = mysql_query("SELECT * FROM location WHERE walkID = '$actualpath'");
		while($getpath = mysql_fetch_array($route))
		{
		?>
			var pathlatlng = new google.maps.LatLng(<?=$getpath['latitude']?>,<?=$getpath['longitude']?>);
			userroute.push(pathlatlng)
		<?php
		}
		?>
		
		var path = new google.maps.Polyline
		({
			path: userroute,
			geodesic: true,
			strokeColor: '#336699',
			strokeOpacity: 0.6,
			strokeWeight:5
		});
		path.setMap(map);
	}
	
	google.maps.event.addDomListener(window, 'load', initialize);
    </script>
</head>

<body>
 <div id="map-canvas"></div>


<div id="content">
 
<div id="wrapper">
<p align="center">

<hr />
</div>
<!-- end #content -->

<script src="js/jquery-1.7.2.min.js"></script>
<script src="js/jquery-ui-1.8.18.custom.min.js"></script>
<script src="js/jquery.smooth-scroll.min.js"></script>
<script src="js/lightbox.js"></script>

<?php


$getphotos = mysql_query ("SELECT * FROM photos");
while($photograph = mysql_fetch_assoc($getphotos))
{
	$data = $photograph['photoName'];
	echo '<img src="data:image/jpg;base64,' . $data . '" />  ';
}
?>

</body>
</html>
