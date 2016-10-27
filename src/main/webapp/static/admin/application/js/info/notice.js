;
(function ($, window, document, undefined) {
    var mapping = {
        "/api/info/notice/page": "infoNotice"
    };
    App.requestMapping = $.extend({}, window.App.requestMapping, mapping);
    App.infoNotice = {
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body" id="notice_grid"></div>');
            window.App.content.append(content);
            App.infoNotice.initEvents();
        }
    };
    App.infoNotice.formItems = [
        {
            type: 'hidden',
            name: 'noticeId',
            id: 'noticeId'
        }, {
            type: 'text',
            name: 'noticeTitle',
            id: 'noticeTitle',
            label: '通知公告标题',
            cls: 'input-large',
            rule: {
                required: true
            },
            message: {
                required: "请输入公告名"
            }
        }, {
            type: 'datepicker',
            name: 'noticePublishTime',
            id: 'noticePublishTime',
            label: '发布时间',
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
                required: "请选择发布时间"
            }
        }, {
            type: 'kindEditor',
            name: 'noticeContent',
            id: 'noticeContent',
            label: '公告内容',
            height: "300px",
            rule: {
                required: true
            },
            message: {
                required: "请输入公告内容"
            }
        }, {
            type: 'files',
            name: 'attachments',
            id: 'attachments',
            label: '附件',
            limit: 5,
            allowType: ".jpg,.bmp,.png,.gif,.xls,.xlsx,.doc,.docx,.ppt,.pptx"
        }
    ];
    App.infoNotice.columns = [{
        title: "noticeId",
        field: "noticeId",
        width: "5%"
    }, {
        title: "公告标题",
        field: "noticeTitle"
    }];
    App.infoNotice.initEvents = function () {
        var grid;
        var options = {
            url: App.href + "/api/info/notice/page",
            beforeSend: function (request) {
                request.setRequestHeader("X-Auth-Token", App.token);
            },
            pageNum: 1,
            pageSize: 15,
            idFiled: "noticeId",
            showCheckbox: true,
            checkboxWidth: "3%",
            showIndexNum: true,
            indexNumWidth: "5%",
            pageSelect: [2, 15, 30, 50],
            columns: App.infoNotice.columns,
            actionColumnText: "操作",
            actionColumnWidth: "20%",
            actionColumns: [{
                text: "编辑",
                cls: "btn-primary btn-sm",
                handle: function (index, data) {
                    var modal = $.topieModal({
                        id: "noticeForm",
                        title: "编辑公告",
                        destroy: true
                    });
                    modal.show();
                    var formOpts = {
                        id: "notice_form",
                        name: "notice_form",
                        method: "POST",
                        action: App.href + "/api/info/notice/update",
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
                        items: App.infoNotice.formItems
                    };
                    var form = modal.$body.topieForm(formOpts);
                    form.loadRemote(App.href + "/api/info/notice/load/" + data.noticeId);
                }
            }, {
                text: "删除",
                cls: "btn-danger btn-sm",
                handle: function (index, data) {
                    var requestUrl = App.href + "/api/info/notice/delete";
                    $.ajax({
                        type: "GET",
                        beforeSend: function (request) {
                            request.setRequestHeader("X-Auth-Token", App.token);
                        },
                        dataType: "json",
                        data: {
                            noticeId: data.id
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
                            id: "notice_modal",
                            title: "添加公告",
                            destroy: true
                        }).show();
                        var formOpts = {
                            id: "add_notice_form",
                            name: "add_notice_form",
                            method: "POST",
                            action: App.href + "/api/info/notice/insert",
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
                            items: App.infoNotice.formItems
                        };
                        var form = modal.$body.topieForm(formOpts);
                    }
                }
            ],
            search: {
                rowEleNum: 2,
                items: [{
                    type: "text",
                    label: "标题",
                    name: "noticeTitle",
                    placeholder: "输入要搜索的标题"
                }]
            }
        };
        grid = window.App.content.find("#notice_grid").topieGrid(options);
    }
})(jQuery, window, document);
