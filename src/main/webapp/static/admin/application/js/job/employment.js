;
(function ($, window, document, undefined) {
    var mapping = {
        "/api/job/page": "infoEmploy"
    };
    App.requestMapping = $.extend({}, window.App.requestMapping, mapping);
    App.infoEmploy = {
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body" id="employment_grid"></div>');
            window.App.content.append(content);
            App.infoEmploy.initEvents();
        }
    };
    App.infoEmploy.loadJobInfo =  function(fileId)
    {
        if ($("#"+fileId).val() == "") {
            alert("请选择上传的文件");
            return;
        }
        $.ajaxFileUpload(
            {
                url: window.App.projectName+"/api/job/importJobData?topie_token=" + App.token,
                type: "POST",
                secureuri: false,
                fileElementId: "jobfile",
                dataType: "json",
                success: function (json, status) {
                    console.info(json);
                    alert(json.message);
                },
                error: function (data, status, e) {
                    alert(e);
                }
            }
        );
    };
				    App.infoEmploy.formItems = [
								{
								    type: 'hidden',
								    name: 'id',
								    id: 'id'
								},
                                 {
                                     type: 'display',
                                     name: 'stuId',
                                     id: 'stuId',
                                     label: '学号'
                                 }, {
                                     type: 'display',
                                     name: 'name',
                                     id: 'employName',
                                     label: '姓名'
                                 }, {
                                     type: 'display',
                                     name: 'phone',
                                     id: 'phone',
                                     label: '手机'
                                 },{
                                     type: 'display',
                                     name: 'education',
                                     id: 'education',
                                     label: '学历'
                                 }
                                 ,{
                                     type: 'display',
                                     name: 'gender',
                                     id: 'gender',
                                     label: '性别'
                                 }
                                 ,{
                                     type: 'display',
                                     name: 'major',
                                     id: 'major',
                                     label: '专业'
                                 }
                                 ,{
                                     type: 'display',
                                     name: 'class_num',
                                     id: 'class_num',
                                     label: '班号'
                                 }
                                 ,{
                                     type: 'display',
                                     name: 'homeAddress',
                                     id: 'homeAddress',
                                     label: '生源地'
                                 }
                                 ,{
                                     type: 'select',
                                     name: 'takeTable',
                                     id: 'takeTable',
                                     label: '领表',
                                     items:[{
                                    	 text:"未领取",
                                    	 value:"0"
                                     },{
                                    	 text:"领取",
                                    	 value:"1"
                                     }
                                            ]                                     
                                 }
                                 ,{
                                     type: 'select',
                                     name: 'employmentStatus',
                                     id: 'employmentStatus',
                                     label: '就业状态',
                                     items:[
                                       	    {
                                           	 text:"证明",
                                           	 value:"证明"
                                            },
                                            {
                                           	 text:"自谋",
                                           	 value:"自谋"
                                            } 
                                        ]
                                  }
                                 ,{
                                     type: 'select',
                                     name: 'signStatus',
                                     id: 'signStatus',
                                     label: '签约状态',
                                     items:[
                                    	 {
                                        	 text:"参军",
                                        	 value:"参军"
                                         },{
                                        	 text:"出国",
                                        	 value:"出国"
                                         } ,{
                                        	 text:"合同",
                                        	 value:"合同"
                                         } ,{
                                        	 text:"三方",
                                        	 value:"三方"
                                         } ,{
                                        	 text:"升本",
                                        	 value:"升本"
                                         } ,{
                                        	 text:"升硕",
                                        	 value:"升硕"
                                         } ,{
                                        	 text:"村官",
                                        	 value:"村官"
                                         } ,{
                                        	 text:"社区",
                                        	 value:"社区"
                                         } ,{
                                        	 text:"西部",
                                        	 value:"西部"
                                         } ,{
                                        	 text:"其他",
                                        	 value:"其他"
                                         } 
                                     ]
                                 },{
                                     type: 'display',
                                     name: 'poorStudent',
                                     id: 'poorStudent',
                                     label: '贫困生'
                                 }
                                 ]
    
				    App.infoEmploy.columns = [{
				        title: "学号",
				        field: "stuId",
				        sort: true,
				        width: "5%"
				    }, {
				        title: "学生名称",
				        field: "name",
				        sort: true
				    }, {
				        title: "联系电话",
				        field: "phone",
				        sort: true
				    },{
				        title: "领表",
				        field: "takeTable",
				        sort: true
				    },
				    {
				        title: "就业",
				        field: "employmentStatus",
				        sort: true
				    },
				    {
				        title: "签约",
				        field: "signStatus",
				        sort: true
				    }
				    ];
    App.infoEmploy.initEvents = function () {
        
    	var grid;
        var options = {
            url: App.href + "/api/job/page",
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
            columns: App.infoEmploy.columns,
            actionColumnText: "操作",
            actionColumnWidth: "20%",
            actionColumns: [{
                text: "编辑",
                cls: "btn-primary btn-sm",
                handle: function (index, data) {
                    var modal = $.topieModal({
                        id: "empFormModal",
                        title: "编辑",
                        destroy: true
                    });
                    var formOpts = {
                        id: "empForm",
                        name: "empForm",
                        method: "POST",
                        ajaxSubmit: true,
                        action: App.href + "/api/job/updateEmployment",
                        beforeSend: function (request) {
                            request.setRequestHeader("X-Auth-Token", App.token);
                        },
                        ajaxSuccess: function () {
                            modal.hide();
                            grid.reload();
                        },
                        submitText: "保存",
                        showReset: true,
                        rowEleNum: 2,
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
                        items: App.infoEmploy.formItems
                    };
                    var form = modal.$body.topieForm(formOpts);
                    console.log($("#empForm"),"11111");
                   form.loadRemote(App.href + "/api/job/load/" + data.id);
                    modal.show();
                }
            }],
            tools: [
				{
				    text: " 导入",
				    cls: "btn btn-primary",
				    icon: "fa fa-cubes",
				    handle: function (grid) {
				        var modal = $.topieModal({
				            id: "employment_modal",
				            title: "数据导入",
				            destroy: true
				        });
				        $.ajax(
				                {
				                    type: 'GET',
				                    url: App.href + "/api/job/importPage",
				                    contentType: "application/json",
				                    dataType: "json",
				                    beforeSend: function (request) {
				                        request.setRequestHeader("X-Auth-Token", App.token);
				                    },
				                    success: function (result) {
				                        if (result.code === 200) {
				                        	 var form = modal.$body.html(result.message);
				     				        modal.show();
				                            window.App.content.find("a[role=template]").click(function () {
				                                window.App.download("/api/info/upload/downloadTemplate");
				                            });
				                            App.upload.initEvents();
				                        } else {
				                            alert(result.message);
				                        }
				                    }
				                }
				            );
				    }
				}/*,
                {
                    text: " 手动添加",
                    cls: "btn btn-primary",
                    icon: "fa fa-cubes",
                    handle: function (grid) {
                        var modal = $.topieModal({
                            id: "employment_modal",
                            title: "添加学生",
                            destroy: true
                        });
                        var formOpts = {
                            id: "add_employment_form",
                            name: "add_employment_form",
                            method: "POST",
                            action: "",
                            ajaxSubmit: true,
                            rowEleNum: 1,
                            beforeSend: function (request) {
                                request.setRequestHeader("X-Auth-Token", App.token);
                            },
                            ajaxSuccess: function () {
                                modal.hide();
                                grid.reload();
                            },
                            submitText: "保存",
                            showReset: true,
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
                            items: App.infoEmploy.formItems
                        };
                        var form = modal.$body.topieForm(formOpts);
                        modal.show();
                    }
                }*/
            ],
            search: {
                rowEleNum: 2,
                items: [{
                    type: "text",
                    label: "学生名",
                    name: "name",
                    placeholder: "输入要搜索的学生名"
                }, {
                    title: "联系电话",
                    field: "contactPhone",
                    sort: true
                }]
            }
        };
        grid = window.App.content.find("#employment_grid").topieGrid(options);
    }
})(jQuery, window, document);
