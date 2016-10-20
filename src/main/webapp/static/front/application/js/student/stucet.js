;
(function ($, window, document, undefined) {
    var mapping = {
        "/api/front/student/cetscore": "frontStuCet"
    };
    App.requestMapping = $.extend({}, window.App.requestMapping, mapping);
    App.frontStuCet = {
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body" id="score_grid"></div>');
            window.App.content.append(content);
            App.frontStuCet.initEvents();
        }
    };

    App.frontStuCet.initEvents = function () {
        var grid;
        var scoreOpts = {
            url: App.href + "/api/front/student/cetscore",
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
            			}*/
        };
        grid = window.App.content.find("#score_grid").topieGrid(scoreOpts);
    }
})(jQuery, window, document);
