/**
 * Created by chenguojun on 8/10/16.
 */
;
(function ($, window, document, undefined) {
    var that = this;
    that.uploadUrl = App.href + "/api/info/upload/uploadExcel";
    var uploadMapping = {
        "/api/info/upload/page": "upload"
    }
    App.requestMapping = $.extend({}, window.App.requestMapping, uploadMapping);
    App.upload = {
        page: function (title) {
            $.ajax(
                {
                    type: 'GET',
                    url: App.href + "/api/info/upload/page",
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
                                window.App.download(App.href + "/api/info/upload/downloadTemplate");
                            });
                            App.upload.initEvents();
                        } else {
                            alert(result.message);
                        }
                    }
                }
            );
        }
    }
    App.upload.initEvents = function () {
        var uploadFile = function (fileId) {
            if ($("#" + fileId).val() == "") {
                alert("请选择上传的文件");
                return;
            }
            $.blockUI({
                css: {
                    border: 'none',
                    padding: '15px',
                    backgroundColor: '#000',
                    '-webkit-border-radius': '10px',
                    '-moz-border-radius': '10px',
                    opacity: .5,
                    color: '#fff'
                }
            });
            $.ajaxFileUpload(
                {
                    url: that.uploadUrl + "?topie_token=" + App.token+"&excelType="+$("select[name=excelType]").val(),
                    type: "POST",
                    secureuri: false,
                    fileElementId: fileId,
                    dataType: "json",
                    success: function (json, status) {
                        console.info(json);
                        $.unblockUI();
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
