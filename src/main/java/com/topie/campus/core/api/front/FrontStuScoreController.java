package com.topie.campus.core.api.front;

import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.common.utils.PageConvertUtil;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.model.StuCet;
import com.topie.campus.core.model.StuScore;
import com.topie.campus.core.service.IStuCetService;
import com.topie.campus.core.service.IStuSeleCourseService;
import com.topie.campus.core.service.IStudentScoreService;
import com.topie.campus.security.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/front/student")
public class FrontStuScoreController {

    @Autowired
    IStudentScoreService studentScoreService;

    @Autowired
    IStuCetService stuCetService;

    @Autowired
    IStuSeleCourseService stuSeleCourseService;

    @RequestMapping("/score")
    @ResponseBody
    public Result findByPage(StuScore stuScore, int pageSize, int pageNum) {
        String loginName = SecurityUtil.getCurrentSecurityUser().getLoginName();
        stuScore.setStuId(loginName);
        SimplePageInfo<StuScore> pageInfo = studentScoreService.findByPage(pageNum, pageSize, stuScore);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }

    @RequestMapping("/cetscore")
    @ResponseBody
    public Result findByPage(StuCet stuCet, int pageSize, int pageNum) {
        String loginName = SecurityUtil.getCurrentSecurityUser().getLoginName();
        stuCet.setStuId(loginName);
        SimplePageInfo<StuCet> pageInfo = stuCetService.findByPage(pageNum, pageSize, stuCet);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }

}
