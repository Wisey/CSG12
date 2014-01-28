<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
	html { height: 100% }
	body { height: 100%; margin: 0; padding: 0 }
	#map-canvas { height: 100% }
	</style>
	<script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCgXd8GzR2kAhJw-fnQqX_ZYpDnBxLLiRw&sensor=false">
	</script>
	
    <script type="text/javascript">
	var image = 'icon2.png';
	var directionsDisplay;
	var directionsService = new google.maps.DirectionsService();
	
	<?php
	$con = mysql_connect("localhost","root","group12");
		if (!$con)
		{
			die('Could not connect: ' . mysql_error());
		}
		mysql_select_db("pathdb", $con);
	?>
	
	
	
	
	function calcRoute()
	{
		
		var waypts = [];	
			waypts.push({
			location: new google.maps.LatLng(52.399982,-4.072388),
			stopover:false});
			waypts.push({
			location: new google.maps.LatLng(52.408867,-4.074300),
			stopover:false});
			waypts.push({
			location: new google.maps.LatLng(52.409698,-4.085050),
			stopover:false});
			waypts.push({
			location: new google.maps.LatLng(52.416687,-4.081175),
			stopover:false});
			waypts.push({
			location: new google.maps.LatLng(52.416092,-4.083629),
			stopover:false});
			waypts.push({
			location: new google.maps.LatLng(52.415176,-4.085528),
			stopover:false});
			waypts.push({
			location: new google.maps.LatLng(52.413960,-4.086737),
			stopover:false});
			waypts.push({
			location: new google.maps.LatLng(52.415661,-4.087775),
			stopover:false});
		
		<?php
		$startpoint = mysql_query("SELECT * FROM points WHERE `order` = 1");
		$starter = mysql_fetch_array($startpoint);
		$endpoint = mysql_query("SELECT * FROM points WHERE `order` = 36");
		$ender = mysql_fetch_array($endpoint);
		?>
		
		var start = new google.maps.LatLng(<?=$starter['lat']?>,<?=$starter['long']?>);
		var end = new google.maps.LatLng(<?=$ender['lat']?>,<?=$ender['long']?>)
		
		var request=
		{
			origin: start,
			destination: end,
			waypoints: waypts,
			optimizeWaypoints: true,
			travelMode: google.maps.TravelMode.WALKING
		};
		directionsService.route(request, function(response, status)
		{
			if (status == google.maps.DirectionsStatus.OK)
			{
				directionsDisplay.setDirections(response);
				directionsDisplay.setOptions({suppressMarkers: true});
			}
		});
		
		
		/*
		<?php
		$allpaths = mysql_query("SELECT * FROM points");
		while($allroutes = mysql_fetch_array($allpaths))
		{
		?>
		var request=
		{
			origin: new google.maps.LatLng(<?=$allroutes['lat']?>,<?=$allroutes['long']?>),
			destination: end,
			travelMode: google.maps.TravelMode.WALKING
		};
		directionsService.route(request, function(response, status)
		{
			if (status == google.maps.DirectionsStatus.OK)
			{
				directionsDisplay.setDirections(response);
				directionsDisplay.setOptions({suppressMarkers: true});
			}
		});
		<?php
		}
		?>
		
		 var flightPlanCoordinates = [
    new google.maps.LatLng(37.772323, -122.214897),
    new google.maps.LatLng(21.291982, -157.821856),
    new google.maps.LatLng(-18.142599, 178.431),
    new google.maps.LatLng(-27.46758, 153.027892)
  ];
  var flightPath = new google.maps.Polyline({
    path: flightPlanCoordinates,
    geodesic: true,
    strokeColor: '#FF0000',
    strokeOpacity: 1.0,
    strokeWeight: 2
  });

		
		*/
		
		
		
	}
	
	
	
	function initialize()
	{
		var mapOptions = {center: new google.maps.LatLng(52.413571,-4.073489), zoom: 14};
		var map = new google.maps.Map(document.getElementById("map-canvas"),mapOptions);
		var infowindow = new google.maps.InfoWindow();
		
		window.directionsService = new google.maps.DirectionsService();
		window.directionsDisplay = new google.maps.DirectionsRenderer();
		directionsDisplay.setMap(map);
		
		<?php
		$res = mysql_query("SELECT * FROM points");
		while($a = mysql_fetch_array($res))
		{
		?>
			var LatLng = new google.maps.LatLng(<?=$a['lat']?>,<?=$a['long']?>);
			var ContentString = "<b><?=$a['shortDesc']?></b></br><?=$a['longDesc']?>";
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
		calcRoute();
	}
	
	google.maps.event.addDomListener(window, 'load', initialize);
    </script>
</head>
<body>
	<div id="map-canvas"/>
</body>
</html>