;
(function ($, window, document, undefined) {
    var mapping = {
        "/api/front/student/score": "frontStuScore"
    };
    App.requestMapping = $.extend({}, window.App.requestMapping, mapping);
    App.frontStuScore = {
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body" id="score_grid"></div>');
            var scoreStatic = $('<table id="score_static" class="table table-striped table-bordered table-hover dataTable no-footer" aria-describedby="score_grid_info"></table>');
            window.App.content.append(content);
            content.after(scoreStatic);
            App.frontStuScore.initEvents();
        }
    };

    App.frontStuScore.initEvents = function () {
        var grid;
        var scoreOpts = {
            url: App.href + "/api/front/student/score",
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
                    title: "学生名称",
                    field: "name"
                }/*, {
                    title: "课程代码",
                    field: "courceCode"
                }*/, {
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
        grid.reload(scoreOpts.url +'?'+getStudyYear());
        $.ajax({
        	url: App.href + "/api/front/student/staticScore?topie_token="+App.token,
        	success:function(result)
        	{
        		var data = '<tr><td>平均分</td><td>'+result.data.avgScore+'分</td><td>平均学分绩点</td><td>'+result.data.avgCredit+'</td>';
        		for(var i in result.data.scoreCourseType)
        			{
        			data = data+ '<td>'+result.data.scoreCourseType[i].courceType+'</td><td>'+result.data.scoreCourseType[i].totalCredit+'分</td>'
        			}
        		data = data+'</tr>'
        		$("#score_static").html(data);           
        	}
        });
    }
})(jQuery, window, document);
