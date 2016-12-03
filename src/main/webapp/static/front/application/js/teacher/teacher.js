;
(function ($, window, document, undefined) {
    var mapping = {
        "/api/front/teacher/page": "frontTeacher"
    };
    App.requestMapping = $.extend({}, window.App.requestMapping, mapping);
    App.frontTeacher = {
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body" id="teacher_grid"></div>');
            window.App.content.append(content);
            App.frontTeacher.initEvents();
        }
    };

    App.frontTeacher.initEvents = function () {
        var grid;
        var teacherOpt = {
            url: App.href + "/api/front/teacher/page?isBind=1",
            beforeSend: function (request) {
                request.setRequestHeader("X-Auth-Token", App.token);
            },
            autoLoad: true,
            pageNum: 1,//当前页码
            pageSize: 15,//每页显示条数
            idFiled: "id",//id域指定
            showCheckbox: true,//是否显示checkbox
            checkboxWidth: "3%",
            showIndexNum: false,
            indexNumWidth: "5%",
            pageSelect: [10, 15, 30, 50],
            columns: [
                {
                    title: "教师名称",
                    field: "name"
                }, {
                    title: "职工号",
                    field: "employeeNo"
                }, {
                    title: "联系电话",
                    field: "contactPhone"
                },
                {
                    title: "email",
                    field: "email"
                },
                {
                    title: "类型",
                    field: "typeName",
                    width: "10%"
                }
                /* ,{
                 title: "是否我的教师",
                 field: "isBind",
                 format: function (num, grid) {
                 if (grid.isBind == 1) {
                 return "是";
                 } else {
                 return "否"
                 }
                 }
                 }*/],
            actionColumnText: "操作",//操作列文本
            actionColumnWidth: "20%",
            actionColumns: [
                /*{
                 textHandle: function (index, stData) {
                 if (stData.isBind == 1) {
                 return "取消选择";
                 } else {
                 return "选择";
                 }
                 },
                 clsHandle: function (index, stData) {
                 if (stData.isBind == 1) {
                 return "btn-danger btn-sm";
                 } else {
                 return "btn-primary btn-sm";
                 }
                 },
                 handle: function (index, stData) {
                 bootbox.confirm("确定该操作吗?", function (result) {
                 if (result) {
                 var requestUrl = App.href + "/api/front/teacher/bindTeacher";
                 if (stData.isBind == 1) {
                 requestUrl = App.href + "/api/front/teacher/unbindTeacher";
                 }
                 $.ajax({
                 type: "GET",
                 beforeSend: function (request) {
                 request.setRequestHeader("X-Auth-Token", App.token);
                 },
                 data: {
                 teacherId: stData.id
                 },
                 dataType: "json",
                 url: requestUrl,
                 success: function (result) {
                 if (result.code === 200) {
                 grid.reload();
                 } else {
                 alert(result.message);
                 }
                 },
                 error: function (e) {
                 alert("请求异常。");
                 }
                 });
                 }
                 });
                 }
                 },*/
                {
                    cls: "btn-info btn-sm",
                    text: "留言",
                    handle: function (index, stData) {
                        var modal = $.topieModal({
                            id: "messageModal",
                            title: "留言",
                            destroy: true
                        });
                        modal.$body.load("./tmpl/message-post-teacher.html?t=" + new Date().getTime(),
                            function () {
                                var that = $(this);
                                that.find("#post-message-btn").on("click", function () {
                                    $.ajax(
                                        {
                                            type: 'POST',
                                            url: App.href + "/api/front/teacher/postMessage?toUserId=" + stData.userId,
                                            dataType: "json",
                                            data: $("#message-form").serialize(),
                                            beforeSend: function (request) {
                                                request.setRequestHeader("X-Auth-Token", App.token);
                                            },
                                            success: function (result) {
                                                if (result.code === 200) {
                                                    bootbox.alert("留言成功~请到发出的留言查看相关动态！", function () {
                                                        modal.hide();
                                                        window.location.href = "?u=/api/front/message/send";
                                                    });
                                                } else {
                                                    alert(result.message);
                                                }
                                            }
                                        }
                                    );
                                });
                            }
                        );
                        modal.show();
                    }
                },
                {
                    /*visible: function (index, stData) {
                     if (stData.isBind == 1) {
                     return true;
                     } else {
                     return false;
                     }
                     },*/
                    cls: "btn-primary btn-sm",
                    text: "查看",
                    handle: function (index, stData) {
                        var modal = $.topieModal({
                            id: "viewModal",
                            title: "查看",
                            destroy: true
                        });
                        $.ajax(
                            {
                                type: 'GET',
                                url: App.href + "/api/front/teacher/profile",
                                contentType: "application/json",
                                dataType: "json",
                                data: {
                                    teacherId: stData.id
                                },
                                beforeSend: function (request) {
                                    request.setRequestHeader("X-Auth-Token", App.token);
                                },
                                success: function (result) {
                                    if (result.code === 200) {
                                        var data = result.data;
                                        if (data == null) {
                                            return;
                                        }
                                        modal.$body.load("./tmpl/view-teacher.html?t=" + new Date().getTime(),
                                            function () {
                                                var that = $(this);
                                                var source = $(this).html();
                                                that.empty();
                                                var render = template.compile(source);
                                                var html = render(data);
                                                that.html(html).show();
                                            }
                                        );
                                        modal.show();
                                    } else {
                                        alert(result.message);
                                    }
                                }
                            }
                        );
                    }
                }
            ]
            /*search: {
             rowEleNum: 2,
             //搜索栏元素
             items: [
             {
             type: "select",
             label: "教师类型",
             name: "typeId",
             itemsUrl: App.href + "/api/info/teacherType/options?topie_token=" + App.token
             }, {
             type: "text",
             label: "教师名称",
             name: "name",
             placeholder: "输入要搜索的教师名称"
             }, {
             type: "text",
             label: "学号",
             name: "teacherNo",
             placeholder: "输入要搜索的学号"
             }, {
             type: "text",
             label: "手机号",
             name: "contactPhone",
             placeholder: "输入要搜索的手机号"
             }, {
             type: "select",
             label: "是否绑定",
             name: "isBind",
             items: [
             {
             value: "",
             text: "全部"
             }, {
             value: 1,
             text: "是"
             }, {
             value: 0,
             text: "否"
             }
             ]
             }]
             }*/
        };
        grid = window.App.content.find("#teacher_grid").topieGrid(teacherOpt);
    }
})(jQuery, window, document);
