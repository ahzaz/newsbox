<?php

    $validate = isset($_POST['qasdf12d3']) && $_POST['qasdf12d3'] == 'dtwvfbxyt783t478btx7t7b182t1';
    $validate = $validate && isset($_GET['qweefxa']) && $_GET['qweefxa'] == '6sd6a8s';
    $validate = $validate && isset($_POST['message']) && isset($_POST['link']);
    
    if(!$validate) {
        err_log("Invalid access");
        echo "404 Not found";
        exit(1);
    }
    
    session_start();
    require_once __DIR__ . '/vendor/autoload.php';
    $longLivedAccessToken = file_get_contents("/home/prod/php/store/pageToken");
    
    $fb = new Facebook\Facebook([
        'app_id' => '',
        'app_secret' => '',
        'default_graph_version' => 'v2.5',
        'default_access_token' => $longLivedAccessToken,
    ]);

    $url = "newsbyte.info/feed";

    $response = $fb->post($url, [
        "message" => $_POST['message'],
        "link" => $_POST['link'],
    ]);

    echo "0";
?>