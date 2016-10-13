package com.topie.campus.core.dto;

import com.topie.campus.core.enums.EthnicGroup;
import com.topie.campus.core.enums.Gender;
import com.topie.campus.core.model.Student;
import com.topie.campus.tools.excel.ExcelCell;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.BeanUtils;

public class StudentExcelDto implements Serializable {

    private static final long serialVersionUID = -3730836890003416948L;

    private String avatar;

    /**
     * ƽ̨�û�id
     */
    private Integer userId;

    /**
     * ѧ��
     */
    @ExcelCell(index=0)
    private String studentNo;

    /**
     * ����
     */
    @ExcelCell(index=1)
    private String name;

    /**
     * �Ա�:0 Ů 1 ��
     */
    @ExcelCell(index=2)
    private String gender;

    /**
     * ��������
     */
    @ExcelCell(index=3)
    private Date birth;

    /**
     * ������ò
     */
    @ExcelCell(index=4)
    private String politicalStatus;

    /**
     * ����
     */
    @ExcelCell(index=5)
    private String ethnicGroup;

    /**
     * ��ȡ����
     */
    @ExcelCell(index=7)
    private String srcRegion;

    /**
     * ѧԺ
     */
    @ExcelCell(index=8)
    private String academe;

    /**
     * רҵ
     */
    @ExcelCell(index=10)
    private String subject;

    @ExcelCell(index=11)
    private String grade;

    /**
     * ѧ��
     */
    @ExcelCell(index=12)
    private String shoolLen;

    /**
     * ѧ��
     */
    @ExcelCell(index=14)
    private String schoolRoll;

    /**
     * �꼶
     */
    @ExcelCell(index=15)
    private String gradeYear;

    /**
     * רҵ����
     */
    @ExcelCell(index=17)
    private String majorDirection;

    /**
     * ��ѧ����
     */
    @ExcelCell(index=19)
    private String enterDate;

    /**
     * ����
     */
    @ExcelCell(index=22)
    private String email;

    /**
     * ��ע
     */
    @ExcelCell(index=29)
    private String comment;

    /**
     * ѧ�����Ӣ��A,Ӣ��B��
     */
    @ExcelCell(index=37)
    private String englishType;

    /**
     * ȫƴ
     */
    @ExcelCell(index=41)
    private String fullName;

    /**
     * רҵ����
     */
    @ExcelCell(index=45)
    private String majorCode;

    /**
     * ��Σ����ƣ���ְ�ȣ�
     */
    @ExcelCell(index=49)
    private String gradation;

    /**
     * �Ƿ���У
     */
    @ExcelCell(index=53)
    private String inschool;

    /**
     * �Ƿ�ע��
     */
    @ExcelCell(index=57)
    private String isRegister;

    /**
     * ��ҵ����
     */
    @ExcelCell(index=60)
    private String graduateDate;

    /**
     * ��ϵ�绰
     */
    @ExcelCell(index=88)
    @Column(name = "contact_phone")
    private String contactPhone;

    /**
     * �Ƿ��ҵ
     */
    @ExcelCell(index=90)
    private String password;
    
    @ExcelCell(index=92)
    private String isGraduate;

    /**
     * ѧԺ
     */
    @ExcelCell(index=124)
    private String college;
    
    


    /**
     * ��ȡͷ��
     *
     * @return avatar - ͷ��
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * ����ͷ��
     *
     * @param avatar ͷ��
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * ��ȡƽ̨�û�id
     *
     * @return user_id - ƽ̨�û�id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * ����ƽ̨�û�id
     *
     * @param userId ƽ̨�û�id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * ��ȡѧ��
     *
     * @return student_no - ѧ��
     */
    public String getStudentNo() {
        return studentNo;
    }

    /**
     * ����ѧ��
     *
     * @param studentNo ѧ��
     */
    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
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
     * ��ȡ�Ա�:0 Ů 1 ��
     *
     * @return gender - �Ա�:0 Ů 1 ��
     */
    public String getGender() {
        return gender;
    }

    /**
     * �����Ա�:0 Ů 1 ��
     *
     * @param gender �Ա�:0 Ů 1 ��
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * ��ȡ��������
     *
     * @return birth - ��������
     */
    public Date getBirth() {
        return birth;
    }

    /**
     * ���ó�������
     *
     * @param birth ��������
     */
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    /**
     * ��ȡ������ò
     *
     * @return political_status - ������ò
     */
    public String getPoliticalStatus() {
        return politicalStatus;
    }

    /**
     * ����������ò
     *
     * @param politicalStatus ������ò
     */
    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    /**
     * ��ȡ����
     *
     * @return ethnic_group - ����
     */
    public String getEthnicGroup() {
        return ethnicGroup;
    }

    /**
     * ��������
     *
     * @param ethnicGroup ����
     */
    public void setEthnicGroup(String ethnicGroup) {
        this.ethnicGroup = ethnicGroup;
    }

    /**
     * ��ȡ��ȡ����
     *
     * @return src_region - ��ȡ����
     */
    public String getSrcRegion() {
        return srcRegion;
    }

