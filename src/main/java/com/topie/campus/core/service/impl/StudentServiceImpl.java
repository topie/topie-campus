package com.topie.campus.core.service.impl;

import com.topie.campus.basedao.service.impl.BaseService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.dao.MajorMapper;
import com.topie.campus.core.dao.StudentMapper;
import com.topie.campus.core.dao.UserFacultyMapper;
import com.topie.campus.core.dto.StudentSimpleDto;
import com.topie.campus.core.model.Student;
import com.topie.campus.core.model.UserFaculty;
import com.topie.campus.core.service.IStudentService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenguojun on 9/13/16.
 */
@Service
public class StudentServiceImpl extends BaseService<Student> implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;
    
    @Autowired
    UserFacultyMapper userFacultyMapper;
    
    @Autowired
    MajorMapper majorMapper;

    @Override
    public SimplePageInfo<Student> findStudentList(Student student, Integer pageNum, Integer pageSize) {
        List<Student> list = studentMapper.findStudentByPageNumAndPageSize(student, (pageNum - 1) * pageSize, pageSize);
        Long total = studentMapper.countStudent(student);
        SimplePageInfo<Student> pageInfo = new SimplePageInfo<>(pageNum, pageSize, total, list);
        return pageInfo;
    }

    @Override
    public SimplePageInfo<StudentSimpleDto> findStudentSimpleDtoListWithBindInfo(StudentSimpleDto studentSimpleDto,
            Integer typeId, Integer teacherId, Integer pageNum, Integer pageSize) {
        List<StudentSimpleDto> list = studentMapper
                .findStudentSimpleDtoByTeacherIdAndPageNumAndPageSize(studentSimpleDto, typeId, teacherId,
                        (pageNum - 1) * pageSize, pageSize);
        Long total = studentMapper.countStudentSimpleDtoListByTeacherId(studentSimpleDto, typeId, teacherId);
        SimplePageInfo<StudentSimpleDto> pageInfo = new SimplePageInfo<>(pageNum, pageSize, total, list);
        return pageInfo;
    }

    @Override
    public Student findStudentByUserId(Integer userId) {
        return studentMapper.findStudentByUserId(userId);
    }

    @Override
    public Integer findStudentIdByUserId(Integer userId) {
        return studentMapper.findStudentIdByUserId(userId);
    }

    @Override
    public String findStudentNameByUserId(Integer userId) {
        return studentMapper.findStudentNameByUserId(userId);
    }

    @Override
    public Integer findIdByStudentNo(String studentNo) {
        return studentMapper.findIdByStudentNo(studentNo);
    }

    @Override
    public String findStudentNoByUserId(Integer userId) {
        return studentMapper.findStudentNoByUserId(userId);
    }

	@Override
	public List<String> findPhoneByMajorId(String zydm) {
		// TODO Auto-generated method stub
		return studentMapper.findPhoneByMajorId(zydm);
	}
	
	@Override
	public Student findByStudentId(Integer studentId) {
		// TODO Auto-generated method stub
		return studentMapper.findByStuId(studentId);
	}

	@Override
	public SimplePageInfo<Student> findByLeadRole(Integer userId,int pageNum,int pageSize,Student student) {
		// TODO Auto-generated method stub
		List<UserFaculty> userFaculties =  userFacultyMapper.findByUserId(userId);
		SimplePageInfo<Student> pageInfo = null;
		List<String> majorIds = new ArrayList<>();
			for(UserFaculty f:userFaculties)
			{
				majorIds.addAll(majorMapper.selectByFacultyId(f.getFaculty()));
		    }
			if(majorIds.size()==0)
			{
			 return	new SimplePageInfo<>(pageNum,pageSize,0,new ArrayList<Student>());
			}
			List<Student> students = studentMapper.findByMajor(majorIds,(pageNum-1)*pageSize,pageSize,student);
			Long total = studentMapper.countByMajor(majorIds,student);
			pageInfo = new SimplePageInfo<>(pageNum,pageSize,total,students);
		return pageInfo;
	}

    @Override
    public List<StudentSimpleDto> findStudentByTeacherIdAndTypeId(Integer teacherId, Integer typeId) {
        return studentMapper.findStudentByTeacherIdAndTypeId(teacherId,typeId);
    }

    @Override
    public List<StudentSimpleDto> findStudentByTypeId(Integer typeId) {
        return studentMapper.findStudentByTypeId(typeId);
    }

}
