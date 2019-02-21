<?php
include("../res/x5engine.php");
$nameList = array("6jd","8et","cde","3mv","3ca","la6","gjw","82y","tk7","sjl");
$charList = array("4","J","E","N","N","M","K","W","H","F");
$cpt = new X5Captcha($nameList, $charList);
//Check Captcha
if ($_GET["action"] == "check")
	echo $cpt->check($_GET["code"], $_GET["ans"]);
//Show Captcha chars
else if ($_GET["action"] == "show")
	echo $cpt->show($_GET['code']);
// End of file x5captcha.php
