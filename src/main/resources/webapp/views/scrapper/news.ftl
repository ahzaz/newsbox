<#include "../template/macros.ftl">
<@baseLayout title="NewsByte" css=['admin/style.css'] js=['scrapper/script.js'] adminPage = true>

<div class="col-xs-12">
<form name="form" id="form">    
<#list links?keys as headline>
    <div class="form-group">
        <div class="checkbox">
            <label><input type="checkbox" class="headline" name="headline" value="${links[headline]}"> <a href="${links[headline]}" target="_blank"> ${headline} </a> </label><br />
            <input type="text" name="tags" class="tags form-control" data-role="tagsinput"> <br />
        </div>
    </div>
</#list>
</form>

<br />
<div onclick="scrapNews('DRAFT', '${website}')" class="btn btn-warning">Scrap and Draft</div>
<div onclick="scrapNews('PUBLISHED', '${website}')" class="btn btn-danger">Scrap and Publish</div>
</div>      
</@baseLayout>