package com.topie.campus.core.service.impl;

import com.topie.campus.basedao.service.impl.BaseService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.dao.NoticeMapper;
import com.topie.campus.core.model.Attachment;
import com.topie.campus.core.model.Notice;
import com.topie.campus.core.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenguojun on 8/28/16.
 */
@Service
public class NoticeServiceImpl extends BaseService<Notice> implements INoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public SimplePageInfo<Notice> findNoticeList(Notice notice, Integer pageNum, Integer pageSize) {
        List<Notice> list = noticeMapper.findNoticeByPageNumAndPageSize(notice, (pageNum - 1) * pageSize, pageSize);
        Long total = noticeMapper.countNotice(notice);
        SimplePageInfo<Notice> pageInfo = new SimplePageInfo<>(pageNum, pageSize, total, list);
        return pageInfo;
    }

    @Override
    public void insertAttachment(Integer noticeId, Integer attr) {
        noticeMapper.insertAttachment(noticeId, attr);
    }

    @Override
    public void deleteAttachment(Integer noticeId) {
        noticeMapper.deleteAttachment(noticeId);
    }

    @Override
    public List<Integer> findAttachmentIds(Integer noticeId) {
        return noticeMapper.selectAttachmentIds(noticeId);
    }

    @Override
    public List<Attachment> findAttachments(Integer noticeId) {
        return noticeMapper.selectAttachments(noticeId);
    }
}
