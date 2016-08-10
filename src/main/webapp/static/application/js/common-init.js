;
(function ($, window, document, undefined) {
    var token = $.cookie('tc_t');
    if (token == undefined) {
        window.location.href = './login.html';
    }
    $(document).ready(function () {

        $.ajax(
            {
                type: 'GET',
                url: "../api/sys/function/current",
                contentType: "application/json",
                dataType: "json",
                beforeSend: function (request) {
                    request.setRequestHeader("X-Auth-Token", token);
                },
                success: function (result) {
                    if (result.code == 200) {
                        var menus = result.data;
                        $.each(menus, function (i, m) {
                            if (m.parentId == 0) {
                                var li = $(
                                    '<li>'
                                    + '<a href="'
                                    + (m.action == "#" ? "javascript:void(0);" : m.action)
                                    + '"><i class="glyphicon glyphicon-list"></i> '
                                    + m.functionName
                                    + '</a>'
                                    + '</li>');
                                $("div.sidebar > .nav").append(li);
                            }
                        });
                        $("div.sidebar > .nav").find("li.submenu > a").click(function (e) {
                            e.preventDefault();
                            var $li = $(this).parent("li");
                            var $lis = $(this).parents("li");
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
                            var $ul = $(this).parent("ul");
                            if ($li.parent("ul").hasClass("nav")) {
                                $(".nav > li > ul").slideUp(150);
                                $(".nav > li").removeClass("open");
                            }
                            $("div.sidebar > .nav").find("li.current").removeClass("current");
                            $(this).parents("li").addClass("current");
                        });

                    } else {
                        alert(result.message);
                    }
                },
                error: function (err) {

                }
            }
        );

    });
})(jQuery, window, document);