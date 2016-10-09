package com.topie.campus.core.dto;

import com.topie.campus.tools.excel.ExcelCell;

public class TeacherStudentRelateExcelDto {

    @ExcelCell(index = 1)
    private String studentNo;

    @ExcelCell(index = 0)
    private String employeeNo;

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }
}
