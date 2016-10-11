package com.topie.campus.core.api.common;

import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.common.utils.Result;
import com.topie.campus.core.enums.Degree;
import com.topie.campus.core.enums.EducationBackground;
import com.topie.campus.core.enums.EthnicGroup;
import com.topie.campus.core.enums.PoliticalStatus;
import com.topie.campus.core.model.Attachment;
import com.topie.campus.core.model.Notice;
import com.topie.campus.core.service.IAttachmentService;
import com.topie.campus.core.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenguojun on 8/31/16.
 * 通用api 我的信息 查看通知公告
 */
@Controller
@RequestMapping("/api/common")
public class CommonController {

    private final static Integer IMAGE = 0;

    private final static Integer FILE = 1;

    @Autowired
    private IAttachmentService iAttachmentService;

    @Autowired
    private INoticeService iNoticeService;

    @RequestMapping(value = "/uploadFile", method = { RequestMethod.POST })
    @ResponseBody
    public Result uploadFile(HttpServletRequest request,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        try {
            if (file == null || file.isEmpty()) {
                return ResponseUtil.error("请选择文件。");
            }
            //文件夹名
            String dirName = "file";
            // 最大文件大小
            long maxSize = 20000000;

            // 定义允许上传的文件扩展名
            HashMap<String, String> extMap = new HashMap<String, String>();
            extMap.put(dirName,
                    "doc,docx,xls,xlsx,ppt,pptx,txt,zip,rar,gz,bz2,gif,jpg,jpeg,png,bmp,swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");

            Attachment attachment = iAttachmentService
                    .uploadFileAttachement(request, file, dirName, maxSize, extMap, FILE);
            return ResponseUtil.success(attachment);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.error("上传失败。");
        }
    }

    @RequestMapping(value = "/uploadImage", method = { RequestMethod.POST })
    @ResponseBody
    public Result uploadImage(HttpServletRequest request,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        try {
            if (file == null || file.isEmpty()) {
                return ResponseUtil.error("请选择文件。");
            }
            //文件夹名
            String dirName = "image";
            // 最大文件大小
            long maxSize = 10000000;

            // 定义允许上传的文件扩展名
            HashMap<String, String> extMap = new HashMap<String, String>();
            extMap.put(dirName, "gif,jpg,jpeg,png,bmp");

            Attachment attachment = iAttachmentService
                    .uploadFileAttachement(request, file, dirName, maxSize, extMap, IMAGE);
            return ResponseUtil.success(attachment);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.error("上传失败。");
        }
    }

    @RequestMapping(value = "/uploadFiles", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadFiles(HttpServletRequest request,
            @RequestParam(value = "files[]", required = false) MultipartFile[] file) throws Exception {
        if (file.length > 0) {
            for (MultipartFile multipartFile : file) {
                //文件夹名
                String dirName = "file";
                // 最大文件大小
                long maxSize = 20000000;

                // 定义允许上传的文件扩展名
                HashMap<String, String> extMap = new HashMap<String, String>();
                extMap.put(dirName,
                        "doc,docx,xls,xlsx,ppt,pptx,txt,zip,rar,gz,bz2,gif,jpg,jpeg,png,bmp,swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");

                Attachment attachment = iAttachmentService
                        .uploadFileAttachement(request, multipartFile, dirName, maxSize, extMap, FILE);
                return attachment;
            }
        }
        return false;
    }

    @RequestMapping(value = "/attachment", method = RequestMethod.POST)
    @ResponseBody
    public Result attachment(@RequestParam("attachmentId") Integer attachmentId) throws Exception {
        if (attachmentId != null) {
            Attachment attachment = iAttachmentService.selectByKey(attachmentId);
            if (attachment != null) {
                return ResponseUtil.success(attachment);
            }
        }
        return ResponseUtil.error("附件id不存在。");
    }

    @RequestMapping(value = "/ethnicGroup/option", method = RequestMethod.POST)
    @ResponseBody
    public List ethnicGroupOption() {
        return EthnicGroup.getOptions();
    }

    @RequestMapping(value = "/politicalStatus/option", method = RequestMethod.POST)
    @ResponseBody
    public List politicalStatusOption() {
        return PoliticalStatus.getOptions();
    }

    @RequestMapping(value = "/educationBackground/option", method = RequestMethod.POST)
    @ResponseBody
    public List educationBackgroundOption() {
        return EducationBackground.getOptions();
    }

    @RequestMapping(value = "/degree/option", method = RequestMethod.POST)
    @ResponseBody
    public List degreeOption() {
        return Degree.getOptions();
    }

    @RequestMapping(value = "/notice/{noticeId}", method = RequestMethod.GET)
    @ResponseBody
    public Result loadNotice(@PathVariable(value = "noticeId") Integer noticeId) {
        Notice notice = iNoticeService.selectByKey(noticeId);
        List<Attachment> attachments = iNoticeService.findAttachments(noticeId);
        Map result = new HashMap();
        result.put("notice",notice);
        result.put("attachments",attachments);
        return ResponseUtil.success(result);
    }
}
