;
(function ($, window, document, undefined) {
    var mapping = {
        "/api/info/exportStatic/page": "msgStatic"
    };
    App.requestMapping = $.extend({}, window.App.requestMapping, mapping);
    App.msgStatic = {
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body" id="msgStatic"></div>');
            window.App.content.append(content);
            App.msgStatic.initEvents();
        }
    };
    App.msgStatic.initEvents = function () {
        var grid;
        var options = {
            url: App.href + "/api/info/exportStatic/page",
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
                title: "职工号",
                field: "teacherNo",
                sort: true
            },
            {
                title: "发送人",
                field: "teacherName"
            }, {
                title: "短信内容",
                field: "msgContent"
            }, {
                title: "短信签名",
                field: "msgSign"
            },
            {
                title: "收件人",
                field: "reciever"
            }],
            actionColumnText: "操作",
            actionColumnWidth: "15%",
            search: {
                rowEleNum: 2,
                items: [
                        {
                    type: "text",
                    label: "职工号",
                    name: "teacherNo",
                    placeholder: "输入要搜索的学号"
                },{
                    type: "text",
                    label: "导师名",
                    name: "teacherName",
                    placeholder: "输入要搜索的导师名"
                }],
	            buttons: [
	                      {
	                          text: "导出",
	                          cls: "btn btn-info",
	                          icon: "fa fa-cubes",
	                          handle: function () {
	                              //var url = App.href + "/api/info/loginInfo/export?topie_token=" + App.token + "&" + grid.$searchForm
	                                //      .serialize();
	                        	  window.open(App.href + "/api/info/exportStatic/excelMsg?topie_token="+App.token);
	                          }
	                      }
	                  ]
            },
        };
        grid = window.App.content.find("#msgStatic").topieGrid(options);
    }
})(jQuery, window, document);
;