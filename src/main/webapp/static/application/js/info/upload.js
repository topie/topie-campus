/**
 * Created by chenguojun on 8/10/16.
 */
;
(function ($, window, document, undefined) {
    var uploadMapping = {
        "/api/protected": "upload"
    }
    window.App.requestMapping = $.extend({}, window.App.requestMapping, uploadMapping);
    window.App.upload = function () {
        window.App.body.empty();
        window.App.title("上传");
        var btn = $("<button type='button'>下载模板</button>");
        btn.click(function () {
            window.App.download("/api/info/template/download");
        });
        window.App.body.append(btn);
    }
})(jQuery, window, document);