package com.topie.campus.core.dto;

import com.topie.campus.core.model.Employment;
import com.topie.campus.tools.excel.ExcelCell;

public class EmploymentExcelDto {

	
	    @ExcelCell(index = 0)
	    private String stuId;

	    @ExcelCell(index = 1)
	    private String name;

	    @ExcelCell(index = 2)
	    private String phone;

	    @ExcelCell(index = 3)
	    private String education;

	    @ExcelCell(index = 4)
	    private String college;

	    @ExcelCell(index = 5)
	    private String major;

	    @ExcelCell(index = 6)
	    private String enterDate;

	    @ExcelCell(index = 7)
	    private String graduateDate;

	    @ExcelCell(index = 8)
	    private String identifyId;

	    @ExcelCell(index = 9)
	    private String homeAddress;

	    @ExcelCell(index = 10)
	    private String employProcess;

	    @ExcelCell(index = 11)
	    private String employPattern;

	    @ExcelCell(index = 12)
	    private String signPattern;

	    @ExcelCell(index = 13)
	    private String signCompany;

	    @ExcelCell(index = 14)
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
	    
	    public Employment buildEmployment(EmploymentExcelDto dto)
	    {
	    	Employment employment = new Employment();
	    	employment.setCollege(this.college);
	    	employment.setEducation(this.education);
	    	employment.setEmployPattern(this.employPattern);
	    	employment.setEmployProcess(this.employProcess);
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
	
