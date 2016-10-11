package com.topie.campus.core.dao;

import com.topie.campus.core.model.Attachment;
import com.topie.campus.core.model.Notice;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface NoticeMapper extends Mapper<Notice> {

    List<Notice> findNoticeByPageNumAndPageSize(@Param("notice") Notice notice, @Param("pageOffset") Integer pageOffset,
            @Param("pageSize") Integer pageSize);

    Long countNotice(@Param("notice") Notice notice);

    void insertAttachment(@Param("noticeId") Integer noticeId, @Param("attachmentId") Integer attr);

    void deleteAttachment(@Param("noticeId") Integer noticeId);

    List<Integer> selectAttachmentIds(@Param("noticeId") Integer noticeId);

    List<Attachment> selectAttachments(@Param("noticeId") Integer noticeId);
}
