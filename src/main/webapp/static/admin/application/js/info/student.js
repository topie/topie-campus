;
(function ($, window, document, undefined) {
    var mapping = {
        "/api/info/student/page": "infoStudent"
    };
    App.requestMapping = $.extend({}, window.App.requestMapping, mapping);
    App.infoStudent = {
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body" id="student_grid"></div>');
            window.App.content.append(content);
            App.infoStudent.initEvents();
        }
    };
    App.infoStudent.formItems = [
        {
            type: 'hidden',
            name: 'id',
            id: 'id'
        }, {
            type: 'image',
            id: 'avatar',
            name: 'avatar',
            label: '头像',
            isAjaxUpload: true,
            onSuccess: function (data) {
                $("#avatar").attr("value", data.attachmentUrl);
            },
            deleteHandle: function () {
                $("#avatar").attr("value", "");
            }
        }, {
            type: 'text',
            name: 'name',
            id: 'name',
            label: '学生名',
            cls: 'input-large',
            rule: {
                required: true
            },
            message: {
                required: "请输入学生名"
            }
        }, {
            type: 'text',
            name: 'studentNo',
            id: 'studentNo',
            label: '学号',
            cls: 'input-large',
            rule: {
                required: true
            },
            message: {
                required: "请输入学号"
            }
        }, {
            type: 'radioGroup',
            name: 'gender',
            id: 'gender',
            label: '性别',
            inline: true,
            items: [{
                value: 1,
                text: '男'
            }, {
                value: 0,
                text: '女'
            }]
        }, {
            type: 'datepicker',
            name: 'birth',
            id: 'birth',
            label: '出生日期',
            config: {
                timePicker: false,
                singleDatePicker: true,
                locale: {
                    format: 'YYYY-MM-DD'
                }
            },
            rule: {
                required: true
            },
            message: {
                required: "请选择出生日期"
            }
        }, {
            type: "select",
            label: "民族",
            name: "ethnicGroup",
            items: [
                {
                    text: "请选择民族",
                    value: ""
                }
            ],
            itemsUrl: App.href + "/api/common/ethnicGroup/option?topie_token=" + App.token
        }, {
            type: "select",
            label: "政治面貌",
            name: "ethnicGroup",
            items: [
                {
                    text: "请选择政治面貌",
                    value: ""
                }
            ],
            itemsUrl: App.href + "/api/common/politicalStatus/option?topie_token=" + App.token
        }, {
            type: 'text',
            name: 'academe',
            id: 'academe',
            label: '学院',
            cls: 'input-large'
        }, {
            type: 'text',
            name: 'subject',
            id: 'subject',
            label: '专业',
            cls: 'input-large'
        }, {
            type: 'text',
            name: 'contactPhone',
            id: 'contactPhone',
            label: '联系电话',
            cls: 'input-large',
            rule: {
                number:true
            },
            message: {
                number: "请输入正确手机号码"
            }
        }, {
            type: 'text',
            name: 'email',
            id: 'email',
            label: '邮箱',
            cls: 'input-large'
        }
    ];
    App.infoStudent.columns = [{
        title: "学号",
        field: "studentNo",
        sort: true,
        width: "5%"
    }, {
        title: "学生名称",
        field: "name",
        sort: true
    }, {
        title: "联系电话",
        field: "contactPhone",
        sort: true
    }];
    App.infoStudent.initEvents = function () {
        var grid;
        var options = {
            url: App.href + "/api/info/student/page",
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
            columns: App.infoStudent.columns,
            actionColumnText: "操作",
            actionColumnWidth: "35%",
            actionColumns: [/*{
             text: "编辑",
             cls: "btn-primary btn-sm",
             handle: function (index, data) {
             var modal = $.topieModal({
             id: "studentForm",
             title: "编辑学生",
             destroy: true
             });
             var formOpts = {
             id: "student_form",
             name: "student_form",
             method: "POST",
             action: App.href + "/api/info/student/update",
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
             items: App.infoStudent.formItems
             };
             var form = modal.$body.topieForm(formOpts);
             form.loadRemote(App.href + "/api/info/student/load/" + data.id);
             modal.show();
             }
             },*/
                {
                    text: "发短信",
                    cls: "btn-success btn-sm",
                    handle: function (index, data) {
                        var modal = $.topieModal({
                            id: "studentMsg",
                            title: "发送短信",
                            destroy: true
                        });
                        var formOpts = {
                            id: "student_msg",
                            name: "student_msg",
                            method: "POST",
                            action: App.href + "/api/info/student/sendOneMsg?phone=" + data.contactPhone,
                            ajaxSubmit: true,
                            beforeSend: function (request) {
                                request.setRequestHeader("X-Auth-Token", App.token);
                            },
                            ajaxSuccess: function (result) {
                                bootbox.alert(result.message);
                                modal.hide();
                                grid.reload();
                            },
                            submitText: "发送",
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
                                    type: 'textarea',
                                    name: 'message',
                                    id: 'message',
                                    label: '短信内容',
                                    cls: 'input-large'
                                },
                                {
                                    type: 'select',
                                    name: 'sign',
                                    id: 'sign',
                                    label: '短信签名',
                                    cls: 'input-large',
                                    itemsUrl: App.href + "/api/dict/3?topie_token=" + App.token
                                }
                            ]
                        };
                        var form = modal.$body.topieForm(formOpts);
                        modal.show();
                    }
                },
                {
                    text: "选择教师",
                    cls: "btn-primary btn-sm",
                    handle: function (index, data) {
                        var modal = $.topieModal({
                            id: "selectTeacherGrid",
                            title: "选择教师",
                            destroy: true
                        });
                        var studentGrid;
                        var studentOpt = {
                            url: App.href + "/api/info/student/teacher?studentId=" + data.id,
                            beforeSend: function (request) {
                                request.setRequestHeader("X-Auth-Token", App.token);
                            },
                            pageNum: 1,//当前页码
                            pageSize: 15,//每页显示条数
                            idFiled: "id",//id域指定
                            showCheckbox: true,//是否显示checkbox
                            checkboxWidth: "3%",
                            showIndexNum: false,
                            indexNumWidth: "5%",
                            pageSelect: [2, 15, 30, 50],
                            columns: [
                                {
                                    title: "教师id",
                                    field: "id",
                                    width: "5%"
                                }, {
                                    title: "教师名称",
                                    field: "name"
                                }, {
                                    title: "职工号",
                                    field: "employeeNo"
                                }, {
                                    title: "联系电话",
                                    field: "contactPhone"
                                }, {
                                    title: "是否我的教师",
                                    field: "isBind",
                                    format: function (num, grid) {
                                        if (grid.isBind == 1) {
                                            return "是";
                                        } else {
                                            return "否"
                                        }
                                    }
                                }],
                            actionColumnText: "操作",//操作列文本
                            actionColumnWidth: "20%",
                            actionColumns: [{
                                textHandle: function (index, stData) {
                                    if (stData.isBind == 1) {
                                        return "取消选择";
                                    } else {
                                        return "选择";
                                    }
                                },
                                clsHandle: function (index, stData) {
                                    if (stData.isBind == 1) {
                                        return "btn-danger btn-sm";
                                    } else {
                                        return "btn-primary btn-sm";
                                    }
                                },
                                handle: function (index, stData) {
                                    bootbox.confirm("确定该操作吗?", function (result) {
                                        if (result) {
                                            var requestUrl = App.href + "/api/info/basic/bind";
                                            if (stData.isBind == 1) {
                                                requestUrl = App.href + "/api/info/basic/unbind";
                                            }
                                            $.ajax({
                                                type: "GET",
                                                beforeSend: function (request) {
                                                    request.setRequestHeader("X-Auth-Token", App.token);
                                                },
                                                dataType: "json",
                                                data: {
                                                    typeId: $("#typeId").val(),
                                                    studentId: data.id,
                                                    teacherId: stData.id
                                                },
                                                url: requestUrl,
                                                success: function (result) {
                                                    if (result.code === 200) {
                                                        studentGrid.reload();
                                                    } else {
                                                        alert(result.message);
                                                    }
                                                },
                                                error: function (e) {
                                                    alert("请求异常。");
                                                }
                                            });
                                        }
                                    });
                                }
                            }],
                            search: {
                                rowEleNum: 2,
                                //搜索栏元素
                                items: [
                                    {
                                        type: "select",
                                        label: "教师类型",
                                        name: "typeId",
                                        id: "typeId",
                                        itemsUrl: App.href + "/api/info/teacherType/options?topie_token=" + App.token
                                    }, {
                                        type: "text",
                                        label: "教师名称",
                                        name: "name",
                                        placeholder: "输入要搜索的教师名称"
                                    }, {
                                        type: "text",
                                        label: "职工号",
                                        name: "employeeNo",
                                        placeholder: "输入要搜索的职工号"
                                    }, {
                                        type: "text",
                                        label: "手机号",
                                        name: "contactPhone",
                                        placeholder: "输入要搜索的手机号"
                                    }, {
                                        type: "select",
                                        label: "是否绑定",
                                        name: "isBind",
                                        items: [
                                            {
                                                value: "",
                                                text: "全部"
                                            }, {
                                                value: 1,
                                                text: "是"
                                            }, {
                                                value: 0,
                                                text: "否"
                                            }
                                        ]
                                    }]
                            }
                        };
                        studentGrid = modal.$body.topieGrid(studentOpt);
                        modal.show();
                    }
                }, {
                    text: "删除",
                    cls: "btn-danger btn-sm",
                    handle: function (index, data) {
                        var requestUrl = App.href + "/api/info/student/delete";
                        $.ajax({
                            type: "GET",
                            beforeSend: function (request) {
                                request.setRequestHeader("X-Auth-Token", App.token);
                            },
                            dataType: "json",
                            data: {
                                studentId: data.id
                            },
                            url: requestUrl,
                            success: function (data) {
                                if (data.code === 200) {
                                    grid.reload();
                                } else {
                                    alert(data.message);
                                }
                            },
                            error: function (e) {
                                alert("请求异常。");
                            }
                        });
                    }
                }, {
                    text: "重置密码",
                    cls: "btn-info btn-sm",
                    handle: function (index, data) {
                        var requestUrl = App.href + "/api/info/student/resetPassword";
                        $.ajax({
                            type: "GET",
                            beforeSend: function (request) {
                                request.setRequestHeader("X-Auth-Token", App.token);
                            },
                            dataType: "json",
                            data: {
                                studentId: data.id
                            },
                            url: requestUrl,
                            success: function (data) {
                                if (data.code === 200) {
                                    alert("重置密码[000000]成功!");
                                    grid.reload();
                                } else {
                                    alert(data.message);
                                }
                            },
                            error: function (e) {
                                alert("请求异常。");
                            }
                        });
                    }
                }],
            tools: [
                /*{
                 text: " 添 加",
                 cls: "btn btn-primary",
                 icon: "fa fa-cubes",
                 handle: function (grid) {
                 var modal = $.topieModal({
                 id: "student_modal",
                 title: "添加学生",
                 destroy: true
                 });
                 var formOpts = {
                 id: "add_student_form",
                 name: "add_student_form",
                 method: "POST",
                 action: App.href + "/api/info/student/insert",
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
                 items: App.infoStudent.formItems
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
        grid = window.App.content.find("#student_grid").topieGrid(options);
    }
})(jQuery, window, document);
