<?php
$dbhost	= "localhost";
$dbuser	= "root";
$dbpass	= "group12";
$dbname	= "pathdb";

	$bd = mysql_connect($dbhost, $dbuser, $dbpass) or die ("Error connecting to database");
	mysql_select_db($dbname) or die ("Error selecting the database");?>
