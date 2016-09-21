package com.topie.campus.core.service;

import com.topie.campus.basedao.service.IService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.model.Notice;

/**
 * Created by chenguojun on 8/28/16.
 */
public interface INoticeService extends IService<Notice> {

    SimplePageInfo<Notice> findNoticeList(Notice notice, Integer pageNum, Integer pageSize);
}
