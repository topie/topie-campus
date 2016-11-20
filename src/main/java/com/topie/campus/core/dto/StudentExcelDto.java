package com.topie.campus.core.dto;

import com.topie.campus.core.enums.EthnicGroup;
import com.topie.campus.core.enums.Gender;
import com.topie.campus.core.model.Student;
import com.topie.campus.tools.excel.ExcelCell;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.BeanUtils;

public class StudentExcelDto implements Serializable {

    private static final long serialVersionUID = -3730836890003416948L;

    /**
     * 学锟斤拷
     */
    @ExcelCell(index=0)
    private String studentNo;

    /**
     * 锟斤拷锟斤拷
     */
    @ExcelCell(index=1)
    private String name;

    /**
     * 锟皆憋拷:0 女 1 锟斤拷
     */
    @ExcelCell(index=2)
    private String gender;

    /**
     * 锟斤拷锟斤拷锟斤拷锟斤拷
     */
    @ExcelCell(index=3)
    private String birth;

    /**
     * 锟斤拷锟斤拷锟斤拷貌
     */
    @ExcelCell(index=4)
    private String politicalStatus;

    /**
     * 锟斤拷锟斤拷
     */
    @ExcelCell(index=5)
    private String ethnicGroup;

    /**
     * 锟斤拷取锟斤拷锟斤拷
     */
    @ExcelCell(index=7)
    private String srcRegion;

    /**
     * 学院
     */
    @ExcelCell(index=8)
    private String academe;

    /**
     * 专业
     */
    @ExcelCell(index=10)
    private String subject;

    @ExcelCell(index=11)
    private String grade;

    /**
     * 学锟斤拷
     */
    @ExcelCell(index=12)
    private String shoolLen;

    /**
     * 学锟斤拷
     */
    @ExcelCell(index=14)
    private String schoolRoll;

    /**
     * 锟疥级
     */
    @ExcelCell(index=15)
    private String gradeYear;

    /**
     * 专业锟斤拷锟斤拷
     */
    @ExcelCell(index=17)
    private String majorDirection;

    /**
     * 锟斤拷学锟斤拷锟斤拷
     */
    @ExcelCell(index=19)
    private String enterDate;

    /**
     * 锟斤拷锟斤拷
     */
    @ExcelCell(index=22)
    private String email;

    /**
     * 锟斤拷注
     */
    @ExcelCell(index=29)
    private String comment;

    /**
     * 学锟斤拷锟斤拷锟接拷锟紸,英锟斤拷B锟斤拷
     */
    @ExcelCell(index=37)
    private String englishType;

    /**
     * 全拼
     */
    @ExcelCell(index=41)
    private String fullName;

    /**
     * 专业锟斤拷锟斤拷
     */
    @ExcelCell(index=45)
    private String majorCode;

    /**
     * 锟斤拷危锟斤拷锟斤拷疲锟斤拷锟街帮拷龋锟�
     */
    @ExcelCell(index=49)
    private String gradation;

    /**
     * 锟角凤拷锟斤拷校
     */
    @ExcelCell(index=53)
    private String inschool;

    /**
     * 锟角凤拷注锟斤拷
     */
    @ExcelCell(index=57)
    private String isRegister;

    /**
     * 锟斤拷业锟斤拷锟斤拷
     */
    @ExcelCell(index=60)
    private String graduateDate;

    /**
     * 锟斤拷系锟界话
     */
    @ExcelCell(index=88)
    @Column(name = "contact_phone")
    private String contactPhone;

    /**
     * 锟角凤拷锟揭�
     */
    @ExcelCell(index=90)
    private String password;
    
    @ExcelCell(index=92)
    private String isGraduate;

    /**
     * 学院
     */
    @ExcelCell(index=124)
    private String college;

    /**
     * 锟斤拷取学锟斤拷
     *
     * @return student_no - 学锟斤拷
     */
    public String getStudentNo() {
        return studentNo;
    }

    /**
     * 锟斤拷锟斤拷学锟斤拷
     *
     * @param studentNo 学锟斤拷
     */
    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    /**
     * 锟斤拷取锟斤拷锟斤拷
     *
     * @return name - 锟斤拷锟斤拷
     */
    public String getName() {
        return name;
    }

    /**
     * 锟斤拷锟斤拷锟斤拷锟斤拷
     *
     * @param name 锟斤拷锟斤拷
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 锟斤拷取锟皆憋拷:0 女 1 锟斤拷
     *
     * @return gender - 锟皆憋拷:0 女 1 锟斤拷
     */
    public String getGender() {
        return gender;
    }

