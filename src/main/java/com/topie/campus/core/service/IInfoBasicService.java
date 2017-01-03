package com.topie.campus.core.service;

import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.common.TreeNode;
import com.topie.campus.core.dto.StudentSimpleDto;
import com.topie.campus.core.dto.TeacherSimpleDto;
import com.topie.campus.core.dto.TreeDto;
import com.topie.campus.core.model.Student;
import com.topie.campus.core.model.Teacher;
import com.topie.campus.core.model.UserFaculty;
import com.topie.campus.tools.excel.ExcelLogs;

import org.apache.http.client.ClientProtocolException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 工程：os-app 创建人 : ChenGJ 创建时间： 2015/9/2 说明：
 */
public interface IInfoBasicService {

    void userUpload(MultipartFile file, ExcelLogs logs) throws IOException;

    SimplePageInfo<Teacher> findTeacherList(Teacher teacher, int pageNum, int pageSize);

    Teacher findOneByTeacherId(Integer teacherId);

    int insertTeacher(Teacher teacher);

    int updateTeacher(Teacher teacher);

    int deleteTeacher(Integer teacherId);

    SimplePageInfo<Student> findStudentList(Student student, int pageNum, int pageSize);

    SimplePageInfo<StudentSimpleDto> findStudentSimpleDtoListWithBindInfo(StudentSimpleDto studentSimpleDto,
            Integer typeId, Integer teacherId, Integer pageNum, Integer pageSize);

    SimplePageInfo<TeacherSimpleDto> findTeacherSimpleDtoListWithBindInfo(TeacherSimpleDto teacherSimpleDto,
            Integer typeId, Integer studentId, Integer pageNum, Integer pageSize);

    Student findOneByStudentId(Integer studentId);

    int insertStudent(Student student);

    int updateStudent(Student student);

    int deleteStudent(Integer studentId);

    void insertToBindStudentTeacher(Integer typeId, Integer studentId, Integer teacherId);

    void deleteToUnbindStudentTeacher(Integer typeId, Integer studentId, Integer teacherId);

    void uploadStuScore(MultipartFile file, ExcelLogs logs) throws IOException;

    void uploadStuCet(MultipartFile file, ExcelLogs logs) throws IOException;

    void uploadStuSeleCourse(MultipartFile file, ExcelLogs logs) throws IOException;

    void uploadStuTimetable(MultipartFile file, ExcelLogs logs) throws IOException;

    void uploadStudentTable(MultipartFile file, ExcelLogs logs) throws IOException;

    void uploadTeacherTable(MultipartFile file, ExcelLogs logs) throws IOException;

    void uploadTeacherStudentRelate(MultipartFile file, ExcelLogs logs) throws IOException;

    void uploadTeacherStudentRelate(Integer typeId, MultipartFile file, ExcelLogs logs) throws IOException;

	boolean sendOneMsg(String sign, String message, String phone)
			throws ClientProtocolException, IOException;

	 List<TreeDto> collegeTree();

	void sendMsg(String message, String reciever, String sign);

	void insertUserFacultyRation(Integer userId, String facultyId);

	UserFaculty getUserFacultyRation(Integer userId);

	boolean teacherSendMsg(String message, String reciever, String sign);

	Integer deleteByStudyYearAndStudyYearNum();

    List<TreeNode> getStudentTreeNodes(Integer typeId, Integer teacherId);

    int updateToResetPassword(Integer teacherId);

	int updateStudentToResetPassword(Integer studentId);
}
