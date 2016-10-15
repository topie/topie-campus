package com.topie.campus.core.api;

import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.security.security.OrangeSideUserCache;
import com.topie.campus.security.utils.SecurityUtil;
import com.topie.campus.tools.freemarker.FreeMarkerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenguojun on 8/23/16.
 */
@Controller
@RequestMapping("/api")
public class IndexController {

    @Autowired
    OrangeSideUserCache orangeSideUserCache;

    @Autowired
    private FreeMarkerUtil freeMarkerUtil;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ResponseBody
    public Object index() throws Exception {
        Map params = new HashMap<>();
        params.put("name", SecurityUtil.getCurrentUserName());
        String html = freeMarkerUtil.getStringFromTemplate("/", "index.ftl", params);
        return ResponseUtil.success(html);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public Result authenticationRequest() {
        Integer userId = SecurityUtil.getCurrentUserId();
        orangeSideUserCache.removeUserFromCacheByUserId(userId);
        return ResponseUtil.success();
    }
}
