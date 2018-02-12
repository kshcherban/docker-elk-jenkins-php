<?php
header('content-type: application/json; charset=utf-8');

//simple counter to test sessions. should increment on each page reload.
session_start();
$count = isset($_SESSION['count']) ? $_SESSION['count'] : 1;

//echo $count;

$_SESSION['count'] = ++$count;

$date = date("Y-m-d H:i:s");

$info = array(
    "ip"=>$_SERVER['REMOTE_ADDR'],
//    "sessions"=>$count,
    "date"=>$date);

$data = json_encode($info);

echo $data;

?>
