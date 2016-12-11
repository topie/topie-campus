package com.topie.campus.core.service;

import com.topie.campus.basedao.service.IService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.model.AtMe;
import com.topie.campus.core.model.Message;
import com.topie.campus.core.model.MessageReply;

/**
 * Created by chenguojun on 2016/12/10.
 */
public interface IAtMeService extends IService<AtMe> {

    SimplePageInfo<AtMe> selectRecordByPage(AtMe atMe, Integer pageNum, Integer pageSize);

    int insertByMessage(Message message);

    int insertByReply(Integer receiveUser, MessageReply messageReply);
}
