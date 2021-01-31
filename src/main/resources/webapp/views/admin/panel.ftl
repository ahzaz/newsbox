<#include "../template/macros.ftl">
<@baseLayout css=['home/home.css', 'admin/style.css'] js=['admin/script.js'] adminPage=true>
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">Add New News</h3>
                </div>
                <div class="panel-body">
                    <form name="newNews" id="newNews">
                    <div class="form-group">
                        <#if news??>
                            <input type="hidden" name="newsId" value="${news.newsId}">
                            <input type="hidden" name="visits" value="${news.visits}">
                            <input type="hidden" name="title" value="${news.title}">
                        </#if>
                        <label for="type">Post Type:</label>
                        <select name="type" class="form-control">
                            <#list contexts as type>
                                <option value=${type?upper_case}> ${type?cap_first} </option>
                            </#list>
                        </select>                    
                        
                        <label for="headline">Headline:</label>
                        <input type="text" id="headline" name="headline" placeholder="Headline" class="form-control" required="true" value="${(news.headline)!}">
                        
                        <label for="details">News:</label>
                        <textarea type="text" rows="10" id="details" name="details" placeholder="News here" class="form-control">${(news.details)!}</textarea>

                        <label for="imageUrl">Image/Video:</label><br/>
                        <input type="text" id="imageUrl" name="imageUrl" class="form-control"  value="${(news.imageUrl)!}">
                        
                        <label for="tags">Tags:</label><br/>
                        <#assign tagsMaybe = (news.tags)!>
                        <#assign tagString = tagsMaybe ? join(",")>
                        <input type="text" id="tags" name="tags" data-role="tagsinput form-control" value="${tagString!}"><br/>

                        <label for="source">Source:</label> 
                        <input type="url" id="source" name="source" placeholder="source" class="form-control" required="true" value="${(news.source)!}">
                        
                        <label for="date">Date:</label>
                        <input type="datetime-local" id="date" name="publishDate" class="form-control" value="${(news.publishDate?string['yyyy-MM-dd\'T\'HH:mm'])!}">
                        
                        <#-- <input type="submit" class="hidden" onclick=""> -->
                    </div>
                        <input type="reset" value="reset" class="btn btn-danger">
                    </form>
                    <div class="row">
                        <div class="col-xs-6">
                            <div class="text-center">
                                <div class="btn-primary btn" onclick="return submitForm('PUBLISHED')">Submit</div>
                            </div>
                        </div>
                        <div class="col-xs-6">
                            <div class="text-center">
                                <div class="btn-primary btn" onclick="return submitForm('DRAFT')">Draft</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <a href="/su/logout" class="btn btn-danger">Logout</a>
        <a href="/su/scrapper" class="btn btn-danger">Scrapper</a>
    </div>
</@baseLayout>