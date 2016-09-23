package com.topie.campus.core.model;

import javax.persistence.*;

@Table(name = "t_employment")
public class Employment {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

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
     * �绰
     */
    private String phone;

    /**
     * ѧ��
     */
    private String education;

    private String college;

    private String major;

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
     * ��ҵ״̬:
�����ҵ��ǩԼ
     */
    @Column(name = "employ_process")
    private String employProcess;

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
     * ǩԼ��˾��ַ
     */
    @Column(name = "sign_company_address")
    private String signCompanyAddress;

    /**
     * ��ȡID
     *
     * @return id - ID
     */
    public String getId() {
        return id;
    }

    /**
     * ����ID
     *
     * @param id ID
     */
    public void setId(String id) {
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
}