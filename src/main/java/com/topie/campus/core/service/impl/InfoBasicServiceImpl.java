package com.topie.campus.core.service.impl;

import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.dao.TeacherStudentMapper;
import com.topie.campus.core.dto.*;
import com.topie.campus.core.model.*;
import com.topie.campus.core.service.*;
import com.topie.campus.security.SecurityConstant;
import com.topie.campus.security.exception.AuBzConstant;
import com.topie.campus.security.exception.AuthBusinessException;
import com.topie.campus.security.model.User;
import com.topie.campus.security.service.UserService;
import com.topie.campus.security.vo.UserVO;
import com.topie.campus.tools.excel.ExcelLogs;
import com.topie.campus.tools.excel.ExcelUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by chenguojun on 8/10/16.
 */
@Service
public class InfoBasicServiceImpl implements IInfoBasicService {

    @Autowired
    IStuCetService stuCetService;

    @Autowired
    IStuSeleCourseService stuSeleCourseService;

    @Autowired
    IStuTimeTableService stuTimeTableService;

    @Autowired
    private UserService userService;

    @Autowired
    private ITeacherService iTeacherService;

    @Autowired
    private IStudentService iStudentService;

    @Autowired
    private IStudentScoreService stuScoreService;

    @Autowired
    private TeacherStudentMapper teacherStudentMapper;

    @Override
    public void userUpload(MultipartFile file, ExcelLogs logs) throws IOException {
        Collection<TeacherExcelDto> teacherList;
        if (file.getOriginalFilename().toLowerCase().endsWith(".xlsx")) {
            teacherList = ExcelUtil.importExcelX(TeacherExcelDto.class, file.getInputStream(), 0, "dd/MM/yy", logs);
        } else {
            teacherList = ExcelUtil.importExcel(TeacherExcelDto.class, file.getInputStream(), 0, "dd/MM/yy", logs);
        }
        Collection<StudentExcelDto> studentList;
        if (file.getOriginalFilename().toLowerCase().endsWith(".xlsx")) {
            studentList = ExcelUtil.importExcelX(StudentExcelDto.class, file.getInputStream(), 1, "dd/MM/yy", logs);
        } else {
            studentList = ExcelUtil.importExcel(StudentExcelDto.class, file.getInputStream(), 1, "dd/MM/yy", logs);
        }
        for (TeacherExcelDto teacherDto : teacherList) {
            //TODO 检测教师职工号是否唯一
            User user = UserVO
                    .buildSimpleUser(teacherDto.getEmployeeNo(), teacherDto.getContactPhone(), teacherDto.getName(),
                            teacherDto.getEmail());
            if (userService.findExistUser(user) > 0) {
                throw new AuthBusinessException(user.getLoginName() + AuBzConstant.LOGIN_NAME_EXIST);
            }
            userService.insertUser(user);
            userService.insertUserRole(user.getId(), SecurityConstant.ROLE_TEACHER);
            Teacher teacher = teacherDto.buildTeacher();
            teacher.setUserId(user.getId());
            iTeacherService.insertSelective(teacher);
        }
        for (StudentExcelDto studentDto : studentList) {
            //TODO 检测学号是否唯一
            User user = UserVO
                    .buildSimpleUser(studentDto.getStudentNo(), studentDto.getContactPhone(), studentDto.getName(),
                            studentDto.getEmail());
            if (userService.findExistUser(user) > 0) {
                throw new AuthBusinessException(user.getLoginName() + AuBzConstant.LOGIN_NAME_EXIST);
            }
            userService.insertUser(user);
            userService.insertUserRole(user.getId(), SecurityConstant.ROLE_STUDENT);
            Student student = studentDto.buildStudent();
            student.setUserId(user.getId());
            iStudentService.insertSelective(student);
        }
    }

