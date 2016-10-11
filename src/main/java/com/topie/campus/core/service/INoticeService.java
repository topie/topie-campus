package com.topie.campus.core.service;

import com.topie.campus.basedao.service.IService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.model.Attachment;
import com.topie.campus.core.model.Notice;

import java.util.List;

/**
 * Created by chenguojun on 8/28/16.
 */
public interface INoticeService extends IService<Notice> {

    SimplePageInfo<Notice> findNoticeList(Notice notice, Integer pageNum, Integer pageSize);

    void insertAttachment(Integer noticeId, Integer attr);

    void deleteAttachment(Integer noticeId);

    List<Integer> findAttachmentIds(Integer noticeId);

    List<Attachment> findAttachments(Integer noticeId);
}
