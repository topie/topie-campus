;
(function ($, window, document, undefined) {
    var mapping = {
        "/api/info/sendMsg": "sendMsg"
    };
    App.requestMapping = $.extend({}, window.App.requestMapping, mapping);
    App.sendMsg = {
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body"><div id="sendMsg"></div></div>');
            window.App.content.append(content);
            App.sendMsg.initEvents();
        }
    };
    
    
    App.sendMsg.initEvents = function () {
    	
    	$("#sendMsg").load("././msg.html");
    }
})(jQuery, window, document);
