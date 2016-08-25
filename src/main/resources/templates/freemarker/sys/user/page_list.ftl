<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered">
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
        <td class="center">-</td>
    </tr>
    </#list>
    </tbody>
</table>
