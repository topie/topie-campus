;
(function ($, window, document, undefined) {
    var mapping = {
        "/api/front/student/timeTable": "timeTable"
    };
    App.requestMapping = $.extend({}, window.App.requestMapping, mapping);
    App.timeTable = {
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body" id="time_table"></div>');
            window.App.content.append(content);
            App.timeTable.initEvents();
        }
    };

    App.timeTable.initEvents = function () {
        var getColSpan = function (list, week, section) {
            var iteS = function (week_, section_) {
                var length = 1;
                $.each(list, function (i, d) {
                    if (d['week'] == week_ && d['section'] == section_) {
                        length = d['sectionLength'];
                    }
                });
                return length;
            };
            var iteC = function (week_, section_) {
                var content = "";
                $.each(list, function (i, d) {
                    if (d['week'] == week_ && d['section'] == section_) {
                        content += (d['content'] + "<br>");
                    }
                });
                return content;
            };
            var map = {};
            for (var j = 1; j <= 13; j++) {
                var l = iteS(week, j);
                map[j] = l;
                if (l > 1) {
                    j = j + l - 1;
                }
            }
            return {"span": map[section], "content": iteC(week, section)};
        };
        var getWeek = function (i) {
            switch (i) {
                case 1 :
                    return "星期一";
                    break;
                case 2 :
                    return "星期二";
                    break;
                case 3 :
                    return "星期三";
                    break;
                case 4 :
                    return "星期四";
                    break;
                case 5 :
                    return "星期五";
                    break;
                case 6 :
                    return "星期六";
                    break;
                case 7 :
                    return "星期日";
                    break;
            }
        };
        $("#time_table").load("./tmpl/time-table.html?t=" + new Date().getTime(), function () {
            var url = App.href + "/api/front/student/timeTable";
            var that = $(this);
            that.find("#searchBtn").on("click", function () {
                if ($.trim(that.find("#studyYear").val()) == "") {
                    return;
                }
                if ($.trim(that.find("#studyYearNum").val()) == "") {
                    return;
                }
                $.ajax(
                    {
                        type: 'GET',
                        "url": url,
                        data: {
                            "studyYear": that.find("#studyYear").val(),
                            "studyYearNum": that.find("#studyYearNum").val()
                        },
                        dataType: "json",
                        beforeSend: function (request) {
                            request.setRequestHeader("X-Auth-Token", App.token);
                        },
                        success: function (result) {
                            if (result.code === 200) {
                                var data = result.data;
                                that.find("#score_grid_wrapper").empty();
                                var table = $('<table class="table table-bordered no-footer"></table>');
                                that.find("#score_grid_wrapper").append(table);
                                for (var j = 0; j <= 13; j++) {
                                    var tr = $('<tr></tr>');
                                    for (var i = 0; i <= 7; i++) {
                                        if (j == 0) {
                                            if (i == 0) {
                                                var td = $('<th style="background-color: #eff0f3;"></th>');
                                            } else {
                                                var week = getWeek(i);
                                                var td = $('<th style="background-color: #eff0f3;">' + week + '</th>');
                                            }
                                            tr.append(td);
                                        } else {
                                            if (i == 0) {
                                                var td = $('<td style="background-color: #eff0f3;">第' + j + '节</td>');
                                                tr.append(td);
                                            } else {
                                                var arr = getColSpan(data, i, j);
                                                var span = arr['span'];
                                                span = (span == undefined ? 0 : span);
                                                var content = arr['content'];
                                                if (span > 0) {
                                                    var style = "";
                                                    if ($.trim(content) != "") {
                                                        style = 'style="background-color: #d9edf7;"';
                                                    }
                                                    var td = $('<td ' + style + ' rowspan="' + span + '">' + content + '</td>');
                                                    tr.append(td);
                                                }
                                            }
                                        }
                                    }
                                    table.append(tr);
                                }

                            } else {
                                alert(result.message);
                            }
                        }
                    }
                );
            });
            $.ajax({
                url: App.href + "/api/dict/1?topie_token=" + App.token,
                type: "post",
                success: function (result) {
                    var opt = '<option value="">请选择</option>';
                    for (var i in result) {
                        opt = opt + '<option value="' + result[i].value + '">' + result[i].text + '</option>';
                    }
                    $("#studyYear").html(opt);
                    getStudyYear();
                    $("#searchBtn").click();
                }
            })
        });
    }
})(jQuery, window, document);
