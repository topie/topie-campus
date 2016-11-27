;
(function ($, window, document, undefined) {
    var mapping = {
        "/api/front/teacherRecord/planList": "planList"
    };
    App.requestMapping = $.extend({}, window.App.requestMapping, mapping);
    App.planList = {
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body" id="record_grid"></div>');
            window.App.content.append(content);
            App.planList.initEvents();
        }
    };
    var modal;
    var grid;
    App.planList.formOpts = {
    		id: "plan_form",
            name: "plan_form",
            method: "POST",
            action: App.href + "/api/front/teacherRecord/insertOrUpdatePlan",
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
                	    id:"type",
	                    type: "select",
	                    label: "类型",
	                    name: "type",
	                    items: [
								{
									text:"请选择",
								    value:""
								},
	                            {
	                             text:"工作计划",
	                             value:"0"
	                            },
	                            {
	                                text:"工作总结",
	                                value:"1"
	                            }
	                    ],
		                   rule: {
		                       required: true
		                   },
		                   message: {
		                       required: "请选择工作计划或工作总结"
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
                        maxlength:500
		            },
		            message: {
		                required: "请输入内容，500字以内",
                        maxlength: "500字以内"
		            }
		        }      
           ]
    };
    
    App.planList.initEvents = function () {
        var planOpt = {
            url: App.href + "/api/front/teacherRecord/planList",
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
                        var form = modal.$body.topieForm(App.planList.formOpts);
                        form.loadRemote(App.href + "/api/front/teacherRecord/loadPlan?id=" + stData.id);
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
                                url: App.href + "/api/front/teacherRecord/deletePlan",
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
                         var form = modal.$body.topieForm(App.planList.formOpts,function(){
                        	 getStudyYear();
                         });
                         modal.show();
                         }
                         }
                    ]
        };
        grid = window.App.content.find("#record_grid").topieGrid(planOpt);
    }
})(jQuery, window, document);
