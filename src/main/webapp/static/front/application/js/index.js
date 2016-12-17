/**
 * Created by chenguojun on 8/10/16.
 */
;
(function ($, window, document, undefined) {
    var token = $.cookie('tc_t');
    if (token == undefined) {
        window.location.href = './login.html';
    }
    App.token = token;

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
                '<div class="row">' +
                '<div class="col-md-12">' +
                '<div class="panel panel-info" style="border-radius:0">' +
                '<div class="panel-heading"><i class="fa fa-bar-chart-o fa-fw"></i>个人中心</div>' +
                '<div class="panel-body" style="display: none" id="profile"></div>' +
                '</div>' +
                '</div>' +
                '<div class="col-md-12">' +
                '<div class="panel panel-info" style="border-radius:0">' +
                '<div class="panel-heading"><i class="fa fa-bar-chart-o fa-fw"></i>通知公告</div>' +
                '<div class="panel-body"  id="notice"></div>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '<div class="col-md-6">' +
                '<div class="row">' +
                '<div class="col-md-12">' +
                '<div class="panel panel-info" style="border-radius:0">' +
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
                url: App.href + "/api/frontIndex/profile?v=" + new Date().getTime(),
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
                        console.log(role);
                        var temp = "";
                        if (role == 4) {
                            temp = "./tmpl/profile-teacher.html";
                        } else if (role == 5) {
                            temp = "./tmpl/profile-student.html";
                        }
                        else if (role == 7) {
                            temp = "./tmpl/profile-leader.html";
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
                    
                   
             if(role!=7)
            	 {
            	 console.log(1111);
            	 
        $("#message").load("./tmpl/message-center.html?t=" + new Date().getTime(),
            function () {
                var that = $(this);
                var source = $(this).html();
                that.empty();
                $.ajax(
                    {
                        type: 'GET',
                        url: App.href + "/api/frontIndex/messageCenter",
                        dataType: "json",
                        beforeSend: function (request) {
                            request.setRequestHeader("X-Auth-Token", App.token);
                        },
                        success: function (result) {
                            if (result.code === 200) {
                                var data = result.data;
                                var render = template.compile(source);
                                var html = render({
                                    "data": data
                                });
                                that.html(html).show();
                                /*if (data.messageStat.length == 0) {
                                    return;
                                }
                                var da = [];
                                $.each(data.messageStat, function (i, d) {
                                    var t = {};
                                    t.label = d.name + "[" + d.message_count + "]";
                                    t.data = parseInt(d.message_count);
                                    da.push(t);
                                });
                                var plotObj = $.plot($("#message_pie"), da, {
                                    series: {
                                        pie: {
                                            show: true
                                        }
                                    },
                                    grid: {
                                        hoverable: true
                                    },
                                    tooltip: true,
                                    tooltipOpts: {
                                        content: "%p.0%, %s",
                                        shifts: {
                                            x: 20,
                                            y: 0
                                        },
                                        defaultTheme: false
                                    }
                                });
                                var da = [];
                                $.each(data.replyStat, function (i, d) {
                                    var t = {};
                                    t.label = d.user_name + "[" + d.reply_count + "]";
                                    t.data = parseInt(d.reply_count);
                                    da.push(t);
                                });
                                var plotObj = $.plot($("#reply_pie"), da, {
                                    series: {
                                        pie: {
                                            show: true
                                        }
                                    },
                                    grid: {
                                        hoverable: true
                                    },
                                    tooltip: true,
                                    tooltipOpts: {
                                        content: "%p.0%, %s",
                                        shifts: {
                                            x: 20,
                                            y: 0
                                        },
                                        defaultTheme: false
                                    }
                                });*/

                            } else {
                                alert(result.message);
                            }
                        }
                    }
                );
            }
        );
        $("#notice").load("./tmpl/notice-top5.html?t=" + new Date().getTime(),
            function () {
                var that = $(this);
                var source = $(this).html();
                that.empty();
                $.ajax(
                    {
                        type: 'GET',
                        url: App.href + "/api/frontIndex/noticeTop5",
                        dataType: "json",
                        beforeSend: function (request) {
                            request.setRequestHeader("X-Auth-Token", App.token);
                        },
                        success: function (result) {
                            if (result.code === 200) {
                                var data = result.data;
                                var render = template.compile(source);
                                var html = render({
                                    "list": data
                                });
                                that.html(html).show();
                                that.find("ul.notice").bootstrapNews({
                                    newsPerPage: 3,
                                    autoplay: true,
                                    pauseOnHover: true,
                                    direction: 'up',
                                    newsTickerInterval: 4000
                                });
                            } else {
                                alert(result.message);
                            }
                        }
                    }
                );
            }
        );
        
            	 }else
            		 {
            		 App.content.empty();
            		 App.content.append('欢迎进入教师导学辅助系统！<font color="red">点击菜单进行查看</font>');
            		 }
        /**ajax结束***/
                }
            }
        );
    };
    $(document).ready(function () {
        App.menu.initVerticalMenu("#vertical-menu");
    });
})(jQuery, window, document);
