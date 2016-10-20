package com.topie.campus.core.task;

import com.topie.campus.common.utils.date.DateStyle;
import com.topie.campus.common.utils.date.DateUtil;
import com.topie.campus.core.enums.OnlineStatus;
import com.topie.campus.core.model.SurveyGroup;
import com.topie.campus.core.service.ISurveyGroupService;
import com.topie.campus.tools.redis.DistributeLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenguojun on 2016/10/20.
 */
@Service
public class SurveyGroupTask implements Task {

    private static final String TASK_LOCK = "topie_survey_group_task_lock";

    @Autowired
    private ISurveyGroupService iSurveyGroupService;

    @Autowired
    private DistributeLock distributeLock;

    @Override
    public void run() {
        long expTime = distributeLock.tryLock(TASK_LOCK);
        if (expTime == 0) {
            return;
        }
        String current = DateUtil.getCurrent(DateStyle.YYYY_MM_DD_HH_MM_SS);
        List<SurveyGroup> startTaskGroups = iSurveyGroupService.selectByCurrentForStartTask(current);
        for (SurveyGroup startTaskGroup : startTaskGroups) {
            startTaskGroup.setOnlineStatus(OnlineStatus.ING.getCode());
            iSurveyGroupService.updateSelective(startTaskGroup);
        }
        List<SurveyGroup> endTaskGroups = iSurveyGroupService.selectByCurrentForEndTask(current);
        for (SurveyGroup endTaskGroup : endTaskGroups) {
            endTaskGroup.setOnlineStatus(OnlineStatus.END.getCode());
            iSurveyGroupService.updateSelective(endTaskGroup);
        }
        distributeLock.unlock(TASK_LOCK, expTime);
    }
}
