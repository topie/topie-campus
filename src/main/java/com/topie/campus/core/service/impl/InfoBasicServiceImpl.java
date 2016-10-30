package com.topie.campus.core.service.impl;

import com.topie.campus.common.SimplePageInfo;
import com.topie.campus.core.dao.CollegeMapper;
import com.topie.campus.core.dao.FacultyMapper;
import com.topie.campus.core.dao.MajorMapper;
import com.topie.campus.core.dao.StudentMapper;
import com.topie.campus.core.dao.TeacherMapper;
import com.topie.campus.core.dao.TeacherStudentMapper;
import com.topie.campus.core.dao.UserFacultyMapper;
import com.topie.campus.core.dto.*;
import com.topie.campus.core.model.*;
import com.topie.campus.core.service.*;
import com.topie.campus.security.SecurityConstant;
import com.topie.campus.security.exception.AuBzConstant;
import com.topie.campus.security.exception.AuthBusinessException;
import com.topie.campus.security.model.User;
import com.topie.campus.security.service.UserService;
import com.topie.campus.security.utils.SecurityUtil;
import com.topie.campus.security.vo.UserVO;
import com.topie.campus.tools.excel.ExcelLogs;
import com.topie.campus.tools.excel.ExcelUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    @Autowired
    CollegeMapper collegeMapper;
    
    @Autowired
    FacultyMapper facultyMapper;
    
    @Autowired
    MajorMapper majorMapper;
    
    @Autowired
    StudentMapper studentMapper;
    
    @Autowired
    TeacherMapper teacherMapper;
    
    @Autowired
    UserFacultyMapper userFacultyMapper;
    
    @Value("${sendUrl}")
    private String sendUrl;
    
    @Value("${msgusername}")
    private String username;
    
    @Value("${password}")
    private String password;

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
        	 if (StringUtils.isEmpty(studentDto.getPassword())) {
        		 studentDto.setPassword(studentDto.getStudentNo());
             } 
            User user = UserVO
                    .buildSimpleUser(studentDto.getStudentNo(), studentDto.getPassword(), studentDto.getName(),
                            studentDto.getEmail());
            if (userService.findExistUser(user) > 0) {
                //throw new AuthBusinessException(user.getLoginName() + AuBzConstant.LOGIN_NAME_EXIST);
                //user.setPassword(studentDto.getPassword());
                //userService.updateUser(user);
            } else {
                userService.insertUser(user);
                userService.insertUserRole(user.getId(), SecurityConstant.ROLE_STUDENT);
                Student student = studentDto.buildStudent();
                student.setAvatar("/photos/student/"+student.getStudentNo()+".jpg");
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
        	 if (StringUtils.isEmpty(teacherDto.getJsmm())) {
        		 teacherDto.setJsmm(teacherDto.getEmployeeNo());
             }
            User user = UserVO
                    .buildSimpleUser(teacherDto.getEmployeeNo(), teacherDto.getJsmm(), teacherDto.getName(),
                            teacherDto.getEmail());
            if (userService.findExistUser(user) > 0) {
                //throw new AuthBusinessException(user.getLoginName() + AuBzConstant.LOGIN_NAME_EXIST);
            }
            userService.insertUser(user);
            userService.insertUserRole(user.getId(), SecurityConstant.ROLE_TEACHER);
            Teacher teacher = teacherDto.buildTeacher();
            teacher.setUserId(user.getId());
            teacher.setAvatar("/photos/teacher/"+teacher.getEmployeeNo()+".jpg");
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
            List<StuScore> stuScores = stuScoreService.findByStuNoAndCourseNum(dto.getCourseNum(),dto.getStuId());
            if(stuScores.size()==0)
            {
            	stuScoreService.insert(stuScore);
            }
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
            List<StuCet> stuCets = stuCetService.findByStuNoAndStudyYear(stuCet);
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
            long num = stuSeleCourseService.countByStuIdAndCourseNumAndStudyYear(stuSeleCourse);
            if(num==0)
            {
            stuSeleCourseService.insert(stuSeleCourse);
            }
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
            List<StuTimeTable> stuTimeTables = stuTimeTableService.findByStuNoAndCourseNum(stuTimeTable.getSelectCourseNum(),stuTimeTable.getStuId());
            if(stuTimeTables.size()==0)
            {
            	 stuTimeTableService.insert(stuTimeTable);
            }
           
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

	@Override
	public boolean sendOneMsg(String sign, String message,String phone) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(phone))
		{
			return false;
		}
		else{
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost=new HttpPost(sendUrl);
		//httppost.setHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk"); 
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username",username));
		params.add(new BasicNameValuePair("password",password));
		params.add(new BasicNameValuePair("mobile",phone));
		message = new String("【"+sign+"】"+message);
		params.add(new BasicNameValuePair("message",message));
		httppost.setEntity(new UrlEncodedFormEntity(params,"GBK"));
		HttpResponse response=httpclient.execute(httppost);
		System.out.println(EntityUtils.toString(response.getEntity()));
		if(response.getStatusLine().getStatusCode()==200){//如果状态码为200,就是正常返回
			return true;
		}
		return false;
		}
	}

	@Override
	public List<TreeDto> collegeTree() {
		// TODO Auto-generated method stub
		List<College> colleges = collegeMapper.selectAll();
		List<TreeDto> dtos = new ArrayList<TreeDto>();
		for(College c:colleges)
		{
			dtos.add(new TreeDto(c));
			List<Faculty> faculties = facultyMapper.selectAll();
			for(Faculty f:faculties)
			{
				dtos.add(new TreeDto(f));
			}
		}
		return dtos;
	}

	@Async
	@Override
	public void sendMsg(String message, String reciever, String sign) {
		// TODO Auto-generated method stub
		String cx[] = reciever.split(",");
		List<String> facultyIds = facultyIds = Arrays.asList(cx);
		for(String f:facultyIds)
		{
			List<String> majorIds = majorMapper.selectByFacultyId(f);
			for(String m:majorIds)
			{
				List<String> phones = iStudentService.findPhoneByMajorId(m);
				StringBuffer stringBuffer = new StringBuffer();
				for(int i=0;i<phones.size();i++)
				{
					stringBuffer.append(phones.get(i));
					if(i<phones.size()-1)
					stringBuffer.append(",");
				}
				System.out.println(stringBuffer.toString());
				sendMsgTo(message, sign, stringBuffer.toString());
			}
		}
	}
	
	private void sendMsgTo(String message,String sign,String phone) 
	{
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost=new HttpPost(sendUrl);
		//httppost.setHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk"); 
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username",username));
		params.add(new BasicNameValuePair("password",password));
		params.add(new BasicNameValuePair("mobile",phone));
		message = new String("【"+sign+"】"+message);
		params.add(new BasicNameValuePair("message",message));
		try {
			httppost.setEntity(new UrlEncodedFormEntity(params,"GBK"));
			HttpResponse response=httpclient.execute(httppost);
			System.out.println(EntityUtils.toString(response.getEntity()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void insertUserFacultyRation(Integer userId, String facultyId) {
		// TODO Auto-generated method stub
		String facultyIdArray[] = facultyId.split(",");
		userFacultyMapper.deleteByUserId(userId);
		for(String fa:facultyIdArray)
		{
			UserFaculty record = new UserFaculty();
			record.setUserId(userId);
			record.setFaculty(fa);
			userFacultyMapper.insert(record);
		}
	}
	
	@Override
	public UserFaculty getUserFacultyRation(Integer userId) {
		// TODO Auto-generated method stub
		List<UserFaculty> userFaculties =  userFacultyMapper.findByUserId(userId);
		String faculties = "";
		for(int i=0;i<userFaculties.size();i++)
		{
			faculties = faculties+userFaculties.get(i).getFaculty();
			if(i<userFaculties.size()-1)
			{
				faculties = faculties+",";
			}
		}
		UserFaculty userFaculty = new UserFaculty();
		userFaculty.setFaculty(faculties);
		userFaculty.setUserId(userId);
		return userFaculty;
	}

	@Async
	@Override
	public void teacherSendMsg(String message, String reciever, String sign) {
		// TODO Auto-generated method stub
		Integer userId = SecurityUtil.getCurrentUserId();
		Integer teacherId = teacherMapper.selectTeacherIdByUserId(userId);
		String typeIdsStr[] = reciever.split(",");
		List<Integer> typeIds = new ArrayList<Integer>();
		for(String str : typeIdsStr)
		{
			typeIds.add(Integer.valueOf(str));
		}
		List<Integer> studentIds = teacherStudentMapper.selectStudentByTeacherIdAndTypeId(teacherId,typeIds);
		String phones = "";
		for(int i=0;i<studentIds.size();i++)
		{
			String phone = studentMapper.selectByPrimaryKey(studentIds.get(i)).getContactPhone();
			if(i<studentIds.size()-1)
			{
			if(StringUtils.isNotEmpty(phone))
			phones = phones + phone+",";
			}
			else
			{
			phones = phones + phone;
			}
		}
		sendMsgTo(message, sign, phones);
	}
}
