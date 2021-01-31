<#macro news_list newsList=[]>
    <#assign counter = 1>
    <#list newsList as news>        
            <div class="news-item">        
            <div class="row">
                <#-- <div class="col-md-1 bg-img" style="background-image: url('${news.imageUrl!news.defaultImage}')"> -->
                    <#-- <img src="${news.imageUrl!news.defaultImage}" /> -->
                <#-- </div> -->
                <div class="col-md-12">
                    <div class="row">
                        <div class="col-xs-9">
                            <div class="row">
                                <div class="col-md-12">
                                    <span class="text-bold"> <a href="${news.getUrl()}" class="headline-text">${news.shortenHeadline()}</a></span>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-3">
                                    <h5><small>${news.publishDate?string["dd-MMM-yy HH:mm"]}</small></h5>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-3">
                            <div class="row">
                                <div class="col-xs-12">
                                    <#if adminLoggedIn!false>
                                    <script type="text/javascript" src="/static/js/admin/home.js"></script>
                                    <span class="pull-right text-bold"> 
                                        <a href="/su/edit/${news.newsId}" class="link read-more"> Edit </a>
                                        <a href="#" class="link read-more red-text" onclick="confirmDelete('${news.headline}',${news.newsId});"> Delete </a>
                                    </span>
                                    <#else>
                                    <span class="pull-right text-bold"> 
                                        <a href="${news.getUrl()}" class="link read-more"> Read More </a>
                                    </span>                            
                                    </#if>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-6 col-md-3 col-md-offset-6">
                                    <#-- <h5 class="pull-right"><small><i class="fa fa-share-alt-square fa-lg" aria-hidden="true">&nbsp;${news.shareCount}</i></small></h5> -->
                                </div>                            
                                <div class="col-xs-6 col-md-3">
                                    <h5 class="pull-right"><small><i class="fa fa-eye fa-lg" aria-hidden="true">&nbsp;${news.visits}</i></small></h5>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row tags">
                        <div class="col-md-12">
                            <#list news.tags as tag>
                            <a href="/tags/${tag.name}"><span class="label label-primary link">${tag.name}</span></a>
                            </#list>                        
                        </div>
                    </div>
                </div>
            </div>
            </div>
        <#if counter % 4 == 0>
            <#-- <@news_advert></@news_advert> -->
        </#if>
        <#assign counter++>
    </#list>
</#macro>