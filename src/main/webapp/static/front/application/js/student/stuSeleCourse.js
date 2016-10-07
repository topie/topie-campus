;
(function ($, window, document, undefined) {
    var mapping = {
        "/api/front/student/selectCourse": "stuSelectCourse"
    };
    App.requestMapping = $.extend({}, window.App.requestMapping, mapping);
    App.stuSelectCourse = {
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body" id="select_course">aaaa</div>');
            window.App.content.append(content);
            App.stuSelectCourse.initEvents();
        }
    };

    App.stuSelectCourse.initEvents = function () {

    }
})(jQuery, window, document);
