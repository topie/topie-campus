/**
 * Created by chenguojun on 8/10/16.
 */
;
(function ($, window, document, undefined) {
    initIndex();
    $(document).ready(function () {
        initMenu();
    });
    var requestMapping = {
        "/api/index": "index"
    };
    App.requestMapping = $.extend({}, App.requestMapping, requestMapping);

    App.index = {
        page: function (title) {
            App.content.empty();
            App.title(title);
            var content = $('<div class="panel-body" id="profile"></div>');
            App.content.append(content);
            App.index.initEvents();
        }
    };
    /**
     * 初始化事件
     */
    App.index.initEvents = function () {
        $("#profile").load("./tmpl/profile.html",
            function () {
                var that = $(this);
                var source = $(this).html();
                that.empty();
                $.ajax(
                    {
                        type: 'GET',
                        url: App.href + "/api/frontCommon/profile",
                        contentType: "application/json",
                        dataType: "json",
                        beforeSend: function (request) {
                            request.setRequestHeader("X-Auth-Token", App.token);
                        },
                        success: function (result) {
                            if (result.code === 200) {
                                var data = result.data;
                                if (data == null) {
                                    return;
                                }
                                var render = template.compile(source);
                                var html = render(data);
                                that.html(html);
                            } else {
                                alert(result.message);
                            }
                        }
                    }
                );
            }
        );
    };

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
            if (m.pId == menuId) {
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
            if (m.pId == 0) {
                topMenus.push(m);
            } else {
                var subMenus = getMenu(menus, m.pId);
                if (subMenus.length == 0) {
                    topMenus.push(m);
                }
            }
        });
        return topMenus;
    }

    function secondMenu(ele, menus, subMenus) {
        if (subMenus.length > 0) {
            ele += "<ul class='nav nav-second-level collapse'>";
            $.each(subMenus, function (i, m) {
                ele += ('<li data-level="sub">'
                + '<a data-url="' + m.url
                + '" data-title="' + m.name
                + '" href="javascript:void(0);"><i class="' + (m.icon == null ? "glyphicon glyphicon-list" : m.icon) + '"></i> '
                + m.name
                + '</a>');
                var sMenus = getSubMenu(menus, m.id);
                ele = thirdMenu(ele, sMenus);
                ele += '</li>';
            });
            ele += "</ul>";
        }
        return ele;
    }

    function thirdMenu(ele, subMenus) {
        if (subMenus.length > 0) {
            ele += "<ul class='nav nav-third-level collapse'>";
            $.each(subMenus, function (i, m) {
                ele += ('<li data-level="sub">'
                + '<a data-url="' + m.url
                + '" data-title="' + m.name
                + '" href="javascript:void(0);"><i class="' + (m.icon == null ? "glyphicon glyphicon-list" : m.icon) + '"></i> '
                + m.name
                + '</a>');
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
                url: App.href + "/api/sys/function/current",
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
                            if (m.pId == 0) {
                                var ele =
                                    '<li data-level="top">'
                                    + '<a data-url="' + m.url
                                    + '" data-title="' + m.name
                                    + '" href="javascript:void(0);"><i class="' + (m.icon == null ? "glyphicon glyphicon-list" : m.icon) + '"></i> '
                                    + m.name
                                    + '</a>';
                                var subMenus = getSubMenu(menus, m.id);
                                if (subMenus.length > 0) {
                                    ele = secondMenu(ele, menus, subMenus);
                                }
                                ele += '</li>';
                                var li = $(ele);
                                li.find("li[data-level=sub]").parents("li[data-level=top]").addClass("submenu").find("a:eq(0)").append('<span class="caret pull-right"></span>');
                                $("#side-menu").append(li);
                            }
                        });
                        $("#side-menu").metisMenu();
                        $("#side-menu").find("li[class!=submenu] > a")
                            .each(function () {
                                    var url = $(this).attr("data-url");
                                    var f = App.requestMapping[url];
                                    if (f != undefined) {
                                        $(this).on("click", function () {
                                            var title = $(this).attr("data-title");
                                            App[f].page(title);
                                            $(this).parent().parent().removeClass("collapse").addClass("in");
                                            window.history.pushState({}, 0, 'http://' + window.location.host + App.projectName + '/static/front/index.html#!' + url);
                                        });
                                    }
                                }
                            );
                        refreshHref();
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

    /**
     * 完成后执行
     */
    var refreshHref = function () {
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
})(jQuery, window, document);
