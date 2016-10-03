;
(function ($, window, document, undefined) {
    var mapping = {
        "/api/job/staticEmploy": "infoStatic"
    };
    App.requestMapping = $.extend({}, window.App.requestMapping, mapping);
    App.infoStatic = {
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body" id="static_grid"></div>');
            var radios = '<div class="col-md-4"><label>专业分类<input  type="radio" checked="checked" onclick="App.infoStatic.gridChange(this)" name="classification" value="1" /></label></div><div class="col-md-4"><label>班级分类<input type="radio" onclick="App.infoStatic.gridChange(this)" name="classification" value="2" /></label></div><div class="col-md-4"><label>导师分类<input type="radio" onclick="App.infoStatic.gridChange(this)" name="classification" value="3" /></label></div>';
             window.App.content.append(radios);
            window.App.content.append(content);
            App.infoStatic.initEvents();
        }
    };
    
    App.infoStatic.gridChange = function(radiohtml)
    {
    	if(radiohtml.value=='1')
    		{
    		$("#static_grid").html('');
    		 grid = window.App.content.find("#static_grid").topieGrid(App.infoStatic.majorOptions);
    		}
    	else if(radiohtml.value=='2')
    		{
    		$("#static_grid").html('');
    		grid = window.App.content.find("#static_grid").topieGrid(App.infoStatic.classOptions);
    		}
    	else if(radiohtml.value=='3')
    		{
    		$("#static_grid").html('');
    		grid = window.App.content.find("#static_grid").topieGrid(App.infoStatic.tutorOptions);
    		}
    }
    
    App.infoStatic.columns = [ 
    {
        sort: true,
        field: 'major',
        title: '专业',
        width: "25%",
    }, {
        field: 'signRate',
        title: '签约率',
        width: "10%",
        sort: true,
        format: function(i, c) {
        	return (c.signRate*100).toFixed(2)+"%"
        }
    }, {
        field: 'employmentRate',
        title: '就业率',
        width: "10%",
        sort: true,
        format: function(i, c) {
        	return (c.employmentRate*100).toFixed(2)+"%"
        }
    }
    ];
    
    App.infoStatic.majorOptions = {
            url: App.href + "/api/job/staticEmploy",
            beforeSend: function (request) {
                request.setRequestHeader("X-Auth-Token", App.token);
            },
            pageNum: 1,
            pageSize: 15,
            idFiled: "id",
            showCheckbox: false,
            showIndexNum: true,
            indexNumWidth: "5%",
            pageSelect: [2, 15, 30, 50],
            columns: App.infoStatic.columns,
            actionColumnText: "操作",
            actionColumnWidth: "20%",
            search: {
                rowEleNum: 2,
                items: [{
                    type: "text",
                    label: "专业名称",
                    name: "major",
                    placeholder: "输入要搜索的专业名"
                }]
            }
        };
    
    App.infoStatic.classOptions = {
            url: App.href + "/api/job/staticClassNum",
            beforeSend: function (request) {
                request.setRequestHeader("X-Auth-Token", App.token);
            },
            pageNum: 1,
            pageSize: 15,
            idFiled: "id",
            showCheckbox: false,
            showIndexNum: true,
            indexNumWidth: "5%",
            pageSelect: [2, 15, 30, 50],
            columns: [ 
                      {
                          sort: true,
                          field: 'classNum',
                          title: '班级',
                          width: "25%",
                      }, {
                          field: 'signRate',
                          title: '签约率',
                          width: "10%",
                          sort: true,
                          format: function(i, c) {
                          	return (c.signRate*100).toFixed(2)+"%"
                          }
                      }, {
                          field: 'employmentRate',
                          title: '就业率',
                          width: "10%",
                          sort: true,
                          format: function(i, c) {
                          	return (c.employmentRate*100).toFixed(2)+"%"
                          }
                      }
                      ],
            actionColumnText: "操作",
            actionColumnWidth: "20%",
            search: {
                rowEleNum: 2,
                items: [{
                    type: "text",
                    label: "班级名称",
                    name: "classNum",
                    placeholder: "输入要搜索的班级名"
                }]
            }
        };
    
    App.infoStatic.tutorOptions = {
            url: App.href + "/api/job/staticTutor",
            beforeSend: function (request) {
                request.setRequestHeader("X-Auth-Token", App.token);
            },
            pageNum: 1,
            pageSize: 15,
            idFiled: "id",
            showCheckbox: false,
            showIndexNum: true,
            indexNumWidth: "5%",
            pageSelect: [2, 15, 30, 50],
            columns: [ 
                      {
                          sort: true,
                          field: 'tutor',
                          title: '导师',
                          width: "25%",
                      }, {
                          field: 'signRate',
                          title: '签约率',
                          width: "10%",
                          sort: true,
                          format: function(i, c) {
                          	return (c.signRate*100).toFixed(2)+"%"
                          }
                      }, {
                          field: 'employmentRate',
                          title: '就业率',
                          width: "10%",
                          sort: true,
                          format: function(i, c) {
                          	return (c.employmentRate*100).toFixed(2)+"%"
                          }
                      }
                      ],
            actionColumnText: "操作",
            actionColumnWidth: "20%",
            search: {
                rowEleNum: 2,
                items: [{
                    type: "text",
                    label: "导师名称",
                    name: "tutor",
                    placeholder: "输入要搜索的导师名"
                }]
            }
        };
    
    App.infoStatic.initEvents = function () {
    	var grid;
        grid = window.App.content.find("#static_grid").topieGrid(App.infoStatic.majorOptions);
    }
})(jQuery, window, document);
