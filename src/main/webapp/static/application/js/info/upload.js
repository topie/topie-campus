/**
 * Created by chenguojun on 8/10/16.
 */
;
(function ($, window, document, undefined) {
    var that = this;
    that.uploadUrl = window.App.href + "/api/info/user/uploadExcel";
    var uploadMapping = {
        "/api/info/user/upload": "upload"
    }
    App.requestMapping = $.extend({}, window.App.requestMapping, uploadMapping);
    App.upload = {
        page: function (title) {
            $.ajax(
                {
                    type: 'GET',
                    url: App.href + "/api/info/user/upload",
                    contentType: "application/json",
                    dataType: "json",
                    beforeSend: function (request) {
                        request.setRequestHeader("X-Auth-Token", App.token);
                    },
                    success: function (result) {
                        if (result.code === 200) {
                            window.App.content.empty();
                            window.App.title(title);
                            window.App.content.append(result.message);
                            window.App.content.find("a[role=template]").click(function () {
                                window.App.download("/api/info/user/downloadTemplate");
                            });
                            App.upload.initUpload();
                        } else {
                            alert(result.msg);
                        }
                    }
                }
            );
        }
    }
    App.upload.initUpload = function () {
        var uploadFile = function (fileId) {
            if ($("#" + fileId).val() == "") {
                alert("请选择上传的文件");
                return;
            }
            $.ajaxFileUpload(
                {
                    url: that.uploadUrl + "?topie_token=" + App.token,
                    type: "POST",
                    secureuri: false,
                    fileElementId: fileId,
                    dataType: "json",
                    success: function (json, status) {
                        console.info(json);
                    },
                    error: function (data, status, e) {
                        alert(e);
                    }
                }
            );

        };
        window.App.content.find("span[role=upload]").on("click", function () {
            uploadFile($(this).attr("target"));
        });
    }

})(jQuery, window, document);
