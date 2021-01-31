<#include "../template/macros.ftl">
<@baseLayout css=['home/home.css']>
<div class="col-md-8 col-md-offset-1">
    <#-- <#if (context!"news") == "news"> -->
    <@news_list newsList=newsList> </@news_list>
    <#-- </#if> -->
    <div class="row">
        <#if hasPrevious!false>
        <div class="col-xs-2">
            <div class="btn btn-default" onclick="window.location = '/${context}/${currentPage-1}';">Previous</div>
        </div>
        </#if>
        <#if hasNext!false>
        <div class="col-xs-2">
            <div class="btn btn-default link" onclick="window.location ='/{$context}/${currentPage+1}';">Next</div>
        </div>
        </#if>
    </div>
</div>
<div class="col-md-3">
    <hr class="trending hidden-lg hidden-md" /> 
    Tredding News
    <hr/> 
    <@trendingNews newsList=trending![] context=context> </@trendingNews>
</div>

</@baseLayout>