<!doctype html>
<html lang="en-us">
<head>

	<meta charset="utf-8">
	<title>CS Group 12: Walking Tour</title>
	<link rel="stylesheet" href="css/screen.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="css/lightbox.css" type="text/css" media="screen" />

  <link href='http://fonts.googleapis.com/css?family=Fredoka+One|Open+Sans:400,700' rel='stylesheet' type='text/css'>
	
</head>
<nav><ul>
			<li class="active"><a href="#">Home</a></li>
			<li><a href="#">Tours</a></li>
			<li><a href="#">About</a></li>
		</ul></nav>
		<div id="footer"></div>

<body>

<div id="content">
<h1>CS Group 12: Walking Tour</h1>
  <div class="map">
	<iframe width="950px" height="450px" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://maps.google.com/maps?f=q&amp;source=s_q&amp;hl=en&amp;geocode=&amp;q=Aberystwyth,+United+Kingdom&amp;aq=0&amp;oq=aberytwyth&amp;sll=37.0625,-95.677068&amp;sspn=45.418852,79.013672&amp;ie=UTF8&amp;hq=&amp;hnear=Aberystwyth,+Ceredigion,+United+Kingdom&amp;t=m&amp;ll=52.415299,-4.08288&amp;spn=0.026176,0.068579&amp;z=14&amp;iwloc=A&amp;output=embed"></iframe><br />&nbsp;</div>
<div id="wrapper">
<p align="center">
<div class="section" id="example">
	
	<div class="imageRow">
  	<div class="set">
	
<?php
include('config.php');
$result = mysql_query("SELECT * FROM photos1");
while($row = mysql_fetch_array($result))
{
	
  	 echo '<div class="single"><div class="wrap">
  		  <a href="'.$row['location'].'" rel="lightbox[plants]" title="'.$row['caption'].'"><img src="'.$row['location'].'" alt="Plants: image 1 0f 4 thumb" /></a>
  		</div></div>';
}				
?>		

  </div>
  </div>
	</div>
</div>

<hr />
</div>
<!-- end #content -->

<script src="js/jquery-1.7.2.min.js"></script>
<script src="js/jquery-ui-1.8.18.custom.min.js"></script>
<script src="js/jquery.smooth-scroll.min.js"></script>
<script src="js/lightbox.js"></script>

<script>
  jQuery(document).ready(function($) {
      $('a').smoothScroll({
        speed: 1000,
        easing: 'easeInOutCubic'
      });

      $('.showOlderChanges').on('click', function(e){
        $('.changelog .old').slideDown('slow');
        $(this).fadeOut();
        e.preventDefault();
      })
  });

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-2196019-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
</body>
</html>