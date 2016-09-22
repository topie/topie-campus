;
(function ($, window, document, undefined) {
    var mapping = {
        "/api/job/page": "infoEmploy"
    };
    App.requestMapping = $.extend({}, window.App.requestMapping, mapping);
    App.infoEmploy = {
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body" id="student_grid"></div>');
            window.App.content.append(content);
            App.infoEmploy.initEvents();
        }
    };
    App.infoEmploy.formItems = [
        {
            type: 'hidden',
            name: 'id',
            id: 'id'
        }, {
            type: 'text',
            name: 'name',
            id: 'name',
            label: '学生名',
            cls: 'input-large',
            rule: {
                required: true
            },
            message: {
                required: "请输入学生名"
            }
        }, {
            type: 'text',
            name: 'studentNo',
            id: 'studentNo',
            label: '学号',
            cls: 'input-large',
            rule: {
                required: true
            },
            message: {
                required: "请输入学号"
            }
        }, {
            type: 'radioGroup',
            name: 'gender',
            id: 'gender',
            label: '性别',
            inline: true,
            items: [{
                value: 1,
                text: '男'
            }, {
                value: 0,
                text: '女'
            }]
        }, {
            type: 'datepicker',
            name: 'birth',
            id: 'birth',
            label: '出生日期',
            config: {
                timePicker: false,
                singleDatePicker: true,
                locale: {
                    format: 'YYYY-MM-DD'
                }
            },
            rule: {
                required: true
            },
            message: {
                required: "请选择出生日期"
            }
        }, {
            type: "select",
            label: "民族",
            name: "ethnicGroup",
            items: [
                {
                    text: "请选择民族",
                    value: ""
                }
            ],
            itemsUrl: App.href + "/api/common/ethnicGroup/option?topie_token=" + App.token
        }, {
            type: "select",
            label: "政治面貌",
            name: "ethnicGroup",
            items: [
                {
                    text: "请选择政治面貌",
                    value: ""
                }
            ],
            itemsUrl: App.href + "/api/common/politicalStatus/option?topie_token=" + App.token
        }, {
            type: 'text',
            name: 'academe',
            id: 'academe',
            label: '学院',
            cls: 'input-large'
        }, {
            type: 'text',
            name: 'subject',
            id: 'subject',
            label: '专业',
            cls: 'input-large'
        }, {
            type: 'text',
            name: 'contactPhone',
            id: 'contactPhone',
            label: '联系电话',
            cls: 'input-large'
        }, {
            type: 'text',
            name: 'email',
            id: 'email',
            label: '邮箱',
            cls: 'input-large'
        }
    ];
    App.infoEmploy.columns = [{
        title: "id",
        field: "id",
        sort: true,
        width: "5%"
    }, {
        title: "学生名称",
        field: "name",
        sort: true
    }, {
        title: "联系电话",
        field: "contactPhone",
        sort: true
    }];
    App.infoEmploy.initEvents = function () {
        var grid;
        var options = {
            url: App.href + "/api/job/page",
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
            columns: App.infoEmploy.columns,
            actionColumnText: "操作",
            actionColumnWidth: "20%",
            actionColumns: [{
                text: "编辑",
                cls: "btn-primary btn-sm",
                handle: function (index, data) {
                    var modal = $.topieModal({
                        id: "studentForm",
                        title: "编辑学生",
                        destroy: true
                    });
                    var formOpts = {
                        id: "student_form",
                        name: "student_form",
                        method: "POST",
                        action: App.href + "/api/info/student/update",
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
                        rowEleNum: 2,
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
                        items: App.infoEmploy.formItems
                    };
                    var form = modal.$body.topieForm(formOpts);
                    form.loadRemote(App.href + "/api/info/student/load/" + data.id);
                    modal.show();
                }
            }, {
                text: "删除",
                cls: "btn-danger btn-sm",
                handle: function (index, data) {
                    var requestUrl = App.href + "/api/info/student/delete";
                    $.ajax({
                        type: "POST",
                        beforeSend: function (request) {
                            request.setRequestHeader("X-Auth-Token", App.token);
                        },
                        dataType: "json",
                        data: {
                            studentId: data.id
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
                            id: "student_modal",
                            title: "添加学生",
                            destroy: true
                        });
                        var formOpts = {
                            id: "add_student_form",
                            name: "add_student_form",
                            method: "POST",
                            action: App.href + "/api/info/student/insert",
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
                            items: App.infoEmploy.formItems
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
                    label: "学生名",
                    name: "name",
                    placeholder: "输入要搜索的学生名"
                }, {
                    title: "联系电话",
                    field: "contactPhone",
                    sort: true
                }]
            }
        };
        grid = window.App.content.find("#student_grid").topieGrid(options);
    }
})(jQuery, window, document);
