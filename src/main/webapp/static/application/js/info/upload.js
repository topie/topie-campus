/**
 * Created by chenguojun on 8/10/16.
 */
;
(function ($, window, document, undefined) {
    var that = this;
    that.page = "/api/info/user/upload";
    that.pageUrl = window.App.href + that.page;
    that.uploadUrl = window.App.href + "/api/info/user/uploadExcel";
    var uploadMapping = {}
    uploadMapping[that.page] = "upload";
    window.App.requestMapping = $.extend({}, window.App.requestMapping, uploadMapping);
    window.App.upload = {
        page: function () {
            $.ajax(
                {
                    type: 'GET',
                    url: that.pageUrl,
                    contentType: "application/json",
                    dataType: "json",
                    beforeSend: function (request) {
                        request.setRequestHeader("X-Auth-Token", App.token);
                    },
                    success: function (result) {
                        if (result.code === 200) {
                            window.App.content.empty();
                            window.App.title("上传");
                            window.App.content.append(result.message);
                            window.App.content.find("a[role=template]").click(function () {
                                window.App.download("/api/info/user/downloadTemplate");
                            });
                            initUpload();
                        } else {
                            alert(result.msg);
                        }
                    }
                }
            );
        }
    }
    function initUpload() {
        var uploadFile = function (fileId) {
            if ($("#" + fileId).val() == "") {
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
