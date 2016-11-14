package com.topie.campus.core.service.impl;

import com.topie.campus.basedao.service.impl.BaseService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.common.constants.ResultCode;
import com.topie.campus.common.utils.ResponseUtil;
import com.topie.campus.core.dao.SurveyGroupMapper;
import com.topie.campus.core.dao.TeacherStudentMapper;
import com.topie.campus.core.dto.SurveyAnswerExcelDto;
import com.topie.campus.core.model.GroupStudentStat;
import com.topie.campus.core.model.GroupTeacherStat;
import com.topie.campus.core.model.SurveyGroup;
import com.topie.campus.core.service.ISurveyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
        surveyGroupMapper.deleteGroupStudentRelate(groupId);
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
    public Boolean selectCompleteByStudentId(Integer groupId, Integer studentId) {
        Boolean result = surveyGroupMapper.selectCompleteByStudentId(groupId, studentId);
        return result;
    }

    @Override
    public List<GroupTeacherStat> selectTeacherStatByGroupId(Integer groupId) {
        return surveyGroupMapper.selectTeacherStatByGroupId(groupId);
    }

    @Override
    public List<SurveyGroup> selectByCurrentForStartTask(String current) {

        return surveyGroupMapper.selectByCurrentForStartTask(current);
    }

    @Override
    public List<SurveyGroup> selectByCurrentForEndTask(String current) {
        return surveyGroupMapper.selectByCurrentForEndTask(current);
    }

    @Override
    public List<SurveyAnswerExcelDto> selectSurveyComment(Integer groupId, Integer groupType) {
        return surveyGroupMapper.selectSurveyComment(groupId, groupType);
    }

    @Override
    public List<Map> selectStudentProcessByGroupId(Integer groupId) {
        return surveyGroupMapper.selectStudentProcessByGroupId(groupId);
    }

    @Override
    public int insertInitGroupTeacher(Integer groupId, Integer typeId) {
        List<Integer> teacherIds = teacherStudentMapper.selectTeacherIdByTypeId(typeId);
        surveyGroupMapper.deleteGroupTeacherRelate(groupId);
        if (teacherIds.size() > 0) {
            for (Integer teacherId : teacherIds) {
                surveyGroupMapper.insertGroupTeacherRelate(groupId, teacherId, false);
            }
        }
        return 0;
    }

    @Override
    public SimplePageInfo<SurveyGroup> selectByPageByTeacherId(SurveyGroup surveyGroup, Integer teacherId, int pageNum,
            int pageSize) {
        List<SurveyGroup> list = surveyGroupMapper
                .selectByPageByTeacherId(surveyGroup, teacherId, (pageNum - 1) * pageSize, pageSize);
        Long total = surveyGroupMapper.countByTeacherId(surveyGroup, teacherId);
        SimplePageInfo<SurveyGroup> pageInfo = new SimplePageInfo<>(pageNum, pageSize, total, list);
        return pageInfo;
    }

    @Override
    public Boolean selectCompleteByTeacherId(Integer groupId, Integer teacherId) {
        Boolean result = surveyGroupMapper.selectCompleteByTeacherId(groupId, teacherId);
        return result;
    }

    @Override
    public int updateGroupTeacherComplete(Integer groupId, Integer teacherId) {
        return surveyGroupMapper.updateGroupTeacherComplete(groupId, teacherId);
    }

    @Override
    public List<GroupStudentStat> selectStudentStatByGroupId(Integer groupId) {
        return surveyGroupMapper.selectStudentStatByGroupId(groupId);
    }

    @Override
    public List<Map> selectTeacherProcessByGroupId(Integer groupId) {
        return surveyGroupMapper.selectTeacherProcessByGroupId(groupId);
    }

    @Override
    public int insertSelectiveSurveyGroup(SurveyGroup surveyGroup) {
        int result = insertSelective(surveyGroup);
        Integer groupType = surveyGroup.getGroupType();
        if (result > 0) {
            Integer groupId = surveyGroup.getGroupId();
            if (groupType == 1) {
                insertInitGroupStudent(groupId, surveyGroup.getTypeId());
            } else {
                insertInitGroupTeacher(groupId, surveyGroup.getTypeId());
            }
        }
        return result;

    }

    @Override
    public int updateSelectiveSurveyGroup(SurveyGroup surveyGroup) {
        int result = updateSelective(surveyGroup);
        if (result > 0) {
            if (surveyGroup.getGroupType() == 1) {
                insertInitGroupStudent(surveyGroup.getGroupId(), surveyGroup.getTypeId());
            } else {
                insertInitGroupTeacher(surveyGroup.getGroupId(), surveyGroup.getTypeId());
            }
        }
        return result;
    }

    @Override
    public List<SurveyAnswerExcelDto> selectSurveyAnswer(Integer groupId, Integer groupType) {
        return surveyGroupMapper.selectSurveyAnswer(groupId, groupType);
    }

}
