package com.topie.campus.core.service.impl;

import com.topie.campus.basedao.service.impl.BaseService;
import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.dao.EmploymentMapper;
import com.topie.campus.core.dao.StudentMapper;
import com.topie.campus.core.dao.TeacherMapper;
import com.topie.campus.core.dao.TeacherStudentMapper;
import com.topie.campus.core.dto.EmploymentExcelDto;
import com.topie.campus.core.dto.TeacherStudentRelateExcelDto;
import com.topie.campus.core.model.Employment;
import com.topie.campus.core.model.StaticEmployment;
import com.topie.campus.core.model.Student;
import com.topie.campus.core.model.Teacher;
import com.topie.campus.core.model.TeacherStudent;
import com.topie.campus.core.service.IEmploymentService;
import com.topie.campus.tools.excel.ExcelLogs;
import com.topie.campus.tools.excel.ExcelUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class EmploymentServiceImpl extends BaseService<Employment> implements IEmploymentService {

    @Autowired
    EmploymentMapper employmentMapper;
    
    @Autowired
    StudentMapper studentMapper;
    
    @Autowired
    TeacherMapper teacherMapper;
    
    @Autowired
    TeacherStudentMapper teacherStudentMapper;

    @Override
    public SimplePageInfo<Employment> findByPage(int pageNum, int pageSize, Employment employMent) {
        // TODO Auto-generated method stub
        List<Employment> employments = employmentMapper.findByPage(employMent, (pageNum - 1) * pageSize, pageSize);
        Long total = employmentMapper.countEmploy(employMent);
        SimplePageInfo<Employment> pageInfo = new SimplePageInfo<>(pageNum, pageSize, total, employments);
        return pageInfo;
    }

    @Override
    public void employUpload(MultipartFile file, ExcelLogs logs) throws IOException {
        // TODO Auto-generated method stub
        Collection<EmploymentExcelDto> employmentExcelDtos;
        if (file.getOriginalFilename().toLowerCase().endsWith(".xlsx")) {
            employmentExcelDtos = ExcelUtil.importExcelX(EmploymentExcelDto.class, file.getInputStream(), 0, "dd/MM/yy", logs);
        } else {
            employmentExcelDtos = ExcelUtil.importExcel(EmploymentExcelDto.class, file.getInputStream(), 0, "dd/MM/yy", logs);
        }
        for (EmploymentExcelDto employmentExcelDto : employmentExcelDtos) {
            //TODO 检测教师职工号是否唯一
        	if(StringUtils.isNotEmpty(employmentExcelDto.getIsImport()) && employmentExcelDto.getIsImport().equals("1"))
        	{
        	Integer studentId = studentMapper.findIdByStudentNo(employmentExcelDto.getStuId());
        	Student student = studentMapper.findByStuId(studentId);
            Employment employment = employmentExcelDto.buildEmployment(employmentExcelDto);
            TeacherStudent teacherStudent = teacherStudentMapper.selectTeacherRelationByTypeIdAndStudentId(1, studentId);
            Teacher teacher = null;
            if(teacherStudent!=null)
            {
            	teacher = teacherMapper.selectByPrimaryKey(teacherStudent.getTeacherId());
            }
            if(student!=null)
            {
	            employment.setClassNum(student.getGrade());
	            employment.setCollege(student.getCollege());
	            employment.setPhone(student.getContactPhone());
	            employment.setFaculty(student.getFaculty());
	            employment.setGender(student.getGender());
	            employment.setEducation(student.getGradation());
	            employment.setMajor(student.getSubject());
	            employment.setName(student.getName());
	            if(teacher!=null)
	            {
	            	employment.setTutor(teacher.getName());
	                employment.setTeacherNo(teacher.getEmployeeNo());
	            }
	            else
	            {
	            	TeacherStudent teacherStudentBZR = teacherStudentMapper.selectTeacherRelationByTypeIdAndStudentId(4, studentId);
	                Teacher teacherBZR= null;
	                if(teacherStudentBZR!=null)
	                {
	                	teacherBZR = teacherMapper.selectByPrimaryKey(teacherStudentBZR.getTeacherId());
	                	employment.setTutor(teacherBZR.getName());
		                employment.setTeacherNo(teacherBZR.getEmployeeNo());
	                }
	            }
	            List<Employment> employMents = new ArrayList<Employment>();
	            if (StringUtils.isNotEmpty(employmentExcelDto.getStuId()))
	                employMents = employmentMapper.findByStuId(employmentExcelDto.getStuId());
	            if (employMents.size() > 0) {
	            	employment.setId(employMents.get(0).getId());
	                employmentMapper.updateByPrimaryKey(employment);
	            } else {
	                employmentMapper.insertSelective(employment);
	            }
	        }
        	}
        }
    }

    @Override
    public SimplePageInfo<StaticEmployment> findByPageGroupByMajor(int pageNum, int pageSize, Employment employment) {
        // TODO Auto-generated method stub
        List<StaticEmployment> staticEmployments = employmentMapper
                .findByPageGroupByMajor(employment, (pageNum - 1) * pageSize, pageSize);
        for (StaticEmployment se : staticEmployments) {
            Employment em = new Employment();
            em.setMajor(se.getMajor());
            List<StaticEmployment> seo = employmentMapper.findOtherByPageGroupByMajor(em);
            if (seo.size() > 0) {
                se.setMan(seo.get(0).getMan());
                se.setWoman(seo.get(0).getWoman());
                se.setPoorRate(seo.get(0).getPoorRate());
            }
        }
        Long total = employmentMapper.countByPageGroupByMajor(employment);
        SimplePageInfo<StaticEmployment> pageInfo = new SimplePageInfo<>(pageNum, pageSize, total, staticEmployments);
        return pageInfo;
    }

    @Override
    public SimplePageInfo<StaticEmployment> findByPageGroupByClassNum(int pageNum, int pageSize,
            Employment employment) {
        // TODO Auto-generated method stub
        List<StaticEmployment> staticEmployments = employmentMapper
                .findByPageGroupByClassNum(employment, (pageNum - 1) * pageSize, pageSize);
        for (StaticEmployment se : staticEmployments) {
            Employment em = new Employment();
            em.setClassNum(se.getClassNum());
            List<StaticEmployment> seo = employmentMapper.findOtherByPageGroupByClassNum(em);
            if (seo.size() > 0) {
                se.setMan(seo.get(0).getMan());
                se.setWoman(seo.get(0).getWoman());
                se.setPoorRate(seo.get(0).getPoorRate());
            }
        }
        Long total = employmentMapper.countByPageGroupByClassNum(employment);
        SimplePageInfo<StaticEmployment> pageInfo = new SimplePageInfo<>(pageNum, pageSize, total, staticEmployments);
        return pageInfo;
    }

    @Override
    public SimplePageInfo<StaticEmployment> findByPageGroupByTutor(int pageNum, int pageSize, Employment employment) {
        // TODO Auto-generated method stub
        List<StaticEmployment> staticEmployments = employmentMapper
                .findByPageGroupByTutor(employment, (pageNum - 1) * pageSize, pageSize);
        Long total = employmentMapper.countByPageGroupByTutor(employment);
        SimplePageInfo<StaticEmployment> pageInfo = new SimplePageInfo<>(pageNum, pageSize, total, staticEmployments);
        return pageInfo;
    }

    @Override
    public void updateEmploymentStatus(Employment employment) {
        // TODO Auto-generated method stub
        Employment old = employmentMapper.selectByPrimaryKey(employment.getId());
        old.setTakeTable(employment.getTakeTable());
        old.setEmploymentStatus(employment.getEmploymentStatus());
        old.setSignStatus(employment.getSignStatus());
        employmentMapper.updateByPrimaryKey(old);
    }

	@Override
	public SimplePageInfo<StaticEmployment> findByPageGroupByCollege(
			int pageNum, int pageSize, Employment employment) {
		// TODO Auto-generated method stub
		List<StaticEmployment> staticEmployments = employmentMapper
                .findByPageGroupByCollege(employment, (pageNum - 1) * pageSize, pageSize);
        Long total = employmentMapper.countByPageGroupByCollege(employment);
        SimplePageInfo<StaticEmployment> pageInfo = new SimplePageInfo<>(pageNum, pageSize, total, staticEmployments);
        return pageInfo;
	}

	@Override
	public SimplePageInfo<StaticEmployment> findByPageGroupByFaculty(
			int pageNum, int pageSize, Employment employment) {
		// TODO Auto-generated method stub
		List<StaticEmployment> staticEmployments = employmentMapper
                .findByPageGroupByFaculty(employment, (pageNum - 1) * pageSize, pageSize);
        Long total = employmentMapper.countByPageGroupByFaculty(employment);
        SimplePageInfo<StaticEmployment> pageInfo = new SimplePageInfo<>(pageNum, pageSize, total, staticEmployments);
        return pageInfo;
	}

}
