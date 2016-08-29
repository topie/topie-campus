/**
 * Created by chenguojun on 8/10/16.
 */
;
(function ($, window, document, undefined) {
    var that = this;
    that.uploadUrl = window.App.href + "/api/info/user/uploadExcel";
    var uploadMapping = {
        "/api/sys/user/pageList": "sysUser"
    }
    window.App.requestMapping = $.extend({}, window.App.requestMapping, uploadMapping);
    window.App.sysUser = {
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body" id="user_grid"></div>');
            window.App.content.append(content);
            initEvents();
        }
    }
    initEvents = function () {
        var grid;
        var options = {
            url: App.href + "/api/sys/user/pageList",
            beforeSend: function (request) {
                request.setRequestHeader("X-Auth-Token", App.token);
            },
            pageNum: 1,//当前页码
            pageSize: 15,//每页显示条数
            idFiled: "id",//id域指定
            showCheckbox: true,//是否显示checkbox
            checkboxWidth: "3%",
            showIndexNum: true,
            indexNumWidth: "5%",
            pageSelect: [2, 15, 30, 50],
            columns: [{
                title: "id",
                field: "id",
                sort: true
            }, {
                title: "登录名",
                field: "loginName",
                sort: true
            }, {
                title: "昵称",
                field: "displayName"
            }],
            actionColumnText: "操作",//操作列文本
            actionColumnWidth: "5%",
            actionColumns: [{
                text: "编辑",
                cls: "btn-primary btn-sm",
                handle: function (index, data) {
                    var userId = data.id;
                    var modal = $.topieModal({
                        id: "userForm",
                        title: "编辑用户",
                        destroy: true
                    });
                    $.ajax(
                        {
                            type: 'GET',
                            url: App.href + "/api/sys/user/load/" + userId,
                            contentType: "application/json",
                            dataType: "json",
                            beforeSend: function (request) {
                                request.setRequestHeader("X-Auth-Token", App.token);
                            },
                            success: function (result) {
                                if (result.code === 200) {
                                    modal.content(result.data.html);
                                    modal.$modal.find("button[role=cancel]").on("click", function () {
                                        modal.hide();
                                    });
                                    modal.$modal.find("button[role=submit]").on("click", function () {
                                        $.ajax(
                                            {
                                                type: 'POST',
                                                url: App.href + "/api/sys/user/update",
                                                data: modal.$modal.find("form[role=form]").serialize(),
                                                dataType: "json",
                                                beforeSend: function (request) {
                                                    request.setRequestHeader("X-Auth-Token", App.token);
                                                },
                                                success: function (result) {
                                                    if (result.code === 200) {
                                                        grid.reload();
                                                        modal.hide();
                                                    }
                                                }
                                            }
                                        );
                                    });
                                    modal.show();
                                } else {
                                    alert(result.msg);
                                }
                            }
                        }
                    );
                }
            }],
            tools: [
                {
                    text: " 添 加",//按钮文本
                    cls: "btn btn-primary",//按钮样式
                    icon: "fa fa-cubes",
                    handle: function (grid) {

                    }
                }
            ],
            search: {
                rowEleNum: 2,
                //搜索栏元素
                items: [{
                    type: "text",
                    label: "登录名",
                    name: "loginName",
                    placeholder: "输入要搜索的登录名"
                },{
                    type: "text",
                    label: "昵称",
                    name: "displayName",
                    placeholder: "输入要搜索的昵称"
                }]
            }
        };
        grid = window.App.content.find("#user_grid").topieGrid(options);
    }
})(jQuery, window, document);
