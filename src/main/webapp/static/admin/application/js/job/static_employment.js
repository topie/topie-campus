;
(function ($, window, document, undefined) {
	
	 if(isIE()){
	        $.ajaxSetup({
	            cache: false //关闭AJAX缓存
	        });
	    }
	
    var mapping = {
        "/api/job/staticByCollege": "infoStatic"
    };
    App.requestMapping = $.extend({}, window.App.requestMapping, mapping);
    App.infoStatic = {
        page: function (title) {
            window.App.content.empty();
            window.App.title(title);
            var content = $('<div class="panel-body" id="static_grid"></div>');
            window.App.content.append(content);
            App.infoStatic.initEvents();
        }
    };
    var grid;
    var college = "";
    var faculty = "";
    var major = "";
    var teacherNo = "";
    App.infoStatic.gridChange = function(type,value)
    {
    	 var sourceValue = value;
         if(isIE())
        	 {
        	 value = encodeURI(value);
        	 }
    	if(type==0)
    		{
    		$("#static_grid").html('');
    		grid = window.App.content.find("#static_grid").topieGrid(App.infoStatic.collegeOptions);
    		}
    	if(type==1)
    		{
    		$("#static_grid").html('');
    		college = sourceValue;
    		 App.infoStatic.facultyOptions.url = App.href + "/api/job/staticByFaculty?college="+value;
    		 grid = window.App.content.find("#static_grid").topieGrid(App.infoStatic.facultyOptions);
    		 //grid.reload({url:App.infoStatic.facultyOptions.url +"?&college="+value});
    		}
    	else if(type==2)
    		{
    		$("#static_grid").html('');
    		faculty = sourceValue;
    		App.infoStatic.majorOptions.url = App.href + "/api/job/staticByMajor?faculty="+value;
    		grid = window.App.content.find("#static_grid").topieGrid(App.infoStatic.majorOptions);
    		//grid.reload({url:App.infoStatic.majorOptions.url +"?&faculty="+value});
    		}
    	else if(type==3)
    		{
    		$("#static_grid").html('');
    		major = sourceValue;
    		App.infoStatic.tutorOptions.url = App.href + "/api/job/staticTutor?major="+value;
    		grid = window.App.content.find("#static_grid").topieGrid(App.infoStatic.tutorOptions);
    		//grid.reload({url:App.infoStatic.classNumOptions.url +"?&major="+value});
    		}
    	else if(type==4)
		{
		$("#static_grid").html('');
		teacheNo = sourceValue;
		var newMajor = major;
		if(isIE())
	   	 {
			newMajor = encodeURI(major);
	   	 }
		App.infoStatic.EmploymentOptions.url = App.href + "/api/job/staticByTutor?teacherNo="+value+"&major="+newMajor;
		grid = window.App.content.find("#static_grid").topieGrid(App.infoStatic.EmploymentOptions);
		//grid.reload({url:App.infoStatic.classNumOptions.url +"?&major="+value});
		}
    	console.log(value);
    }
    
    App.infoStatic.EmploymentOptions = {
            url: App.href + "/api/job/staticByTutor",
            beforeSend: function (request) {
                request.setRequestHeader("X-Auth-Token", App.token);
            },
            pageNum: 1,
            pageSize: 15,
            idFiled: "id",
            showCheckbox: true,
            checkboxWidth: "3%",
            showIndexNum: true,
            indexNumWidth: "5%",
            pageSelect: [2, 15, 30, 50],
            columns: [{
		        title: "学号",
		        field: "stuId",
		        sort: true,
		        width: "5%"
		    }, {
		        title: "学生名称",
		        field: "name",
		        sort: true
		    }, {
		        title: "联系电话",
		        field: "phone",
		        sort: true
		    },{
		        title: "性别",
		        field: "gender",
		        sort: true
		    },
		    {
		        title: "困难生",
		        field: "poorStudent",
		        sort: true
		    },
		    {
		        title: "领表",
		        field: "takeTable",
		        sort: true
		    },
		    {
		        title: "就业",
		        field: "employmentStatus",
		        sort: true
		    },
		    {
		        title: "签约",
		        field: "signStatus",
		        sort: true
		    }
		    ],
            actionColumnText: "操作",
            actionColumnWidth: "20%",
            tools: [
    				{
    				    text: " 返回",
    				    cls: "btn btn-primary",
    				    icon: "fa fa-cubes",
    				    handle: function (grid) {
    				    	App.infoStatic.gridChange(3,major);
    				    }
    				}],
            search: {
                rowEleNum: 2,
                items: [{
                    type: "text",
                    label: "学生名",
                    name: "name",
                    placeholder: "输入要搜索的学生名"
                }, {
                    title: "联系电话",
                    field: "contactPhone",
                    sort: true
                }]
            }
        };
    
    
    App.infoStatic.columns = [ 
    {
        sort: true,
        field: 'college',
        title: '学院',
        width: "25%",
        format: function(i, c) {
        	return '<a href="javascript:void(0)" onclick="javascript:App.infoStatic.gridChange(1,\''+c.college+'\')">'+c.college+'</a>';
        }
    },{
        field: 'tableRate',
        title: '领表率',
        width: "10%",
        sort: true,
        format: function(i, c) {
        	return (c.tableRate*100).toFixed(2)+"%"
        }
    }, 
    {
        field: 'signRate',
        title: '签约率',
        width: "10%",
        sort: true,
        format: function(i, c) {
        	return (c.signRate*100).toFixed(2)+"%"
        }
    }, {
        field: 'employmentRate',
        title: '就业率',
        width: "10%",
        sort: true,
        format: function(i, c) {
        	return (c.employmentRate*100).toFixed(2)+"%"
        }
    },{
        field: 'poorRate',
        title: '困难生数',
        width: "10%",
        sort: true
        /*format: function(i, c) {
        	return (c.poorRate*100).toFixed(2)+"%"
        }*/
    },
    {
        field: 'manToWoman',
        title: '男/女',
        width: "10%",
        sort: true,
        format: function(i, c) {
        	return c.man+'/'+c.woman;
        }
    }
    ];
    
    App.infoStatic.facultyColumns = [ 
                              {
                                  sort: true,
                                  field: 'faculty',
                                  title: '院系',
                                  width: "25%",
                                  format: function(i, c) {
                                  	return '<a href="javascript:void(0)" onclick="App.infoStatic.gridChange(2,\''+c.faculty+'\')">'+c.faculty+'</a>';
                                  }
                              },{
                                  field: 'tableRate',
                                  title: '领表率',
                                  width: "10%",
                                  sort: true,
                                  format: function(i, c) {
                                  	return (c.tableRate*100).toFixed(2)+"%"
                                  }
                              },  
                              {
                                  field: 'signRate',
                                  title: '签约率',
                                  width: "10%",
                                  sort: true,
                                  format: function(i, c) {
                                  	return (c.signRate*100).toFixed(2)+"%"
                                  }
                              }, {
                                  field: 'employmentRate',
                                  title: '就业率',
                                  width: "10%",
                                  sort: true,
                                  format: function(i, c) {
                                  	return (c.employmentRate*100).toFixed(2)+"%"
                                  }
                              },{
                                  field: 'poorRate',
                                  title: '困难生数',
                                  width: "10%",
                                  sort: true
                                  /*format: function(i, c) {
                                  	return (c.poorRate*100).toFixed(2)+"%"
                                  }*/
                              },
                              {
                                  field: 'manToWoman',
                                  title: '男/女',
                                  width: "10%",
                                  sort: true,
                                  format: function(i, c) {
                                  	return c.man+'/'+c.woman;
                                  }
                              }
                              ];
    
    App.infoStatic.majorColumns = [ 
                                     {
                                         sort: true,
                                         field: 'major',
                                         title: '专业',
                                         width: "25%",
                                         format: function(i, c) {
                                         	return '<a href="javascript:void(0)" onclick="App.infoStatic.gridChange(3,\''+c.major+'\')">'+c.major+'</a>';
                                         }
                                     },{
                                         field: 'tableRate',
                                         title: '领表率',
                                         width: "10%",
                                         sort: true,
                                         format: function(i, c) {
                                         	return (c.tableRate*100).toFixed(2)+"%"
                                         }
                                     },  
                                     {
                                         field: 'signRate',
                                         title: '签约率',
                                         width: "10%",
                                         sort: true,
                                         format: function(i, c) {
                                         	return (c.signRate*100).toFixed(2)+"%"
                                         }
                                     }, {
                                         field: 'employmentRate',
                                         title: '就业率',
                                         width: "10%",
                                         sort: true,
                                         format: function(i, c) {
                                         	return (c.employmentRate*100).toFixed(2)+"%"
                                         }
                                     },{
                                         field: 'poorRate',
                                         title: '困难生数',
                                         width: "10%",
                                         sort: true
                                         /*format: function(i, c) {
                                         	return (c.poorRate*100).toFixed(2)+"%"
                                         }*/
                                     },
                                     {
                                         field: 'manToWoman',
                                         title: '男/女',
                                         width: "10%",
                                         sort: true,
                                         format: function(i, c) {
                                         	return c.man+'/'+c.woman;
                                         }
                                     }
                                     ];
    
    App.infoStatic.collegeOptions = {
            url: App.href + "/api/job/staticByCollege",
            beforeSend: function (request) {
                request.setRequestHeader("X-Auth-Token", App.token);
            },
            pageNum: 1,
            pageSize: 15,
            idFiled: "id",
            showCheckbox: false,
            showIndexNum: true,
            indexNumWidth: "5%",
            pageSelect: [2, 15, 30, 50],
            columns: App.infoStatic.columns,
            actionColumnText: "操作",
            actionColumnWidth: "20%",
            actionColumns: [{
                            text: "统计图",
                            cls: "btn-primary btn-sm",
                            handle: function (index, data) {
                                var modal = $.topieModal({
                                    id: "studentForm",
                                    title: "学院统计",
                                    width:"600px",
                                    destroy: true
                                });
                                modal.$body.html('<div id="collegeCharts" style="width:500px !important;height:300px !important;display:block;">111</div>');
                                modal.show();
                              var mychart = echarts.init(document.getElementById('collegeCharts')); 
                              mychart.title = "学院统计图"
                              var option = {
                            		    title:{text:data.college+"统计"},
                            		    color: ['#3398DB'],
                            		    tooltip : {
                            		        trigger: 'axis',
                            		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                            		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                            		        }
                            		    },
                            		    grid: {
                            		        left: '3%',
                            		        right: '4%',
                            		        bottom: '3%',
                            		        containLabel: true
                            		    },
                            		    xAxis : [
                            		        {
                            		            type : 'category',
                            		            data : ['领表率','签约率','就业率','男生百分比','女生百分比'],
                            		            axisTick: {
                            		                alignWithLabel: true
                            		            }
                            		        }
                            		    ],
                            		    yAxis : [
                            		        {
                            		            type : 'value',
                            		            axisLabel: {
                            		                  show: true,
                            		                  interval: 'auto',
                            		                  formatter: '{value} %'
                            		                }
                            		        }
                            		    ],
                            		    series : [
                            		        {
                            		            name:'(单位%)',
                            		            type:'bar',
                            		            barWidth: '40%',
                            		            data:[(data.tableRate*100).toFixed(2),(data.signRate*100).toFixed(2),(data.employmentRate*100).toFixed(2),(data.man*100/(data.man+data.woman)).toFixed(2),(data.woman*100/(data.man+data.woman)).toFixed(2)]
                            		        }
                            		    ]
                            		};
                               mychart.setOption(option);
                            }
                        } 
                      ],
            search: {
                rowEleNum: 2,
                items: [{
                    type: "select",
                    label: "毕业年份",
                    name: "graduateDate",
                    id: 'graduateDate',
                    value:getGraduateYear(),
                    itemsUrl:App.href+"/api/dict/4?topie_token=" + App.token
                }]
            }
        };
    
    App.infoStatic.facultyOptions = {
            url: App.href + "/api/job/staticByFaculty",
            beforeSend: function (request) {
                request.setRequestHeader("X-Auth-Token", App.token);
            },
            pageNum: 1,
            pageSize: 15,
            idFiled: "id",
            showCheckbox: false,
            showIndexNum: true,
            indexNumWidth: "5%",
            pageSelect: [2, 15, 30, 50],
            columns: App.infoStatic.facultyColumns,
            actionColumnText: "操作",
            actionColumnWidth: "20%",
            actionColumns: [{
                text: "统计图",
                cls: "btn-primary btn-sm",
                handle: function (index, data) {
                    var modal = $.topieModal({
                        id: "studentForm",
                        title: "院系统计",
                        width:"600px",
                        destroy: true
                    });
                    modal.$body.html('<div id="collegeCharts" style="width:500px !important;height:300px !important;display:block;">111</div>');
                    modal.show();
                  var mychart = echarts.init(document.getElementById('collegeCharts')); 
                  mychart.title = "院系统计图"
                  var option = {
                		    title:{text:data.faculty+"统计"},
                		    color: ['#3398DB','#23Db45'],
                		    tooltip : {
                		        trigger: 'axis',
                		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                		        }
                		    },
                		    grid: {
                		        left: '3%',
                		        right: '4%',
                		        bottom: '3%',
                		        containLabel: true
                		    },
                		    xAxis : [
                		        {
                		            type : 'category',
                		            data : ['领表率','签约率','就业率','男生百分比','女生百分比'],
                		            axisTick: {
                		                alignWithLabel: true
                		            }
                		        }
                		    ],
                		    yAxis : [
                		        {
                		            type : 'value',
                		            axisLabel: {
                		                  show: true,
                		                  interval: 'auto',
                		                  formatter: '{value} %'
                		                }
                		        }
                		    ],
                		    series : [
                		        {
                		            name:'(单位%)',
                		            type:'bar',
                		            barWidth: '40%',
                		            data:[(data.tableRate*100).toFixed(2),(data.signRate*100).toFixed(2),(data.employmentRate*100).toFixed(2),(data.man*100/(data.man+data.woman)).toFixed(2),(data.woman*100/(data.man+data.woman)).toFixed(2)]
                		        }
                		    ]
                		};
                   mychart.setOption(option);
                }
            } 
          ],
            tools: [
    				{
    				    text: " 返回",
    				    cls: "btn btn-primary",
    				    icon: "fa fa-cubes",
    				    handle: function (grid) {
    				    	App.infoStatic.gridChange(0,'');
    				    }
    				}],
            	search: {
                    rowEleNum: 2,
                    items: [{
                        type: "select",
                        label: "毕业年份",
                        name: "graduateDate",
                        id: 'graduateDate',
                        value:getGraduateYear(),
                        itemsUrl:App.href+"/api/dict/4?topie_token=" + App.token
                    }]
                }
        };
    
    App.infoStatic.majorOptions = {
            url: App.href + "/api/job/staticByMajor",
            beforeSend: function (request) {
                request.setRequestHeader("X-Auth-Token", App.token);
            },
            pageNum: 1,
            pageSize: 15,
            idFiled: "id",
            showCheckbox: false,
            showIndexNum: true,
            indexNumWidth: "5%",
            pageSelect: [2, 15, 30, 50],
            columns: App.infoStatic.majorColumns,
            actionColumnText: "操作",
            actionColumnWidth: "20%",
            actionColumns: [{
                text: "统计图",
                cls: "btn-primary btn-sm",
                handle: function (index, data) {
                    var modal = $.topieModal({
                        id: "studentForm",
                        title: "院系统计",
                        width:"600px",
                        destroy: true
                    });
                    modal.$body.html('<div id="collegeCharts" style="width:500px !important;height:300px !important;display:block;">111</div>');
                    modal.show();
                  var mychart = echarts.init(document.getElementById('collegeCharts')); 
                  mychart.title = "专业统计图"
                  var option = {
                		    title:{text:data.major+"专业统计"},
                		    color: ['#3398DB','#FF3300'],
                		    tooltip : {
                		        trigger: 'axis',
                		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                		        }
                		    },
                		    grid: {
                		        left: '3%',
                		        right: '4%',
                		        bottom: '3%',
                		        containLabel: true
                		    },
                		    xAxis : [
                		        {
                		            type : 'category',
                		            data : ['领表率','签约率','就业率','男生百分比','女生百分比'],
                		            axisTick: {
                		                alignWithLabel: true
                		            }
                		        }
                		    ],
                		    yAxis : [
                		        {
                		            type : 'value',
                		            axisLabel: {
                		                  show: true,
                		                  interval: 'auto',
                		                  formatter: '{value} %'
                		                }
                		        }
                		    ],
                		    series : [
                		        {
                		            name:'(单位%)',
                		            type:'bar',
                		            barWidth: '40%',
                		            data:[(data.tableRate*100).toFixed(2),(data.signRate*100).toFixed(2),(data.employmentRate*100).toFixed(2),(data.man*100/(data.man+data.woman)).toFixed(2),(data.woman*100/(data.man+data.woman)).toFixed(2)]
                		        }
                		    ]
                		};
                   mychart.setOption(option);
                }
            } 
          ],
            tools: [
    				{
    				    text: " 返回",
    				    cls: "btn btn-primary",
    				    icon: "fa fa-cubes",
    				    handle: function (grid) {
    				    	App.infoStatic.gridChange(1,college);
    				    }
    				}],
            	search: {
                    rowEleNum: 2,
                    items: [{
                        type: "select",
                        label: "毕业年份",
                        name: "graduateDate",
                        id: 'graduateDate',
                        value:getGraduateYear(),
                        itemsUrl:App.href+"/api/dict/4?topie_token=" + App.token
                    }]
                }
        };
    
    App.infoStatic.classNumOptions = {
            url: App.href + "/api/job/staticByClassNum",
            beforeSend: function (request) {
                request.setRequestHeader("X-Auth-Token", App.token);
            },
            pageNum: 1,
            pageSize: 15,
            idFiled: "id",
            showCheckbox: false,
            showIndexNum: true,
            indexNumWidth: "5%",
            pageSelect: [2, 15, 30, 50],
            columns: [ 
                      {
                          sort: true,
                          field: 'classNum',
                          title: '班级',
                          width: "25%",
                      },
                      {
                          field: 'tableRate',
                          title: '领表率',
                          width: "10%",
                          sort: true,
                          format: function(i, c) {
                          	return (c.tableRate*100).toFixed(2)+"%"
                          }
                      },
                      {
                          field: 'signRate',
                          title: '签约率',
                          width: "10%",
                          sort: true,
                          format: function(i, c) {
                          	return (c.signRate*100).toFixed(2)+"%"
                          }
                      }, {
                          field: 'employmentRate',
                          title: '就业率',
                          width: "10%",
                          sort: true,
                          format: function(i, c) {
                          	return (c.employmentRate*100).toFixed(2)+"%"
                          }
                      },{
                          field: 'poorRate',
                          title: '困难生率',
                          width: "10%",
                          sort: true
                         /* format: function(i, c) {
                          	return (c.poorRate*100).toFixed(2)+"%"
                          }*/
                      },
                      {
                          field: 'manToWoman',
                          title: '男/女',
                          width: "10%",
                          sort: true,
                          format: function(i, c) {
                          	return c.man+'/'+c.woman;
                          }
                      }
                      ],
            actionColumnText: "操作",
            actionColumnWidth: "20%",
            actionColumns: [{
                text: "统计图",
                cls: "btn-primary btn-sm",
                handle: function (index, data) {
                    var modal = $.topieModal({
                        id: "studentForm",
                        title: "院系统计",
                        width:"600px",
                        destroy: true
                    });
                    modal.$body.html('<div id="collegeCharts" style="width:500px !important;height:300px !important;display:block;">111</div>');
                    modal.show();
                  var mychart = echarts.init(document.getElementById('collegeCharts')); 
                  mychart.title = "班级统计图"
                  var option = {
                		    title:{text:data.classNum+"统计"},
                		    color: ['#3398DB','#FF3300'],
                		    tooltip : {
                		        trigger: 'axis',
                		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                		        }
                		    },
                		    grid: {
                		        left: '3%',
                		        right: '4%',
                		        bottom: '3%',
                		        containLabel: true
                		    },
                		    xAxis : [
                		        {
                		            type : 'category',
                		            data : ['领表率','签约率','就业率','男生百分比','女生百分比'],
                		            axisTick: {
                		                alignWithLabel: true
                		            }
                		        }
                		    ],
                		    yAxis : [
                		        {
                		            type : 'value',
                		            axisLabel: {
                		                  show: true,
                		                  interval: 'auto',
                		                  formatter: '{value} %'
                		                }
                		        }
                		    ],
                		    series : [
                		        {
                		            name:'(单位%)',
                		            type:'bar',
                		            barWidth: '40%',
                		            data:[(data.tableRate*100).toFixed(2),(data.signRate*100).toFixed(2),(data.employmentRate*100).toFixed(2),(data.man*100/(data.man+data.woman)).toFixed(2),(data.woman*100/(data.man+data.woman)).toFixed(2)]
                		        }
                		    ]
                		};
                   mychart.setOption(option);
                }
            } 
          ],
            tools: [
    				{
    				    text: " 返回",
    				    cls: "btn btn-primary",
    				    icon: "fa fa-cubes",
    				    handle: function (grid) {
    				    	App.infoStatic.gridChange(2,'');
    				    }
    				}],
            search: {
                rowEleNum: 2,
                items: [{
                    type: "select",
                    label: "毕业年份",
                    name: "graduateDate",
                    id: 'graduateDate',
                    value:getGraduateYear(),
                    itemsUrl:App.href+"/api/dict/4?topie_token=" + App.token
                }]
            }
        };
    
    App.infoStatic.tutorOptions = {
            url: App.href + "/api/job/staticTutor",
            beforeSend: function (request) {
                request.setRequestHeader("X-Auth-Token", App.token);
            },
            pageNum: 1,
            pageSize: 15,
            idFiled: "id",
            showCheckbox: false,
            showIndexNum: true,
            indexNumWidth: "5%",
            pageSelect: [2, 15, 30, 50],
            columns: [ 
                      {
                          sort: true,
                          field: 'tutor',
                          title: '导师',
                          width: "25%",
                          format: function(i, c) {
                        	  return '<a href="javascript:void(0)" onclick="javascript:App.infoStatic.gridChange(4,\''+c.teacherNo+'\')">'+c.tutor+'</a>';
                          }
                      }, {
                          field: 'signRate',
                          title: '签约率',
                          width: "10%",
                          sort: true,
                          format: function(i, c) {
                          	return (c.signRate*100).toFixed(2)+"%"
                          }
                      }, {
                          field: 'employmentRate',
                          title: '就业率',
                          width: "10%",
                          sort: true,
                          format: function(i, c) {
                          	return (c.employmentRate*100).toFixed(2)+"%"
                          }
                      },{
                          field: 'poorRate',
                          title: '困难生数',
                          width: "10%",
                          sort: true
                          /*format: function(i, c) {
                          	return (c.poorRate*100).toFixed(2)+"%"
                          }*/
                      },
                      {
                          field: 'manToWoman',
                          title: '男/女',
                          width: "10%",
                          sort: true,
                          format: function(i, c) {
                          	return c.man+'/'+c.woman;
                          }
                      }
                      ],
            actionColumnText: "操作",
            actionColumnWidth: "20%",
            tools: [
    				{
    				    text: " 返回",
    				    cls: "btn btn-primary",
    				    icon: "fa fa-cubes",
    				    handle: function (grid) {
    				    	App.infoStatic.gridChange(2,faculty);
    				    }
    				}],
            search: {
                rowEleNum: 2,
                items: [
                        {
                    type: "select",
                    label: "毕业年份",
                    name: "graduateDate",
                    id: 'graduateDate',
                    value:getGraduateYear(),
                    itemsUrl:App.href+"/api/dict/4?topie_token=" + App.token
                }]/*,{
                    type: "text",
                    label: "导师名称",
                    name: "tutor",
                    placeholder: "输入要搜索的导师名"
                }]*/
            }
        };
    
    App.infoStatic.initEvents = function () {
        grid = window.App.content.find("#static_grid").topieGrid(App.infoStatic.collegeOptions);
    }
})(jQuery, window, document);