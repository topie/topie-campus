package com.topie.campus.core.api.info;

import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.common.constants.ResultCode;
import com.topie.campus.common.utils.PageConvertUtil;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.model.Notice;
import com.topie.campus.core.service.INoticeService;
import com.topie.campus.security.utils.SecurityUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by chenguojun on 9/21/16.
 */
@Controller
@RequestMapping("/api/info/notice")
public class InfoNoticeController {

    @Autowired
    private INoticeService iNoticeService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Result page(Notice notice,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize) {
        SimplePageInfo<Notice> pageInfo = iNoticeService.findNoticeList(notice, pageNum, pageSize);
        return ResponseUtil.success(PageConvertUtil.grid(pageInfo));
    }

    @RequestMapping(value = "/load/{noticeId}", method = RequestMethod.GET)
    @ResponseBody
    public Result loadNotice(@PathVariable(value = "noticeId") Integer noticeId) {
        Notice notice = iNoticeService.selectByKey(noticeId);
        List<Integer> attachmentList = iNoticeService.findAttachmentIds(noticeId);
        String attachments = StringUtils.join(attachmentList, ",");
        notice.setAttachments(attachments);
        return ResponseUtil.success(notice);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Result noticeInsert(Notice notice, Integer[] attachments) {
        notice.setNoticePublishUser(SecurityUtil.getCurrentUserName());
        int result = iNoticeService.insertSelective(notice);

        if (result > 0) {
            if (attachments != null && attachments.length > 0) {
                for (Integer attr : attachments) {
                    iNoticeService.insertAttachment(notice.getNoticeId(), attr);
                }
            }
            return ResponseUtil.success(ResultCode.OP_SUCCESS);
        }
        return ResponseUtil.error(ResultCode.OP_FAIL);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Result noticeUpdate(Notice notice, Integer[] attachments) {
        int result = iNoticeService.updateSelective(notice);
        if (result > 0) {
            if (attachments != null && attachments.length > 0) {
                iNoticeService.deleteAttachment(notice.getNoticeId());
                for (Integer attr : attachments) {
                    iNoticeService.insertAttachment(notice.getNoticeId(), attr);
                }
            }
            return ResponseUtil.success(ResultCode.OP_SUCCESS);
        }
        return ResponseUtil.error(ResultCode.OP_FAIL);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public Result noticeDelete(@RequestParam("id") Integer noticeId) {
        int result = iNoticeService.delete(noticeId);
        if (result > 0) return ResponseUtil.success(ResultCode.OP_SUCCESS);
        return ResponseUtil.error(ResultCode.OP_FAIL);
    }

}
