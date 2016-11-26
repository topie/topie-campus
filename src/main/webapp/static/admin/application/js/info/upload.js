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
            var html = '<form class="form-horizontal" id="clearForm" action="">'
            	+ '<legend>清除表数据</legend>'
            	+ '<div class="form-group">'
            	+ '<div class="col-md-3">'
            	+ '<select class="btn btn-default" name="clearType">'
                + '<option>请选择需要清除的表</option>'
                + '<option value=1>学生成绩信息表</option>'
                + '<option value=2>学生课程信息表</option>'
                + '<option value=3>等级考试信息表</option>'
                + '<option value=4>学生选课信息表</option>'
                + '<option value=5>教师学生关联信息表</option>'
                + '</select>'
                + '</div>'
                + '<div class="col-md-3" id="studyYear">'
                + '<label class="col-md-4 control-label">学年</label>'
                + '<select class="btn btn-default" name="studyYear">'
                + '</select>'
                + '</div>'
                + '<div class="col-md-3" id="studyYearNum" >'
                + '<label class="col-md-4 control-label">学期</label>'
                + '<select class="btn btn-default" name="studyYearNum">'
                + '<option value=1>1</option>'
                + '<option value=2>2</option>'
                + '</select>'
                + '</div>'
                + '<button type="button" class="btn" id="clearBtn">清除</button>'
                + '</div>'
            	+ '</form>'
            	+ '<form class="form-horizontal" action="">'
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
                + '没有模板? <a href="../import.zip" style="cursor: pointer" role="template">点击下载模板</a>'
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
                url: App.href+"/api/dict/1?topie_token=" + App.token,
                success: function (data) {
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
                            ).appendTo(window.App.content.find("select[name=studyYear]"));
                        }
                    );
                },
                error: function (err) {
                    console.error("请求错误");
                }
            }
        );
        
        var optionTmpl = '<option value="${value_}" ${selected}>${text_}</option>';
        $.ajax({
                type: "GET",
                dataType: "json",
                url: App.href + "/api/info/teacherType/options?topie_token=" + App.token,
                success: function (data) {
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
        
        window.App.content.find("select[name=clearType]").change(function () {
            if ($(this).val() != 5) {
                $("#studyYearNum").show();
                $("#studyYear").show();
            } else {
                $("#studyYearNum").hide();
                $("#studyYear").hide();
            }
        });
        
        $("#clearBtn").click(function(){
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
        	  $.ajax({
        		  url: App.href + "/api/info/basic/deleteData?topie_token=" + App.token,
        		  data:$("#clearForm").serialize(),
        	      success:function(data)
        	      {
        	    	  $.unblockUI();
        	    	  alert(data.message);
        	      }
        		  
        	  })
        })
        
        window.App.content.find("select[name=excelType]").change(function () {
            if ($(this).val() == 8) {
                $("#typeDiv").show();
            } else {
                $("#typeDiv").hide();
            }
        });
        /*window.App.content.find("a[role=template]").click(function () {
            window.App.download(App.href + "/api/info/upload/downloadTemplate");
        });*/
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
            var url = that.uploadUrl + "?topie_token=" + App.token + "&typeId=" + $("select[name=typeId]").val() + "&excelType=" + $("select[name=excelType]").val();
           if($("select[name=typeId]").val()==null)
        	   {
        	   url=that.uploadUrl + "?topie_token=" + App.token + "&excelType=" + $("select[name=excelType]").val();
        	   }
            $.ajaxFileUpload(
                {
                    url: url,
                    type: "POST",
                    secureuri: false,
                    fileElementId: fileId,
                    dataType: "json",
                    success: function (json, status) {
                        console.info(json);
                        $.unblockUI();
                        alert(json.message);
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
