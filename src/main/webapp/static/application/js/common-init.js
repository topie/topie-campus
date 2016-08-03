;
(function ($, window, document, undefined) {
    if ($.cookie('tc_t') == undefined) {
        window.location.href = './login.html';
    }
    $(document).ready(function () {

        $("li.submenu > a").click(function (e) {
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

    });
})(jQuery, window, document);