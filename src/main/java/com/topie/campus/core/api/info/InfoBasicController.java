package com.topie.campus.core.api.info;

import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.service.IInfoBasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by chenguojun on 9/26/16.
 */
@Controller
@RequestMapping("/api/info/basic")
public class InfoBasicController {

    @Autowired
    private IInfoBasicService iInfoBasicService;

    @RequestMapping(value = "/bind", method = RequestMethod.GET)
    @ResponseBody
    public Result bind(@RequestParam(value = "studentId") Integer studentId,
            @RequestParam(value = "teacherId") Integer teacherId) {
        iInfoBasicService.insertToBindStudentTeacher(studentId, teacherId);
        return ResponseUtil.success();
    }

    @RequestMapping(value = "/unbind", method = RequestMethod.GET)
    @ResponseBody
    public Result unbind(@RequestParam(value = "studentId") Integer studentId,
            @RequestParam(value = "teacherId") Integer teacherId) {
        iInfoBasicService.deleteToUnbindStudentTeacher(studentId, teacherId);
        return ResponseUtil.success();
    }
}