    /**
     * 锟斤拷锟斤拷锟皆憋拷:0 女 1 锟斤拷
     *
     * @param gender 锟皆憋拷:0 女 1 锟斤拷
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	/**
     * 锟斤拷取锟斤拷锟斤拷锟斤拷貌
     *
     * @return political_status - 锟斤拷锟斤拷锟斤拷貌
     */
    public String getPoliticalStatus() {
        return politicalStatus;
    }

    /**
     * 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷貌
     *
     * @param politicalStatus 锟斤拷锟斤拷锟斤拷貌
     */
    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    /**
     * 锟斤拷取锟斤拷锟斤拷
     *
     * @return ethnic_group - 锟斤拷锟斤拷
     */
    public String getEthnicGroup() {
        return ethnicGroup;
    }

    /**
     * 锟斤拷锟斤拷锟斤拷锟斤拷
     *
     * @param ethnicGroup 锟斤拷锟斤拷
     */
    public void setEthnicGroup(String ethnicGroup) {
        this.ethnicGroup = ethnicGroup;
    }

    /**
     * 锟斤拷取锟斤拷取锟斤拷锟斤拷
     *
     * @return src_region - 锟斤拷取锟斤拷锟斤拷
     */
    public String getSrcRegion() {
        return srcRegion;
    }

    /**
     * 锟斤拷锟斤拷锟斤拷取锟斤拷锟斤拷
     *
     * @param srcRegion 锟斤拷取锟斤拷锟斤拷
     */
    public void setSrcRegion(String srcRegion) {
        this.srcRegion = srcRegion;
    }

    /**
     * 锟斤拷取学院
     *
     * @return academe - 学院
     */
    public String getAcademe() {
        return academe;
    }

    /**
     * 锟斤拷锟斤拷学院
     *
     * @param academe 学院
     */
    public void setAcademe(String academe) {
        this.academe = academe;
    }

    /**
     * 锟斤拷取专业
     *
     * @return subject - 专业
     */
    public String getSubject() {
        return subject;
    }

    /**
     * 锟斤拷锟斤拷专业
     *
     * @param subject 专业
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
     * 锟斤拷取学锟斤拷
     *
     * @return shool_len - 学锟斤拷
     */
    public String getShoolLen() {
        return shoolLen;
    }

    /**
     * 锟斤拷锟斤拷学锟斤拷
     *
     * @param shoolLen 学锟斤拷
     */
    public void setShoolLen(String shoolLen) {
        this.shoolLen = shoolLen;
    }

    /**
     * 锟斤拷取学锟斤拷
     *
     * @return school_roll - 学锟斤拷
     */
    public String getSchoolRoll() {
        return schoolRoll;
    }

    /**
     * 锟斤拷锟斤拷学锟斤拷
     *
     * @param schoolRoll 学锟斤拷
     */
    public void setSchoolRoll(String schoolRoll) {
        this.schoolRoll = schoolRoll;
    }

    /**
     * 锟斤拷取锟疥级
     *
     * @return grade_year - 锟疥级
     */
    public String getGradeYear() {
        return gradeYear;
    }

    /**
     * 锟斤拷锟斤拷锟疥级
     *
     * @param gradeYear 锟疥级
     */
    public void setGradeYear(String gradeYear) {
        this.gradeYear = gradeYear;
    }

    /**
     * 锟斤拷取专业锟斤拷锟斤拷
     *
     * @return major_direction - 专业锟斤拷锟斤拷
     */
    public String getMajorDirection() {
        return majorDirection;
    }

    /**
     * 锟斤拷锟斤拷专业锟斤拷锟斤拷
     *
     * @param majorDirection 专业锟斤拷锟斤拷
     */
    public void setMajorDirection(String majorDirection) {
        this.majorDirection = majorDirection;
    }

    /**
     * 锟斤拷取锟斤拷学锟斤拷锟斤拷
     *
     * @return enter_date - 锟斤拷学锟斤拷锟斤拷
     */
    public String getEnterDate() {
        return enterDate;
    }

    /**
     * 锟斤拷锟斤拷锟斤拷学锟斤拷锟斤拷
     *
     * @param enterDate 锟斤拷学锟斤拷锟斤拷
     */
    public void setEnterDate(String enterDate) {
        this.enterDate = enterDate;
    }

    /**
     * 锟斤拷取锟斤拷锟斤拷
     *
     * @return email - 锟斤拷锟斤拷
     */
    public String getEmail() {
        return email;
    }

    /**
     * 锟斤拷锟斤拷锟斤拷锟斤拷
     *
     * @param email 锟斤拷锟斤拷
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 锟斤拷取锟斤拷注
     *
     * @return comment - 锟斤拷注
     */
    public String getComment() {
        return comment;
    }

