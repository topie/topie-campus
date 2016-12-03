;
(function ($, window, document, undefined) {
    var mapping = {
        "/api/leader/student/page": "leaderStudent"
    };
    App.requestMapping = $.extend({}, window.App.requestMapping, mapping);
    App.leaderStudent = {
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body" id="student_grid"></div>');
            window.App.content.append(content);
            App.leaderStudent.initEvents();
        }
    };
    App.leaderStudent.initEvents = function () {
        var grid;
        var options = {
            url: App.href + "/api/leader/student/page",
            beforeSend: function (request) {
                request.setRequestHeader("X-Auth-Token", App.token);
            },
            pageNum: 1,
            pageSize: 15,
            idFiled: "id",
            showCheckbox: true,
            checkboxWidth: "3%",
            showIndexNum: true,
            indexNumWidth: "5%",
            pageSelect: [2, 15, 30, 50],
            columns:  [{
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
            actionColumnText: "操作",
            actionColumnWidth: "15%",
            actionColumns: [
            {
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
                                    modal.$body.load("../front/tmpl/view-student.html?t=" + new Date().getTime(),
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
                                                //scoreGrid.$searchForm.find("select").val("");
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
                                                /*search: {
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
                                                }*/
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
            },
            {
            	cls: "btn-primary btn-sm",
                text: "该生的导师",
                handle: function (index, stData) {
                    var modal = $.topieModal({
                        id: "messageModal",
                        title: "查看",
                        destroy: true
                    });
            var grid;
            var teacherOpt = {
                url: App.href + "/api/front/teacher/leaderPage?isBind=1&studentId="+stData.id,
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
                    ],
                actionColumnText: "操作",//操作列文本
                actionColumnWidth: "20%",
                actionColumns: [
                        {
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
            };
            //grid = window.App.content.find("#teacher_grid").topieGrid(teacherOpt);
            modal.$body.topieGrid(teacherOpt);
            modal.show();
              }
            }
            ],
            search: {
                rowEleNum: 2,
                items: [
                        {
                    type: "text",
                    label: "学号",
                    name: "studentNo",
                    placeholder: "输入要搜索的学号"
                },{
                    type: "text",
                    label: "学生名",
                    name: "name",
                    placeholder: "输入要搜索的学生名"
                }]
            }
        };
        grid = window.App.content.find("#student_grid").topieGrid(options);
    }
})(jQuery, window, document);
