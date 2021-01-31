<#macro baseLayout title="NewsByte | Your daily dose of news" css=[] js=[] company={} displayFooter=true
        noIndex=true og={} adminPage=false>
<#assign contexts = ["news", "videos"]>
    <!DOCTYPE html>
    <html>
    <head>

        <title>${title}</title>

        <#if !adminPage>
            <script>
                  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
                  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
                  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
                  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');
                ga('create', 'UA-96854725-1', 'auto');
                ga('send', 'pageview');
            </script>    
        </#if>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <#if noIndex>
            <meta name="robots" content="noindex, nofollow">
        </#if>
        <#-- For Facebook -->
        <#if og.url ??>
            <meta property="og:url" content="${og.url}" />
            <meta property="og:type" content="${og.type}" />
            <meta property="og:title" content="${og.title}" />
            <meta property="og:description" content="${og.description}" />
            <meta property="og:image" content="${og.image}" />        
        </#if>
        <#--  For detailed help: https://www.w3.org/2005/10/howto-favicon -->
            <#-- <link rel="stylesheet" href="/static/css/lib/bootstrap.min.css"> -->
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/css/bootstrap.min.css">
            <#-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/css/bootstrap-theme.min.css"> -->
            <link rel="stylesheet" href="/static/css/lib/font-awesome.min.css">
            <link rel="stylesheet" href="/static/css/lib/bootstrap-tagsinput.css">
            <link rel="stylesheet" href="/static/css/bootstrap-override.css">
            <link rel="stylesheet" href="/static/css/common.css">
            <#list css as cssScript>
                <link rel="stylesheet" href="/static/css/${cssScript}">
            </#list>
            </head>
    <body>

    <#-- NOTE: `currentDevice` value is injected into requestAttribute by `org.springframework.mobile.device.DeviceResolverHandlerInterceptor` defined in `dispatcher-servlet.xml`-->

    <#-- True if this device is a mobile device such as an Apple iPhone or an Nexus One Android. -->
    <#-- <#assign isMobile = currentDevice.isMobile() /> -->
    <#assign isMobile = false />

    <#-- True if this device is not a mobile or tablet device. -->
    <#-- <#assign isNormal = currentDevice.isMobile() /> -->

    <#-- True if this device is a tablet device such as an Apple iPad or a Motorola Xoom. -->
    <#-- <#assign isTablet = currentDevice.isMobile() /> -->

    <#-- Global DIV container for alert -->
    <div id="alertContainer" class="alertContainer">
    </div>

    <#-- <div class="container-fluid"> -->
        <#include "navbar.ftl" />
    <#-- </div> -->
    <div class="container-fluid">
        <div class="bodyContainer <#if isMobile>container</#if>">
            <div class="row">
                <#nested />
            </div>
        </div>
    </div>
    <div class="footer">    
        <#include "footer.ftl" />
    </div>        
    
    <script type="text/javascript" src="/static/js/lib/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="/static/js/lib/bootstrap.min.js"></script>
    <#if adminPage>
            <script type="text/javascript" src="/static/js/lib/bootstrap-tagsinput.js"></script>
            <script type="text/javascript" src="/static/js/lib/typeahead.bundle.min.js"></script>
            <script type="text/javascript" src="/static/js/lib/fs-jquery-plugin.min.js"></script>
    </#if>    
    <#-- <script type="text/javascript" src="/static/js/util.js"></script> -->
    <#list js as jsScript>
        <script type="text/javascript" src="/static/js/${jsScript}"></script>
    </#list>
    <#if !adminPage>
    <!-- Go to www.addthis.com/dashboard to customize your tools --> 
    <script type="text/javascript" src="//s7.addthis.com/js/300/addthis_widget.js#pubid=ra-58e0c666e5b80cdb" async></script>   
    </#if>

    </body>
    </html>
</#macro>

<#macro news_advert>
<div class="news-add news-item">
    <div class="row">
        <div class="col-md-12">
            Your Ad here
        </div>
    </div>
</div>
</#macro>

<#macro trendingNews newsList=[] context="news">
    <#list newsList as news>
        <ul class="list-unstyled">
            <li>
                <#if context == "news">
                    
                    <div style="background-image: url('${news.imageUrl}'); width:100%; height: 128px;   background-repeat: no-repeat;background-size: cover;" >  
                        <a href="${news.getUrl()}">  ${news.headline} </a>
                    </div>
                    
                    <#-- <img src="${news.imageUrl}" width="64" height="64">  <a href="${news.getUrl()}">${news.trendingHeadline()}</a> -->
                <#else>
                    <a href="${news.getUrl()}">${news.trendingHeadline()}</a>
                </#if>

            </li>
        </ul>
    </#list>
</#macro>