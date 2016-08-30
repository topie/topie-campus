/**
 * Created by chenguojun on 8/10/16.
 */
;
(function ($, window, document, undefined) {
    var that = this;
    that.uploadUrl = window.App.href + "/api/info/user/uploadExcel";
    /**
     * 功能菜单 对应 当前的唯一别名
     * @type {{/api/sys/user/pageList: string}}
     */
    var uploadMapping = {
        "/api/sys/user/pageList": "sysUser"
    }
    /**
     * 加入全局mapping
     */
    window.App.requestMapping = $.extend({}, window.App.requestMapping, uploadMapping);
    /**
     * 对应requestMapping值 sysUser page函数为进入页面入口方法
      * @type {{page: App.sysUser.page}}
     */
    window.App.sysUser = {
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body" id="user_grid"></div>');
            window.App.content.append(content);
            initEvents();
        }
    }
    /**
     * 初始化事件
     */
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
                sort: true,
                width: "5%",
            }, {
                title: "登录名",
                field: "loginName",
                sort: true
            }, {
                title: "昵称",
                field: "displayName"
            }],
            actionColumnText: "操作",//操作列文本
            actionColumnWidth: "25%",
            actionColumns: [{
                text: "编辑(非form控件)",
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
            }, {
                text: "编辑(form控件)",
                cls: "btn-primary btn-sm",
                handle: function (index, data) {
                    var modal = $.topieModal({
                        id: "userForm",
                        title: "编辑用户",
                        destroy: true
                    });
                    var formOpts = {
                        id: "index_form",//表单id
                        name: "index_form",//表单名
                        method: "POST",//表单method
                        action: App.href + "/api/sys/user/update",//表单action
                        ajaxSubmit: true,//是否使用ajax提交表单
                        rowEleNum: 2,
                        beforeSubmit: function () {
                        },
                        beforeSend: function (request) {
                            request.setRequestHeader("X-Auth-Token", App.token);
                        },
                        ajaxSuccess: function () {
                            modal.hide();
                            grid.reload();
                        },
                        submitText: "保存",//保存按钮的文本
                        showReset: true,//是否显示重置按钮
                        resetText: "重置",//重置按钮文本
                        isValidate: true,//开启验证
                        buttons: [{
                            type: 'button',
                            text: '关闭',
                            handle: function () {
                                modal.hide();
                            }
                        }],
                        buttonsAlign: "center",
                        items: [{
                            type: 'hidden',
                            name: 'id',
                            id: 'id'
                        }, {
                            type: 'text',//类型
                            name: 'loginName',//name
                            id: 'loginName',//id
                            label: '登录名',//左边label
                            cls: 'input-large',
                            readonly:true,
                            rule: {
                                required: true
                            },
                            message: {
                                required: "请输入登录名"
                            }
                        }, {
                            type: 'text',//类型
                            name: 'displayName',//name
                            id: 'displayName',//id
                            label: '昵称',//左边label
                            cls: 'input-large',
                            rule: {
                                required: true
                            },
                            message: {
                                required: "请输入昵称"
                            }
                        }]
                    };
                    var form = modal.$modal.topieForm(formOpts);
                    form.loadLocal(data);
                    modal.show();
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
                }, {
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
