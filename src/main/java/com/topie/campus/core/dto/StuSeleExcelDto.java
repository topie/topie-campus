package com.topie.campus.core.dto;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.github.pagehelper.StringUtil;
import com.topie.campus.core.model.StuSeleCourse;
import com.topie.campus.tools.excel.ExcelCell;

public class StuSeleExcelDto {
	
	
	    @ExcelCell(index=0)
	    private String studyYear;

	    @ExcelCell(index=1)
	    private String studyYearNum;

	    /**
	     * ѡ�οκ�
	     */
	    @ExcelCell(index=2)
	    private String selectCourseNum;

	    @ExcelCell(index=3)
	    private String stuId;

	    @ExcelCell(index=4)
	    private String name;
	    
	    @ExcelCell(index=5)
	    private String major;

	    private String isSelect;

	    @ExcelCell(index=6)
	    private String selectTime;

	    @ExcelCell(index=7)
	    private String grade;

	    @ExcelCell(index=8)
	    private String isMinor;
	    
	    @ExcelCell(index=9)
	    private String courseCode;
	    
	    @ExcelCell(index=10)
	    private String courseName;
	    
	    @ExcelCell(index=11)
	    private String courseNature;
	    
	    @ExcelCell(index=12)
	    private String courseAffiliation;

	    @ExcelCell(index=13)
	    private String courseCredit;
	    
	    @ExcelCell(index=14)
	    private String teacherName;
	    
	    @ExcelCell(index=15)
	    private String examTime;

	    @ExcelCell(index=16)
	    private String classroomName;
	    
	    @ExcelCell(index=17)
	    private String classroomNum;
	    
	    @ExcelCell(index=18)
	    private String seatNum;
	    
	    @ExcelCell(index=19)
	    private String comment;
	    
	    @ExcelCell(index=20)
	    private String restudyFlag;

	    private String couseStudyTime;
	    


	    /**
	     * @return study_year
	     */
	    public String getStudyYear() {
	        return studyYear;
	    }

	    /**
	     * @param studyYear
	     */
	    public void setStudyYear(String studyYear) {
	        this.studyYear = studyYear;
	    }

	    /**
	     * @return study_year_num
	     */
	    public String getStudyYearNum() {
	        return studyYearNum;
	    }

	    /**
	     * @param studyYearNum
	     */
	    public void setStudyYearNum(String studyYearNum) {
	        this.studyYearNum = studyYearNum;
	    }

	    /**
	     * ��ȡѡ�οκ�
	     *
	     * @return select_course_num - ѡ�οκ�
	     */
	    public String getSelectCourseNum() {
	        return selectCourseNum;
	    }

	    /**
	     * ����ѡ�οκ�
	     *
	     * @param selectCourseNum ѡ�οκ�
	     */
	    public void setSelectCourseNum(String selectCourseNum) {
	        this.selectCourseNum = selectCourseNum;
	    }

	    /**
	     * ��ȡѧ��
	     *
	     * @return stu_id - ѧ��
	     */
	    public String getStuId() {
	        return stuId;
	    }

	    /**
	     * ����ѧ��
	     *
	     * @param stuId ѧ��
	     */
	    public void setStuId(String stuId) {
	        this.stuId = stuId;
	    }

	    /**
	     * ��ȡ����
	     *
	     * @return name - ����
	     */
	    public String getName() {
	        return name;
	    }

	    /**
	     * ��������
	     *
	     * @param name ����
	     */
	    public void setName(String name) {
	        this.name = name;
	    }

	    /**
	     * ��ȡרҵ
	     *
	     * @return major - רҵ
	     */
	    public String getMajor() {
	        return major;
	    }

	    /**
	     * ����רҵ
	     *
	     * @param major רҵ
	     */
	    public void setMajor(String major) {
	        this.major = major;
	    }

	    /**
	     * ��ȡ�Ƿ�ѡ��
	     *
	     * @return is_select - �Ƿ�ѡ��
	     */
	    public String getIsSelect() {
	        return isSelect;
	    }

	    /**
	     * �����Ƿ�ѡ��
	     *
	     * @param isSelect �Ƿ�ѡ��
	     */
	    public void setIsSelect(String isSelect) {
	        this.isSelect = isSelect;
	    }

	    /**
	     * ��ȡѡ������
	     *
	     * @return select_times - ѡ������
	     */
	    public String getSelectTime() {
	        return selectTime;
	    }

	    /**
	     * ����ѡ������
	     *
	     * @param selectTimes ѡ������
	     */
	    public void setSelectTime(String selectTime) {
	        this.selectTime = selectTime;
	    }

