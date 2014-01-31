<?php
//CREATE DATABASE CONNECTION, WITH DEBUG.
$con = mysql_connect("localhost","root","group12");
if (!$con)
{
	die('Could not connect: ' . mysql_error());
}
else
{
	echo('Connection Established.');
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
<ul>
	<li class="active"><a href="#">Home</a></li>
	<li><a href="#">Tours</a></li>
	<li><a href="#">About</a></li>
</ul>
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
		?>
			var LatLng = new google.maps.LatLng(<?=$a['latitude']?>,<?=$a['longitude']?>);
			var ContentString = "<b><?=$description['name']?></b></br><?=$description['description']?>";
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
		
		/*
		userroute = [
		new google.maps.LatLng(52.415100,-4.063118),
		new google.maps.LatLng(52.415779,-4.062887),
		new google.maps.LatLng(52.408504,-4.059840),
		new google.maps.LatLng(52.408371,-4.059551),
		new google.maps.LatLng(52.404819,-4.064931),
		new google.maps.LatLng(52.399982,-4.072388),
		new google.maps.LatLng(52.408867,-4.074300),
		new google.maps.LatLng(52.409698,-4.085050),
		new google.maps.LatLng(52.411865,-4.085286),
		new google.maps.LatLng(52.412354,-4.088138),
		new google.maps.LatLng(52.413265,-4.086802),
		new google.maps.LatLng(52.413319,-4.085775),
		new google.maps.LatLng(52.413448,-4.085581),
		new google.maps.LatLng(52.412949,-4.083897),
		new google.maps.LatLng(52.414127,-4.082470),
		new google.maps.LatLng(52.414204,-4.082309),
		new google.maps.LatLng(52.414177,-4.081690),
		new google.maps.LatLng(52.414452,-4.081775),
		new google.maps.LatLng(52.415977,-4.077967),
		new google.maps.LatLng(52.416061,-4.079947),
		new google.maps.LatLng(52.416687,-4.081175),
		new google.maps.LatLng(52.420563,-4.084423),
		new google.maps.LatLng(52.416473,-4.085048),
		new google.maps.LatLng(52.417122,-4.083452),
		new google.maps.LatLng(52.415943,-4.081427),
		new google.maps.LatLng(52.416092,-4.083629),
		new google.maps.LatLng(52.416119,-4.083943),
		new google.maps.LatLng(52.415936,-4.084753),
		new google.maps.LatLng(52.415176,-4.085528),
		new google.maps.LatLng(52.414875,-4.086166),
		new google.maps.LatLng(52.413898,-4.086434),
		new google.maps.LatLng(52.413960,-4.086737),
		new google.maps.LatLng(52.415321,-4.086872),
		new google.maps.LatLng(52.415630,-4.087333),
		new google.maps.LatLng(52.415661,-4.087775),
		new google.maps.LatLng(52.424648,-4.082924)
		];
		*/
		
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
</body>
</html>
