package com.topie.campus.core.api.front;

import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.common.utils.PageConvertUtil;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.model.StuSeleCourse;
import com.topie.campus.core.model.StuTimeTable;
import com.topie.campus.core.service.IStuSeleCourseService;
import com.topie.campus.core.service.IStuTimeTableService;
import com.topie.campus.security.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/front/student")
public class FrontStuCourseController {

    @Autowired
    IStuSeleCourseService stuSeleCourseService;

    @Autowired
    IStuTimeTableService iStuTimeTableService;

    @RequestMapping("/selectCourse")
    @ResponseBody
    public Result selectCourse(StuSeleCourse stuSeleCourse, int pageSize, int pageNum) {
        String loginName = SecurityUtil.getCurrentUserName();
        stuSeleCourse.setStuId(loginName);
        SimplePageInfo<StuSeleCourse> pageInfo = stuSeleCourseService.findByPage(pageNum, pageSize, stuSeleCourse);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }

    @RequestMapping("/examCourse")
    @ResponseBody
    public Result examCourse(StuSeleCourse stuSeleCourse, int pageSize, int pageNum) {
        String loginName = SecurityUtil.getCurrentUserName();
        stuSeleCourse.setStuId(loginName);
        stuSeleCourse.setExamTime("filter");
        SimplePageInfo<StuSeleCourse> pageInfo = stuSeleCourseService.findByPage(pageNum, pageSize, stuSeleCourse);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }

    @RequestMapping("/timeTable")
    @ResponseBody
    public Result timeTable(StuTimeTable stuTimeTable) {
        String loginName = SecurityUtil.getCurrentUserName();
        stuTimeTable.setStuId(loginName);
        List<StuTimeTable> list = iStuTimeTableService.find(stuTimeTable);
        return ResponseUtil.success(list);
    }

    @RequestMapping("/sTimeTable")
    @ResponseBody
    public Result sTimeTable(StuTimeTable stuTimeTable, @RequestParam("studentNo") String studentNo) {
        stuTimeTable.setStuId(studentNo);
        List<StuTimeTable> list = iStuTimeTableService.find(stuTimeTable);
        return ResponseUtil.success(list);
    }
}
