 function getStudyYear()
    {
    	var studyYear
    	var date = new Date();
    	var year = date.getFullYear();
    	var month = date.getMonth()+1;
    	if(month<8)
    	{
    		studyYear = (year-1)+"-"+year;
    		studyYearNum = 2; 
    	    $("#studyYear").val(studyYear);
    	    $("#studyYearNum").val(studyYearNum);
    	}
    	else
    		{
    		studyYear = year+"-"+(year+1);
    		studyYearNum = 1; 
    	    $("#studyYear").val(studyYear);
    	    $("#studyYearNum").val(studyYearNum);
    		}
    	return 'studyYear='+studyYear+'&studyYearNum'+studyYearNum;
    }
 
 function getGraduateYear()
 {
 	var graduateDate;
 	var date = new Date();
 	var year = date.getFullYear();
 	var month = date.getMonth()+1;
 	if(month<8)
 	{
 		graduateDate = year;
 	    $("#graduateDate").val(graduateDate);
 	}
 	else
 		{
 		graduateDate=year+1;
 		console.log(graduateDate);
 	    $("#graduateDate").val(graduateDate);
 		}
 	return graduateDate;
 }