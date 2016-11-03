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
                },
                {
                    title: "重修标记",
                    field: "restudyFlag"
                },
                {
                    title: "辅修标记",
                    field: "isMinor"
                },
                {
                    title: "选课时间",
                    field: "selectTime"
                }
            ],
            actionColumnText: "操作",//操作列文本
            actionColumnWidth: "20%",
            search: {
                rowEleNum: 2,
                //搜索栏元素
                items: [
                    {
                    	id:"studyYear",
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
                    	id:"studyYearNum",
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
        //grid.reload(scoreOpts.url +'?'+getStudyYear());
    }
})(jQuery, window, document);
