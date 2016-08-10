/**
 * Created by chenguojun on 8/10/16.
 */
;
(function ($, window, document, undefined) {
    window.App = {
        initLogin: initLogin,
        initIndex: initIndex,
        indexClick: indexClick,
        initMenu: initMenu,
        requestMapping: {
            "#dash": "indexClick"
        }
    };
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
    App.title = function (title) {
        $("#main-title").text(title);
    }
    App.body = {
        append: function (ele) {
            $("#main-body").append(ele);
        },
        empty: function () {
            $("#main-body").empty();
        }
    }
    function indexClick() {
        App.title("index");
    }

    function initLogin() {
        $("#login_btn").on("click", function () {
            var fields = JSON.stringify({
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
                    if (result.code == 200) {
                        var menus = result.data;
                        $.each(menus, function (i, m) {
                            if (m.parentId == 0) {
                                var li = $(
                                    '<li>'
                                    + '<a data-url="' + m.action
                                    + '" href="javascript:void(0);"><i class="glyphicon glyphicon-list"></i> '
                                    + m.functionName
                                    + '</a>'
                                    + '</li>');
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
                                        App[f]();
                                    });
                                }
                            });
                    } else {
                        alert(result.message);
                    }
                },
                error: function (err) {
                }
            }
        );
    }

})(jQuery, window, document);