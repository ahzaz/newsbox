<@baseLayout css=['home/home.css', 'admin/style.css'] js=['admin/script.js'] adminPage=true>
<div class="row">
    <div class="col-md-9 col-md-offset-2">
        ${logContent}
    </div>
    <a href="/su/logout" class="btn btn-danger">Logout</a>
</div>
</@baseLayout>