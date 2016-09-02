package com.topie.campus.core.service.impl;

import com.topie.campus.basedao.service.impl.BaseService;
import com.topie.campus.common.utils.FileUtil;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.core.model.Attachment;
import com.topie.campus.core.service.IAttachmentService;
import com.topie.campus.security.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by chenguojun on 8/28/16.
 */
@Service
public class AttachmentServiceImpl extends BaseService<Attachment> implements IAttachmentService {

    @Value("${upload.folder}")
    String uploadFolder;

    @Override
    public Attachment uploadFileAttachement(HttpServletRequest request, MultipartFile file, String dirName,
            long maxSize, HashMap<String, String> extLimitMap, Integer suffix) throws IOException {
        //文件保存路径
        String savePath = request.getSession().getServletContext().getRealPath("/") + uploadFolder + "/" + SecurityUtil
                .getCurrentUserName() + "/";
        // 文件保存目录URL
        String saveUrl = request.getContextPath() + "/" + uploadFolder + "/" + SecurityUtil.getCurrentUserName() + "/";
        File targetFile = new File(savePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        // 创建文件夹
        savePath += dirName + "/";
        saveUrl += dirName + "/";
        File saveDirFile = new File(savePath);
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(new Date());
        savePath += ymd + "/";
        saveUrl += ymd + "/";
        File dirFile = new File(savePath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }

        String fileName = file.getOriginalFilename();
        // 检查文件大小
        if (file.getSize() > maxSize) {
            ResponseUtil.error("上传文件大小超过限制。");
        }
        // 检查扩展名
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if (!Arrays.<String>asList(extLimitMap.get(dirName).split(",")).contains(fileExt)) {
            ResponseUtil.error("上传文件扩展名是不允许的扩展名。\n只允许" + extLimitMap.get(dirName) + "格式。");
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "_" + fileName;

        FileUtil.saveFileFromInputStream(file.getInputStream(), savePath, newFileName);

        Attachment attachment = new Attachment();
        attachment.setAttachmentPath(savePath + newFileName);
        attachment.setAttachmentUrl(saveUrl + newFileName);
        attachment.setAttachmentName(fileName);
        attachment.setUploadLoginName(SecurityUtil.getCurrentUserName());
        attachment.setAttachmentSuffix(fileExt);
        attachment.setAttachmentType(suffix);
        attachment.setAttachmentSize(file.getSize());
        getMapper().insertSelective(attachment);
        return attachment;

    }
}
