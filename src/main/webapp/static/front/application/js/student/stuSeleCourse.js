;
(function ($, window, document, undefined) {
    var mapping = {
        "/api/front/student/selectCourse": "stuSelectCourse"
    };
    App.requestMapping = $.extend({}, window.App.requestMapping, mapping);
    App.stuSelectCourse = {
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body" id="score_grid"></div>');
            window.App.content.append(content);
            App.stuSelectCourse.initEvents();
        }
    };

    App.stuSelectCourse.initEvents = function () {
        var grid;
        var scoreOpts = {
            url: App.href + "/api/front/student/selectCourse",
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
                    title: "是否选中",
                    field: "isSelect"
                },
                {
                    title: "上课时间",
                    field: "couseStudyTime"
                },
                {
                    title: "教室编号",
                    field: "classroomNum"
                },
                {
                    title: "教室名称",
                    field: "classroomName"
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
                        items:[
                            {
                                text:"请选择",
                                value:""
                            }
                        ],
                        itemsUrl:App.href+"/api/dict/1?topie_token=" + App.token
                    },
                    {
                        type: "select",
                        label: "学期",
                        name: "studyYearNum",
                        items: [
                            {
                                text:"请选择",
                                value:""
                            },
                            {
                                text:"1",
                                value:"1"
                            },
                            {
                                text:"2",
                                value:"2"
                            }
                        ]
                    }
                ]
            }
        };
        grid = window.App.content.find("#score_grid").topieGrid(scoreOpts);
    }
})(jQuery, window, document);
