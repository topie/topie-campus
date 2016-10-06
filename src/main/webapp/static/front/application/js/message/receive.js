;
(function ($, window, document, undefined) {
    var mapping = {
        "/api/front/message/receive": "messageReceive"
    };
    App.requestMapping = $.extend({}, window.App.requestMapping, mapping);
    App.messageReceive = {
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body" style="display: none" id="message"></div>');
            window.App.content.append(content);
            App.messageReceive.initEvents();
        }
    };
    App.messageReplyTmpl = '{{each list}}<li class="comment"><a class="pull-left" href="#">' +
        '<img class="avatar" src="../vendors/bootstrap-comment/img/default-avatar.jpg" alt="avatar"></a>' +
        '<div class="comment-body">' +
        '<div class="comment-heading">' +
        '<h4 class="user">{{$value.replyUserName}}</h4>' +
        '<h5 class="time">{{$value.replyDateTime}}</h5></div>' +
        '<p>{{$value.replyContent}}</p>' +
        '<a href="#" class="btn btn-sm btn-danger stat-item">删除</a></div></li>{{/each}}';

    App.messageReceive.initEvents = function () {
        $("#message").load("./tmpl/message.html?t=" + new Date().getTime(),
            function () {
                var that = $(this);
                var source = $(this).html();
                that.empty();
                $.ajax(
                    {
                        type: 'GET',
                        url: App.href + "/api/front/message/receive",
                        dataType: "json",
                        beforeSend: function (request) {
                            request.setRequestHeader("X-Auth-Token", App.token);
                        },
                        success: function (result) {
                            if (result.code === 200) {
                                var data = result.data;
                                var render = template.compile(source);
                                var html = render({
                                    "list": data.list
                                });
                                that.html(html).show();
                                App.messageReceive.replyInitEvents(that);
                            } else {
                                alert(result.message);
                            }
                        }
                    }
                );
            }
        );
    }

    App.messageReceive.replyInitEvents = function (ele) {
        var loadReply = function (messageId) {
            $.ajax(
                {
                    type: 'GET',
                    url: App.href + "/api/front/message/reply",
                    dataType: "json",
                    beforeSend: function (request) {
                        request.setRequestHeader("X-Auth-Token", App.token);
                    },
                    data: {
                        "messageId": messageId
                    },
                    success: function (result) {
                        if (result.code === 200) {
                            var data = result.data;
                            var render = template.compile(App.messageReplyTmpl);
                            var html = render({
                                "list": data.list
                            });
                            ele.find("#reply_" + messageId).empty();
                            ele.find("#reply_" + messageId).append(html);
                        } else {
                            alert(result.message);
                        }
                    }
                }
            );
        };
        ele.find("button[role=message-btn]").click(function () {
            var target = $(this).attr("reply-target");
            loadReply(target);
        });
        ele.find("a[role=reply-submit]").click(function () {
            $.ajax(
                {
                    type: 'POST',
                    url: App.href + "/api/front/message/postReply",
                    dataType: "json",
                    data: {
                        messageId: ele.find("#messageId").val(),
                        replyContent: ele.find("#replyContent").val(),
                    },
                    beforeSend: function (request) {
                        request.setRequestHeader("X-Auth-Token", App.token);
                    },
                    success: function (result) {
                        if (result.code === 200) {
                            ele.find("#replyContent").val("");
                            loadReply(ele.find("#messageId").val());
                        } else {
                            alert(result.message);
                        }
                    }
                }
            );
        });
    };
})(jQuery, window, document);
