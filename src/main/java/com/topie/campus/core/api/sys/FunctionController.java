package com.topie.campus.core.api.sys;

import com.topie.campus.common.handler.ControllerExceptionHandler;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.security.exception.AuBzConstant;
import com.topie.campus.security.exception.AuthBusinessException;
import com.topie.campus.security.service.UserService;
import com.topie.campus.security.utils.SecurityUtils;
import com.topie.campus.security.vo.FunctionVO;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by cgj on 2016/4/9.
 */
@Controller
@RequestMapping("/api/sys/function")
public class FunctionController extends ControllerExceptionHandler {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    @ResponseBody
    public Result myFunction() {
        String currentLoginName = SecurityUtils.getCurrentUserName();
        if (StringUtils.isEmpty(currentLoginName)) {
            throw new AuthBusinessException(AuBzConstant.IS_NOT_LOGIN);
        }
        List<FunctionVO> function = userService.findUserFunctionByLoginName(currentLoginName);
        return ResponseUtil.success(function);
    }


}
