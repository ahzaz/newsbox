<?php
error_reporting(E_ALL);    
ini_set("display_errors", 1);

session_start();
    require_once __DIR__ . '/vendor/autoload.php';

$fb = new Facebook\Facebook([
  'app_id' => '791902174319599',
  'app_secret' => '97651508e8c2895677a1c020b40dd2b8',
  'default_graph_version' => 'v2.5',
]);

$oAuth2Client = $fb->getOAuth2Client();

$shortToken = file_get_contents("/home/prod/php/store/shortToken");


// Exchanges a short-lived access token for a long-lived one
$longLivedAccessToken = $oAuth2Client->getLongLivedAccessToken($shortToken);

echo $longLivedAccessToken;

file_put_contents("/home/prod/php/store/longToken", $longLivedAccessToken);


?>
