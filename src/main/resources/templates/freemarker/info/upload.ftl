<form class="form-horizontal" action="">
    <fieldset>
        <legend>上传用户信息(excel文件)</legend>
        <div class="form-group">
            <label class="col-md-2 control-label">选择类型</label>
            <div class="col-md-5">
                <div class="input-group">
                    <select class="btn btn-default form-control" name="excelType">
                        <option>请选择上传类型</option>
                        <option value=1>学生基本信息表</option>
                        <option value=2>教师基本信息表</option>
                        <option value=3>学生成绩信息表</option>
                        <option value=4>学生课程信息表</option>
                        <option value=5>等级考试信息表</option>
                        <option value=6>学生选课信息表</option>
                        <option value=7>学生就业信息表</option>
                        <option value=8>教师学生关联信息表</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">选择文件</label>
            <div class="col-md-8">
                <div class="input-group">
                    <input type="file" id="file" class="btn btn-default form-control" name="file"/>
                    <span style="cursor: pointer" target="file" role=upload class="input-group-addon">上传</span>
                </div>
                <p class="help-block">
                    没有模板? <a href="../static/导入模板.zip" style="cursor: pointer" role="template">点击下载模板</a>
                </p>
            </div>
        </div>
    </fieldset>
</form>
