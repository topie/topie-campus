;
(function ($, window, document, undefined) {
    var mapping = {
        "/api/info/message/page": "message"
    };
    App.requestMapping = $.extend({}, window.App.requestMapping, mapping);
    App.message = {
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body" id="message_grid"></div>');
            window.App.content.append(content);
            App.message.initEvents();
        }
    };

    App.message.columns = [
        {
            title: "留言内容",
            field: "messageContentShort",
            format: function (i, d) {
                return '<a href="javascript:void(0);" data-toggle="tooltip" title="' + d.messageContent + '">' + d.messageContentShort + '</a>';
            }
        }, {
            title: "留言给",
            field: "messageToUserName"
        }, {
            title: "留言者",
            field: "messageFromUserName"
        }, {
            title: "留言时间",
            field: "messageDateTime"
        }, {
            title: "回复内容",
            field: "replyContentShort",
            format: function (i, d) {
                return '<a href="javascript:void(0);" data-toggle="tooltip" title="' + d.replyContent + '">' + d.replyContentShort + '</a>';
            }
        }, {
            title: "回复者",
            field: "replyUserName"
        }, {
            title: "回复时间",
            field: "replyDateTime"
        }];
    App.message.initEvents = function () {
        var grid;
        var options = {
            url: App.href + "/api/info/message/page",
            beforeSend: function (request) {
                request.setRequestHeader("X-Auth-Token", App.token);
            },
            pageNum: 1,
            pageSize: 15,
            idFiled: "id",
            showCheckbox: false,
            showIndexNum: true,
            indexNumWidth: "10%",
            pageSelect: [2, 15, 30, 50],
            columns: App.message.columns,
            search: {
                rowEleNum: 2,
                items: [
                    {
                        type: "datepicker",
                        label: "留言时间区间",
                        name: "messagePeriod",
                        id: "messagePeriod",
                        placeholder: "留言时间区间"
                    }
                ],
                buttons: [
                    {
                        text: "导出",
                        cls: "btn btn-info",
                        icon: "fa fa-cubes",
                        handle: function () {
                            var url = App.href + "/api/info/message/export?topie_token=" + App.token + "&" + grid.$searchForm
                                    .serialize();
                            window.open(url);
                        }
                    }
                ]
            }
        };
        grid = window.App.content.find("#message_grid").topieGrid(options);
    }
})(jQuery, window, document);