    /**
     * ������ȡ����
     *
     * @param srcRegion ��ȡ����
     */
    public void setSrcRegion(String srcRegion) {
        this.srcRegion = srcRegion;
    }

    /**
     * ��ȡѧԺ
     *
     * @return academe - ѧԺ
     */
    public String getAcademe() {
        return academe;
    }

    /**
     * ����ѧԺ
     *
     * @param academe ѧԺ
     */
    public void setAcademe(String academe) {
        this.academe = academe;
    }

    /**
     * ��ȡרҵ
     *
     * @return subject - רҵ
     */
    public String getSubject() {
        return subject;
    }

    /**
     * ����רҵ
     *
     * @param subject רҵ
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * @param grade
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * ��ȡѧ��
     *
     * @return shool_len - ѧ��
     */
    public String getShoolLen() {
        return shoolLen;
    }

    /**
     * ����ѧ��
     *
     * @param shoolLen ѧ��
     */
    public void setShoolLen(String shoolLen) {
        this.shoolLen = shoolLen;
    }

    /**
     * ��ȡѧ��
     *
     * @return school_roll - ѧ��
     */
    public String getSchoolRoll() {
        return schoolRoll;
    }

    /**
     * ����ѧ��
     *
     * @param schoolRoll ѧ��
     */
    public void setSchoolRoll(String schoolRoll) {
        this.schoolRoll = schoolRoll;
    }

    /**
     * ��ȡ�꼶
     *
     * @return grade_year - �꼶
     */
    public String getGradeYear() {
        return gradeYear;
    }

    /**
     * �����꼶
     *
     * @param gradeYear �꼶
     */
    public void setGradeYear(String gradeYear) {
        this.gradeYear = gradeYear;
    }

    /**
     * ��ȡרҵ����
     *
     * @return major_direction - רҵ����
     */
    public String getMajorDirection() {
        return majorDirection;
    }

    /**
     * ����רҵ����
     *
     * @param majorDirection רҵ����
     */
    public void setMajorDirection(String majorDirection) {
        this.majorDirection = majorDirection;
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
     * ��ȡ����
     *
     * @return email - ����
     */
    public String getEmail() {
        return email;
    }

    /**
     * ��������
     *
     * @param email ����
     */
    public void setEmail(String email) {
        this.email = email;
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
     * ��ȡѧ�����Ӣ��A,Ӣ��B��
     *
     * @return english_type - ѧ�����Ӣ��A,Ӣ��B��
     */
    public String getEnglishType() {
        return englishType;
    }

    /**
     * ����ѧ�����Ӣ��A,Ӣ��B��
     *
     * @param englishType ѧ�����Ӣ��A,Ӣ��B��
     */
    public void setEnglishType(String englishType) {
        this.englishType = englishType;
    }

    /**
     * ��ȡȫƴ
     *
     * @return full_name - ȫƴ
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * ����ȫƴ
     *
     * @param fullName ȫƴ
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * ��ȡרҵ����
     *
     * @return major_code - רҵ����
     */
    public String getMajorCode() {
        return majorCode;
    }

    /**
     * ����רҵ����
     *
     * @param majorCode רҵ����
     */
    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
    }

    /**
     * ��ȡ��Σ����ƣ���ְ�ȣ�
     *
     * @return gradation - ��Σ����ƣ���ְ�ȣ�
     */
    public String getGradation() {
        return gradation;
    }

    /**
     * ���ò�Σ����ƣ���ְ�ȣ�
     *
     * @param gradation ��Σ����ƣ���ְ�ȣ�
     */
    public void setGradation(String gradation) {
        this.gradation = gradation;
    }

    /**
     * ��ȡ�Ƿ���У
     *
     * @return inschool - �Ƿ���У
     */
    public String getInschool() {
        return inschool;
    }

    /**
     * �����Ƿ���У
     *
     * @param inschool �Ƿ���У
     */
    public void setInschool(String inschool) {
        this.inschool = inschool;
    }

    /**
     * ��ȡ�Ƿ�ע��
     *
     * @return is_register - �Ƿ�ע��
     */
    public String getIsRegister() {
        return isRegister;
    }

    /**
     * �����Ƿ�ע��
     *
     * @param isRegister �Ƿ�ע��
     */
    public void setIsRegister(String isRegister) {
        this.isRegister = isRegister;
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
     * ��ȡ��ϵ�绰
     *
     * @return contact_phone - ��ϵ�绰
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * ������ϵ�绰
     *
     * @param contactPhone ��ϵ�绰
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    /**
     * ��ȡ�Ƿ��ҵ
     *
     * @return is_graduate - �Ƿ��ҵ
     */
    public String getIsGraduate() {
        return isGraduate;
    }

    /**
     * �����Ƿ��ҵ
     *
     * @param isGraduate �Ƿ��ҵ
     */
    public void setIsGraduate(String isGraduate) {
        this.isGraduate = isGraduate;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Student buildStudent() {
		// TODO Auto-generated method stub
		Student stu = new Student();
		BeanUtils.copyProperties(this, stu);
		return stu;
	}
    
}
