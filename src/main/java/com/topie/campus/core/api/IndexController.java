package com.topie.campus.core.api;

import com.github.pagehelper.PageInfo;
import com.topie.campus.common.utils.PageConvertUtil;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.security.model.User;
import com.topie.campus.security.service.UserService;
import com.topie.campus.security.utils.SecurityUtils;
import com.topie.campus.tools.freemarker.FreeMarkerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenguojun on 8/23/16.
 */
@Controller
@RequestMapping("/api")
public class IndexController {

    @Autowired
    private FreeMarkerUtil freeMarkerUtil;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ResponseBody
    public Object index() throws Exception {
        Map params = new HashMap<>();
        params.put("name", SecurityUtils.getCurrentUserName());
        String html = freeMarkerUtil.getStringFromTemplate("/", "index.ftl", params);
        return ResponseUtil.success(html);
    }

    @RequestMapping(value = "/indexGrid", method = RequestMethod.GET)
    @ResponseBody
    public Result indexGrid(HttpServletRequest request, User user,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize) {
        PageInfo<User> pageInfo = userService.findUserList(pageNum, pageSize, user);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }
}
