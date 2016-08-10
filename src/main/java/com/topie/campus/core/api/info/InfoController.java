package com.topie.campus.core.api.info;

import com.topie.campus.common.utils.DateStyle;
import com.topie.campus.common.utils.DateUtil;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.model.InfoBasic;
import com.topie.campus.core.service.InfoBasicService;
import com.topie.campus.security.service.UserService;
import com.topie.campus.tools.excel.ExcelFileUtil;
import com.topie.campus.tools.excel.ExcelLogs;
import com.topie.campus.tools.excel.ExcelUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by chenguojun on 8/10/16.
 */
@Controller
@RequestMapping("/api/info")
public class InfoController {
    @Autowired
    private UserService userService;
    @Autowired
    private InfoBasicService infoBasicService;

    @RequestMapping(value = "/template/download", method = RequestMethod.GET)
    public void download(HttpServletResponse response) throws Exception {
        String filePath = this.getClass().getResource("/excel/template.xlsx").getPath();
        ExcelFileUtil.download(response, filePath, "模板.xlsx");
    }

    @RequestMapping(value = "/excel/upload", method = RequestMethod.POST)
    public void upload(HttpServletResponse response, HttpServletRequest httpServletRequest,
                       @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        try {
            if (file == null || file.isEmpty()) {
                ResponseUtil.writeJson(response, new Result(500, "附件为空"));
                return;
            }
            ExcelLogs logs = new ExcelLogs();
            Collection<Map> teacher;
            if (file.getOriginalFilename().toLowerCase().endsWith(".xlsx")) {
                teacher = ExcelUtil.importExcelX(Map.class, file.getInputStream(), 0, "dd/MM/yy", logs);
            } else {
                teacher = ExcelUtil.importExcel(Map.class, file.getInputStream(), 0, "dd/MM/yy", logs);
            }
            Integer i = 0;
            for (Map map : teacher) {
                Integer userCode = ++i;
                String code = ((Double) map.get("唯一编码")).toString();
                String name = (String) map.get("姓名");
                Date birth = (Date) map.get("出生日期");
                String gender = (String) map.get("性别");
                String mobile = ((Double) map.get("手机")).toString();
                System.out.println(DateUtil.DateToString(birth, DateStyle.YYYY_MM_DD));
                InfoBasic t = new InfoBasic();
                t.setUserId(i);
                t.setUserType(1);
                t.setUserCode(code);
                t.setUserName(name);
                t.setBirth(birth);
                t.setGender((gender.equals("男") ? 1 : 0));
                t.setMobilePhone(mobile);
                infoBasicService.save(t);
                System.out.println(t.getUserId());
            }
            ResponseUtil.writeJson(response, new Result(200, teacher));
        } catch (Exception e) {
            e.printStackTrace();
            ResponseUtil.writeJson(response, new Result(500, "解析错误"));
        }
    }
}
