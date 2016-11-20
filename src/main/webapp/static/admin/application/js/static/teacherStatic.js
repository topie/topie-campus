;
(function ($, window, document, undefined) {
    var mapping = {
        "/api/info/exportStatic/teacherPage": "teacherStatic"
    };
    App.requestMapping = $.extend({}, window.App.requestMapping, mapping);
    App.teacherStatic = {
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body" id="teacher_grid"></div>');
            window.App.content.append(content);
            App.teacherStatic.initEvents();
        }
    };
    
    App.teacherStatic.columns = [
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

    App.teacherStatic.initEvents = function () {
        var grid;
        var options = {
            url: App.href + "/api/info/exportStatic/teacherPage",
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
            columns: App.teacherStatic.columns,
            actionColumnText: "操作",
            actionColumnWidth: "35%",
            actionColumns: [
                            {
                            	cls: "btn-primary btn-sm",
                                text: "查看指导学生的记录",
                                handle: function (index, stData) {
                                    var modal = $.topieModal({
                                        id: "messageModal",
                                        title: "学生记录",
                                        destroy: true
                                    	});
                                    var recordOpt = {
                                            url: App.href + "/api/front/teacherRecord/sysRecordList?teacherId="+stData.id,
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
                                                },{
                                                    title: "内容",
                                                    field: "content"
                                                }],
                                    }
                                    grid = modal.$body.topieGrid(recordOpt);
                                    modal.show();
                                }
                            },
                            {
                            	cls: "btn-primary btn-sm",
                                text: "查看工作计划与总结",
                                handle: function (index, stData) {
                                    var modal = $.topieModal({
                                        id: "planModal",
                                        title: "工作计划总结",
                                        destroy: true
                                    	});
                                    var planOpt = {
                                            url: App.href + "/api/info/exportStatic/planList?teacherId="+stData.id,
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
                                                    title: "类型",
                                                    field: "type",
                                                    format:function(i,c)
                                                    {
                                                    	if(c.type==0)
                                                    		return "计划";
                                                    	else if(c.type==1)
                                                    		{
                                                    		return "总结";
                                                    		}
                                                    }
                                                },{
                                                    title: "内容",
                                                    field: "content"
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
                                                 }*/]
                                        };
                                        grid = modal.$body.topieGrid(planOpt);
                                        modal.show();
                                }
                            }
            ],
            tools: [
                {
                    text: "导出工作计划与总结",
                    cls: "btn btn-primary",
                    icon: "fa fa-cubes",
                    handle: function (grid) {
                    	window.open(App.href + "/api/info/exportStatic/excelPlanSummary?topie_token="+App.token);
                    }
                },
                {
                    text: "导出指导学生的记录",
                    cls: "btn btn-primary",
                    icon: "fa fa-cubes",
                    handle: function (grid) {
                    	window.open(App.href + "/api/info/exportStatic/excelRecord?topie_token="+App.token);
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
