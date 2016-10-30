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
        '</div></li>{{/each}}';

    App.messageReceive.initEvents = function () {
        var doInit = function (ele, source, pageNum) {
            pageNum = pageNum == undefined ? 1 : pageNum;
            var sortType = ele.find("#message-sort li.active").attr("sort-type");
            sortType = sortType == undefined ? 0 : sortType;
            ele.empty();
            $.ajax(
                {
                    type: 'GET',
                    url: App.href + "/api/front/message/receive",
                    data: {
                        "pageNum": pageNum,
                        "pageSize": 15,
                        "sortType": sortType
                    },
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
                            ele.html(html).show();
                            App.messagePager(ele.find("#message-pager"), pageNum, data.pages);
                            ele.find('#message-pager li').not(".disabled").off("click");
                            ele.find('#message-pager li').not(".disabled").on(
                                "click", function () {
                                    var pN = $(this).attr("aria-controls");
                                    if (parseFloat(pN)) {
                                        doInit(ele, source, pN);
                                    }
                                }
                            );
                            ele.find("#message-sort > li").on("click", function () {
                                $(this).siblings("li").removeClass("active");
                                $(this).addClass("active");
                                doInit(ele, source);
                            });
                            ele.find("#message-sort > li[sort-type=\"" + sortType + "\"]").addClass("active");
                            App.messageReceive.replyInitEvents(ele);
                        } else {
                            alert(result.message);
                        }
                    }
                }
            );
        };
        $("#message").load("./tmpl/message.html?t=" + new Date().getTime(),
            function () {
                var that = $(this);
                var source = $(this).html();
                doInit(that, source);
            }
        );
    }

    App.messageReceive.replyInitEvents = function (ele) {
        var loadReply = function (messageId, pageNum) {
            pageNum = pageNum == undefined ? 1 : pageNum;
            $.ajax(
                {
                    type: 'GET',
                    url: App.href + "/api/front/message/reply",
                    dataType: "json",
                    beforeSend: function (request) {
                        request.setRequestHeader("X-Auth-Token", App.token);
                    },
                    data: {
                        "messageId": messageId,
                        "pageSize": 5
                    },
                    success: function (result) {
                        if (result.code === 200) {
                            var data = result.data;
                            var render = template.compile(App.messageReplyTmpl);
                            var html = render({
                                "list": data.list
                            });
                            ele.find("#reply_" + messageId).empty();
                            ele.find("#reply-pager_" + messageId).empty();
                            ele.find("#reply_" + messageId).append(html);
                            App.messagePager(ele.find("#reply-pager_" + messageId), pageNum, data.pages);
                            ele.find("#reply-pager_" + messageId + " li").not(".disabled").off("click");
                            ele.find("#reply-pager_" + messageId + " li").not(".disabled").on(
                                "click", function () {
                                    var pN = $(this).attr("aria-controls");
                                    if (parseFloat(pN)) {
                                        loadReply(messageId, pN);
                                    }
                                }
                            );
                        } else {
                            alert(result.message);
                        }
                    }
                }
            );
        };
        ele.find("a[role=message-btn]").click(function () {
            var target = $(this).attr("reply-target");
            if (!$("#div_message_" + target).is(':visible')) {
                loadReply(target);
            }
            $("#div_message_" + target).toggle();
        });
        ele.find("a[role=reply-submit]").click(function () {
            var that = $(this);
            var rep = that.parent().parent().find("#replyContent").val();
            if ($.trim(rep) == "") {
                return;
            }
            $.ajax(
                {
                    type: 'POST',
                    url: App.href + "/api/front/message/postReply",
                    dataType: "json",
                    data: {
                        messageId: that.parent().parent().find("#messageId").val(),
                        replyContent: rep
                    },
                    beforeSend: function (request) {
                        request.setRequestHeader("X-Auth-Token", App.token);
                    },
                    success: function (result) {
                        if (result.code === 200) {
                            that.parent().parent().find("#replyContent").val("");
                            var count = $("#reply_count_" + that.parent().parent().find("#messageId").val()).text();
                            if ($.trim(count) == "") {
                                count = 0;
                            } else {
                                count = parseInt(count);
                            }
                            $("#reply_count_" + that.parent().parent().find("#messageId").val()).text(++count);
                            loadReply(that.parent().parent().find("#messageId").val());
                        } else {
                            alert(result.message);
                        }
                    }
                }
            );
        });
    };
})(jQuery, window, document);
