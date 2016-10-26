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
            actionColumnWidth: "35%",
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
