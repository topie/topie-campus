package com.topie.campus.core.model;

import javax.persistence.*;

@Table(name = "t_employment")
public class Employment {
    /**
     * ID
     */
    @Id
    private Integer id;

    /**
     * ѧ��
     */
    @Column(name = "stu_id")
    private String stuId;

    /**
     * ����
     */
    private String name;

    /**
     * �Ա�
     */
    private String gender;

    /**
     * �绰
     */
    private String phone;

    /**
     * ѧ��
     */
    private String education;

    /**
     * ѧԺ
     */
    private String college;

    /**
     * רҵ
     */
    private String major;

    /**
     * �༶��
     */
    @Column(name = "class_num")
    private String classNum;

    /**
     * ��ѧ����
     */
    @Column(name = "enter_date")
    private String enterDate;

    /**
     * ��ҵ����
     */
    @Column(name = "graduate_date")
    private String graduateDate;

    /**
     * ���֤��
     */
    @Column(name = "identify_id")
    private String identifyId;

    @Column(name = "home_address")
    private String homeAddress;

    /**
     * �Ƿ����0,δ��ȡ��1��ȡ
     */
    @Column(name = "take_table")
    private String takeTable;

    /**
     * ��ҵ״̬��֤������ı
     */
    @Column(name = "employment_status")
    private String employmentStatus;

    /**
     * ǩԼ״̬���ξ�����������ͬ����������������˶����١�����������
     */
    @Column(name = "sign_status")
    private String signStatus;

    /**
     * ������
     */
    @Column(name = "poor_student")
    private String poorStudent;

    /**
     * ��ҵģʽ��֤������ı
     */
    @Column(name = "employ_pattern")
    private String employPattern;

    /**
     * ǩԼ��ʽ���ξ�����������ͬ����������������˶����١�����������
     */
    @Column(name = "sign_pattern")
    private String signPattern;

    /**
     * ǩԼ��˾
     */
    @Column(name = "sign_company")
    private String signCompany;

    /**
     * ��ʦ
     */
    private String tutor;

    private String comment;

    /**
     * ǩԼ��˾��ַ
     */
    @Column(name = "sign_company_address")
    private String signCompanyAddress;

    /**
     * ��ȡID
     *
     * @return id - ID
     */
    private String faculty;
    
    private String teacherNo;
    
    public Integer getId() {
        return id;
    }

    /**
     * ����ID
     *
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
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
     * ��ȡ�Ա�
     *
     * @return gender - �Ա�
     */
    public String getGender() {
        return gender;
    }

    /**
     * �����Ա�
     *
     * @param gender �Ա�
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * ��ȡ�绰
     *
     * @return phone - �绰
     */
    public String getPhone() {
        return phone;
    }

    /**
     * ���õ绰
     *
     * @param phone �绰
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * ��ȡѧ��
     *
     * @return education - ѧ��
     */
    public String getEducation() {
        return education;
    }

    /**
     * ����ѧ��
     *
     * @param education ѧ��
     */
    public void setEducation(String education) {
        this.education = education;
    }

    /**
     * ��ȡѧԺ
     *
     * @return college - ѧԺ
     */
    public String getCollege() {
        return college;
    }