	    /**
	     * ��ȡ����ʱ��
	     *
	     * @return exam_time - ����ʱ��
	     */
	    public String getExamTime() {
	        return examTime;
	    }

	    /**
	     * ���ÿ���ʱ��
	     *
	     * @param examTime ����ʱ��
	     */
	    public void setExamTime(String examTime) {
	        this.examTime = examTime;
	    }

	    /**
	     * ��ȡ��ע
	     *
	     * @return comment - ��ע
	     */
	    public String getComment() {
	        return comment;
	    }

	    /**
	     * ���ñ�ע
	     *
	     * @param comment ��ע
	     */
	    public void setComment(String comment) {
	        this.comment = comment;
	    }

	    /**
	     * ��ȡ���ޱ��
	     *
	     * @return restudy_flag - ���ޱ��
	     */
	    public String getRestudyFlag() {
	        return restudyFlag;
	    }

	    /**
	     * �������ޱ��
	     *
	     * @param restudyFlag ���ޱ��
	     */
	    public void setRestudyFlag(String restudyFlag) {
	        this.restudyFlag = restudyFlag;
	    }

	    /**
	     * ��ȡ���Һ�
	     *
	     * @return classroom_num - ���Һ�
	     */
	    public String getClassroomNum() {
	        return classroomNum;
	    }

	    /**
	     * ���ý��Һ�
	     *
	     * @param classroomNum ���Һ�
	     */
	    public void setClassroomNum(String classroomNum) {
	        this.classroomNum = classroomNum;
	    }

	    /**
	     * ��ȡ��������
	     *
	     * @return classroom_name - ��������
	     */
	    public String getClassroomName() {
	        return classroomName;
	    }

	    /**
	     * ���ý�������
	     *
	     * @param classroomName ��������
	     */
	    public void setClassroomName(String classroomName) {
	        this.classroomName = classroomName;
	    }

	    /**
	     * ��ȡ�Ͽ�ʱ��
	     *
	     * @return couse_study_time - �Ͽ�ʱ��
	     */
	    public String getCouseStudyTime() {
	        return couseStudyTime;
	    }

	    /**
	     * �����Ͽ�ʱ��
	     *
	     * @param couseStudyTime �Ͽ�ʱ��
	     */
	    public void setCouseStudyTime(String couseStudyTime) {
	        this.couseStudyTime = couseStudyTime;
	    }

	    /**
	     * ��ȡ�꼶
	     *
	     * @return grade - �꼶
	     */
	    public String getGrade() {
	        return grade;
	    }

	    /**
	     * �����꼶
	     *
	     * @param grade �꼶
	     */
	    public void setGrade(String grade) {
	        this.grade = grade;
	    }

	    /**
	     * ��ȡ���ޱ��
	     *
	     * @return is_minor - ���ޱ��
	     */
	    public String getIsMinor() {
	        return isMinor;
	    }

	    /**
	     * ���ø��ޱ��
	     *
	     * @param isMinor ���ޱ��
	     */
	    public void setIsMinor(String isMinor) {
	        this.isMinor = isMinor;
	    }

		public String getCourseName() {
			return courseName;
		}

		public void setCourseName(String courseName) {
			this.courseName = courseName;
		}
		
		public String getCourseCode() {
			return courseCode;
		}

		public void setCourseCode(String courseCode) {
			this.courseCode = courseCode;
		}

		public String getCourseNature() {
			return courseNature;
		}

		public void setCourseNature(String courseNature) {
			this.courseNature = courseNature;
		}

		public String getCourseAffiliation() {
			return courseAffiliation;
		}

		public void setCourseAffiliation(String courseAffiliation) {
			this.courseAffiliation = courseAffiliation;
		}

		public String getCourseCredit() {
			return courseCredit;
		}

		public void setCourseCredit(String courseCredit) {
			this.courseCredit = courseCredit;
		}

		public String getTeacherName() {
			return teacherName;
		}

		public void setTeacherName(String teacherName) {
			this.teacherName = teacherName;
		}
		
		public String getSeatNum() {
			return seatNum;
		}

		public void setSeatNum(String seatNum) {
			this.seatNum = seatNum;
		}

		public StuSeleCourse buildstuSeleCourse(StuSeleExcelDto dto) {
			// TODO Auto-generated method stub
			StuSeleCourse stuSelectCourse = new StuSeleCourse();
			BeanUtils.copyProperties(dto, stuSelectCourse);
			if(StringUtils.isNotEmpty(dto.getCourseCredit()))
				{
				stuSelectCourse.setCourseCredit(Float.valueOf(dto.getCourseCredit()));
				}
			return stuSelectCourse;      
		}

}
