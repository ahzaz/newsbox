<#include "../template/macros.ftl">
<@baseLayout title="NewsByte" css=['scrapper/style.css'] js=['scrapper/script.js'] adminPage = true>
<#list sites?keys as site>    
    <div class="col-md-2 col-xs-12">
        <div class="btn btn-primary btn-block" name="${site}" id="${site}" value="${sites[site].getName()}" onclick="window.location='/su/scrapper/${sites[site].getName()}'"> ${site} </div> <br/>
    </div>
</#list>
</@baseLayout>