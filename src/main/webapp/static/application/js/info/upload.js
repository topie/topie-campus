/**
 * Created by chenguojun on 8/10/16.
 */
;
(function ($, window, document, undefined) {
    var that = this;
    that.uploadUrl = window.App.href + "/api/info/excel/upload";
    var uploadMapping = {
        "/api/protected": "upload"
    }
    window.App.requestMapping = $.extend({}, window.App.requestMapping, uploadMapping);
    window.App.upload = {
        page: function () {

            window.App.body.empty();
            window.App.title("上传");
            var btn = $("<button type='button'>下载模板</button>");
            btn.click(function () {
                window.App.download("/api/info/template/download");
            });
            window.App.body.append(btn);
            initUpload();
        }
    }
    function initUpload() {
        var uploadInput = $('<input type="file" role="file" id="file" name="file"/>');
        window.App.body.append(uploadInput);
        var uploadFile = function () {
            if ($("#file").val() == "") {
                return;
            }
            $.ajaxFileUpload(
                {
                    url: that.uploadUrl+"?topie_token="+App.token,
                    type: "POST",
                    secureuri: false,
                    fileElementId: "file",
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
        uploadInput.on("change", function () {
            uploadFile();
        });
    }

})(jQuery, window, document);