    @Override
    public void uploadStudentTable(MultipartFile file, ExcelLogs logs) throws IOException {
        // TODO Auto-generated method stub

        Collection<StudentExcelDto> studentList;
        if (file.getOriginalFilename().toLowerCase().endsWith(".xlsx")) {
            studentList = ExcelUtil.importExcelX(StudentExcelDto.class, file.getInputStream(), 0, "dd/MM/yy", logs);
        } else {
            studentList = ExcelUtil.importExcel(StudentExcelDto.class, file.getInputStream(), 0, "dd/MM/yy", logs);
        }
        for (StudentExcelDto studentDto : studentList) {
            //TODO 检测学号是否唯一
            User user = UserVO
                    .buildSimpleUser(studentDto.getStudentNo(), studentDto.getContactPhone(), studentDto.getName(),
                            studentDto.getEmail());
            if (userService.findExistUser(user) > 0) {
                // throw new AuthBusinessException(user.getLoginName() + AuBzConstant.LOGIN_NAME_EXIST);
                user.setPassword(studentDto.getPassword());
                userService.updateUser(user);
            } else {
                if (StringUtils.isNotEmpty(studentDto.getPassword())) {
                    user.setPassword(studentDto.getPassword());
                } else {
                    user.setPassword(studentDto.getStudentNo());
                }
                userService.insertUser(user);
                userService.insertUserRole(user.getId(), SecurityConstant.ROLE_STUDENT);
                Student student = studentDto.buildStudent();
                System.out.println(student);
                student.setUserId(user.getId());
                iStudentService.insert(student);
            }
        }

    }

    @Override
    public void uploadTeacherTable(MultipartFile file, ExcelLogs logs) throws IOException {
        Collection<TeacherExcelDto> teacherList;
        if (file.getOriginalFilename().toLowerCase().endsWith(".xlsx")) {
            teacherList = ExcelUtil.importExcelX(TeacherExcelDto.class, file.getInputStream(), 0, "dd/MM/yy", logs);
        } else {
            teacherList = ExcelUtil.importExcel(TeacherExcelDto.class, file.getInputStream(), 0, "dd/MM/yy", logs);
        }
        for (TeacherExcelDto teacherDto : teacherList) {
            //TODO 检测教师职工号是否唯一
            User user = UserVO
                    .buildSimpleUser(teacherDto.getEmployeeNo(), teacherDto.getEmployeeNo(), teacherDto.getName(),
                            teacherDto.getEmail());
            if (userService.findExistUser(user) > 0) {
                throw new AuthBusinessException(user.getLoginName() + AuBzConstant.LOGIN_NAME_EXIST);
            }
            userService.insertUser(user);
            userService.insertUserRole(user.getId(), SecurityConstant.ROLE_TEACHER);
            Teacher teacher = teacherDto.buildTeacher();
            teacher.setPassword(teacherDto.getEmployeeNo());
            teacher.setUserId(user.getId());
            iTeacherService.insertSelective(teacher);
        }
    }

    @Override
    public SimplePageInfo<Teacher> findTeacherList(Teacher teacher, int pageNum, int pageSize) {
        return iTeacherService.findTeacherList(teacher, pageNum, pageSize);
    }

    @Override
    public Teacher findOneByTeacherId(Integer teacherId) {
        return iTeacherService.selectByKey(teacherId);
    }

    @Override
    public int insertTeacher(Teacher teacher) {
        //TODO 检测教师职工号是否唯一
        User user = UserVO.buildSimpleUser(teacher.getEmployeeNo(), teacher.getContactPhone(), teacher.getName(),
                teacher.getEmail());
        if (userService.findExistUser(user) > 0) {
            throw new AuthBusinessException(user.getLoginName() + AuBzConstant.LOGIN_NAME_EXIST);
        }
        userService.insertUser(user);
        teacher.setUserId(user.getId());
        return iTeacherService.insertSelective(teacher);
    }

    @Override
    public int updateTeacher(Teacher teacher) {
        return iTeacherService.updateSelective(teacher);
    }

