<div class="row">
    <div class="col-xs-6">

    </div>
    <div class="col-xs-6">
        <div class="dataTables_filter" id="example_filter">
            <label>Search: <input type="text" aria-controls="example"/></label>
        </div>
    </div>
</div>
<div id="example_wrapper" class="dataTables_wrapper form-inline"
     style="position: relative; overflow: auto; width: 100%;" role="grid">
    <table cellpadding="0" cellspacing="0" border="0"
           class="table table-striped table-bordered dataTable" id="example"
           aria-describedby="example_info">
        <thead>
        <tr>
            <th>用户ID</th>
            <th>用户登录名</th>
            <th>昵称</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <#list list as item>
        <tr class="gradeA">
            <td class="center">${item.id}</td>
            <td class="center">${item.loginName}</td>
            <td class="center">${item.displayName}</td>
            <td class="center"><a role="row_edit" target="${item.id}" href="javascript:void(0);">编辑</a></td>
        </tr>
        </#list>
        </tbody>
    </table>
</div>
<div class="row">
    <div class="col-xs-6">
        <div id="example_length" class="dataTables_length"><label>
            <select size="1" name="example_length" aria-controls="example">
                <option value="10" selected="selected">10</option>
                <option value="25">25</option>
                <option value="50">50</option>
                <option value="100">100</option>
            </select></label></div>
    </div>
    <div class="col-xs-6">
        <div class="dataTables_paginate paging_bootstrap">
            <ul class="pagination">
                <li class="prev disabled"><a href="#">←</a></li>
                <li class="next"><a href="#">→</a></li>
            </ul>
        </div>
    </div>
</div>
