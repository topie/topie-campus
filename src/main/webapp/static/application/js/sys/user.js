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
                        } else {
                            alert(result.msg);
                        }
                    }
                }
            );
        }
    }
})(jQuery, window, document);
