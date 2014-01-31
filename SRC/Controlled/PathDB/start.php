<?php
	$con = mysqli_connect("csgp12_13_14", "csadmgp12", "rs85OZHZ");
	if (!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
	
	$sql = 'CREATE DATABASE pathdb';
	if (mysql_query($sql, $con))
	{
		echo "Smoke weed err'day \n It's the one and only D-O-Double-G SNOOP DOOOOOOOOG.\n";
	}
	else
	{
		echo 'Connection Failed: ' . mysql_error() . "\n";
	}
	
	create table points
	(
	
	)
	
	//SERVER
	//->Apache/PHP, MySQL.
	//Port 80 for apache.
	//1212
	//PHP 5
?>

