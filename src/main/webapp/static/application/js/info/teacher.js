/**
 * Created by chenguojun on 8/10/16.
 */
;
(function ($, window, document, undefined) {
    /**
     * 功能菜单 对应 当前的唯一别名
     * @type {{/api/info/teacher/pageList: string}}
     */
    var mapping = {
        "/api/info/teacher/pageList": "infoTeacher"
    }
    App.requestMapping = $.extend({}, window.App.requestMapping, mapping);
    App.infoTeacher = {
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body" id="teacher_grid"></div>');
            window.App.content.append(content);
            App.infoTeacher.initEvents();
        }
    }
    App.infoTeacher.initEvents = function () {
        var grid;
        var options = {
            url: App.href + "/api/info/teacher/pageList",
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
                width: "5%"
            }, {
                title: "教师名称",
                field: "name",
                sort: true
            }],
            actionColumnText: "操作",//操作列文本
            actionColumnWidth: "20%",
            actionColumns: [{
                text: "编辑",
                cls: "btn-primary btn-sm",
                handle: function (index, data) {
                    var modal = $.topieModal({
                        id: "teacherForm",
                        title: "编辑教师",
                        destroy: true
                    });
                    var formOpts = {
                        id: "index_form",//表单id
                        name: "index_form",//表单名
                        method: "POST",//表单method
                        action: App.href + "/api/info/teacher/update",//表单action
                        ajaxSubmit: true,//是否使用ajax提交表单
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
                            name: 'name',//name
                            id: 'name',//id
                            label: '教师名',//左边label
                            cls: 'input-large',
                            readonly: true,
                            rule: {
                                required: true
                            },
                            message: {
                                required: "请输入教师名"
                            }
                        }, {
                            type: 'tree',//类型
                            name: 'functions',
                            id: 'functions',//id
                            label: '菜单',//左边label
                            url: App.href + "/api/info/function/treeNodes?topie_token=" + App.token,
                            expandAll: true,
                            autoParam: ["id", "name", "pId"],
                            chkStyle: "checkbox",
                            rule: {
                                required: true
                            },
                            message: {
                                required: "请选择至少一个菜单"
                            }
                        }]
                    };
                    var form = modal.$body.topieForm(formOpts);
                    form.loadRemote(App.href + "/api/info/teacher/load/" + data.id);
                    modal.show();
                }
            }, {
                text: "学生",
                cls: "btn-primary btn-sm",
                handle: function (index, data) {
                    var studentGrid;
                    var modal = $.topieModal({
                        id: "student_grid",
                        title: "学生",
                        destroy: true
                    });
                    var options = {
                        url: App.href + "/api/info/teacher/hasTeacherUserList?teacherId=" + data.id,
                        beforeSend: function (request) {
                            request.setRequestHeader("X-Auth-Token", App.token);
                        },
                        pageNum: 1,//当前页码
                        pageSize: 15,//每页显示条数
                        idFiled: "id",//id域指定
                        showCheckbox: true,//是否显示checkbox
                        checkboxWidth: "3%",
                        showIndexNum: false,
                        indexNumWidth: "5%",
                        pageSelect: [2, 15, 30, 50],
                        columns: [{
                            title: "设置学生id",
                            field: "id",
                            sort: true,
                            width: "5%",
                        }, {
                            title: "姓名",
                            field: "name",
                            sort: true
                        }],
                        actionColumnText: "操作",//操作列文本
                        actionColumnWidth: "20%",
                        actionColumns: [{
                            textHandle: function (index, studentData) {
                                if (studentData.hasTeacher) {
                                    return "取消";
                                } else {
                                    return "选择";
                                }
                            },
                            clsHandle: function (index, studentData) {
                                if (studentData.hasTeacher) {
                                    return "btn-danger btn-sm";
                                } else {
                                    return "btn-primary btn-sm";
                                }
                            },
                            handle: function (index, studentData) {
                                var requestUrl = App.href + "/api/info/teacher/selectUser";
                                if (studentData.hasTeacher) {
                                    requestUrl = App.href + "/api/info/teacher/cancelUser";
                                }
                                $.ajax({
                                    type: "GET",
                                    beforeSend: function (request) {
                                        request.setRequestHeader("X-Auth-Token", App.token);
                                    },
                                    dataType: "json",
                                    data: {
                                        teacherId: data.id,
                                        id: studentData.id
                                    },
                                    url: requestUrl,
                                    success: function (data) {
                                        if (data.code === 200) {
                                            studentGrid.reload();
                                        } else {
                                            alert(data.message);
                                        }
                                    },
                                    error: function (e) {
                                        alert("请求异常。");
                                    }
                                });
                            }
                        }],
                        search: {
                            rowEleNum: 2,
                            //搜索栏元素
                            items: [{
                                type: "text",
                                label: "姓名",
                                name: "name",
                                placeholder: "输入要搜索的姓名"
                            }]
                        }
                    };
                    studentGrid = modal.$body.topieGrid(options);
                    modal.show();
                }
            }, {
                text: "删除",
                cls: "btn-danger btn-sm",
                handle: function (index, data) {
                    var requestUrl = App.href + "/api/info/teacher/delete";
                    $.ajax({
                        type: "POST",
                        beforeSend: function (request) {
                            request.setRequestHeader("X-Auth-Token", App.token);
                        },
                        dataType: "json",
                        data: {
                            teacherId: data.id
                        },
                        url: requestUrl,
                        success: function (data) {
                            if (data.code === 200) {
                                grid.reload();
                            } else {
                                alert(data.message);
                            }
                        },
                        error: function (e) {
                            alert("请求异常。");
                        }
                    });
                }
            }],
            tools: [
                {
                    text: " 添 加",//按钮文本
                    cls: "btn btn-primary",//按钮样式
                    icon: "fa fa-cubes",
                    handle: function (grid) {
                        var modal = $.topieModal({
                            id: "teacherForm",
                            title: "添加教师",
                            destroy: true
                        });
                        var formOpts = {
                            id: "add_teacher_form",
                            name: "add_teacher_form",
                            method: "POST",
                            action: App.href + "/api/info/teacher/insert",//表单action
                            ajaxSubmit: true,//是否使用ajax提交表单
                            rowEleNum: 1,
                            beforeSubmit: function () {
                            },
                            beforeSend: function (request) {
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
                                type: 'text',
                                name: 'name',
                                id: 'name',
                                label: '教师名',//左边label
                                cls: 'input-large',
                                rule: {
                                    required: true
                                },
                                message: {//对应验证提示信息
                                    required: "请输入教师名"
                                }
                            }]
                        };
                        var form = modal.$body.topieForm(formOpts);
                        modal.show();
                    }
                }
            ],
            search: {
                rowEleNum: 2,
                //搜索栏元素
                items: [{
                    type: "text",
                    label: "教师名",
                    name: "name",
                    placeholder: "输入要搜索的教师名"
                }]
            }
        };
        grid = window.App.content.find("#teacher_grid").topieGrid(options);
    }
})(jQuery, window, document);
