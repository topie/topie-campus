/**
 * Created by chenguojun on 8/10/16.
 */
;
(function ($, window, document, undefined) {
    var that = this;
    var requestMapping = {
        "/api/index": "index"
    };
    App.requestMapping = $.extend({}, App.requestMapping, requestMapping);

    App.index = {
        page: function (title) {
            App.content.empty();
            App.title(title);
            var content = $('<div class="panel-body" id="index_grid"></div>');
            App.content.append(content);
            App.index.initEvents();
        }
    };
    /**
     * 初始化事件
     */
    App.index.initEvents = function () {
        var formOpts = {
            id: "index_form",//表单id
            name: "index_form",//表单名
            method: "post",//表单method
            action: "",//表单action
            ajaxSubmit: true,//是否使用ajax提交表单
            labelInline: false,
            rowEleNum: 2,
            beforeSubmit: function () {
            },
            ajaxSuccess: function () {
            },
            submitText: "保存",//保存按钮的文本
            showReset: true,//是否显示重置按钮
            resetText: "重置",//重置按钮文本
            isValidate: true,//开启验证
            buttons: [{
                type: 'button',
                text: '关闭',
                handle: function () {
                    alert("点击了关闭");
                }
            }],
            buttonsAlign: "center",
            //表单元素
            items: [{
                type: 'text',//类型
                name: 'name1',//name
                id: 'name1',//id
                label: '角色名',//左边label
                cls: 'input-large',
                rule: {
                    required: true
                },
                message: {
                    required: "请输入角色名"
                }
            }, {
                type: 'file',
                id: 'templatePath',
                name: 'templatePath',
                label: '模板路径',
                isAjaxUpload: true,
                uploadUrl: './upload',//
                onSuccess: function (data) {
                    if (data.status == 1) {
                        $("#templatePath").attr("value", data.path);
                    } else {
                        alert(data.message);
                    }
                },
                deleteHandle: function () {
                    $("#templatePath").attr("value", "");
                },
                rule: {
                    required: true
                },
                message: {
                    required: "请上传文件"
                }
            }, {
                type: 'image',
                id: 'image',
                name: 'image',
                label: '模板图片',
                isAjaxUpload: true,
                uploadUrl: './upload',//
                onSuccess: function (data) {
                    if (data.status == 1) {
                        $("#templatePath").attr("value", data.path);
                    } else {
                        alert(data.message);
                    }
                },
                deleteHandle: function () {
                    $("#templatePath").attr("value", "");
                },
                rule: {
                    required: true
                },
                message: {
                    required: "请上传文件"
                }
            }, {
                type: 'tree',//类型
                name: 'menuGroupIds',//name
                id: 'menuGroupIds',//id
                label: '菜单',//左边label
                url: App.href + "/api/sys/function/treeNodes?topie_token=" + App.token,
                data: {},
                expandAll: true,
                autoParam: ["id", "name", "pId"],
                chkStyle: "checkbox",
                rule: {
                    required: true
                },
                message: {
                    required: "请选择至少一项菜单"
                }
            }, {
                type: 'datepicker',
                name: 'datepicker',
                id: 'datepicker',
                label: '时间选择器',
                span: '4',
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
                    required: "请选择日期"
                }
            }]
        };
        var form = App.content.find("#index_grid").topieForm(formOpts);
    }
})(jQuery, window, document);
