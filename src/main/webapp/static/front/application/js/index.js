/**
 * Created by chenguojun on 8/10/16.
 */
;
(function ($, window, document, undefined) {
    initIndex();
    $(document).ready(function () {
        initVerticalMenu();
    });
    var requestMapping = {
        "/api/front/index": "index"
    };
    App.requestMapping = $.extend({}, App.requestMapping, requestMapping);

    App.index = {
        page: function (title) {
            App.content.empty();
            App.title(title);
            var content = $(
                '<div class="row">' +
                '<div class="col-md-6">' +
                '<div class="panel panel-default" style="border-radius:0">' +
                '<div class="panel-heading"><i class="fa fa-bar-chart-o fa-fw"></i>个人中心</div>' +
                '<div class="panel-body" style="display: none" id="profile"></div>' +
                '</div>' +
                '</div>' +
                '<div class="col-md-6">' +
                '<div class="row">' +
                '<div class="col-md-12">' +
                '<div class="panel panel-default" style="border-radius:0">' +
                '<div class="panel-heading"><i class="fa fa-bar-chart-o fa-fw"></i>通知公告</div>' +
                '<div class="panel-body"  id="notice"></div>' +
                '</div>' +
                '</div>' +
                '<div class="col-md-12">' +
                '<div class="panel panel-default" style="border-radius:0">' +
                '<div class="panel-heading"><i class="fa fa-bar-chart-o fa-fw"></i>留言板</div>' +
                '<div class="panel-body"  id="message"></div>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>'
            );
            App.content.append(content);
            App.index.initEvents();
        }
    };
    /**
     * 初始化事件
     */
    App.index.initEvents = function () {
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
                        var data = result.data.data;
                        var role = result.data.role;
                        if (data == null) {
                            return;
                        }
                        var temp = "";
                        if (role == 4) {
                            temp = "./tmpl/profile-teacher.html";
                        } else if (role == 5) {
                            temp = "./tmpl/profile-student.html";
                        }
                        if (temp == "") {
                            return;
                        }
                        $("#profile").load(temp + "?t=" + new Date().getTime(),
                            function () {
                                var that = $(this);
                                var source = $(this).html();
                                that.empty();
                                var render = template.compile(source);
                                var html = render(data);
                                that.html(html).show();
                            }
                        );
                    } else {
                        alert(result.message);
                    }
                }
            }
        );
        $("#message").load("./tmpl/message.html?t=" + new Date().getTime(),
            function () {
                var that = $(this);
                var source = $(this).html();
                that.empty();
                $.ajax(
                    {
                        type: 'GET',
                        url: App.href + "/api/front/message/send",
                        dataType: "json",
                        beforeSend: function (request) {
                            request.setRequestHeader("X-Auth-Token", App.token);
                        },
                        success: function (result) {
                            if (result.code === 200) {
                                var data = result.data;
                                var render = template.compile(source);
                                var html = render({
                                    "list": data.list
                                });
                                that.html(html).show();
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

    function secondVerticalMenu(ele, menus, subMenus) {
        if (subMenus.length > 0) {
            ele += "<ul class='dropdown-menu animated flipInX'>";
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

    function initSideMenu() {
        $.ajax(
            {
                type: 'GET',
                url: App.href + "/api/frontCommon/current",
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
    };

    function initVerticalMenu() {
        $.ajax(
            {
                type: 'GET',
                url: App.href + "/api/frontCommon/current",
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
                                var subMenus = getSubMenu(menus, m.id);
                                var dropDown = "";
                                if (subMenus.length > 0) {
                                    dropDown = 'class="dropdown"'
                                }
                                var ele =
                                    '<li ' + dropDown + ' data-level="top">'
                                    + '<a data-url="' + m.url
                                    + '" data-title="' + m.name
                                    + '" href="javascript:void(0);"> '
                                    + m.name
                                    + '</a>';
                                if (subMenus.length > 0) {
                                    ele = secondVerticalMenu(ele, menus, subMenus);
                                }
                                ele += '</li>';
                                var li = $(ele);
                                $("#vertical-menu").append(li);
                            }
                        });
                        $("#vertical-menu").find("li[class!=dropdown] > a")
                            .each(function () {
                                    var url = $(this).attr("data-url");
                                    var f = App.requestMapping[url];
                                    if (f != undefined) {
                                        $(this).on("click", function () {
                                            var title = $(this).attr("data-title");
                                            App[f].page(title);
                                            $(this).parent().siblings("li").removeClass("active");
                                            $(this).parent().addClass("active");
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
            window.location.href = window.location.href + "#!/api/front/index";
            url = "/api/front/index";
            $('a[data-url="' + url + '"]').trigger("click");
        }

    }
})(jQuery, window, document);
