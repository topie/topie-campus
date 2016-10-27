;
(function ($, window, document, undefined) {
    var mapping = {
        "/api/info/teacherType/page": "teacherType"
    };
    App.requestMapping = $.extend({}, window.App.requestMapping, mapping);
    App.teacherType = {
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body" id="teacherType_grid"></div>');
            window.App.content.append(content);
            App.teacherType.initEvents();
        }
    };
    App.teacherType.formItems = [
        {
            type: 'hidden',
            name: 'typeId',
            id: 'typeId'
        }, {
            type: 'text',
            name: 'typeName',
            id: 'typeName',
            label: '教师类型名称',
            cls: 'input-large',
            rule: {
                required: true
            },
            message: {
                required: "请输入教师类型名称"
            }
        }
    ];
    App.teacherType.columns = [{
        title: "typeId",
        field: "typeId",
        width: "5%"
    }, {
        title: "教师类型名称",
        field: "typeName"
    }];
    App.teacherType.initEvents = function () {
        var grid;
        var options = {
            url: App.href + "/api/info/teacherType/page",
            beforeSend: function (request) {
                request.setRequestHeader("X-Auth-Token", App.token);
            },
            pageNum: 1,
            pageSize: 15,
            idFiled: "typeId",
            showCheckbox: true,
            checkboxWidth: "3%",
            showIndexNum: true,
            indexNumWidth: "5%",
            pageSelect: [2, 15, 30, 50],
            columns: App.teacherType.columns,
            actionColumnText: "操作",
            actionColumnWidth: "20%",
            actionColumns: [{
                text: "编辑",
                cls: "btn-primary btn-sm",
                handle: function (index, data) {
                    var modal = $.topieModal({
                        id: "teacherTypeForm",
                        title: "编辑导师类型",
                        destroy: true
                    });
                    modal.show();
                    var formOpts = {
                        id: "teacherType_form",
                        name: "teacherType_form",
                        method: "POST",
                        action: App.href + "/api/info/teacherType/update",
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
                        items: App.teacherType.formItems
                    };
                    var form = modal.$body.topieForm(formOpts);
                    form.loadRemote(App.href + "/api/info/teacherType/load/" + data.typeId);
                }
            }, {
                text: "删除",
                cls: "btn-danger btn-sm",
                handle: function (index, data) {
                    var requestUrl = App.href + "/api/info/teacherType/delete";
                    $.ajax({
                        type: "GET",
                        beforeSend: function (request) {
                            request.setRequestHeader("X-Auth-Token", App.token);
                        },
                        dataType: "json",
                        data: {
                            teacherTypeId: data.id
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
                            id: "teacherType_modal",
                            title: "添加导师类型",
                            destroy: true
                        }).show();
                        var formOpts = {
                            id: "add_teacherType_form",
                            name: "add_teacherType_form",
                            method: "POST",
                            action: App.href + "/api/info/teacherType/insert",
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
                            items: App.teacherType.formItems
                        };
                        var form = modal.$body.topieForm(formOpts);
                    }
                }
            ],
            search: {
                rowEleNum: 1,
                items: [{
                    type: "text",
                    label: "教师类型名称",
                    name: "typeName",
                    placeholder: "教师类型名称"
                }]
            }
        };
        grid = window.App.content.find("#teacherType_grid").topieGrid(options);
    }
})(jQuery, window, document);
