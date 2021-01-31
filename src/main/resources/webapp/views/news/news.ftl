<#include "../template/macros.ftl">
<#assign og = {"url":"http://newbyte.com/${context}/${news.newsId}/${news.title}", "type":"article", "title":"${news.headline}", "description":"${news.details}", "image":"${news.imageUrl}"}>
<@baseLayout title="NewsByte| ${news.headline}" css=['home/home.css', 'news/style.css'] js=['news/comment-plugin.js'] og = og>
        <div class="col-md-7 col-md-offset-1">
            <div class="row border-bottom">
                <div class="col-xs-12">
                    <h3 class="verdana-fonts headline">${news.headline}</h3>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <span class="news-details">
                        ${news.details}
                    <br/>
                    <#if news.imageUrl?? && news.imageUrl != ''><a class="link" href="${news.source}">Read More</a></#if>
                    </span>
                </div>
            </div>
            <div class="row padded-5">
            <#if news.imageUrl?? && news.imageUrl != ''>
                <div class="col-xs-12">                    
                    <#if context == "news">
                        <div class="news-image" style="background-image: url('${news.imageUrl}');" ></div>
                        <h6><cite>Source:${news.imageUrl}</cite></h6>
                    <#elseif context == "videos">
                        <div> ${news.imageUrl} </div>
                    </#if>
                </div>
            </#if>
            </div>
            <div class="row">
                <div class="col-md-4 col-xs-12">
                    <div class="row">
                        <div class="col-md-4 col-xs-6">
                            <a href="${news.source}" target="_blank"><span class="btn btn-default">Source</span></a>
                        </div>
                        <div class="col-md-4 col-xs-6">
                            <h4 class=""><small><i class="fa fa-eye fa-lg" aria-hidden="true">&nbsp;${news.visits}</i></small></h4>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 col-xs-12 col-md-offset-2">
                        <!-- Go to www.addthis.com/dashboard to customize your tools --> <div class="addthis_inline_share_toolbox"></div>                     
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 col-xs-12">
                    <h4>
                    <#list news.tags as tag>
                        <a href="/tags/${tag.name}"><span class="label label-primary link">${tag.name}</span></a>
                    </#list>
                    </h4>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12">
                <div class="fb-comments" data-href="http://newsbyte.info/news/${news.newsId}/${news.title}" data-numposts="10" ></div>
                <div id="fb-root"></div>
<#--                     <div id="disqus_thread"></div>
                    <script type="text/javascript">
                        var _page_identifier = '${news.newsId}-${news.title}'
                    </script>
                        <noscript>Please enable JavaScript to view the <a href="https://disqus.com/?ref_noscript">comments powered by Disqus.</a></noscript> -->
                </div>
            </div>  
        </div>
        <div class="col-md-3">
            <hr class="trending hidden-lg hidden-md" /> 
            Tredding News
            <hr/> 
            <@trendingNews newsList=trending![] context=context> </@trendingNews>
        </div>

<#if urlRewrite!false >
<script type="text/javascript">
    window.history.replaceState({}, "${news.title}", encodeURIComponent("${news.title}"));
</script>
</#if>
</@baseLayout>