    @Override
    public int deleteTeacher(Integer teacherId) {
        //TODO 删除用户
        Teacher teacher = iTeacherService.selectByKey(teacherId);
        if (teacher != null) {
            userService.delete(teacher.getUserId());
        }
        return iTeacherService.delete(teacherId);
    }

    @Override
    public SimplePageInfo<Student> findStudentList(Student student, int pageNum, int pageSize) {
        return iStudentService.findStudentList(student, pageNum, pageSize);
    }

    @Override
    public SimplePageInfo<StudentSimpleDto> findStudentSimpleDtoListWithBindInfo(StudentSimpleDto studentSimpleDto,
            Integer typeId, Integer teacherId, Integer pageNum, Integer pageSize) {
        return iStudentService
                .findStudentSimpleDtoListWithBindInfo(studentSimpleDto, typeId, teacherId, pageNum, pageSize);
    }

    @Override
    public SimplePageInfo<TeacherSimpleDto> findTeacherSimpleDtoListWithBindInfo(TeacherSimpleDto teacherSimpleDto,
            Integer typeId, Integer studentId, Integer pageNum, Integer pageSize) {
        return iTeacherService
                .findTeacherSimpleDtoListWithBindInfo(teacherSimpleDto, typeId, studentId, pageNum, pageSize);
    }

    @Override
    public Student findOneByStudentId(Integer studentId) {
        return iStudentService.selectByKey(studentId);
    }

    @Override
    public int insertStudent(Student student) {
        //TODO 检测学号是否唯一
        User user = UserVO.buildSimpleUser(student.getStudentNo(), student.getContactPhone(), student.getName(),
                student.getEmail());
        if (userService.findExistUser(user) > 0) {
            throw new AuthBusinessException(user.getLoginName() + AuBzConstant.LOGIN_NAME_EXIST);
        }
        userService.insertUser(user);
        student.setUserId(user.getId());
        return iStudentService.insertSelective(student);
    }

    @Override
    public int updateStudent(Student student) {
        return iStudentService.updateSelective(student);
    }

    @Override
    public int deleteStudent(Integer studentId) {
        //TODO 删除用户
        Student student = iStudentService.selectByKey(studentId);
        if (student != null) {
            userService.delete(student.getUserId());
        }
        return iStudentService.delete(studentId);
    }

    @Override
    public void insertToBindStudentTeacher(Integer typeId, Integer studentId, Integer teacherId) {
        iTeacherService.insertToBindStudent(typeId, studentId, teacherId);
    }

    @Override
    public void deleteToUnbindStudentTeacher(Integer typeId, Integer studentId, Integer teacherId) {
        iTeacherService.deleteToUnBindStudent(typeId, studentId, teacherId);
    }

    @Override
    public void uploadStuScore(MultipartFile file, ExcelLogs logs) throws IOException {
        Collection<StuScoreExcelDto> stuScoreList;
        if (file.getOriginalFilename().toLowerCase().endsWith(".xlsx")) {
            stuScoreList = ExcelUtil.importExcelX(StuScoreExcelDto.class, file.getInputStream(), 0, "dd/MM/yy", logs);
        } else {
            stuScoreList = ExcelUtil.importExcel(StuScoreExcelDto.class, file.getInputStream(), 0, "dd/MM/yy", logs);
        }
        for (StuScoreExcelDto dto : stuScoreList) {
            StuScore stuScore = dto.buildStuScore(dto);
            stuScoreService.insert(stuScore);
        }
    }

    @Override
    public void uploadStuCet(MultipartFile file, ExcelLogs logs) throws IOException {
        // TODO Auto-generated method stub

        Collection<StuCetExcelDto> StuCetExcelDtos;
        if (file.getOriginalFilename().toLowerCase().endsWith(".xlsx")) {
            StuCetExcelDtos = ExcelUtil.importExcelX(StuCetExcelDto.class, file.getInputStream(), 0, "dd/MM/yy", logs);
        } else {
            StuCetExcelDtos = ExcelUtil.importExcel(StuCetExcelDto.class, file.getInputStream(), 0, "dd/MM/yy", logs);
        }
        for (StuCetExcelDto dto : StuCetExcelDtos) {
            StuCet stuCet = dto.buildStuCet(dto);
            stuCetService.insert(stuCet);
        }
    }

