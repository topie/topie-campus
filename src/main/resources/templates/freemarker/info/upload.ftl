<form class="form-horizontal" action="">
    <fieldset>
        <legend>上传用户信息(excel文件)</legend>
        <div class="form-group">
            <label class="col-md-2 control-label">选择文件</label>
            <div class="col-md-10">
                <div class="input-group">
                    <input type="file" id="file" class="btn btn-default form-control" name="file"/>
                    <span style="cursor: pointer" target="file" role=upload class="input-group-addon">上传</span>
                </div>
                <p class="help-block">
                    没有模板? <a href="javascript:void(0);" style="cursor: pointer" role="template">点击下载模板</a>
                </p>
            </div>
        </div>
    </fieldset>
</form>
<div class="well-large">
    <legend>字段内容限定</legend>
    <p>性别:<#list gender as item>${item}/</#list></p>
    <p>政治面貌:<#list ps as item>${item}/</#list></p>
    <p>学历:<#list eb as item>${item}/</#list></p>
    <p>学位:<#list degree as item>${item}/</#list></p>
</div>
