;
(function ($, window, document, undefined) {
    var mapping = {
        "/api/info/loginInfo/page": "loginInfo"
    };
    App.requestMapping = $.extend({}, window.App.requestMapping, mapping);
    App.loginInfo = {
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body" id="loginInfo_grid"></div>');
            window.App.content.append(content);
            App.loginInfo.initEvents();
        }
    };

    App.loginInfo.columns = [
        {
            title: "登录id",
            field: "loginUserId",
            width: "10%"
        }, {
            title: "姓名",
            field: "userName"
        }, {
            title: "登录用户名",
            field: "loginName"
        }, {
            title: "登录Ip",
            field: "loginIp"
        }, {
            title: "登录时间",
            field: "loginTime"
        }];
    App.loginInfo.initEvents = function () {
        var grid;
        var options = {
            url: App.href + "/api/info/loginInfo/page",
            beforeSend: function (request) {
                request.setRequestHeader("X-Auth-Token", App.token);
            },
            pageNum: 1,
            pageSize: 15,
            idFiled: "id",
            showCheckbox: false,
            showIndexNum: true,
            indexNumWidth: "10%",
            pageSelect: [2, 15, 30, 50],
            columns: App.loginInfo.columns,
            search: {
                rowEleNum: 2,
                items: [
                    {
                        type: "select",
                        label: "用户类型",
                        name: "userType",
                        id: "userType",
                        items: [
                            {
                                text: "老师",
                                value: 1
                            }, {
                                text: "学生",
                                value: 2
                            }
                        ]
                    },
                    {
                        type: "datepicker",
                        label: "登录时间区间",
                        name: "timePeriod",
                        id: "timePeriod",
                        placeholder: "登录时间区间"
                    }
                ],
                buttons: [
                    {
                        text: "导出",
                        cls: "btn btn-info",
                        icon: "fa fa-cubes",
                        handle: function () {
                            var url = App.href + "/api/info/loginInfo/export?topie_token=" + App.token + "&" + grid.$searchForm
                                    .serialize();
                            window.open(url);
                        }
                    }
                ]
            }
        };
        grid = window.App.content.find("#loginInfo_grid").topieGrid(options);
    }
})(jQuery, window, document);
