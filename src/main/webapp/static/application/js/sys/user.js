/**
 * Created by chenguojun on 8/10/16.
 */
;
(function ($, window, document, undefined) {
    var that = this;
    that.uploadUrl = window.App.href + "/api/info/user/uploadExcel";
    var uploadMapping = {
        "/api/sys/user/pageList": "sysUser"
    }
    window.App.requestMapping = $.extend({}, window.App.requestMapping, uploadMapping);
    window.App.sysUser = {
        page: function (title) {
            $('body').modalmanager('loading');
            $.ajax(
                {
                    type: 'GET',
                    url: App.href + "/api/sys/user/pageList",
                    contentType: "application/json",
                    dataType: "json",
                    beforeSend: function (request) {
                        request.setRequestHeader("X-Auth-Token", App.token);
                    },
                    success: function (result) {
                        if (result.code === 200) {
                            window.App.content.empty();
                            window.App.title(title);
                            window.App.content.append(result.data.html);
                            window.App.sysUser.initEvents();
                            $('body').modalmanager('removeLoading');
                        } else {
                            alert(result.msg);
                        }
                    }
                }
            );
        }
    }
    window.App.sysUser.initEvents = function () {
        window.App.content.find("a[role='row_edit']").each(function () {
            $(this).on("click", function () {
                var userId = $(this).attr("target");
                $.ajax(
                    {
                        type: 'GET',
                        url: App.href + "/api/sys/user/load/" + userId,
                        contentType: "application/json",
                        dataType: "json",
                        beforeSend: function (request) {
                            request.setRequestHeader("X-Auth-Token", App.token);
                        },
                        success: function (result) {
                            if (result.code === 200) {
                                $("#user_modal").remove();
                                window.App.content.append(result.data.html);
                                $("#user_modal").modal();
                            } else {
                                alert(result.msg);
                            }
                        }
                    }
                );
            });
        });
    }
})(jQuery, window, document);
