<div id="user_modal" class="modal fade" tabindex="-1" data-width="760">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3>用户编辑</h3>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn">取消</button>
        <button type="button" role="submit" class="btn btn-primary">保存</button>
    </div>
</div>

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