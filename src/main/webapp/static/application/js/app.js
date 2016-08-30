/**
 * Created by chenguojun on 8/10/16.
 */
;
(function ($, window, document, undefined) {
    window.App = {
        initLogin: initLogin,
        initIndex: initIndex,
        initMenu: initMenu
    };
    App.href = "..";
    App.requestMapping = {
        "/api/index": "index"
    };
    App.index = {
        initEvents: function () {
            var formOpts = {
                id: "index_form",//表单id
                name: "index_form",//表单名
                method: "post",//表单method
                action: "./ajaxSave",//表单action
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
                    name: 'name',//name
                    id: 'name',//id
                    label: '角色名',//左边label
                    cls: 'input-large',
                    rule: {
                        required: true
                    },
                    message: {
                        required: "请输入角色名"
                    }
                }, {
                    type: 'text',//类型
                    name: 'homePage',//name
                    id: 'homePage',//id
                    label: '跳转路径',//左边label
                    cls: 'input-large',
                    rule: {
                        required: true
                    },
                    message: {
                        required: "请输入跳转路径"
                    }
                }, {
                    type: 'textarea',//类型
                    name: 'detail',//name
                    id: 'detail',//id
                    label: '说明',
                    cls: 'input-large',
                    rows: 2
                }]
            };
            var form = window.App.content.find("#index_grid").topieForm(formOpts);
        },
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body" id="index_grid"></div>');
            window.App.content.append(content);
            App.index.initEvents();
        }
    };
    App.ready = function () {
        var location = window.location.href;
        var url = location.substring(location.lastIndexOf("#!") + 2);
        if (location.lastIndexOf("#!") > 0 && url != undefined && $.trim(url) != "") {
            $('a[data-url="' + url + '"]').trigger("click");
        } else {
            window.location.href = window.location.href + "#!/api/index";
            url = "/api/index";
            $('a[data-url="' + url + '"]').trigger("click");
        }

    }
    /**
     * 下载文件
     * @param href
     */
    App.download = function (href) {
        var xhr = new XMLHttpRequest();
        xhr.open('GET', ".." + href, true);
        xhr.responseType = 'arraybuffer';
        xhr.onload = function () {
            if (this.status === 200) {
                var filename = "";
                var disposition = xhr.getResponseHeader('Content-Disposition');
                if (disposition && disposition.indexOf('attachment') !== -1) {
                    var filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
                    var matches = filenameRegex.exec(disposition);
                    if (matches != null && matches[1]) {
                        filename = matches[1].replace(/['"]/g, '');
                    }
                }
                var type = xhr.getResponseHeader('Content-Type');
                var blob = new Blob([this.response], {type: type});
                if (typeof window.navigator.msSaveBlob !== 'undefined') {
                    // IE workaround for "HTML7007: One or more blob URLs were revoked by closing
                    // the blob for which they were created. These URLs will no longer resolve as
                    // the data backing the URL has been freed."
                    window.navigator.msSaveBlob(blob, filename);
                } else {
                    var URL = window.URL || window.webkitURL;
                    var downloadUrl = URL.createObjectURL(blob);

                    if (filename) {
                        // use HTML5 a[download] attribute to specify filename
                        var a = document.createElement("a");
                        // safari doesn't support this yet
                        if (typeof a.download === 'undefined') {
                            window.location = downloadUrl;
                        } else {
                            a.href = downloadUrl;
                            a.download = filename;
                            document.body.appendChild(a);
                            a.click();
                        }
                    } else {
                        window.location = downloadUrl;
                    }

                    setTimeout(function () {
                        URL.revokeObjectURL(downloadUrl);
                    }, 100);
                }
            }
        };
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhr.setRequestHeader('X-Auth-Token', App.token);
        xhr.send();
    }
    /**
     * 更改右侧内容标题
     * @param title
     */
    App.title = function (title) {
        $("#main-title").text(title);
    }
    /**
     * 右侧内容方法集合
     * @type {{append: App.body.append, empty: App.body.empty}}
     */
    App.content = {
        append: function (ele) {
            $("#main-body").append(ele);
        },
        empty: function () {
            $("#main-body").empty();
        },
        find: function (ele) {
            return $("#main-body").find(ele);
        }
    }

    App.$content = function () {
        return $("#main-body");
    }

    function initLogin() {
        $("#login_btn").on("click", function () {
            var fields = JSON.stringify(
                {
                    "username": $("#username").val(),
                    "password": $("#password").val()
                });
            $.ajax({
                type: 'POST',
                url: "../api/token/generate",
                contentType: "application/json",
                dataType: "json",
                data: fields,
                success: function (result) {
                    if (result.code == 200) {
                        $.cookie('tc_t', result.token, {expires: 7, path: '/'});
                        window.location.href = "./index.html";
                    } else {
                        alert(result.message);
                    }
                },
                error: function (err) {

                }
            });
        });
    }

    function initIndex() {
        var token = $.cookie('tc_t');
        if (token == undefined) {
            window.location.href = './login.html';
        }
        App.token = token;
    }

    function getSubMenu(menus, menuId) {
        var subMenus = [];
        $.each(menus, function (i, m) {
            if (m.parentId == menuId) {
                subMenus.push(m);
            }
        });
        return subMenus;
    }

    function getMenu(menus, menuId) {
        var subMenus = [];
        $.each(menus, function (i, m) {
            if (m.id == menuId) {
                subMenus.push(m);
            }
        });
        return subMenus;
    }

    function getTopMenu(menus) {
        var topMenus = [];
        $.each(menus, function (i, m) {
            if (m.parentId == 0) {
                topMenus.push(m);
            } else {
                var subMenus = getMenu(menus, m.parentId);
                if (subMenus.length == 0) {
                    topMenus.push(m);
                }
            }
        });
        return topMenus;
    }

    function recursionMenu(ele, menus, subMenus) {
        if (subMenus.length > 0) {
            ele += "<ul>";
            $.each(subMenus, function (i, m) {
                ele += ('<li data-level="sub">'
                + '<a data-url="' + m.action
                + '" data-title="' + m.functionName
                + '" href="javascript:void(0);"><i class="' + (m.icon == null ? "glyphicon glyphicon-list" : m.icon) + '"></i> '
                + m.functionName
                + '</a>');
                var sMenus = getSubMenu(menus, m.id);
                ele += '</li>';
            });
            ele += "</ul>";
        }
        return ele;
    }

    function initMenu() {
        $.ajax(
            {
                type: 'GET',
                url: "../api/sys/function/current",
                contentType: "application/json",
                dataType: "json",
                beforeSend: function (request) {
                    request.setRequestHeader("X-Auth-Token", App.token);
                },
                success: function (result) {
                    if (result.code === 200) {
                        var menus = result.data;
                        var topMenus = getTopMenu(menus);
                        $.each(topMenus, function (i, m) {
                            if (m.parentId == 0) {
                                var ele = '<li data-level="top">'
                                    + '<a data-url="' + m.action
                                    + '" data-title="' + m.functionName
                                    + '" href="javascript:void(0);"><i class="' + (m.icon == null ? "glyphicon glyphicon-list" : m.icon) + '"></i> '
                                    + m.functionName
                                    + '</a>';
                                var subMenus = getSubMenu(menus, m.id);
                                if (subMenus.length > 0) {
                                    ele = recursionMenu(ele, menus, subMenus);
                                }
                                ele += '</li>';
                                var li = $(ele);
                                li.find("li[data-level=sub]").parents("li[data-level=top]").addClass("submenu").find("a:eq(0)").append('<span class="caret pull-right"></span>');
                                $("div.sidebar > .nav").append(li);
                            }
                        });
                        $("div.sidebar > .nav").find("li.submenu > a").click(function (e) {
                            e.preventDefault();
                            var $li = $(this).parent("li");
                            var $ul = $(this).next("ul");
                            if ($li.hasClass("open")) {
                                $ul.slideUp(150);
                                $li.removeClass("open");
                            } else {
                                if ($li.parent("ul").hasClass("nav")) {
                                    $(".nav > li > ul").slideUp(150);
                                    $(".nav > li").removeClass("open");
                                }
                                $ul.slideDown(150);
                                $li.addClass("open");
                            }
                        });

                        $("div.sidebar > .nav").find("li[class!=submenu] > a").click(function (e) {
                            e.preventDefault();
                            var $li = $(this).parent("li");
                            if ($li.parent("ul").hasClass("nav")) {
                                $(".nav > li > ul").slideUp(150);
                                $(".nav > li").removeClass("open");
                            }
                            $("div.sidebar > .nav").find("li.current").removeClass("current");
                            $(this).parents("li").addClass("current");
                        });

                        $("div.sidebar > .nav").find("li[class!=submenu] > a")
                            .each(function () {
                                    var url = $(this).attr("data-url");
                                    var f = App.requestMapping[url];
                                    if (f != undefined) {
                                        $(this).on("click", function () {
                                            var title = $(this).attr("data-title");
                                            $(this).parent("li").parent("ul").show().parent("li").parent("ul").show();
                                            App[f].page(title);
                                            window.history.pushState({}, 0, 'http://' + window.location.host + '/static/index.html#!' + url);
                                        });
                                    }
                                }
                            );
                        App.ready();
                    } else if (result.code === 401) {
                        alert("token失效,请登录!");
                        window.location.href = './login.html';
                    }
                },
                error: function (err) {
                }
            }
        );
    }

})(jQuery, window, document);