    @Override
    public void uploadStuSeleCourse(MultipartFile file, ExcelLogs logs) throws IOException {
        // TODO Auto-generated method stub

        Collection<StuSeleExcelDto> stuSeleExcelDtos;
        if (file.getOriginalFilename().toLowerCase().endsWith(".xlsx")) {
            stuSeleExcelDtos = ExcelUtil
                    .importExcelX(StuSeleExcelDto.class, file.getInputStream(), 0, "dd/MM/yy", logs);
        } else {
            stuSeleExcelDtos = ExcelUtil.importExcel(StuSeleExcelDto.class, file.getInputStream(), 0, "dd/MM/yy", logs);
        }
        for (StuSeleExcelDto dto : stuSeleExcelDtos) {

            StuSeleCourse stuSeleCourse = dto.buildstuSeleCourse(dto);
            stuSeleCourseService.insert(stuSeleCourse);
        }
    }

    @Override
    public void uploadStuTimetable(MultipartFile file, ExcelLogs logs) throws IOException {
        // TODO Auto-generated method stub

        Collection<StuTimeTableExcelDto> stuTimeTableExcelDtos;
        if (file.getOriginalFilename().toLowerCase().endsWith(".xlsx")) {
            stuTimeTableExcelDtos = ExcelUtil
                    .importExcelX(StuTimeTableExcelDto.class, file.getInputStream(), 0, "dd/MM/yy", logs);
        } else {
            stuTimeTableExcelDtos = ExcelUtil
                    .importExcel(StuTimeTableExcelDto.class, file.getInputStream(), 0, "dd/MM/yy", logs);
        }
        for (StuTimeTableExcelDto dto : stuTimeTableExcelDtos) {
            StuTimeTable stuTimeTable = dto.buildStuTimeTable(dto);
            stuTimeTableService.insert(stuTimeTable);
        }

    }

    @Override
    public void uploadTeacherStudentRelate(MultipartFile file, ExcelLogs logs) throws IOException {
        uploadTeacherStudentRelate(0, file, logs);
    }

    @Override
    public void uploadTeacherStudentRelate(Integer typeId, MultipartFile file, ExcelLogs logs) throws IOException {
        Collection<TeacherStudentRelateExcelDto> teacherStudentRelateExcelDtos;
        if (file.getOriginalFilename().toLowerCase().endsWith(".xlsx")) {
            teacherStudentRelateExcelDtos = ExcelUtil
                    .importExcelX(TeacherStudentRelateExcelDto.class, file.getInputStream(), 0, "dd/MM/yy", logs);
        } else {
            teacherStudentRelateExcelDtos = ExcelUtil
                    .importExcel(TeacherStudentRelateExcelDto.class, file.getInputStream(), 0, "dd/MM/yy", logs);
        }
        for (TeacherStudentRelateExcelDto teacherStudentRelateExcelDto : teacherStudentRelateExcelDtos) {
            Integer teacherId = iTeacherService.findIdByEmployeeNo(teacherStudentRelateExcelDto.getEmployeeNo());
            Integer studentId = iStudentService.findIdByStudentNo(teacherStudentRelateExcelDto.getStudentNo());
            if (teacherId != null && studentId != null) {
                TeacherStudent teacherStudent = new TeacherStudent();
                teacherStudent.setStudentId(studentId);
                teacherStudent.setTeacherId(teacherId);
                teacherStudent.setEmployeeNo(teacherStudentRelateExcelDto.getEmployeeNo());
                teacherStudent.setStudentNo(teacherStudentRelateExcelDto.getStudentNo());
                teacherStudent.setTypeId(typeId);
                teacherStudentMapper.insertIgnore(teacherStudent);
            }
        }
    }

}
