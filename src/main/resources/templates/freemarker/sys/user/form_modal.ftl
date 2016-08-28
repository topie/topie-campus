<div id="user_modal" class="modal fade" tabindex="-1" data-width="760">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3>用户编辑</h3>
    </div>
    <div class="modal-body">
        <form class="form-horizontal" action="">
            <fieldset>
                <div class="form-group">
                    <label class="col-md-2 control-label" for="text-field">用户登录名</label>
                    <div class="col-md-10">
                        <input class="form-control" placeholder="输入用户登录名" value="${user.loginName}" type="text">
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn">取消</button>
        <button type="button" class="btn btn-primary">保存</button>
    </div>
</div>
