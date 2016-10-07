/**
 * Created by chenguojun on 9/28/16.
 */
;
(function ($, window, document, undefined) {
    App.menu = {
        "initVerticalMenu": initVerticalMenu,
        "initSideMenu": initSideMenu
    };
    App.menusMapping = {};
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

    function initSideMenu(ul) {
        $.ajax(
            {
                type: 'GET',
                url: App.href + "/api/frontIndex/current",
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
                                $(ul).append(li);
                            }
                        });
                        $(ul).metisMenu();
                        $(ul).find("li[class!=submenu] > a")
                            .each(function () {
                                    var url = $(this).attr("data-url");
                                    var f = App.requestMapping[url];
                                    if (f != undefined) {
                                        $(this).on("click", function () {
                                            var title = $(this).attr("data-title");
                                            App[f].page(title);
                                            $(this).parent().parent().removeClass("collapse").addClass("in");
                                            window.history.pushState({}, 0, 'http://' + window.location.host + App.projectName + '/static/front/index.html?u=' + url);
                                        });
                                    }
                                }
                            );
                        refreshHref(ul);
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

    function initVerticalMenu(ul) {
        $.ajax(
            {
                type: 'GET',
                url: App.href + "/api/frontIndex/current",
                contentType: "application/json",
                dataType: "json",
                beforeSend: function (request) {
                    request.setRequestHeader("X-Auth-Token", App.token);
                },
                success: function (result) {
                    if (result.code === 200) {
                        var menus = result.data;
                        $.each(menus, function (i, m) {
                            App.menusMapping[m.url] = m.name;
                        });
                        var topMenus = getTopMenu(menus);
                        $.each(topMenus, function (i, m) {
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
                                //+ '<span class="label label-danger" style="position: absolute;font-size:5px;border-radius: 8px 8px 8px 8px;">&nbsp;</span>'
                                + '</a>';
                            if (subMenus.length > 0) {
                                ele = secondVerticalMenu(ele, menus, subMenus);
                            }
                            ele += '</li>';
                            var li = $(ele);
                            $(ul).append(li);
                        });
                        $(ul).find("li[class!=dropdown] > a")
                            .each(function () {
                                    var url = $(this).attr("data-url");
                                    var f = App.requestMapping[url];
                                    if (f != undefined) {
                                        $(this).on("click", function () {
                                            window.location.href = 'http://' + window.location.host + App.projectName + '/static/front/index.html?u=' + url;
                                        });
                                    }
                                }
                            );
                        refreshHref(ul);
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
    var refreshHref = function (ul) {
        var location = window.location.href;
        var url = location.substring(location.lastIndexOf("?u=") + 3);
        if (location.lastIndexOf("?u=") > 0 && url != undefined && $.trim(url) != "") {
            var title = App.menusMapping[url];
            var f = App.requestMapping[url];
            if (f != undefined) {
                App[f].page(title);
                if (ul == "#vertical-menu") {
                    a = $(ul).find("li[class!=dropdown] > a[data-url='" + url + "']");
                    a.parent().siblings("li").removeClass("active");
                    a.parent().parent().parent().siblings("li").removeClass("active");
                    a.parent().addClass("active");
                    a.parent().parent().parent().addClass("active");
                } else {
                    a = $(ul).find("li[class!=submenu] > a[data-url='" + url + "']");
                    a.addClass("active");
                    a.parent().parent().removeClass("collapse").addClass("in");
                }
            }
        } else {
            window.location.href = window.location.href + "?u=/api/front/index";
        }

    }
})(jQuery, window, document);
