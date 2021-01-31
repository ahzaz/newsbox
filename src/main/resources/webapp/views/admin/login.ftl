<@baseLayout title="Login for Admin" css=['scrapper/style.css'] adminPage = true> 
    <div class="col-md-4 col-md-offset-4 colcol-xs-12">
        <form action="/su/login" method="post">
            <input type="text" class="form-control"  name="username" placeholder="Username"> <br />
            <input type="password" class="form-control" name="password" placeholder="Password"> <br />
            <input type="submit" class="btn btn-primary btn-block" name="submit" value="Submit"> <br />
        </form>
    </div>
</@baseLayout><!DOCTYPE html>