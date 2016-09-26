;
(function ($, window, document, undefined) {
    var mapping = {
        "/api/front/message/send": "messageSend"
    };
    App.requestMapping = $.extend({}, window.App.requestMapping, mapping);
    App.messageSend = {
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body" style="display: none" id="message"></div>');
            window.App.content.append(content);
            App.messageSend.initEvents();
        }
    };

    App.messageSend.initEvents = function () {
        $("#message").load("./tmpl/message.html?t=" + new Date().getTime(),
            function () {
                var that = $(this);
                var source = $(this).html();
                that.empty();
                $.ajax(
                    {
                        type: 'GET',
                        url: App.href + "/api/front/message/send",
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
                            } else {
                                alert(result.message);
                            }
                        }
                    }
                );
            }
        );
    }
})(jQuery, window, document);
