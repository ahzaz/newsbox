<?php

session_start();    
    require_once __DIR__ . '/vendor/autoload.php';

$fb = new Facebook\Facebook([
  'app_id' => '',
  'app_secret' => '',
  'default_graph_version' => 'v2.5',
]);


 $helper = $fb->getRedirectLoginHelper();
 $permissions = ['manage_pages,  publish_pages']; // optional
 $loginUrl = $helper->getLoginUrl('http://newsbyte.info/php/handle.php', $permissions);
 
 echo "<a href=\"$loginUrl\"> Login </a>";


?>
