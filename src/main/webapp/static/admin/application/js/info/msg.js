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
    	 var formOpts = {
                 id: "student_msg",
                 name: "student_msg",
                 method: "POST",
                 action: App.href + "/api/info/basic/sendMsg",
                 ajaxSubmit: true,
                 beforeSend: function (request) {
                     request.setRequestHeader("X-Auth-Token", App.token);
                     
                 },
                 ajaxSuccess: function (result) {
                	 bootbox.alert(result.message);
                 },
                 submitText: "发送",
                 showReset: true,
                 rowEleNum: 1,
                 resetText: "重置",
                 isValidate: true,
                /* buttons: [{
                     type: 'button',
                     text: '关闭',
                     handle: function () {
                         modal.hide();
                     }
                 }],*/
                 buttonsAlign: "center",
                 items: [
							{
							    type: 'tree',
							    name: 'reciever',
							    id: 'reciever',
							    label: '收件人',
							    cls: 'input-large',
							    url : App.href+"/api/info/basic/collegeTree?topie_token="+App.token,
								autoParam : [ "id", "name", "pId" ],
								expandAll : false,
								chkboxType:{"Y": "s","Y": "s"}
							},
							{
							    type: 'textarea',
							    name: 'message',
							    id: 'message',
							    label: '短信内容',
							    cls: 'input-large'
							},
							{
							    type: 'select',
							    name: 'sign',
							    id: 'sign',
							    label: '短信签名',
							    cls: 'input-large',
							    itemsUrl:App.href+"/api/dict/3?topie_token=" + App.token
							}
                     ]
             };
    	
    	$("#sendMsg").topieForm(formOpts);
    }
})(jQuery, window, document);
