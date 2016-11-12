package com.topie.campus.core.dto;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.topie.campus.core.model.Employment;
import com.topie.campus.tools.excel.ExcelCell;

public class EmploymentExcelDto implements Serializable{

		/**
	 * 
	 */
	private static final long serialVersionUID = 7334040070907041634L;

		private String faculty;
		
	    private String major;

		private String classNum;
		
	    @ExcelCell(index = 0)
	    private String stuId;

	    private String name;
	    
	    private String gender;
	    
	    @ExcelCell(index = 1)
	    private String takeTable;

	    @ExcelCell(index = 2)
	    private String employmentStatus;
	    
	    @ExcelCell(index = 3)
	    private String signStatus;
	    
	    @ExcelCell(index = 4)
	    private String poorStudent;
	    

	    @ExcelCell(index = 5)
	    private String homeAddress;

	    private String phone;

	    /**
	     * 所在年级
	     */
	    private String education;

	    private String tutor;
	    
	    private String teacherNo;
	    
	    @ExcelCell(index = 6)
	    private String comment;
	    
	    private String enterDate;
	    
	    private String college;
	    
	    @ExcelCell(index = 7)
	    private String graduateDate;
	    /**
	     * 是否毕业班
	     */
	    @ExcelCell(index = 8)
	    private String isImport;

	    private String identifyId;

	    private String employProcess;

	    private String employPattern;
	    
	    private String signPattern;

	    private String signCompany;

	    private String signCompanyAddress;
	    

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
	     * @return college
	     */
	    public String getCollege() {
	        return college;
	    }

	    /**
	     * @param college
	     */
	    public void setCollege(String college) {
	        this.college = college;
	    }

	    /**
	     * @return major
	     */
	    public String getMajor() {
	        return major;
	    }

	    /**
	     * @param major
	     */
	    public void setMajor(String major) {
	        this.major = major;
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
	     * ��ȡ��ҵ״̬:
	�����ҵ��ǩԼ
	     *
	     * @return employ_process - ��ҵ״̬:
	�����ҵ��ǩԼ
	     */
	    public String getEmployProcess() {
	        return employProcess;
	    }

	    /**
	     * ���þ�ҵ״̬:
	�����ҵ��ǩԼ
	     *
	     * @param employProcess ��ҵ״̬:
	�����ҵ��ǩԼ
	     */
	    public void setEmployProcess(String employProcess) {
	        this.employProcess = employProcess;
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

	    public String getClassNum() {
			return classNum;
		}

		public void setClassNum(String classNum) {
			this.classNum = classNum;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getTakeTable() {
			return takeTable;
		}

		public void setTakeTable(String takeTable) {
			this.takeTable = takeTable;
		}

		public String getEmploymentStatus() {
			return employmentStatus;
		}

		public void setEmploymentStatus(String employmentStatus) {
			this.employmentStatus = employmentStatus;
		}

		public String getSignStatus() {
			return signStatus;
		}

		public void setSignStatus(String signStatus) {
			this.signStatus = signStatus;
		}

		public String getPoorStudent() {
			return poorStudent;
		}

		public void setPoorStudent(String poorStudent) {
			this.poorStudent = poorStudent;
		}

		public String getTutor() {
			return tutor;
		}

		public void setTutor(String tutor) {
			this.tutor = tutor;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
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

		public String getIsImport() {
			return isImport;
		}

		public void setIsImport(String isImport) {
			this.isImport = isImport;
		}

		public Employment buildEmployment(EmploymentExcelDto dto)
	    {
	    	Employment employment = new Employment();
	    	employment.setCollege(this.college);
	    	employment.setFaculty(this.faculty);
	    	employment.setTeacherNo(this.teacherNo);
	    	employment.setEducation(this.education);
	    	employment.setEmployPattern(this.employPattern);
	    	employment.setEnterDate(this.enterDate);
	    	employment.setGraduateDate(this.graduateDate);
	    	employment.setHomeAddress(this.homeAddress);
	    	employment.setIdentifyId(identifyId);
	    	employment.setMajor(major);
	    	employment.setName(name);
	    	employment.setPhone(phone);
	    	employment.setSignCompany(signCompany);
	    	employment.setSignCompanyAddress(signCompanyAddress);
	    	employment.setSignPattern(signPattern);
	    	employment.setStuId(stuId);
	    	employment.setClassNum(classNum);
	    	employment.setComment(comment);
	    	employment.setEmploymentStatus(employmentStatus);
	    	employment.setGender(gender);
	    	if(StringUtils.isNotEmpty(poorStudent))
	    	{
	    	 employment.setPoorStudent("是");
	    	}
	    	else
	    	{
	    	  employment.setPoorStudent("否");
	    	}
	    	employment.setTakeTable(takeTable);
	    	employment.setTutor(tutor);
	    	employment.setSignStatus(signStatus);
			return employment;
	    }

		@Override
		public String toString() {
			return "EmploymentExcelDto [stuId=" + stuId + ", name=" + name
					+ ", phone=" + phone + ", education=" + education
					+ ", college=" + college + ", major=" + major
					+ ", enterDate=" + enterDate + ", graduateDate="
					+ graduateDate + ", identifyId=" + identifyId
					+ ", homeAddress=" + homeAddress + ", employProcess="
					+ employProcess + ", employPattern=" + employPattern
					+ ", signPattern=" + signPattern + ", signCompany="
					+ signCompany + ", signCompanyAddress="
					+ signCompanyAddress + "]";
		}
	    
	}
	
