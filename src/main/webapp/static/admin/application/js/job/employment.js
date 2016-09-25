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
                    alert(json.message)
                },
                error: function (data, status, e) {
                    alert(e);
                }
            }
        );
    };
    App.infoEmploy.formItems = [
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
        title: "就业进度",
        field: "employProcess",
        sort: true
    },
    {
        title: "毕业去向",
        field: "signCompany",
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
                text: "查看详情",
                cls: "btn-primary btn-sm",
                handle: function (index, data) {
                    var modal = $.topieModal({
                        id: "empForm",
                        title: "查看详情",
                        destroy: true
                    });
                    var formOpts = {
                        id: "empForm",
                        name: "empForm",
                        method: "POST",
                        ajaxSubmit: true,
                        beforeSend: function (request) {
                            request.setRequestHeader("X-Auth-Token", App.token);
                        },
                        showReset: false,
                        rowEleNum: 2,
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
