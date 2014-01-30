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
	$con = mysql_connect("localhost","root","");
		if (!$con)
		{
			die('Could not connect: ' . mysql_error());
		}
		mysql_select_db("pathdb", $con);
	?>

	function initialize()
	{
		var mapOptions = {center: new google.maps.LatLng(52.413571,-4.073489), zoom: 14};
		var map = new google.maps.Map(document.getElementById("map-canvas"),mapOptions);
		var infowindow = new google.maps.InfoWindow();
		
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
		pathco = [
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
		
		var path = new google.maps.Polyline
		({
			path: pathco,
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
	<div id="map-canvas"/>
</body>
</html>
