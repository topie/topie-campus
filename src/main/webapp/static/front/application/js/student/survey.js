;
(function ($, window, document, undefined) {
    var mapping = {
        "/api/front/studentSurvey/page": "studentSurvey"
    };
    App.requestMapping = $.extend({}, window.App.requestMapping, mapping);
    App.studentSurvey = {
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body" id="studentSurvey_grid"></div>');
            window.App.content.append(content);
            App.studentSurvey.initEvents();
        }
    };
    App.studentSurvey.columns = [
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

    App.studentSurvey.initEvents = function () {
        var grid;
        var options = {
            url: App.href + "/api/front/studentSurvey/page",
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
            columns: App.studentSurvey.columns,
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
                items: [{
                    type: "text",
                    label: "问卷名称",
                    name: "groupName",
                    placeholder: "问卷组名称"
                }]
            }
        };
        grid = window.App.content.find("#studentSurvey_grid").topieGrid(options);
    }
})(jQuery, window, document);
