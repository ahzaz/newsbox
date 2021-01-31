<?php
error_reporting(E_ALL);    
ini_set("display_errors", 1);

session_start();
    require_once __DIR__ . '/vendor/autoload.php';

$longLivedAccessToken = file_get_contents("/home/prod/php/store/longToken");

$fb = new Facebook\Facebook([
  'app_id' => '',
  'app_secret' => '',
  'default_graph_version' => 'v2.5',
  'default_access_token' => $longLivedAccessToken,
]);

$url = "https://graph.facebook.com/newsbyte.info?fields=access_token";

$response = $fb->get('newsbyte.info?fields=access_token');

echo $response->getBody();
echo '<hr><br/>';
print_r($response->getDecodedBody());


?>
