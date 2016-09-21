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
            name: 'department',
            id: 'department',
            label: '系(所)',
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
            type: 'text',
            name: 'mainTeacherCertificateNo',
            id: 'mainTeacherCertificateNo',
            label: '主讲教师资格证号',
            cls: 'input-large'
        }, {
            type: 'text',
            name: 'staffNo',
            id: 'staffNo',
            label: '人事职工号',
            cls: 'input-large'
        }, {
            type: 'textarea',
            name: 'teachQualityComment',
            id: 'teachQualityComment',
            label: '教学质量评价',
            cls: 'input-large'
        }
    ];
    App.infoTeacher.columns = [{
        title: "id",
        field: "id",
        sort: true,
        width: "5%"
    }, {
        title: "教师名称",
        field: "name",
        sort: true
    }, {
        title: "联系电话",
        field: "contactPhone",
        sort: true
    }];
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
            actionColumnWidth: "20%",
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
                text: "删除",
                cls: "btn-danger btn-sm",
                handle: function (index, data) {
                    var requestUrl = App.href + "/api/info/teacher/delete";
                    $.ajax({
                        type: "POST",
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
                    title: "联系电话",
                    field: "contactPhone",
                    sort: true
                }]
            }
        };
        grid = window.App.content.find("#teacher_grid").topieGrid(options);
    }
})(jQuery, window, document);
