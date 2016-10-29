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
        $("#timeTable").load("./tmpl/time-table.html?t=" + new Date().getTime(), function () {
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
    App.frontStudent.initEvents = function () {
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
            actionColumnText: "操作",//操作列文本
            actionColumnWidth: "20%",
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
                                                scoreGrid = $("#score").topieGrid(scoreOpts);
                                                that.find("a[role=score]").on("click", function () {
                                                    scoreGrid.reload();
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
                }
            ],
            search: {
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
