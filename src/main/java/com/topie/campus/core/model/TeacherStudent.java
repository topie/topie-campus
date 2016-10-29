package com.topie.campus.core.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_relate_student_teacher")
public class TeacherStudent {

    /**
     * 学生id
     */
    @Id
    @Column(name = "type_id")
    private Integer typeId;

    /**
     * 学生id
     */
    @Id
    @Column(name = "student_id")
    private Integer studentId;

    /**
     * 老师id
     */
    @Id
    @Column(name = "teacher_id")
    private Integer teacherId;

    /**
     * 学号
     */
    @Column(name = "student_no")
    private String studentNo;

    /**
     * 职工号
     */
    @Column(name = "employee_no")
    private String employeeNo;

    /**
     * 获取学生id
     *
     * @return student_id - 学生id
     */
    public Integer getStudentId() {
        return studentId;
    }

    /**
     * 设置学生id
     *
     * @param studentId 学生id
     */
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    /**
     * 获取老师id
     *
     * @return teacher_id - 老师id
     */
    public Integer getTeacherId() {
        return teacherId;
    }

    /**
     * 设置老师id
     *
     * @param teacherId 老师id
     */
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * 获取学号
     *
     * @return student_no - 学号
     */
    public String getStudentNo() {
        return studentNo;
    }

    /**
     * 设置学号
     *
     * @param studentNo 学号
     */
    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    /**
     * 获取职工号
     *
     * @return employee_no - 职工号
     */
    public String getEmployeeNo() {
        return employeeNo;
    }

    /**
     * 设置职工号
     *
     * @param employeeNo 职工号
     */
    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}
