<form class="form-horizontal" role="form" action="">
    <input type="hidden" name="id" value="${user.id}">
    <div class="form-group">
        <label class="col-md-2 control-label" for="text-field">用户登录名</label>
        <div class="col-md-10">
            <input class="form-control" placeholder="输入用户登录名" readonly name="loginName" value="${user.loginName}"
                   type="text">
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 control-label" for="text-field">用户昵称</label>
        <div class="col-md-10">
            <input class="form-control" placeholder="输入用户昵称" name="displayName" value="${user.displayName}"
                   type="text">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-4 col-sm-8">
            <button type="button" role="submit" class="btn btn-primary">保存</button>
            <button type="button" role="cancel" class="btn btn-default">取消</button>
        </div>
    </div>
</form>
