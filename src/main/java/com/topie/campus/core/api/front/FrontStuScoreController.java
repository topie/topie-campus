package com.topie.campus.core.api.front;

import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.common.utils.PageConvertUtil;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.dto.StaticScoreDto;
import com.topie.campus.core.model.StuCet;
import com.topie.campus.core.model.StuScore;
import com.topie.campus.core.service.IStuCetService;
import com.topie.campus.core.service.IStuSeleCourseService;
import com.topie.campus.core.service.IStudentScoreService;
import com.topie.campus.core.service.IStudentService;
import com.topie.campus.security.utils.SecurityUtil;

import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    IStudentService iStudentService;

    @RequestMapping("/score")
    @ResponseBody
    public Result findByPage(StuScore stuScore, int pageSize, int pageNum) {
        Integer userId = SecurityUtil.getCurrentUserId();
        if (userId == null) {
            return ResponseUtil.error(401, "未登录");
        }
        String studentNo = iStudentService.findStudentNoByUserId(userId);
        if (StringUtils.isEmpty(studentNo)) {
            return ResponseUtil.error(401, "当前用户非学生角色");
        }
        stuScore.setStuId(studentNo);
        SimplePageInfo<StuScore> pageInfo = studentScoreService.findByPage(pageNum, pageSize, stuScore);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }
    
    @RequestMapping("/staticScore")
    @ResponseBody
    public Result findStaticScoreByStudentId() {
        Integer userId = SecurityUtil.getCurrentUserId();
        if (userId == null) {
            return ResponseUtil.error(401, "未登录");
        }
        String studentNo = iStudentService.findStudentNoByUserId(userId);
        if (StringUtils.isEmpty(studentNo)) {
            return ResponseUtil.error(401, "当前用户非学生角色");
        }
        StaticScoreDto dto = studentScoreService.findByStuScoreStatic(studentNo);
        return ResponseUtil.success(dto);
    }

    @RequestMapping("/cetscore")
    @ResponseBody
    public Result findByPage(StuCet stuCet, int pageSize, int pageNum) {
        Integer userId = SecurityUtil.getCurrentUserId();
        if (userId == null) {
            return ResponseUtil.error(401, "未登录");
        }
        String studentNo = iStudentService.findStudentNoByUserId(userId);
        if (StringUtils.isEmpty(studentNo)) {
            return ResponseUtil.error(401, "当前用户非学生角色");
        }
        stuCet.setStuId(studentNo);
        SimplePageInfo<StuCet> pageInfo = stuCetService.findByPage(pageNum, pageSize, stuCet);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }

}
