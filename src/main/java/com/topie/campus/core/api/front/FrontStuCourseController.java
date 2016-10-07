package com.topie.campus.core.api.front;

import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.common.utils.PageConvertUtil;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.model.StuSeleCourse;
import com.topie.campus.core.service.IStuSeleCourseService;
import com.topie.campus.security.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/front/student")
public class FrontStuCourseController {

    @Autowired
    IStuSeleCourseService stuSeleCourseService;

    @RequestMapping("/selectCourse")
    @ResponseBody
    public Result selectCourse(StuSeleCourse stuSeleCourse, int pageSize, int pageNum) {
        String loginName = SecurityUtil.getCurrentUserName();
        stuSeleCourse.setStuId(loginName);
        SimplePageInfo<StuSeleCourse> pageInfo = stuSeleCourseService.findByPage(pageNum, pageSize, stuSeleCourse);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }
}
