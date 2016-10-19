package com.topie.campus.core.service.impl;

import com.topie.campus.basedao.service.impl.BaseService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.dao.SurveyGroupMapper;
import com.topie.campus.core.dao.TeacherStudentMapper;
import com.topie.campus.core.model.GroupStat;
import com.topie.campus.core.model.SurveyGroup;
import com.topie.campus.core.service.ISurveyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenguojun on 8/28/16.
 */
@Service
public class SurveyGroupImpl extends BaseService<SurveyGroup> implements ISurveyGroupService {

    @Autowired
    private SurveyGroupMapper surveyGroupMapper;

    @Autowired
    private TeacherStudentMapper teacherStudentMapper;

    @Override
    public SimplePageInfo<SurveyGroup> selectByPage(SurveyGroup surveyGroup, Integer pageNum, Integer pageSize) {
        List<SurveyGroup> list = surveyGroupMapper.selectByPage(surveyGroup, (pageNum - 1) * pageSize, pageSize);
        Long total = surveyGroupMapper.count(surveyGroup);
        SimplePageInfo<SurveyGroup> pageInfo = new SimplePageInfo<>(pageNum, pageSize, total, list);
        return pageInfo;
    }

    @Override
    public int insertGroupQuestionRelate(Integer groupId, Integer questionId) {
        return surveyGroupMapper.insertGroupQuestionRelate(groupId, questionId);
    }

    @Override
    public int updateGroupQuestionSort(Integer groupId, Integer questionId, Integer sort) {
        return surveyGroupMapper.updateGroupQuestionSort(groupId, questionId, sort);
    }

    @Override
    public int deleteGroupQuestionRelate(Integer groupId, Integer questionId) {
        return surveyGroupMapper.deleteGroupQuestionRelate(groupId, questionId);
    }

    @Override
    public int countGroupQuestionRelate(Integer groupId) {
        return surveyGroupMapper.countGroupQuestionRelate(groupId);
    }

    @Override
    public int updateOnlineStatus(Integer groupId, Integer onlineStatus) {
        return surveyGroupMapper.updateOnlineStatus(groupId, onlineStatus);
    }

    @Override
    public int insertInitGroupStudent(Integer groupId, Integer typeId) {
        List<Integer> studentIds = teacherStudentMapper.selectStudentIdsAndTypeId(typeId);
        if (studentIds.size() > 0) {
            for (Integer studentId : studentIds) {
                surveyGroupMapper.insertGroupStudentRelate(groupId, studentId, false);
            }
        }
        return 0;
    }

    @Override
    public SimplePageInfo<SurveyGroup> selectByPageByStudentId(SurveyGroup surveyGroup, Integer studentId, int pageNum,
            int pageSize) {
        List<SurveyGroup> list = surveyGroupMapper
                .selectByPageByStudentId(surveyGroup, studentId, (pageNum - 1) * pageSize, pageSize);
        Long total = surveyGroupMapper.countByStudentId(surveyGroup, studentId);
        SimplePageInfo<SurveyGroup> pageInfo = new SimplePageInfo<>(pageNum, pageSize, total, list);
        return pageInfo;
    }

    @Override
    public Integer selectTypeIdByGroupId(Integer groupId) {
        return surveyGroupMapper.selectTypeIdByGroupId(groupId);
    }

    @Override
    public int updateGroupStudentComplete(Integer groupId, Integer studentId) {
        return surveyGroupMapper.updateGroupStudentComplete(groupId, studentId);
    }

    @Override
    public Boolean selectComplete(Integer groupId, Integer studentId) {
        Boolean result = surveyGroupMapper.selectComplete(groupId, studentId);
        return result;
    }

    @Override
    public List<GroupStat> selectStatByGroupId(Integer groupId) {
        return surveyGroupMapper.selectStatByGroupId(groupId);
    }

}