    /**
     * 锟斤拷锟矫憋拷注
     *
     * @param comment 锟斤拷注
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * 锟斤拷取学锟斤拷锟斤拷锟接拷锟紸,英锟斤拷B锟斤拷
     *
     * @return english_type - 学锟斤拷锟斤拷锟接拷锟紸,英锟斤拷B锟斤拷
     */
    public String getEnglishType() {
        return englishType;
    }

    /**
     * 锟斤拷锟斤拷学锟斤拷锟斤拷锟接拷锟紸,英锟斤拷B锟斤拷
     *
     * @param englishType 学锟斤拷锟斤拷锟接拷锟紸,英锟斤拷B锟斤拷
     */
    public void setEnglishType(String englishType) {
        this.englishType = englishType;
    }

    /**
     * 锟斤拷取全拼
     *
     * @return full_name - 全拼
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 锟斤拷锟斤拷全拼
     *
     * @param fullName 全拼
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 锟斤拷取专业锟斤拷锟斤拷
     *
     * @return major_code - 专业锟斤拷锟斤拷
     */
    public String getMajorCode() {
        return majorCode;
    }

    /**
     * 锟斤拷锟斤拷专业锟斤拷锟斤拷
     *
     * @param majorCode 专业锟斤拷锟斤拷
     */
    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
    }

    /**
     * 锟斤拷取锟斤拷危锟斤拷锟斤拷疲锟斤拷锟街帮拷龋锟�
     *
     * @return gradation - 锟斤拷危锟斤拷锟斤拷疲锟斤拷锟街帮拷龋锟�
     */
    public String getGradation() {
        return gradation;
    }

    /**
     * 锟斤拷锟矫诧拷危锟斤拷锟斤拷疲锟斤拷锟街帮拷龋锟�
     *
     * @param gradation 锟斤拷危锟斤拷锟斤拷疲锟斤拷锟街帮拷龋锟�
     */
    public void setGradation(String gradation) {
        this.gradation = gradation;
    }

    /**
     * 锟斤拷取锟角凤拷锟斤拷校
     *
     * @return inschool - 锟角凤拷锟斤拷校
     */
    public String getInschool() {
        return inschool;
    }

    /**
     * 锟斤拷锟斤拷锟角凤拷锟斤拷校
     *
     * @param inschool 锟角凤拷锟斤拷校
     */
    public void setInschool(String inschool) {
        this.inschool = inschool;
    }

    /**
     * 锟斤拷取锟角凤拷注锟斤拷
     *
     * @return is_register - 锟角凤拷注锟斤拷
     */
    public String getIsRegister() {
        return isRegister;
    }

    /**
     * 锟斤拷锟斤拷锟角凤拷注锟斤拷
     *
     * @param isRegister 锟角凤拷注锟斤拷
     */
    public void setIsRegister(String isRegister) {
        this.isRegister = isRegister;
    }

    /**
     * 锟斤拷取锟斤拷业锟斤拷锟斤拷
     *
     * @return graduate_date - 锟斤拷业锟斤拷锟斤拷
     */
    public String getGraduateDate() {
        return graduateDate;
    }

    /**
     * 锟斤拷锟矫憋拷业锟斤拷锟斤拷
     *
     * @param graduateDate 锟斤拷业锟斤拷锟斤拷
     */
    public void setGraduateDate(String graduateDate) {
        this.graduateDate = graduateDate;
    }

    /**
     * 锟斤拷取锟斤拷系锟界话
     *
     * @return contact_phone - 锟斤拷系锟界话
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * 锟斤拷锟斤拷锟斤拷系锟界话
     *
     * @param contactPhone 锟斤拷系锟界话
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    /**
     * 锟斤拷取锟角凤拷锟揭�
     *
     * @return is_graduate - 锟角凤拷锟揭�
     */
    public String getIsGraduate() {
        return isGraduate;
    }

    /**
     * 锟斤拷锟斤拷锟角凤拷锟揭�
     *
     * @param isGraduate 锟角凤拷锟揭�
     */
    public void setIsGraduate(String isGraduate) {
        this.isGraduate = isGraduate;
    }

    /**
     * 锟斤拷取学院
     *
     * @return college - 学院
     */
    public String getCollege() {
        return college;
    }

    /**
     * 锟斤拷锟斤拷学院
     *
     * @param college 学院
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
		/*SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = formate.parse(this.getBirth());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stu.setBirth(date);
		*/
		return stu;
	}
    
}
