;
(function ($, window, document, undefined) {
    var mapping = {
        "/api/info/teacher/page": "infoTeacher"
    };
    App.requestMapping = $.extend({}, window.App.requestMapping, mapping);
    App.infoTeacher = {
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body" id="teacher_grid"></div>');
            window.App.content.append(content);
            App.infoTeacher.initEvents();
        }
    };
    App.infoTeacher.formItems = [
        {
            type: 'hidden',
            name: 'id',
            id: 'id'
        }, {
            type: 'image',
            id: 'avatar',
            name: 'avatar',
            span: 2,
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
            label: '教师名',
            cls: 'input-large',
            rule: {
                required: true
            },
            message: {
                required: "请输入教师名"
            }
        }, {
            type: 'text',
            name: 'shortName',
            id: 'shortName',
            label: '姓名简拼',
            cls: 'input-large'
        }, {
            type: 'text',
            name: 'password',
            id: 'password',
            label: '密码明文',
            cls: 'input-large'
        }, {
            type: 'text',
            name: 'employeeNo',
            id: 'employeeNo',
            label: '职工号',
            cls: 'input-large',
            rule: {
                required: true
            },
            message: {
                required: "请输入职工号"
            }
        }, {
            type: 'tree',//类型
            name: 'typeIds',
            id: 'typeIds',//id
            label: '导师类型',//左边label
            url: App.href + "/api/info/teacherType/treeNodes?topie_token=" + App.token,
            expandAll: true,
            autoParam: ["id", "name", "pId"],
            chkStyle: "checkbox"
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
            }/*,
            rule: {
                required: true
            },
            message: {
                required: "请选择出生日期"
            }*/
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
            name: "politicalStatus",
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
            name: 'department',
            id: 'department',
            label: '科室',
            cls: 'input-large'
        }, {
            type: 'text',
            name: 'teachMajor',
            id: 'teachMajor',
            label: '所属专业',
            cls: 'input-large'
        }, {
            type: 'text',
            name: 'contactPhone',
            id: 'contactPhone',
            label: '联系电话',
            cls: 'input-large'
        }, {
            type: 'text',
            name: 'email',
            id: 'email',
            label: '邮箱',
            cls: 'input-large'
        }, {
            type: 'text',
            name: 'graduatedUniversity',
            id: 'graduatedUniversity',
            label: '毕业院校',
            cls: 'input-large'
        }, {
            type: 'text',
            name: 'graduatedMajor',
            id: 'graduatedMajor',
            label: '毕业专业',
            cls: 'input-large'
        }, {
            type: "select",
            label: "学历",
            name: "educationBackground",
            items: [
                {
                    text: "请选择学历",
                    value: ""
                }
            ],
            itemsUrl: App.href + "/api/common/educationBackground/option?topie_token=" + App.token
        }, {
            type: "select",
            label: "学位",
            name: "degree",
            items: [
                {
                    text: "请选择学位",
                    value: ""
                }
            ],
            itemsUrl: App.href + "/api/common/degree/option?topie_token=" + App.token
        }, {
            type: 'text',
            name: 'professionalTitle',
            id: 'professionalTitle',
            label: '职称',
            cls: 'input-large'
        }, {
            type: 'text',
            name: 'professionalPosition',
            id: 'professionalPosition',
            label: '职务',
            cls: 'input-large'
        }, {
            type: 'text',
            name: 'subjectDirection',
            id: 'subjectDirection',
            label: '学科方向',
            cls: 'input-large'
        }, {
            type: 'text',
            name: 'teacherCertificateNo',
            id: 'teacherCertificateNo',
            label: '教师资格证号',
            cls: 'input-large'
        }, {
            type: 'radioGroup',
            name: 'isLabStaff',
            id: 'isLabStaff',
            label: '是否实验室人员',
            inline: true,
            items: [{
                value: true,
                text: '是'
            }, {
                value: false,
                text: '否'
            }]
        }, {
            type: 'text',
            name: 'mainTeacherCertificateNo',
            id: 'mainTeacherCertificateNo',
            label: '主讲教师资格证号',
            cls: 'input-large'
        }, {
            type: 'text',
            name: 'staffNo',
            id: 'staffNo',
            label: '人事号',
            cls: 'input-large'
        }, {
            type: 'radioGroup',
            name: 'hasTeacherCertificate',
            id: 'hasTeacherCertificate',
            label: '是否教师资格证',
            inline: true,
            items: [{
                value: true,
                text: '是'
            }, {
                value: false,
                text: '否'
            }]
        }, {
            type: 'radioGroup',
            name: 'isOutside',
            id: 'isOutside',
            label: '是否外聘',
            inline: true,
            items: [{
                value: 1,
                text: '是'
            }, {
                value: 0,
                text: '否'
            }]
        }, {
            type: 'textarea',
            name: 'shortIntroduce',
            id: 'shortIntroduce',
            label: '简介',
            cls: 'input-large'
        }
    ];
    App.infoTeacher.columns = [
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

    App.infoTeacher.initEvents = function () {
        var grid;
        var options = {
            url: App.href + "/api/info/teacher/page",
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
            columns: App.infoTeacher.columns,
            actionColumnText: "操作",
            actionColumnWidth: "35%",
            actionColumns: [{
                text: "编辑",
                cls: "btn-primary btn-sm",
                handle: function (index, data) {
                    var modal = $.topieModal({
                        id: "teacherForm",
                        title: "编辑教师",
                        destroy: true
                    });
                    var formOpts = {
                        id: "teacher_form",
                        name: "teacher_form",
                        method: "POST",
                        action: App.href + "/api/info/teacher/update",
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
                        items: App.infoTeacher.formItems
                    };
                    var form = modal.$body.topieForm(formOpts);
                    form.loadRemote(App.href + "/api/info/teacher/load/" + data.id);
                    modal.show();
                }
            }, {
                text: "选择学生",
                cls: "btn-primary btn-sm",
                handle: function (index, data) {
                    var modal = $.topieModal({
                        id: "selectStudentGrid",
                        title: "选择学生",
                        destroy: true
                    });
                    var studentGrid;
                    var studentOpt = {
                        url: App.href + "/api/info/teacher/student?teacherId=" + data.id,
                        //url: App.href + "/api/info/teacher/student",
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
                                title: "学生id",
                                field: "id",
                                width: "5%"
                            }, {
                                title: "学生名称",
                                field: "name"
                            }, {
                                title: "学号",
                                field: "studentNo"
                            }, {
                                title: "联系电话",
                                field: "contactPhone"
                            }, {
                                title: "是否我的学生",
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
                                    return "取消";
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
                                                studentId: stData.id,
                                                teacherId: data.id
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
                                    label: "学生名称",
                                    name: "name",
                                    placeholder: "输入要搜索的学生名称"
                                }, {
                                    type: "text",
                                    label: "学号",
                                    name: "studentNo",
                                    placeholder: "输入要搜索的学号"
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
                    var requestUrl = App.href + "/api/info/teacher/delete";
                    $.ajax({
                        type: "GET",
                        beforeSend: function (request) {
                            request.setRequestHeader("X-Auth-Token", App.token);
                        },
                        dataType: "json",
                        data: {
                            teacherId: data.id
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
                    var requestUrl = App.href + "/api/info/teacher/resetPassword";
                    $.ajax({
                        type: "GET",
                        beforeSend: function (request) {
                            request.setRequestHeader("X-Auth-Token", App.token);
                        },
                        dataType: "json",
                        data: {
                            teacherId: data.id
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
                {
                    text: " 添 加",
                    cls: "btn btn-primary",
                    icon: "fa fa-cubes",
                    handle: function (grid) {
                        var modal = $.topieModal({
                            id: "teacher_modal",
                            title: "添加教师",
                            destroy: true
                        });
                        var formOpts = {
                            id: "add_teacher_form",
                            name: "add_teacher_form",
                            method: "POST",
                            action: App.href + "/api/info/teacher/insert",
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
                            items: App.infoTeacher.formItems
                        };
                        var form = modal.$body.topieForm(formOpts);
                        modal.show();
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
