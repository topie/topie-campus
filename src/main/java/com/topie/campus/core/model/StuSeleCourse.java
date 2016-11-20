package com.topie.campus.core.model;

import javax.persistence.*;

@Table(name = "t_stu_sele_course")
public class StuSeleCourse {
    @Id
    private Integer id;

    @Column(name = "study_year")
    private String studyYear;

    @Column(name = "study_year_num")
    private String studyYearNum;

    /**
     * ѡ�οκ�
     */
    @Column(name = "select_course_num")
    private String selectCourseNum;

    /**
     * ѧ��
     */
    @Column(name = "stu_id")
    private String stuId;

    /**
     * ����
     */
    private String name;
    
    @Column(name = "course_name")
    private String courseName;

    /**
     * רҵ
     */
    private String major;

    /**
     * �Ƿ�ѡ��
     */
    @Column(name = "is_select")
    private String isSelect;

    /**
     * ѡ������
     */
    @Column(name = "select_times")
    private String selectTimes;

    /**
     * ����ʱ��
     */
    @Column(name = "exam_time")
    private String examTime;

    /**
     * ��ע
     */
    private String comment;

    /**
     * ���ޱ��
     */
    @Column(name = "restudy_flag")
    private String restudyFlag;

    /**
     * ���Һ�
     */
    @Column(name = "classroom_num")
    private String classroomNum;

    /**
     * ��������
     */
    @Column(name = "classroom_name")
    private String classroomName;

    /**
     * �Ͽ�ʱ��
     */
    @Column(name = "couse_study_time")
    private String couseStudyTime;

    /**
     * ѡ��ʱ��
     */
    @Column(name = "select_time")
    private String selectTime;

    /**
     * �꼶
     */
    private String grade;

    /**
     * ���ޱ��
     */
    @Column(name = "is_minor")
    private String isMinor;
    
    @Column(name = "course_code")
    private String courseCode;
    
    @Column(name = "course_nature")
    private String courseNature;
    
    @Column(name = "course_affiliation")
    private String courseAffiliation;

    @Column(name = "course_credit")
    private Float courseCredit;
    
    @Column(name = "teacher_name")
    private String teacherName;
    
    @Column(name = "seat_num")
    private String seatNum;
    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

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
    public String getSelectTimes() {
        return selectTimes;
    }

    /**
     * ����ѡ������
     *
     * @param selectTimes ѡ������
     */
    public void setSelectTimes(String selectTimes) {
        this.selectTimes = selectTimes;
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
     * ��ȡѡ��ʱ��
     *
     * @return select_time - ѡ��ʱ��
     */
    public String getSelectTime() {
        return selectTime;
    }

    /**
     * ����ѡ��ʱ��
     *
     * @param selectTime ѡ��ʱ��
     */
    public void setSelectTime(String selectTime) {
        this.selectTime = selectTime;
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

	public Float getCourseCredit() {
		return courseCredit;
	}

	public void setCourseCredit(Float courseCredit) {
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

}