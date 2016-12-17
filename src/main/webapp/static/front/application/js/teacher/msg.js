;
(function ($, window, document, undefined) {
    var mapping = {
        "/api/info/teacherSendMsg": "teacherSendMsg"
    };
    App.requestMapping = $.extend({}, window.App.requestMapping, mapping);
    App.teacherSendMsg = {
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body"><div id="teacherSendMsg"></div></div>');
            window.App.content.append(content);
            App.teacherSendMsg.initEvents();
        }
    };
    
    
    App.teacherSendMsg.initEvents = function () {
    	
    	$.ajax({
    		url:App.href+"/api/info/teacherType/treeNodesByTeacherId?topie_token="+App.token,
    		success:function(result)
    		{
    			var role = "";
    			for(var i in result)
    				{
    				if(i==result.length-1)
    					{
    					 role = role + result[i].name;
    					}
    				else
    					{
    					 role = role + result[i].name+",";
    					}
    				}
    			$("#teacherSendMsg").before("<div style='text-align:center;font-size:24px;margin-bottom:40px;'>您的角色是<font color='red'>"+role+"</font>;您可以给您相应角色下的学生发送短息通知。</div>");
    		}
    	});
    	
    	 var formOpts = {
                 id: "student_msg",
                 name: "student_msg",
                 method: "POST",
                 action: App.href + "/api/info/basic/teacherSendMsg",
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
							    url : App.href+"/api/info/teacherType/treeNodesAndStudentIdByTeacherId?topie_token="+App.token,
								autoParam : [ "id", "name", "pId" ],
								expandAll : false,
								chkboxType:{"Y": "s","Y": "s"},
								rule : {
									required : true,
								},
								message : {
									required : "请选择导师内容",
								}
							},
							{
							    type: 'textarea',
							    name: 'message',
							    id: 'message',
							    label: '短信内容',
							    cls: 'input-large',
							    rule : {
									required : true,
									maxlength : 64
								},
								message : {
									required : "请输入短信内容",
									maxlength : "最多输入64字节"
								}
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
    	
    	$("#teacherSendMsg").topieForm(formOpts);
    }
})(jQuery, window, document);