    /**
     * ����ѧԺ
     *
     * @param college ѧԺ
     */
    public void setCollege(String college) {
        this.college = college;
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
     * ��ȡ�༶��
     *
     * @return class_num - �༶��
     */
    public String getClassNum() {
        return classNum;
    }

    /**
     * ���ð༶��
     *
     * @param classNum �༶��
     */
    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    /**
     * ��ȡ��ѧ����
     *
     * @return enter_date - ��ѧ����
     */
    public String getEnterDate() {
        return enterDate;
    }

    /**
     * ������ѧ����
     *
     * @param enterDate ��ѧ����
     */
    public void setEnterDate(String enterDate) {
        this.enterDate = enterDate;
    }

    /**
     * ��ȡ��ҵ����
     *
     * @return graduate_date - ��ҵ����
     */
    public String getGraduateDate() {
        return graduateDate;
    }

    /**
     * ���ñ�ҵ����
     *
     * @param graduateDate ��ҵ����
     */
    public void setGraduateDate(String graduateDate) {
        this.graduateDate = graduateDate;
    }

    /**
     * ��ȡ���֤��
     *
     * @return identify_id - ���֤��
     */
    public String getIdentifyId() {
        return identifyId;
    }

    /**
     * �������֤��
     *
     * @param identifyId ���֤��
     */
    public void setIdentifyId(String identifyId) {
        this.identifyId = identifyId;
    }

    /**
     * @return home_address
     */
    public String getHomeAddress() {
        return homeAddress;
    }

    /**
     * @param homeAddress
     */
    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    /**
     * ��ȡ�Ƿ����0,δ��ȡ��1��ȡ
     *
     * @return take_table - �Ƿ����0,δ��ȡ��1��ȡ
     */
    public String getTakeTable() {
        return takeTable;
    }

    /**
     * �����Ƿ����0,δ��ȡ��1��ȡ
     *
     * @param takeTable �Ƿ����0,δ��ȡ��1��ȡ
     */
    public void setTakeTable(String takeTable) {
        this.takeTable = takeTable;
    }

    /**
     * ��ȡ��ҵ״̬��֤������ı
     *
     * @return employment_status - ��ҵ״̬��֤������ı
     */
    public String getEmploymentStatus() {
        return employmentStatus;
    }

    /**
     * ���þ�ҵ״̬��֤������ı
     *
     * @param employmentStatus ��ҵ״̬��֤������ı
     */
    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    /**
     * ��ȡǩԼ״̬���ξ�����������ͬ����������������˶����١�����������
     *
     * @return sign_status - ǩԼ״̬���ξ�����������ͬ����������������˶����١�����������
     */
    public String getSignStatus() {
        return signStatus;
    }

    /**
     * ����ǩԼ״̬���ξ�����������ͬ����������������˶����١�����������
     *
     * @param signStatus ǩԼ״̬���ξ�����������ͬ����������������˶����١�����������
     */
    public void setSignStatus(String signStatus) {
        this.signStatus = signStatus;
    }

    /**
     * ��ȡ������
     *
     * @return poor_student - ������
     */
    public String getPoorStudent() {
        return poorStudent;
    }

    /**
     * ����������
     *
     * @param poorStudent ������
     */
    public void setPoorStudent(String poorStudent) {
        this.poorStudent = poorStudent;
    }

    /**
     * ��ȡ��ҵģʽ��֤������ı
     *
     * @return employ_pattern - ��ҵģʽ��֤������ı
     */
    public String getEmployPattern() {
        return employPattern;
    }

    /**
     * ���þ�ҵģʽ��֤������ı
     *
     * @param employPattern ��ҵģʽ��֤������ı
     */
    public void setEmployPattern(String employPattern) {
        this.employPattern = employPattern;
    }

    /**
     * ��ȡǩԼ��ʽ���ξ�����������ͬ����������������˶����١�����������
     *
     * @return sign_pattern - ǩԼ��ʽ���ξ�����������ͬ����������������˶����١�����������
     */
    public String getSignPattern() {
        return signPattern;
    }

    /**
     * ����ǩԼ��ʽ���ξ�����������ͬ����������������˶����١�����������
     *
     * @param signPattern ǩԼ��ʽ���ξ�����������ͬ����������������˶����١�����������
     */
    public void setSignPattern(String signPattern) {
        this.signPattern = signPattern;
    }

    /**
     * ��ȡǩԼ��˾
     *
     * @return sign_company - ǩԼ��˾
     */
    public String getSignCompany() {
        return signCompany;
    }

    /**
     * ����ǩԼ��˾
     *
     * @param signCompany ǩԼ��˾
     */
    public void setSignCompany(String signCompany) {
        this.signCompany = signCompany;
    }

    /**
     * ��ȡ��ʦ
     *
     * @return tutor - ��ʦ
     */
    public String getTutor() {
        return tutor;
    }

    /**
     * ���õ�ʦ
     *
     * @param tutor ��ʦ
     */
    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    /**
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * ��ȡǩԼ��˾��ַ
     *
     * @return sign_company_address - ǩԼ��˾��ַ
     */
    public String getSignCompanyAddress() {
        return signCompanyAddress;
    }

    /**
     * ����ǩԼ��˾��ַ
     *
     * @param signCompanyAddress ǩԼ��˾��ַ
     */
    public void setSignCompanyAddress(String signCompanyAddress) {
        this.signCompanyAddress = signCompanyAddress;
    }

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getTeacherNo() {
		return teacherNo;
	}

	public void setTeacherNo(String teacherNo) {
		this.teacherNo = teacherNo;
	}
	
	
    
}