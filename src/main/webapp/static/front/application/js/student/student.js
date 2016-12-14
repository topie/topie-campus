;
(function ($, window, document, undefined) {
    var mapping = {
        "/api/front/student/page": "frontStudent"
    };
    App.requestMapping = $.extend({}, window.App.requestMapping, mapping);
    App.frontStudent = {
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body" id="student_grid"></div>');
            window.App.content.append(content);
            App.frontStudent.initEvents();
        }
    };

    var getColSpan = function (list, week, section) {
        var iteS = function (week_, section_) {
            var length = 1;
            $.each(list, function (i, d) {
                if (d['week'] == week_ && d['section'] == section_) {
                    length = d['sectionLength'];
                }
            });
            return length;
        };
        var iteC = function (week_, section_) {
            var content = "";
            $.each(list, function (i, d) {
                if (d['week'] == week_ && d['section'] == section_) {
                    content = d['content'];
                }
            });
            return content;
        };
        var map = {};
        for (var j = 1; j <= 13; j++) {
            var l = iteS(week, j);
            map[j] = l;
            if (l > 1) {
                j = j + l - 1;
            }
        }
        return {"span": map[section], "content": iteC(week, section)};
    };
    var getWeek = function (i) {
        switch (i) {
            case 1 :
                return "星期一";
                break;
            case 2 :
                return "星期二";
                break;
            case 3 :
                return "星期三";
                break;
            case 4 :
                return "星期四";
                break;
            case 5 :
                return "星期五";
                break;
            case 6 :
                return "星期六";
                break;
            case 7 :
                return "星期日";
                break;
        }
    };
    App.frontStudent.timeTable = function (studentNo) {
        $("#timeTable").empty();
        $("#timeTable").load(App.href + "/static/front/tmpl/time-table.html?t=" + new Date().getTime(), function () {
            var url = App.href + "/api/front/student/sTimeTable";
            var that = $(this);
            that.find("#searchBtn").off("click");
            that.find("#searchBtn").on("click", function () {
                if ($.trim(that.find("#studyYear").val()) == "") {
                    return;
                }
                if ($.trim(that.find("#studyYearNum").val()) == "") {
                    return;
                }
                $.ajax(
                    {
                        type: 'GET',
                        "url": url,
                        data: {
                            "studentNo": studentNo,
                            "studyYear": that.find("#studyYear").val(),
                            "studyYearNum": that.find("#studyYearNum").val()
                        },
                        dataType: "json",
                        beforeSend: function (request) {
                            request.setRequestHeader("X-Auth-Token", App.token);
                        },
                        success: function (result) {
                            if (result.code === 200) {
                                var data = result.data;
                                that.find("#score_grid_wrapper").empty();
                                var table = $('<table class="table table-bordered no-footer"></table>');
                                that.find("#score_grid_wrapper").append(table);
                                for (var j = 0; j <= 13; j++) {
                                    var tr = $('<tr></tr>');
                                    for (var i = 0; i <= 7; i++) {
                                        if (j == 0) {
                                            if (i == 0) {
                                                var td = $('<th style="background-color: #eff0f3;"></th>');
                                            } else {
                                                var week = getWeek(i);
                                                var td = $('<th style="background-color: #eff0f3;">' + week + '</th>');
                                            }
                                            tr.append(td);
                                        } else {
                                            if (i == 0) {
                                                var td = $('<td style="background-color: #eff0f3;">第' + j + '节</td>');
                                                tr.append(td);
                                            } else {
                                                var arr = getColSpan(data, i, j);
                                                var span = arr['span'];
                                                span = (span == undefined ? 0 : span);
                                                var content = arr['content'];
                                                if (span > 0) {
                                                    var style = "";
                                                    if ($.trim(content) != "") {
                                                        style = 'style="background-color: #d9edf7;"';
                                                    }
                                                    var td = $('<td ' + style + ' rowspan="' + span + '">' + content + '</td>');
                                                    tr.append(td);
                                                }
                                            }
                                        }
                                    }
                                    table.append(tr);
                                }

                            } else {
                                alert(result.message);
                            }
                        }
                    }
                );
            });
            $.ajax({
                url: App.href + "/api/dict/1?topie_token=" + App.token,
                type: "post",
                success: function (result) {
                    var opt = '<option value="">请选择</option>';
                    for (var i in result) {
                        opt = opt + '<option value="' + result[i].value + '">' + result[i].text + '</option>';
                    }
                    $("#studyYear").html(opt);
                    getStudyYear();
                    $("#searchBtn").click();
                }
            })
        });
    };
    App.frontStudent.teacherSurveyColumns = [
        {
            title: "问卷组名称",
            field: "groupName"
        }, {
            title: "开始时间",
            field: "start"
        }, {
            title: "结束时间",
            field: "end"
        }, {
            title: "状态",
            field: "onlineStatus",
            format: function (i, data) {
                if (data.onlineStatus == 0) {
                    return '<span class="label label-default">未开始</span>';
                } else if (data.onlineStatus == 1) {
                    return '<span class="label label-info">进行中</span>';
                } else {
                    return '<span class="label label-warning">已结束</span>';
                }
            }
        }
    ];
    App.frontStudent.initEvents = function () {

        $.ajax({
            url: App.href + "/api/info/teacherType/treeNodesByTeacherId?topie_token=" + App.token,
            success: function (result) {
                var role = "";
                for (var i in result) {
                    if (i == result.length - 1) {
                        role = role + result[i].name;
                    }
                    else {
                        role = role + result[i].name + ",";
                    }
                }
                $("#student_grid").before("<div style='text-align:center;font-size:24px;margin-bottom:40px;'>您的角色是<font color='red'>" + role + "</font>;您可以在搜索栏里查询您不同角色下的学生。</div>");
            }
        });


        var grid;
        var studentOpt = {
            url: App.href + "/api/front/student/page?isBind=1",
            beforeSend: function (request) {
                request.setRequestHeader("X-Auth-Token", App.token);
            },
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
                    title: "学号",
                    field: "studentNo",
                    sort: true
                },
                {
                    title: "学生名称",
                    field: "name"
                }, {
                    title: "联系电话",
                    field: "contactPhone"
                }, {
                    title: "专业",
                    field: "subject"
                }],
            tools: [
                {
                    text: "批量留言",
                    cls: "btn btn-primary",
                    icon: "fa fa-list",
                    handle: function (grid) {
                        var modal = $.topieModal({
                            id: "batch_message_modal",
                            title: "批量留言",
                            destroy: true
                        });
                        var form;
                        var formOpts = {
                            id: "batch_message_form",
                            name: "batch_message_form",
                            method: "POST",
                            action: App.href + "/api/front/student/postGroupMessage",
                            ajaxSubmit: true,
                            rowEleNum: 1,
                            beforeSend: function (request) {
                                request.setRequestHeader("X-Auth-Token", App.token);
                            },
                            ajaxSuccess: function () {
                                bootbox.alert("留言成功~请到发出的留言查看相关动态！");
                                modal.hide();
                            },
                            submitText: "提交",
                            showReset: true,
                            resetText: "重置",
                            isValidate: true,
                            buttons: [{
                                type: 'button',
                                text: '关闭',
                                handle: function () {
                                    modal.hide();
                                }
                            }],
                            buttonsAlign: "center",
                            items: [
                                {
                                    type: "select",
                                    label: "导师类型",
                                    name: "typeId",
                                    id: "typeId",
                                    async: false,
                                    itemsUrl: App.href + "/api/info/teacherType/options?topie_token=" + App.token,
                                    events: [{
                                        name: "change",
                                        action: function (e, ele) {
                                            var zTree = $.fn.zTree.getZTreeObj("tree_toUserIds");
                                            zTree.setting.async.url = App.href + "/api/front/student/treeNodes?topie_token=" + App.token + "&typeId=" + ele.val();
                                            zTree.reAsyncChildNodes(null, "refresh");
                                        }
                                    }]
                                }, {
                                    type: 'tree',//类型
                                    name: 'toUserIds',
                                    id: 'toUserIds',//id
                                    label: '学生',//左边label
                                    url: App.href + "/api/front/student/treeNodes?topie_token=" + App.token + "&typeId=" + $("#typeId").val(),
                                    expandAll: true,
                                    autoParam: ["id", "name", "pId"],
                                    chkStyle: "checkbox",
                                    rule: {
                                        required: true
                                    },
                                    message: {
                                        required: "请选择至少一个学生"
                                    }
                                }, {
                                    type: 'textarea',
                                    name: 'messageContent',
                                    id: 'messageContent',
                                    label: '留言内容',
                                    cls: 'input-large',
                                    rule: {
                                        required: true,
                                        maxlength: 140
                                    },
                                    message: {
                                        required: "留言内容，140字以内",
                                        maxlength: "字符在1-140之间"
                                    }
                                }
                            ]
                        };
                        form = modal.$body.topieForm(formOpts);
                        modal.show();
                    }
                }
            ],
            actionColumnText: "操作",//操作列文本
            actionColumnWidth: "40%",
            actionColumns: [
                {
                    cls: "btn-info btn-sm",
                    text: "留言",
                    handle: function (index, stData) {
                        var modal = $.topieModal({
                            id: "messageModal",
                            title: "留言",
                            destroy: true
                        });
                        modal.$body.load("./tmpl/message-post-student.html?t=" + new Date().getTime(),
                            function () {
                                var that = $(this);
                                that.find("#post-message-btn").on("click", function () {
                                    $.ajax(
                                        {
                                            type: 'POST',
                                            url: App.href + "/api/front/student/postMessage?toUserId=" + stData.userId,
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
                /*
                 {
                 cls: "btn-info btn-sm",
                 text: "评论(问卷)",
                 handle: function (index, stData) {
                 var modal = $.topieModal({
                 id: "commentModal",
                 title: "学生问卷列表",
                 destroy: true
                 });
                 var options = {
                 url: App.href + "/api/front/teacherSurvey/page?typeId=" + grid.$searchForm.find("#typeId").val(),
                 beforeSend: function (request) {
                 request.setRequestHeader("X-Auth-Token", App.token);
                 },
                 pageNum: 1,
                 pageSize: 15,
                 idFiled: "groupId",
                 showCheckbox: true,
                 checkboxWidth: "3%",
                 showIndexNum: true,
                 indexNumWidth: "7%",
                 pageSelect: [2, 15, 30, 50],
                 columns: App.frontStudent.teacherSurveyColumns,
                 actionColumnText: "操作",
                 actionColumnWidth: "25%",
                 actionColumns: [
                 {
                 visible: function (i, data) {
                 return data.onlineStatus == 1;
                 },
                 text: "参与",
                 cls: "btn-primary btn-sm",
                 handle: function (index, data) {
                 window.open(App.href + "/static/front/survey.html?u=" + data.groupId);
                 }
                 }
                 ],
                 search: {
                 rowEleNum: 1,
                 hide:true,
                 items: [{
                 type: "text",
                 label: "问卷名称",
                 name: "groupName",
                 placeholder: "问卷组名称"
                 }]
                 }
                 };
                 modal.$body.topieGrid(options);
                 modal.show();
                 }
                 },*/
                {
                    cls: "btn-info btn-sm",
                    text: "修改联系电话",
                    handle: function (index, stData) {
                        var modal = $.topieModal({
                            id: "mobileModal",
                            title: "修改联系电话",
                            destroy: true
                        });
                        var formOpts = {
                            id: "mobile_form",//表单id
                            name: "mobile_form",//表单名
                            method: "post",//表单method
                            action: "",//表单action
                            ajaxSubmit: false,//是否使用ajax提交表单
                            rowEleNum: 1,
                            showSubmit: false,
                            submitText: "修改",//保存按钮的文本
                            showReset: false,//是否显示重置按钮
                            resetText: "重置",//重置按钮文本
                            isValidate: true,//开启验证
                            buttonsAlign: "center",
                            buttons: [
                                {
                                    type: 'button',
                                    cls: 'btn-primary',
                                    text: '确认修改',
                                    handle: function () {
                                        var tel = $("#contactPhone").val();
                                        var reg = /^0?1[3|4|5|8][0-9]\d{8}$/;
                                        if (!reg.test(tel)) {
                                            alert("手机号格式有误~");
                                            return;
                                        }
                                        $.ajax(
                                            {
                                                type: 'POST',
                                                url: App.href + "/api/front/teacher/updateStp",
                                                dataType: "json",
                                                data: {
                                                    studentId: stData.id,
                                                    contactPhone: tel
                                                },
                                                beforeSend: function (request) {
                                                    request.setRequestHeader("X-Auth-Token", App.token);
                                                },
                                                success: function (result) {
                                                    if (result.code === 200) {
                                                        modal.hide();
                                                        grid.reload();
                                                    } else {
                                                        alert(result.message);
                                                    }
                                                }
                                            }
                                        );
                                    }
                                },
                                {
                                    type: 'button',
                                    cls: 'btn-default',
                                    text: '关闭',
                                    handle: function () {
                                        modal.hide();
                                    }
                                }
                            ],
                            //表单元素
                            items: [{
                                type: 'text',//类型
                                name: 'contactPhone',//name
                                id: 'contactPhone',//id
                                label: '联系电话',//左边label
                                cls: 'input-large',
                                rule: {
                                    required: true
                                },
                                message: {
                                    required: "请输入联系电话"
                                }
                            }]
                        };
                        var f = modal.$body.topieForm(formOpts);
                        f.setValue("contactPhone", stData.contactPhone);
                        modal.show();

                    }
                },
                {
                    visible: function (index, stData) {
                        return stData.isBind == 1;
                    },
                    cls: "btn-primary btn-sm",
                    text: "查看",
                    handle: function (index, stData) {
                        var modal = $.topieModal({
                            id: "messageModal",
                            title: "查看",
                            destroy: true
                        });
                        $.ajax(
                            {
                                type: 'GET',
                                url: App.href + "/api/front/student/profile",
                                contentType: "application/json",
                                dataType: "json",
                                data: {
                                    studentId: stData.id
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
                                        modal.$body.load("./tmpl/view-student.html?t=" + new Date().getTime(),
                                            function () {
                                                var that = $(this);
                                                var source = $(this).html();
                                                that.empty();
                                                var render = template.compile(source);
                                                var html = render(data);
                                                that.html(html).show();

                                                that.find("a[role=timeTable]").on("click", function () {
                                                    App.frontStudent.timeTable(stData.studentNo);
                                                });

                                                var scoreGrid;
                                                var scoreOpts = {
                                                    url: App.href + "/api/front/teacher/stuScore?studentNo=" + stData.studentNo,
                                                    beforeSend: function (request) {
                                                        request.setRequestHeader("X-Auth-Token", App.token);
                                                    },
                                                    pageNum: 1,//当前页码
                                                    pageSize: 15,//每页显示条数
                                                    idFiled: "id",//id域指定
                                                    showCheckbox: false,//是否显示checkbox
                                                    checkboxWidth: "3%",
                                                    showIndexNum: false,
                                                    indexNumWidth: "5%",
                                                    pageSelect: [2, 15, 30, 50],
                                                    simplePaging: true,
                                                    autoLoad: false,
                                                    columns: [
                                                        {
                                                            title: "学年",
                                                            field: "studyYear"
                                                        },
                                                        {
                                                            title: "学期",
                                                            field: "studyYearNum"
                                                        },
                                                        {
                                                            title: "学生名称",
                                                            field: "name"
                                                        }, {
                                                            title: "课程代码",
                                                            field: "courceCode"
                                                        }, {
                                                            title: "课程名称",
                                                            field: "courceName"
                                                        }, {
                                                            title: "课程性质",
                                                            field: "courceType"
                                                        },
                                                        {
                                                            title: "课程归属",
                                                            field: "courceAttr"
                                                        },
                                                        {
                                                            title: "学分",
                                                            field: "credit"
                                                        },
                                                        {
                                                            title: "绩点",
                                                            field: "scorePoint"
                                                        },
                                                        {
                                                            title: "成绩",
                                                            field: "score"
                                                        },
                                                        {
                                                            title: "辅修标记",
                                                            field: "minorFlag"
                                                        },
                                                        {
                                                            title: "补考成绩",
                                                            field: "secondScore"
                                                        },
                                                        {
                                                            title: "重修成绩",
                                                            field: "restudyScore"
                                                        },
                                                        {
                                                            title: "重修标记",
                                                            field: "minorFlag"
                                                        }
                                                    ],
                                                    actionColumnText: "操作",//操作列文本
                                                    actionColumnWidth: "20%",
                                                    search: {
                                                        rowEleNum: 2,
                                                        //搜索栏元素
                                                        items: [
                                                            {
                                                                type: "select",
                                                                label: "学年",
                                                                name: "studyYear",
                                                                items: [
                                                                    {
                                                                        text: "请选择",
                                                                        value: ""
                                                                    }
                                                                ],
                                                                itemsUrl: App.href + "/api/dict/1?topie_token=" + App.token
                                                            },
                                                            {
                                                                type: "select",
                                                                label: "学期",
                                                                name: "studyYearNum",
                                                                items: [
                                                                    {
                                                                        text: "请选择",
                                                                        value: ""
                                                                    },
                                                                    {
                                                                        text: "1",
                                                                        value: "1"
                                                                    },
                                                                    {
                                                                        text: "2",
                                                                        value: "2"
                                                                    }
                                                                ]
                                                            }
                                                        ]
                                                    }
                                                };
                                                scoreGrid = $("#score_div").topieGrid(scoreOpts);
                                                that.find("a[role=score]").on("click", function () {
                                                    initStudyYear();
                                                    scoreGrid.reload();
                                                    $.ajax({
                                                        url: App.href + "/api/front/student/staticScoreForTeacher?topie_token=" + App.token,
                                                        data: {
                                                            studentNo: stData.studentNo
                                                        },
                                                        success: function (result) {
                                                            var data = '<tr><td>平均分</td><td>' + (result.data.avgScore == null ? 0 : result.data.avgScore) + '分</td><td>平均学分绩点</td><td>' + (result.data.avgCredit == null ? 0 : parseFloat(result.data.avgCredit).toFixed(2)) + '</td>';
                                                            for (var i in result.data.scoreCourseType) {
                                                                data = data + '<td>' + result.data.scoreCourseType[i].courceType + '</td><td>' + result.data.scoreCourseType[i].totalCredit + '分</td>'
                                                            }
                                                            data = data + '</tr>'
                                                            that.find("#score_static").html(data);
                                                        }
                                                    });
                                                });

                                                var cetGrid;
                                                var cetScoreOpts = {
                                                    url: App.href + "/api/front/teacher/stuCetScore?studentNo=" + stData.studentNo,
                                                    beforeSend: function (request) {
                                                        request.setRequestHeader("X-Auth-Token", App.token);
                                                    },
                                                    pageNum: 1,//当前页码
                                                    pageSize: 15,//每页显示条数
                                                    idFiled: "id",//id域指定
                                                    showCheckbox: false,//是否显示checkbox
                                                    checkboxWidth: "3%",
                                                    showIndexNum: false,
                                                    indexNumWidth: "5%",
                                                    pageSelect: [2, 15, 30, 50],
                                                    simplePaging: true,
                                                    autoLoad: false,
                                                    columns: [
                                                        {
                                                            title: "学年",
                                                            field: "studyYear"
                                                        },
                                                        {
                                                            title: "学期",
                                                            field: "studyYearNum"
                                                        },
                                                        {
                                                            title: "等考名称",
                                                            field: "cetName"
                                                        }, {
                                                            title: "考试时间",
                                                            field: "cetTime"
                                                        }, {
                                                            title: "总成绩",
                                                            field: "cetScore"
                                                        }, {
                                                            title: "备注",
                                                            field: "comment"
                                                        },
                                                        {
                                                            title: "听力",
                                                            field: "listenScore"
                                                        },
                                                        {
                                                            title: "写作",
                                                            field: "writeScore"
                                                        },
                                                        {
                                                            title: "阅读",
                                                            field: "readScore"
                                                        },
                                                        {
                                                            title: "综合",
                                                            field: "compreScore"
                                                        }
                                                    ],
                                                    actionColumnText: "操作",//操作列文本
                                                    actionColumnWidth: "20%"
                                                };
                                                cetGrid = $("#cet").topieGrid(cetScoreOpts);
                                                that.find("a[role=cet]").on("click", function () {
                                                    cetGrid.reload();
                                                });

                                                var selectCourseGrid;
                                                var selectCourseOps = {
                                                    url: App.href + "/api/front/teacher/stuSelectCourse?studentNo=" + stData.studentNo,
                                                    beforeSend: function (request) {
                                                        request.setRequestHeader("X-Auth-Token", App.token);
                                                    },
                                                    pageNum: 1,//当前页码
                                                    pageSize: 15,//每页显示条数
                                                    idFiled: "id",//id域指定
                                                    showCheckbox: false,//是否显示checkbox
                                                    checkboxWidth: "3%",
                                                    showIndexNum: false,
                                                    indexNumWidth: "5%",
                                                    pageSelect: [2, 15, 30, 50],
                                                    simplePaging: true,
                                                    autoLoad: false,
                                                    columns: [
                                                        {
                                                            title: "学年",
                                                            field: "studyYear"
                                                        },
                                                        {
                                                            title: "学期",
                                                            field: "studyYearNum"
                                                        },
                                                        {
                                                            title: "选课课号",
                                                            field: "selectCourseNum"
                                                        }, {
                                                            title: "课程名称",
                                                            field: "courseName"
                                                        },
                                                        {
                                                            title: "课程性质",
                                                            field: "courseNature"
                                                        },
                                                        {
                                                            title: "课程归属",
                                                            field: "courseAffiliation"
                                                        },
                                                        {
                                                            title: "学分",
                                                            field: "courseCredit"
                                                        },
                                                        {
                                                            title: "任课老师",
                                                            field: "teacherName"
                                                        }, {
                                                            title: "重修标记",
                                                            field: "restudyFlag"
                                                        },
                                                        {
                                                            title: "辅修标记",
                                                            field: "isMinor"
                                                        }
                                                    ],
                                                    actionColumnText: "操作",//操作列文本
                                                    actionColumnWidth: "20%",
                                                    search: {
                                                        rowEleNum: 2,
                                                        //搜索栏元素
                                                        items: [
                                                            {
                                                                type: "select",
                                                                label: "学年",
                                                                name: "studyYear",
                                                                items: [
                                                                    {
                                                                        text: "请选择",
                                                                        value: ""
                                                                    }
                                                                ],
                                                                itemsUrl: App.href + "/api/dict/1?topie_token=" + App.token
                                                            },
                                                            {
                                                                type: "select",
                                                                label: "学期",
                                                                name: "studyYearNum",
                                                                items: [
                                                                    {
                                                                        text: "请选择",
                                                                        value: ""
                                                                    },
                                                                    {
                                                                        text: "1",
                                                                        value: "1"
                                                                    },
                                                                    {
                                                                        text: "2",
                                                                        value: "2"
                                                                    }
                                                                ]
                                                            }
                                                        ]
                                                    }
                                                };
                                                selectCourseGrid = $("#selectCourse").topieGrid(selectCourseOps);
                                                that.find("a[role=selectCourse]").on("click", function () {
                                                    //initStudyYear();
                                                    selectCourseGrid.$searchForm.find("select").val("");
                                                    selectCourseGrid.reload();
                                                });
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
                },
                {
                    cls: "btn-primary btn-sm",
                    text: "指导学生的记录",
                    handle: function (index, stData) {
                        var modalout = $.topieModal({
                            id: "messageModal",
                            title: stData.name + "的相关记录",
                            destroy: true
                        });
                        var modal;
                        var formOpts = {
                            id: "plan_form",
                            name: "plan_form",
                            method: "POST",
                            action: App.href + "/api/front/teacherRecord/insertOrUpdateRecord?studentId=" + stData.id,
                            ajaxSubmit: true,
                            beforeSend: function (request) {
                                request.setRequestHeader("X-Auth-Token", App.token);
                            },
                            ajaxSuccess: function () {
                                modal.hide();
                                grid.reload();
                            },
                            submitText: "保存",
                            showReset: true,
                            rowEleNum: 1,
                            resetText: "重置",
                            isValidate: true,
                            buttons: [{
                                type: 'button',
                                text: '关闭',
                                handle: function () {
                                    modal.hide();
                                }
                            }],
                            buttonsAlign: "center",
                            items: [
                                {
                                    type: 'hidden',
                                    name: 'id',
                                    id: 'id'
                                },
                                {
                                    id: "studyYear",
                                    type: "select",
                                    label: "学年",
                                    name: "studyYear",
                                    items: [
                                        {
                                            text: "请选择",
                                            value: ""
                                        }
                                    ],
                                    itemsUrl: App.href + "/api/dict/1?topie_token=" + App.token
                                },
                                {
                                    id: "studyYearNum",
                                    type: "select",
                                    label: "学期",
                                    name: "studyYearNum",
                                    items: [
                                        {
                                            text: "请选择",
                                            value: ""
                                        },
                                        {
                                            text: "1",
                                            value: "1"
                                        },
                                        {
                                            text: "2",
                                            value: "2"
                                        }
                                    ]
                                },
                                {
                                    id: "createTime",
                                    type: "datepicker",
                                    label: "时间",
                                    name: "createTime",
                                    config: {
                                        timePicker: true,
                                        singleDatePicker: true,
                                        locale: {
                                            format: 'YYYY-MM-DD HH:mm:ss'
                                        }
                                    },
                                    rule: {
                                        required: true
                                    },
                                    message: {
                                        required: "请选择时间"
                                    }
                                },
                                {
                                    type: 'textarea',
                                    name: 'content',
                                    id: 'content',
                                    label: '内容',
                                    cls: 'input-large',
                                    rule: {
                                        required: true,
                                        maxlength: 500
                                    },
                                    message: {
                                        required: "请输入内容，500字以内",
                                        maxlength: "字符在1-500之间"
                                    }
                                }
                            ]
                        };
                        var recordOpt = {
                            url: App.href + "/api/front/teacherRecord/recordList?studentId=" + stData.id,
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
                                    title: "学年",
                                    field: "studyYear"
                                }, {
                                    title: "学期",
                                    field: "studyYearNum"
                                },
                                {
                                    title: "日期",
                                    field: "createTime"
                                }, {
                                    title: "内容",
                                    field: "content"
                                }
                                /* ,{
                                 title: "是否我的导师",
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
                                {
                                    cls: "btn-info btn-sm",
                                    text: "编辑",
                                    handle: function (index, stData) {
                                        modal = $.topieModal({
                                            id: "planModal",
                                            title: "留言",
                                            destroy: true
                                        });
                                        var form = modal.$body.topieForm(formOpts);
                                        form.loadRemote(App.href + "/api/front/teacherRecord/loadRecord?id=" + stData.id);
                                        modal.show();
                                    }
                                },
                                {
                                    cls: "btn-primary btn-sm",
                                    text: "删除",
                                    handle: function (index, stData) {
                                        $.ajax(
                                            {
                                                type: 'GET',
                                                url: App.href + "/api/front/teacherRecord/deleteStudentRecord",
                                                contentType: "application/json",
                                                dataType: "json",
                                                data: {
                                                    id: stData.id
                                                },
                                                beforeSend: function (request) {
                                                    request.setRequestHeader("X-Auth-Token", App.token);
                                                },
                                                success: function (result) {
                                                    alert(result.message);
                                                    grid.reload();
                                                }
                                            }
                                        );
                                    }
                                }],
                            tools: [
                                {
                                    text: " 添 加",
                                    cls: "btn btn-primary",
                                    icon: "fa fa-cubes",
                                    handle: function () {
                                        modal = $.topieModal({
                                            id: "record_modal",
                                            title: "添加",
                                            destroy: true
                                        });
                                        var form = modal.$body.topieForm(formOpts, function () {
                                            getStudyYear();
                                        });
                                        modal.show();
                                    }
                                }
                            ]
                        };
                        grid = modalout.$body.topieGrid(recordOpt);
                        modalout.show();
                    }
                }
            ],
            search: {
                rowEleNum: 2,
                //hide:true,
                //搜索栏元素
                items: [
                    {
                        type: "select",
                        label: "导师类型",
                        name: "typeId",
                        id: "typeId",
                        itemsUrl: App.href + "/api/info/teacherType/options?topie_token=" + App.token
                    }, {
                        type: "text",
                        label: "学生名称",
                        name: "name",
                        placeholder: "输入要搜索的学生名称"
                    }, {
                        type: "text",
                        label: "学号",
                        name: "studentNo",
                        placeholder: "输入要搜索的学号"
                    }, {
                        type: "text",
                        label: "手机号",
                        name: "contactPhone",
                        placeholder: "输入要搜索的手机号"
                    }/*, {
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
                     }*/]
            }
        };
        grid = window.App.content.find("#student_grid").topieGrid(studentOpt);
    }
})(jQuery, window, document);
