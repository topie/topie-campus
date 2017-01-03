function getStudyYear() {
    var studyYear;
    var studyYearNum;
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    if (1<month < 8) {
        studyYear = (year - 1) + "-" + year;
        studyYearNum = 2;
        $("#studyYear").val(studyYear);
        $("#studyYearNum").val(studyYearNum);
    }
    else {
        studyYear = year + "-" + (year + 1);
        studyYearNum = 1;
        $("#studyYear").val(studyYear);
        $("#studyYearNum").val(studyYearNum);
    }
    return 'studyYear=' + studyYear + '&studyYearNum' + studyYearNum;
}

function initStudyYear() {
    var studyYear;
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    if (1<month < 8) {
        studyYear = (year - 1) + "-" + year;
        var studyYearNum = 2;
        $("select[name=studyYear]").val(studyYear);
        $("select[name=studyYearNum]").val(studyYearNum);
    }
    else {
        studyYear = year + "-" + (year + 1);
        studyYearNum = 1;
        $("select[name=studyYear]").val(studyYear);
        $("select[name=studyYearNum]").val(studyYearNum);
    }
}

function getGraduateYear() {
    var graduateDate;
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    if (1<month < 8) {
        graduateDate = year;
        $("#graduateDate").val(graduateDate);
    }
    else {
        graduateDate = year + 1;
        $("#graduateDate").val(graduateDate);
    }
    return graduateDate;
}
function isIE() { //ie?
    return !!(!!window.ActiveXObject || "ActiveXObject" in window);
}
