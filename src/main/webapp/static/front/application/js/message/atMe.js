;
(function ($, window, document, undefined) {
    var mapping = {
        "/api/front/atMe/page": "atME"
    };
    App.requestMapping = $.extend({}, window.App.requestMapping, mapping);
    App.atME = {
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body" id="atMe_grid"></div>');
            window.App.content.append(content);
            App.atME.initEvents();
        }
    };
    App.atME.columns = [
        {
            title: "类型",
            field: "contentType",
            format: function (i, data) {
                if (data.contentType == 1) {
                    return '<span class="label label-success">留言</span>';
                } else if (data.contentType == 2) {
                    return '<span class="label label-info">回复</span>';
                }
            }
        }, {
            title: "来源用户",
            field: "fromUserName"
        }, {
            title: "时间",
            field: "atTime"
        }, {
            title: "内容",
            field: "content",
            format: function (i, data) {
                if (data.content.length > 30) {
                    return data.content.substring(0, 30) + "...";
                } else {
                    return data.content;
                }
            }
        }
    ];

    App.atME.formMessageItems = [
        {
            type: 'hidden',
            name: 'id',
            id: 'id'
        }, {
            type: 'display',
            id: 'content',
            name: 'content',
            label: 'TA的留言内容'
        }, {
            type: 'display',
            id: 'atTime',
            name: 'atTime',
            label: '留言时间'
        }, {
            type: 'textarea',
            name: 'replyContent',
            id: 'replyContent',
            label: '你的回复'
        }
    ];
    App.atME.formReplyItems = [
        {
            type: 'hidden',
            name: 'id',
            id: 'id'
        }, {
            type: 'display',
            id: 'content',
            name: 'content',
            label: 'TA的的回复'
        }, {
            type: 'display',
            id: 'atTime',
            name: 'atTime',
            label: '回复时间'
        }, {
            type: 'textarea',
            name: 'replyContent',
            id: 'replyContent',
            label: '你的回复'
        }
    ];
    App.atME.initEvents = function () {
        var grid;
        var options = {
            url: App.href + "/api/front/atMe/page",
            beforeSend: function (request) {
                request.setRequestHeader("X-Auth-Token", App.token);
            },
            pageNum: 1,
            pageSize: 15,
            idFiled: "atMeId",
            contentType : "table",
            headField : "fromUserName",
            showCheck: false,
            checkboxWidth: "3%",
            showIndexNum: true,
            indexNumWidth: "5%",
            pageSelect: [2, 15, 30, 50],
            columns: App.atME.columns,
            actionColumnText: "操作",
            actionColumnWidth: "20%",
            actionColumns: [{
                text: "回复",
                cls: "btn-primary btn-sm",
                handle: function (index, data) {
                    var modal = $.topieModal({
                        id: "atMeForm",
                        title: "回复",
                        destroy: true
                    });
                    modal.show();
                    var formOpts = {
                        id: "atMe_form",
                        name: "atMe_form",
                        method: "POST",
                        action: App.href + "/api/front/atMe/postReply",
                        ajaxSubmit: true,
                        beforeSend: function (request) {
                            request.setRequestHeader("X-Auth-Token", App.token);
                        },
                        ajaxSuccess: function () {
                            modal.hide();
                            grid.reload();
                        },
                        showSubmit: true,
                        showReset: false,
                        isValidate: true,
                        buttons: [{
                            type: 'button',
                            text: '关闭',
                            handle: function () {
                                modal.hide();
                            }
                        }],
                        buttonsAlign: "center"
                    };
                    if (data.contentType == 1) {
                        formOpts.items = App.atME.formMessageItems;
                    } else {
                        formOpts.items = App.atME.formReplyItems;
                    }
                    var form = modal.$body.topieForm(formOpts);
                    form.loadRemote(App.href + "/api/front/atMe/load/" + data.id);
                }
            }],

            search: {
                rowEleNum: 2,
                items: [
                    {
                        type: "datepicker",
                        label: "时间段",
                        name: "period",
                        placeholder: "时间段",
                        config: {
                            "startDate": moment().subtract(1,'week').startOf('day'),
                            "endDate": moment().endOf('day')
                        }
                    }, {
                        type: "select",
                        label: "类型",
                        name: "type",
                        items: [
                            {
                                text: "请选择",
                                value: 0
                            },
                            {
                                text: "留言",
                                value: 1
                            },
                            {
                                text: "回复",
                                value: 2
                            }
                        ]
                    }
                ]
            }
        };
        grid = window.App.content.find("#atMe_grid").topieGrid(options);
    }
})(jQuery, window, document);
