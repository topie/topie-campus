;
(function ($, window, document, undefined) {
    var mapping = {
        "/api/info/teacher/leaderPage": "infoTeacher"
    };
    App.requestMapping = $.extend({}, window.App.requestMapping, mapping);
    App.infoTeacher = {
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body" id="teacher_grid"></div>');
            window.App.content.append(content);
            App.infoTeacher.initEvents();
        }
    };
    App.infoTeacher.formItems = [
        {
            type: 'hidden',
            name: 'id',
            id: 'id'
        }, {
            type: 'image',
            id: 'avatar',
            name: 'avatar',
            span: 2,
            label: '头像',
            isAjaxUpload: true,
            onSuccess: function (data) {
                $("#avatar").attr("value", data.attachmentUrl);
            },
            deleteHandle: function () {
                $("#avatar").attr("value", "");
            }
        }, {
            type: 'text',
            name: 'name',
            id: 'name',
            label: '教师名',
            cls: 'input-large',
            rule: {
                required: true
            },
            message: {
                required: "请输入教师名"
            }
        }, {
            type: 'text',
            name: 'shortName',
            id: 'shortName',
            label: '姓名简拼',
            cls: 'input-large'
        }, {
            type: 'text',
            name: 'password',
            id: 'password',
            label: '密码明文',
            cls: 'input-large'
        }, {
            type: 'text',
            name: 'employeeNo',
            id: 'employeeNo',
            label: '职工号',
            cls: 'input-large',
            rule: {
                required: true
            },
            message: {
                required: "请输入职工号"
            }
        }, {
            type: 'tree',//类型
            name: 'typeIds',
            id: 'typeIds',//id
            label: '导师类型',//左边label
            url: App.href + "/api/info/teacherType/treeNodes?topie_token=" + App.token,
            expandAll: true,
            autoParam: ["id", "name", "pId"],
            chkStyle: "checkbox"
        }, {
            type: 'radioGroup',
            name: 'gender',
            id: 'gender',
            label: '性别',
            inline: true,
            items: [{
                value: 1,
                text: '男'
            }, {
                value: 0,
                text: '女'
            }]
        }, {
            type: 'datepicker',
            name: 'birth',
            id: 'birth',
            label: '出生日期',
            config: {
                timePicker: false,
                singleDatePicker: true,
                locale: {
                    format: 'YYYY-MM-DD'
                }
            }/*,
            rule: {
                required: true
            },
            message: {
                required: "请选择出生日期"
            }*/
        }, {
            type: "select",
            label: "民族",
            name: "ethnicGroup",
            items: [
                {
                    text: "请选择民族",
                    value: ""
                }
            ],
            itemsUrl: App.href + "/api/common/ethnicGroup/option?topie_token=" + App.token
        }, {
            type: "select",
            label: "政治面貌",
            name: "politicalStatus",
            items: [
                {
                    text: "请选择政治面貌",
                    value: ""
                }
            ],
            itemsUrl: App.href + "/api/common/politicalStatus/option?topie_token=" + App.token
        }, {
            type: 'text',
            name: 'academe',
            id: 'academe',
            label: '学院',
            cls: 'input-large'
        }, {
            type: 'text',
            name: 'department',
            id: 'department',
            label: '科室',
            cls: 'input-large'
        }, {
            type: 'text',
            name: 'teachMajor',
            id: 'teachMajor',
            label: '所属专业',
            cls: 'input-large'
        }, {
            type: 'text',
            name: 'contactPhone',
            id: 'contactPhone',
            label: '联系电话',
            cls: 'input-large'
        }, {
            type: 'text',
            name: 'email',
            id: 'email',
            label: '邮箱',
            cls: 'input-large'
        }, {
            type: 'text',
            name: 'graduatedUniversity',
            id: 'graduatedUniversity',
            label: '毕业院校',
            cls: 'input-large'
        }, {
            type: 'text',
            name: 'graduatedMajor',
            id: 'graduatedMajor',
            label: '毕业专业',
            cls: 'input-large'
        }, {
            type: "select",
            label: "学历",
            name: "educationBackground",
            items: [
                {
                    text: "请选择学历",
                    value: ""
                }
            ],
            itemsUrl: App.href + "/api/common/educationBackground/option?topie_token=" + App.token
        }, {
            type: "select",
            label: "学位",
            name: "degree",
            items: [
                {
                    text: "请选择学位",
                    value: ""
                }
            ],
            itemsUrl: App.href + "/api/common/degree/option?topie_token=" + App.token
        }, {
            type: 'text',
            name: 'professionalTitle',
            id: 'professionalTitle',
            label: '职称',
            cls: 'input-large'
        }, {
            type: 'text',
            name: 'professionalPosition',
            id: 'professionalPosition',
            label: '职务',
            cls: 'input-large'
        }, {
            type: 'text',
            name: 'subjectDirection',
            id: 'subjectDirection',
            label: '学科方向',
            cls: 'input-large'
        }, {
            type: 'text',
            name: 'teacherCertificateNo',
            id: 'teacherCertificateNo',
            label: '教师资格证号',
            cls: 'input-large'
        }, {
            type: 'radioGroup',
            name: 'isLabStaff',
            id: 'isLabStaff',
            label: '是否实验室人员',
            inline: true,
            items: [{
                value: true,
                text: '是'
            }, {
                value: false,
                text: '否'
            }]
        }, {
            type: 'text',
            name: 'mainTeacherCertificateNo',
            id: 'mainTeacherCertificateNo',
            label: '主讲教师资格证号',
            cls: 'input-large'
        }, {
            type: 'text',
            name: 'staffNo',
            id: 'staffNo',
            label: '人事号',
            cls: 'input-large'
        }, {
            type: 'radioGroup',
            name: 'hasTeacherCertificate',
            id: 'hasTeacherCertificate',
            label: '是否教师资格证',
            inline: true,
            items: [{
                value: true,
                text: '是'
            }, {
                value: false,
                text: '否'
            }]
        }, {
            type: 'radioGroup',
            name: 'isOutside',
            id: 'isOutside',
            label: '是否外聘',
            inline: true,
            items: [{
                value: 1,
                text: '是'
            }, {
                value: 0,
                text: '否'
            }]
        }, {
            type: 'textarea',
            name: 'shortIntroduce',
            id: 'shortIntroduce',
            label: '简介',
            cls: 'input-large'
        }
    ];
    App.infoTeacher.columns = [
        {
            title: "教师名称",
            field: "name",
            sort: true
        }, {
            title: "职工号",
            field: "employeeNo",
            sort: true
        }, {
            title: "联系电话",
            field: "contactPhone",
            sort: true
        }
    ];

    App.infoTeacher.initEvents = function () {
        var grid;
        var options = {
            url: App.href + "/api/info/teacher/leaderPage",
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
            columns: App.infoTeacher.columns,
            actionColumnText: "操作",
            actionColumnWidth: "25%",
            actionColumns: [{
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
           }, {
                text: "该老师的学生",
                cls: "btn-primary btn-sm",
                handle: function (index, data) {
                    var modal = $.topieModal({
                        id: "selectStudentGrid",
                        title: "学生",
                        destroy: true
                    });
                    var studentGrid;
                    var studentOpt = {
                        url: App.href + "/api/front/student/leaderPage?isBind=1&teacherId=" + data.id,
                        //url: App.href + "/api/info/teacher/student",
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
                        pageSelect: [2, 15, 30, 50],
                        columns: [
                            {
                                title: "学生id",
                                field: "id",
                                width: "5%"
                            }, {
                                title: "学生名称",
                                field: "name"
                            }, {
                                title: "学号",
                                field: "studentNo"
                            }, {
                                title: "联系电话",
                                field: "contactPhone"
                            }],
                        actionColumnText: "操作",//操作列文本
                        actionColumnWidth: "20%",
                        actionColumns: [
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
                        }
                    };
                    studentGrid = modal.$body.topieGrid(studentOpt);
                    modal.show();
                }
            }],
            tools: [
                {
                    text: " 添 加",
                    cls: "btn btn-primary",
                    icon: "fa fa-cubes",
                    handle: function (grid) {
                        var modal = $.topieModal({
                            id: "teacher_modal",
                            title: "添加教师",
                            destroy: true
                        });
                        var formOpts = {
                            id: "add_teacher_form",
                            name: "add_teacher_form",
                            method: "POST",
                            action: App.href + "/api/info/teacher/insert",
                            ajaxSubmit: true,
                            rowEleNum: 1,
                            beforeSend: function (request) {
                                request.setRequestHeader("X-Auth-Token", App.token);
                            },
                            ajaxSuccess: function () {
                                modal.hide();
                                grid.reload();
                            },
                            submitText: "保存",
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
                            items: App.infoTeacher.formItems
                        };
                        var form = modal.$body.topieForm(formOpts);
                        modal.show();
                    }
                }
            ],
            search: {
                rowEleNum: 2,
                items: [{
                    type: "text",
                    label: "教师名",
                    name: "name",
                    placeholder: "输入要搜索的教师名"
                }, {
                    title: "职工号",
                    field: "employeeNo",
                    sort: true
                }, {
                    title: "联系电话",
                    field: "contactPhone",
                    sort: true
                }]
            }
        };
        grid = window.App.content.find("#teacher_grid").topieGrid(options);
    }
})(jQuery, window, document);
