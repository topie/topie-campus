<script>

</script>
<form class="form-horizontal" action="">
    <fieldset>
        <legend>上传用户信息(excel文件)</legend>
        <div class="form-group">
            <label class="col-md-2 control-label">选择文件</label>
            <div class="col-md-10">
                <div class="input-group">
                    <input type="file" id="jobfile" class="btn btn-default form-control" name="file"/>
                    <span style="cursor: pointer" target="jobfile" role=jobUpload class="input-group-addon" onclick="App.infoEmploy.loadJobInfo('jobfile');">导入</span>
                </div>
                <p class="help-block">
                    没有模板? <a href="javascript:void(0);" style="cursor: pointer" role="template">点击下载模板</a>
                </p>
            </div>
        </div>
    </fieldset>
</form>

