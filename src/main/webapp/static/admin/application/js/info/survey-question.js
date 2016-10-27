;
(function ($, window, document, undefined) {
    var mapping = {
        "/api/info/surveyQuestion/page": "surveyQuestion"
    };
    App.requestMapping = $.extend({}, window.App.requestMapping, mapping);
    App.surveyQuestion = {
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body" id="surveyQuestion_grid"></div>');
            window.App.content.append(content);
            App.surveyQuestion.initEvents();
        }
    };
    App.surveyQuestion.formItems = [
        {
            type: 'hidden',
            name: 'questionId',
            id: 'questionId'
        }, {
            label: '题目类型',
            type: 'select',
            name: 'questionType',
            id: 'questionType',
            items: [
                {
                    text: "评分题",
                    value: 1,
                    select: true
                }, {
                    text: "问答题",
                    value: 2
                }
            ],
            rule: {
                required: true
            },
            message: {
                required: "请选择题目类型"
            }
        }, {
            type: 'textarea',
            name: 'questionContent',
            id: 'questionContent',
            label: '问卷题内容',
            cls: 'input-large',
            rule: {
                required: true
            },
            message: {
                required: "请输入问卷题内容"
            }
        }
    ];
    App.surveyQuestion.columns = [
        {
            title: "questionId",
            field: "questionId",
            width: "5%"
        },
        {
            title: "题目类型",
            field: "questionType",
            format: function (i, data) {
                if (data.questionType == 1) {
                    return '<span class="label label-info">评分题</span>';
                } else {
                    return '<span class="label label-success">问答题</span>';
                }
            }
        }, {
            title: "问卷题内容",
            field: "questionContent"
        }];
    App.surveyQuestion.initEvents = function () {
        var grid;
        var options = {
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
            indexNumWidth: "5%",
            pageSelect: [2, 15, 30, 50],
            columns: App.surveyQuestion.columns,
            actionColumnText: "操作",
            actionColumnWidth: "20%",
            actionColumns: [{
                text: "编辑",
                cls: "btn-primary btn-sm",
                handle: function (index, data) {
                    var modal = $.topieModal({
                        id: "surveyQuestionForm",
                        title: "编辑问卷题",
                        destroy: true
                    });
                    modal.show();
                    var formOpts = {
                        id: "surveyQuestion_form",
                        name: "surveyQuestion_form",
                        method: "POST",
                        action: App.href + "/api/info/surveyQuestion/update",
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
                        items: App.surveyQuestion.formItems
                    };
                    var form = modal.$body.topieForm(formOpts);
                    form.loadRemote(App.href + "/api/info/surveyQuestion/load/" + data.questionId);
                }
            }, {
                text: "删除",
                cls: "btn-danger btn-sm",
                handle: function (index, data) {
                    var requestUrl = App.href + "/api/info/surveyQuestion/delete";
                    $.ajax({
                        type: "GET",
                        beforeSend: function (request) {
                            request.setRequestHeader("X-Auth-Token", App.token);
                        },
                        dataType: "json",
                        data: {
                            "id": data.questionId
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
                            id: "surveyQuestion_modal",
                            title: "添加问卷题",
                            destroy: true
                        }).show();
                        var formOpts = {
                            id: "add_surveyQuestion_form",
                            name: "add_surveyQuestion_form",
                            method: "POST",
                            action: App.href + "/api/info/surveyQuestion/insert",
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
                            items: App.surveyQuestion.formItems
                        };
                        var form = modal.$body.topieForm(formOpts);
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
        grid = window.App.content.find("#surveyQuestion_grid").topieGrid(options);
    }
})(jQuery, window, document);
