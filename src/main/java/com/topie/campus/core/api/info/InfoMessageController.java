package com.topie.campus.core.api.info;

import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.common.utils.PageConvertUtil;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.dto.MessageSearchParams;
import com.topie.campus.core.dto.MessageSimpleDto;
import com.topie.campus.core.service.IMessageService;
import com.topie.campus.tools.excel.ExcelFileUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by chenguojun on 2016/11/13.
 */
@Controller
@RequestMapping("/api/info/message")
public class InfoMessageController {

    @Autowired
    private IMessageService iMessageService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Result page(@RequestParam(value = "messagePeriod") String messagePeriod,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize) {
        MessageSearchParams messageSearchParams = new MessageSearchParams();
        if (StringUtils.isNotEmpty(messagePeriod)) {
            String[] arr = messagePeriod.split(" 到 ");
            messageSearchParams.setMessageTimeFrom(arr[0]);
            messageSearchParams.setMessageTimeTo(arr[1]);
        }
        SimplePageInfo<MessageSimpleDto> pageInfo = iMessageService
                .findMessageByPage(messageSearchParams, pageNum, pageSize);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(HttpServletResponse response, @RequestParam(value = "messagePeriod") String messagePeriod)
            throws Exception {
        MessageSearchParams messageSearchParams = new MessageSearchParams();
        if (StringUtils.isNotEmpty(messagePeriod)) {
            String[] arr = messagePeriod.split(" 到 ");
            messageSearchParams.setMessageTimeFrom(arr[0]);
            messageSearchParams.setMessageTimeTo(arr[1]);
        }
        List<MessageSimpleDto> list = iMessageService.findMessageList(messageSearchParams);
        String[] headers = new String[] { "留言ID", "留言内容", "留言给", "留言者", "留言时间", "回复内容", "回复者", "回复时间" };
        String fileName = "留言信息.xlsx";
        ExcelFileUtil.reponseXlsx(response, fileName, headers, list);
    }
}
