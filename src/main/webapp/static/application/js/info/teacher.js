;
(function ($, window, document, undefined) {
    var mapping = {
        "/api/info/teacher/pageList": "infoTeacher"
    };
    App.requestMapping = $.extend({}, window.App.requestMapping, mapping);
    App.infoTeacher = {
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body" id="teacher_grid"></div>');
            window.App.content.append(content);
            App.infoTeacher.initEvents();
        }
    };
    App.infoTeacher.initEvents = function () {
        var grid;
        var options = {
            url: App.href + "/api/info/teacher/pageList",
            beforeSend: function (request) {
                request.setRequestHeader("X-Auth-Token", App.token);
            },
            pageNum: 1,
            pageSize: 15,
            idFiled: "id",
            showCheckbox: true,
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
            actionColumnText: "操作",
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
                        id: "teacher_form",
                        name: "teacher_form",
                        method: "POST",
                        action: App.href + "/api/info/teacher/update",
                        ajaxSubmit: true,
                        beforeSend: function (request) {
                            request.setRequestHeader("X-Auth-Token", App.token);
                        },
                        ajaxSuccess: function () {
                            modal.hide();
                            grid.reload();
                        },
                        submitText: "保存",
                        showReset: true,
                        resetText: "重置",
                        isValidate: true,
                        buttons: [{
                            type: 'button',
                            text: '关闭',
                            handle: function () {
                                modal.hide();
                            }
                        }],
                        buttonsAlign: "center",
                        items: [
                            {
                                type: 'hidden',
                                name: 'id',
                                id: 'id'
                            }, {
                                type: 'text',
                                name: 'name',
                                id: 'name',
                                label: '教师名',
                                cls: 'input-large',
                                readonly: true,
                                rule: {
                                    required: true
                                },
                                message: {
                                    required: "请输入教师名"
                                }
                            }
                        ]
                    };
                    var form = modal.$body.topieForm(formOpts);
                    form.loadRemote(App.href + "/api/info/teacher/load/" + data.id);
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
                    text: " 添 加",
                    cls: "btn btn-primary",
                    icon: "fa fa-cubes",
                    handle: function (grid) {
                        var modal = $.topieModal({
                            id: "teacher_modal",
                            title: "添加教师",
                            destroy: true
                        });
                        var formOpts = {
                            id: "add_teacher_form",
                            name: "add_teacher_form",
                            method: "POST",
                            action: App.href + "/api/info/teacher/insert",
                            ajaxSubmit: true,
                            rowEleNum: 1,
                            beforeSend: function (request) {
                                request.setRequestHeader("X-Auth-Token", App.token);
                            },
                            ajaxSuccess: function () {
                                modal.hide();
                                grid.reload();
                            },
                            submitText: "保存",
                            showReset: true,
                            resetText: "重置",
                            isValidate: true,
                            buttons: [{
                                type: 'button',
                                text: '关闭',
                                handle: function () {
                                    modal.hide();
                                }
                            }],
                            buttonsAlign: "center",
                            items: [
                                {
                                    type: 'text',
                                    name: 'name',
                                    id: 'name',
                                    label: '教师名',
                                    cls: 'input-large',
                                    rule: {
                                        required: true
                                    },
                                    message: {
                                        required: "请输入教师名"
                                    }
                                }
                            ]
                        };
                        var form = modal.$body.topieForm(formOpts);
                        modal.show();
                    }
                }
            ],
            search: {
                rowEleNum: 2,
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
