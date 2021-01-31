<#-- <div class="row">
    <div class="col-md-12"> -->
        <nav class="navbar navbar-default" role="navigation">
            <div class="navbar-header"> 

                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                     <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
                </button> <a class="navbar-brand" href="/">NewsByte</a>
            </div>               

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <#list contexts as ctx>
                        <li <#if (context!"") == ctx>class="active"</#if>>
                            <a href="/${ctx}/">${ctx?cap_first}</a>
                        </li>
                    </#list>
                </ul>
<#--                 <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="#">Something For Future</a>
                    </li>
                    <li> 
                        <a href="#"> Log in (?!) </a>
                    </li>                        
                </ul> -->
            </div>
        </nav>
