package com.topie.campus.core.api.info;

import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.common.utils.PageConvertUtil;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.dto.LoginInfoSearchParams;
import com.topie.campus.core.model.LoginInfo;
import com.topie.campus.core.service.ILoginInfoService;
import com.topie.campus.tools.excel.ExcelFileUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenguojun on 2016/11/13.
 */
@Controller
@RequestMapping("/api/info/loginInfo")
public class InfoLoginInfoController {

    @Autowired
    private ILoginInfoService iLoginInfoService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Result page(@RequestParam(value = "timePeriod") String timePeriod,
            @RequestParam(value = "userType", required = false, defaultValue = "1") Integer userType,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize) {
        LoginInfoSearchParams loginInfoSearchParams = new LoginInfoSearchParams();
        if (StringUtils.isNotEmpty(timePeriod)) {
            String[] arr = timePeriod.split(" 到 ");
            loginInfoSearchParams.setLoginTimeFrom(arr[0]);
            loginInfoSearchParams.setLoginTimeTo(arr[1]);
        }
        SimplePageInfo<LoginInfo> pageInfo = new SimplePageInfo<>();
        if (userType == 2) {
            pageInfo = iLoginInfoService.findStudentLoginInfoByPage(loginInfoSearchParams, pageNum, pageSize);
        } else {
            pageInfo = iLoginInfoService.findTeacherLoginInfoByPage(loginInfoSearchParams, pageNum, pageSize);
        }
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(HttpServletResponse response, @RequestParam(value = "timePeriod") String timePeriod,
            @RequestParam(value = "userType", required = false, defaultValue = "1") Integer userType) throws Exception {
        LoginInfoSearchParams loginInfoSearchParams = new LoginInfoSearchParams();
        if (StringUtils.isNotEmpty(timePeriod)) {
            String[] arr = timePeriod.split(" 到 ");
            loginInfoSearchParams.setLoginTimeFrom(arr[0]);
            loginInfoSearchParams.setLoginTimeTo(arr[1]);
        }
        List<LoginInfo> list = new ArrayList<>();
        if (userType == 2) {
            list = iLoginInfoService.findStudentLoginInfoList(loginInfoSearchParams);
        } else {
            list = iLoginInfoService.findTeacherLoginInfoList(loginInfoSearchParams);
        }
        String[] headers = null;
        String fileName = "登录信息.xlsx";
        if (userType == 2) {
            headers = new String[] { "登录用户id", "登录用户登录名", "学生名称", "登录IP", "登录时间" };
        } else {
            headers = new String[] { "登录用户id", "登录用户登录名", "导师名称", "登录IP", "登录时间" };
        }
        ExcelFileUtil.reponseXlsx(response, fileName, headers, list);
    }
}
