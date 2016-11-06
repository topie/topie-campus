;
(function ($, window, document, undefined) {
    var mapping = {
        "/api/front/teacherRecord/recordList": "recordList"
    };
    App.requestMapping = $.extend({}, window.App.requestMapping, mapping);
    App.recordList = {
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body" id="record_grid"></div>');
            window.App.content.append(content);
            App.recordList.initEvents();
        }
    };
    var modal;
    var grid;
    App.recordList.formOpts = {
    		id: "plan_form",
            name: "plan_form",
            method: "POST",
            action: App.href + "/api/front/teacherRecord/insertOrUpdateRecord",
            ajaxSubmit: true,
            beforeSend: function (request) {
                request.setRequestHeader("X-Auth-Token", App.token);
            },
            ajaxSuccess: function () {
                modal.hide();
                grid.reload();
            },
            submitText: "保存",
            showReset: true,
            rowEleNum: 1,
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
            items: [
				{
				    type: 'hidden',
				    name: 'id',
				    id: 'id'
				}, 
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
                   },
                   {
                	    id:"createTime",
	                    type: "datepicker",
	                    label: "时间",
	                    name: "createTime",
	                    config: {
	                        timePicker: true,
	                        singleDatePicker: true,
	                        locale: {
	                            format: 'YYYY-MM-DD HH:mm:ss'
	                        }
	                    },
		                   rule: {
		                       required: true
		                   },
		                   message: {
		                       required: "请选择时间"
		                   }
	               },
                 {
		            type: 'textarea',
		            name: 'content',
		            id: 'content',
		            label: '内容',
		            cls: 'input-large',
		            rule: {
		                required: true,
		            },
		            message: {
		                required: "请输入内容，500字以内"
		            }
		        }      
           ]
    };
    
    App.recordList.initEvents = function () {
        var recordOpt = {
            url: App.href + "/api/front/teacherRecord/recordList",
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
                 }*/],
            actionColumnText: "操作",//操作列文本
            actionColumnWidth: "20%",
            actionColumns: [
                {
                    cls: "btn-info btn-sm",
                    text: "编辑",
                    handle: function (index, stData) {
                        modal = $.topieModal({
                            id: "planModal",
                            title: "留言",
                            destroy: true
                        });
                        var form = modal.$body.topieForm(App.recordList.formOpts);
                        form.loadRemote(App.href + "/api/front/teacherRecord/loadRecord?id=" + stData.id);
                        modal.show();
                    }
                },
                {
                    cls: "btn-primary btn-sm",
                    text: "删除",
                    handle: function (index, stData) {
                        $.ajax(
                            {
                                type: 'GET',
                                url: App.href + "/api/front/teacherRecord/deleteStudentRecord",
                                contentType: "application/json",
                                dataType: "json",
                                data: {
                                    id: stData.id
                                },
                                beforeSend: function (request) {
                                    request.setRequestHeader("X-Auth-Token", App.token);
                                },
                                success: function (result) {
                                	alert(result.message);
                                	grid.reload();
                                }
                            }
                        );
                    }
                }],
                tools: [
                        {
                         text: " 添 加",
                         cls: "btn btn-primary",
                         icon: "fa fa-cubes",
                         handle: function () {
                        modal = $.topieModal({
                         id: "record_modal",
                         title: "添加",
                         destroy: true
                         });
                         var form = modal.$body.topieForm(App.recordList.formOpts,function(){
                        	 getStudyYear();
                         });
                         modal.show();
                         }
                         }
                    ]
        };
        grid = window.App.content.find("#record_grid").topieGrid(recordOpt);
    }
})(jQuery, window, document);
