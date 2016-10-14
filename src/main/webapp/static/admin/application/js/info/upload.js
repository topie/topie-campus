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
            var html = '<form class="form-horizontal" action="">'
                + '<fieldset>'
                + '<legend>上传用户信息(excel文件)</legend>'
                + '<div class="form-group">'
                + '<label class="col-md-2 control-label">选择类型</label>'
                + '<div class="col-md-5">'
                + '<div class="input-group">'
                + '<select class="btn btn-default form-control" name="excelType">'
                + '<option>请选择上传类型</option>'
                + '<option value=1>学生基本信息表</option>'
                + '<option value=2>教师基本信息表</option>'
                + '<option value=3>学生成绩信息表</option>'
                + '<option value=4>学生课程信息表</option>'
                + '<option value=5>等级考试信息表</option>'
                + '<option value=6>学生选课信息表</option>'
                + '<option value=7>学生就业信息表</option>'
                + '<option value=8>教师学生关联信息表</option>'
                + '</select>'
                + '</div>'
                + '</div>'
                + '</div>'
                + '<div style="display: none" id="typeDiv" class="form-group">'
                + '<label class="col-md-2 control-label">教师类型</label>'
                + '<div class="col-md-5">'
                + '<div class="input-group">'
                + '<select class="btn btn-default form-control" name="typeId">'
                + '</select>'
                + '</div>'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label class="col-md-2 control-label">选择文件</label>'
                + '<div class="col-md-8">'
                + '<div class="input-group">'
                + '<input type="file" id="file" class="btn btn-default form-control" name="file"/>'
                + '<span style="cursor: pointer" target="file" role=upload class="input-group-addon">上传</span>'
                + '</div>'
                + '<p class="help-block">'
                + '没有模板? <a href="javascript:void(0);" style="cursor: pointer" role="template">点击下载模板</a>'
                + '</p>'
                + '</div>'
                + '</div>'
                + '</fieldset>'
                + '</form>';
            window.App.content.empty();
            window.App.title(title);
            window.App.content.append(html);
            App.upload.initEvents();
        }
    }
    App.upload.initEvents = function () {
        var optionTmpl = '<option value="${value_}" ${selected}>${text_}</option>';
        $.ajax({
                type: "GET",
                dataType: "json",
                url: App.href + "/api/info/teacherType/options?topie_token=" + App.token,
                success: function (data) {
                    data = data.data;
                    $.each(
                        data,
                        function (index, option) {
                            $.tmpl(
                                optionTmpl,
                                {
                                    "value_": (option.value == undefined ? ""
                                        : option.value),
                                    "text_": (option.text == undefined ? ""
                                        : option.text)
                                }
                            ).appendTo(window.App.content.find("select[name=typeId]"));
                        }
                    );
                },
                error: function (err) {
                    console.error("请求错误");
                }
            }
        );
        window.App.content.find("select[name=excelType]").change(function () {
            if ($(this).val() == 8) {
                $("#typeDiv").show();
            } else {
                $("#typeDiv").hide();
            }
        });
        window.App.content.find("a[role=template]").click(function () {
            window.App.download(App.href + "/api/info/upload/downloadTemplate");
        });
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
                    url: that.uploadUrl + "?topie_token=" + App.token + "&typeId=" + $("select[name=typeId]").val() + "&excelType=" + $("select[name=excelType]").val(),
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
