;
(function ($, window, document, undefined) {
    var mapping = {
        "/api/info/surveyGroup/page": "surveyGroup"
    };
    App.requestMapping = $.extend({}, window.App.requestMapping, mapping);
    App.surveyGroup = {
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body" id="surveyGroup_grid"></div>');
            window.App.content.append(content);
            App.surveyGroup.initEvents();
        }
    };
    App.surveyGroup.formItems = [
        {
            type: 'hidden',
            name: 'groupId',
            id: 'groupId'
        }, {
            type: 'tree',//类型
            name: 'typeId',
            id: 'typeId',//id
            label: '导师类型',//左边label
            url: App.href + "/api/info/teacherType/treeNodes?topie_token=" + App.token,
            expandAll: true,
            autoParam: ["id", "name", "pId"],
            chkStyle: "radio"
        }, {
            type: 'text',
            name: 'groupName',
            id: 'groupName',
            label: '问卷组名称',
            cls: 'input-large',
            rule: {
                required: true
            },
            message: {
                required: "请输入问卷组名称"
            }
        }, {
            type: 'datepicker',
            name: 'start',
            id: 'start',
            label: '开始时间',
            config: {
                timePicker: true,
                singleDatePicker: true,
                locale: {
                    format: 'YYYY-MM-DD HH:mm:ss'
                }
            },
            rule: {
                required: true
            },
            message: {
                required: "请选择开始时间"
            }
        }, {
            type: 'datepicker',
            name: 'end',
            id: 'end',
            label: '结束时间',
            config: {
                timePicker: true,
                singleDatePicker: true,
                locale: {
                    format: 'YYYY-MM-DD HH:mm:ss'
                }
            },
            rule: {
                required: true
            },
            message: {
                required: "请输入结束时间"
            }
        }
    ];
    App.surveyGroup.columns = [
        {
            title: "问卷组名称",
            field: "groupName"
        }, {
            title: "开始时间",
            field: "start"
        }, {
            title: "结束时间",
            field: "end"
        }, {
            title: "是否上线",
            field: "isOnline",
            format: function (i, data) {
                if (data.isOnline) {
                    return '<span class="label label-info">上线</span>';
                } else {
                    return '<span class="label label-default">未上线</span>';
                }
            }
        }
    ];

    App.surveyGroup.selectQuestionTmpl =
        '<div class="row">'
        + '<div class="col-lg-6">'
        + '<div class="panel panel-default">'
        + '<div class="panel-heading">'
        + '<i class="fa fa-bar-chart-o fa-fw"></i> 题库'
        + '  </div>'
        + '<div id="from" class="panel-body">'
        + '</div>'
        + '</div>'
        + '</div>'
        + '<div class="col-lg-6">'
        + '<div class="panel panel-default">'
        + '<div class="panel-heading">'
        + '<i class="fa fa-bar-chart-o fa-fw"></i> 问卷组题目'
        + ' </div>'
        + '<div id="to" class="panel-body">'
        + '</div>'
        + '</div>'
        + '</div>'
        + '</div>';
    var initSelectQuestion = function (ele, groupId) {
        var grid1;
        var grid2;
        var option1 = {
            url: App.href + "/api/info/surveyQuestion/page",
            beforeSend: function (request) {
                request.setRequestHeader("X-Auth-Token", App.token);
            },
            pageNum: 1,
            pageSize: 15,
            idFiled: "questionId",
            showCheckbox: true,
            checkboxWidth: "3%",
            showIndexNum: true,
            simplePaging: true,
            indexNumWidth: "5%",
            pageSelect: [2, 15, 30, 50],
            columns: [
                {
                    title: "问卷题内容",
                    field: "questionContent"
                }
            ],
            actionColumnText: "操作",
            actionColumnWidth: "20%",
            actionColumns: [
                {
                    text: "添加到问卷组",
                    cls: "btn-primary btn-sm",
                    handle: function (index, data) {
                        $.ajax({
                            type: "GET",
                            beforeSend: function (request) {
                                request.setRequestHeader("X-Auth-Token", App.token);
                            },
                            dataType: "json",
                            data: {
                                "groupId": groupId,
                                "questionId": data.questionId
                            },
                            url: App.href + "/api/info/surveyGroup/insertGroupQuestion",
                            success: function (data) {
                                if (data.code === 200) {
                                    grid1.reload();
                                    grid2.reload();
                                } else {
                                    alert(data.message);
                                }
                            },
                            error: function (e) {
                                alert("请求异常。");
                            }
                        });
                    }
                }
            ],
            search: {
                rowEleNum: 1,
                items: [{
                    type: "text",
                    label: "问卷题内容",
                    name: "questionContent",
                    placeholder: "问卷题内容"
                }]
            }
        };
        grid1 = ele.find("#from").topieGrid(option1);

        var option2 = {
            url: App.href + "/api/info/surveyGroup/questions?groupId=" + groupId,
            beforeSend: function (request) {
                request.setRequestHeader("X-Auth-Token", App.token);
            },
            pageNum: 1,
            pageSize: 10,
            idFiled: "questionId",
            showCheckbox: true,
            checkboxWidth: "3%",
            showIndexNum: false,
            showSearch: false,
            simplePaging: true,
            indexNumWidth: "5%",
            pageSelect: [2, 15, 30, 50],
            columns: [
                {
                    title: "问卷题内容",
                    field: "questionContent"
                },
                {
                    title: "题目序号",
                    field: "sort",
                    width: "12%",
                    format: function (i, data) {
                        return "<input type='text' id='sort_" + i + "' class='form-control input-sm' value='" + data.sort + "'>"
                    }
                }
            ],
            actionColumnText: "操作",
            actionColumnWidth: "30%",
            actionColumns: [
                {
                    text: "修改序号",
                    cls: "btn-primary btn-sm",
                    handle: function (i, data) {
                        $.ajax({
                            type: "GET",
                            beforeSend: function (request) {
                                request.setRequestHeader("X-Auth-Token", App.token);
                            },
                            dataType: "json",
                            data: {
                                "groupId": groupId,
                                "questionId": data.questionId,
                                "sort": $("#sort_" + i).val()
                            },
                            url: App.href + "/api/info/surveyGroup/updateGroupQuestionSort",
                            success: function (data) {
                                if (data.code === 200) {
                                    grid2.reload();
                                } else {
                                    alert(data.message);
                                }
                            },
                            error: function (e) {
                                alert("请求异常。");
                            }
                        });
                    }
                },
                {
                    text: "删除",
                    cls: "btn-danger btn-sm",
                    handle: function (i, data) {
                        $.ajax({
                            type: "GET",
                            beforeSend: function (request) {
                                request.setRequestHeader("X-Auth-Token", App.token);
                            },
                            dataType: "json",
                            data: {
                                "groupId": groupId,
                                "questionId": data.questionId
                            },
                            url: App.href + "/api/info/surveyGroup/deleteGroupQuestion",
                            success: function (data) {
                                if (data.code === 200) {
                                    grid2.reload();
                                } else {
                                    alert(data.message);
                                }
                            },
                            error: function (e) {
                                alert("请求异常。");
                            }
                        });
                    }
                }
            ],
            search: {
                rowEleNum: 1,
                items: [{
                    type: "text",
                    label: "问卷题内容",
                    name: "questionContent",
                    placeholder: "问卷题内容"
                }]
            }
        };
        grid2 = ele.find("#to").topieGrid(option2);
    };
    App.surveyGroup.initEvents = function () {
        var grid;
        var options = {
            url: App.href + "/api/info/surveyGroup/page",
            beforeSend: function (request) {
                request.setRequestHeader("X-Auth-Token", App.token);
            },
            pageNum: 1,
            pageSize: 15,
            idFiled: "groupId",
            showCheckbox: true,
            checkboxWidth: "3%",
            showIndexNum: true,
            indexNumWidth: "7%",
            pageSelect: [2, 15, 30, 50],
            columns: App.surveyGroup.columns,
            actionColumnText: "操作",
            actionColumnWidth: "25%",
            actionColumns: [
                {
                    textHandle: function (index, stData) {
                        if (stData.isOnline) {
                            return "下线";
                        } else {
                            return "上线";
                        }
                    },
                    clsHandle: function (index, stData) {
                        if (stData.isOnline) {
                            return "btn-danger btn-sm";
                        } else {
                            return "btn-primary btn-sm";
                        }
                    },
                    handle: function (index, stData) {
                        bootbox.confirm("确定该操作吗?", function (result) {
                            if (result) {
                                var requestUrl = App.href + "/api/info/surveyGroup/updateOnline";
                                if (stData.isOnline) {
                                    requestUrl = App.href + "/api/info/surveyGroup/updateOffline";
                                }
                                $.ajax({
                                    type: "GET",
                                    beforeSend: function (request) {
                                        request.setRequestHeader("X-Auth-Token", App.token);
                                    },
                                    dataType: "json",
                                    data: {
                                        typeId: $("#typeId").val(),
                                        studentId: stData.id,
                                        teacherId: data.id
                                    },
                                    url: requestUrl,
                                    success: function (result) {
                                        if (result.code === 200) {
                                            grid.reload();
                                        } else {
                                            alert(result.message);
                                        }
                                    },
                                    error: function (e) {
                                        alert("请求异常。");
                                    }
                                });
                            }
                        });
                    }
                }, {
                    text: "编辑",
                    cls: "btn-primary btn-sm",
                    handle: function (index, data) {
                        var modal = $.topieModal({
                            id: "surveyGroupForm",
                            title: "编辑问卷组",
                            destroy: true
                        });
                        modal.show();
                        var formOpts = {
                            id: "surveyGroup_form",
                            name: "surveyGroup_form",
                            method: "POST",
                            action: App.href + "/api/info/surveyGroup/update",
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
                            items: App.surveyGroup.formItems
                        };
                        var form = modal.$body.topieForm(formOpts);
                        form.loadRemote(App.href + "/api/info/surveyGroup/load/" + data.groupId);
                    }
                }, {
                    text: "选择题目",
                    cls: "btn-primary btn-sm",
                    handle: function (index, data) {
                        var modal = $.topieModal({
                            id: "selectQuestion",
                            title: "选择题目",
                            destroy: true,
                            width: "90%"
                        });
                        modal.show();
                        var div = $(App.surveyGroup.selectQuestionTmpl);
                        modal.$body.append(div);
                        initSelectQuestion(div, data.groupId);
                    }
                }, {
                    text: "删除",
                    cls: "btn-danger btn-sm",
                    handle: function (index, data) {
                        var requestUrl = App.href + "/api/info/surveyGroup/delete";
                        $.ajax({
                            type: "POST",
                            beforeSend: function (request) {
                                request.setRequestHeader("X-Auth-Token", App.token);
                            },
                            dataType: "json",
                            data: {
                                "id": data.groupId
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
                }
            ],
            tools: [
                {
                    text: " 添 加",
                    cls: "btn btn-primary",
                    icon: "fa fa-cubes",
                    handle: function (grid) {
                        var modal = $.topieModal({
                            id: "surveyGroup_modal",
                            title: "添加问卷组",
                            destroy: true
                        }).show();
                        var formOpts = {
                            id: "add_surveyGroup_form",
                            name: "add_surveyGroup_form",
                            method: "POST",
                            action: App.href + "/api/info/surveyGroup/insert",
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
                            items: App.surveyGroup.formItems
                        };
                        var form = modal.$body.topieForm(formOpts);
                    }
                }
            ],
            search: {
                rowEleNum: 1,
                items: [{
                    type: "text",
                    label: "问卷组名称",
                    name: "groupName",
                    placeholder: "问卷组名称"
                }]
            }
        };
        grid = window.App.content.find("#surveyGroup_grid").topieGrid(options);
    }
})(jQuery, window, document);
