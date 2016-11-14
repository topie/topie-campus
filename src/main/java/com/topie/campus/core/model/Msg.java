package com.topie.campus.core.model;

import java.util.Date;

import javax.persistence.*;

import com.topie.campus.tools.excel.ExcelCell;

@Table(name = "t_msg")
public class Msg {
	
	@Id
    private Integer id;
	
	@Column(name = "type_id")
	private Integer typeId;

	 @ExcelCell(index=5)
    private String reciever;

    @ExcelCell(index=3)
    @Column(name = "msg_content")
    private String msgContent;

    @ExcelCell(index=4)
    @Column(name = "msg_sign")
    private String msgSign;

    @Column(name = "teacher_id")
    private Integer teacherId;

    @ExcelCell(index=1)
    @Column(name = "teacher_no")
    private String teacherNo;

    @ExcelCell(index=2)
    @Column(name = "teacher_name")
    private String teacherName;
    
    @Column(name = "create_time")
    private Date createTime;

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
     * @return reciever
     */
    public String getReciever() {
        return reciever;
    }

    /**
     * @param reciever
     */
    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    /**
     * @return msg_content
     */
    public String getMsgContent() {
        return msgContent;
    }

    /**
     * @param msgContent
     */
    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    /**
     * @return msg_sign
     */
    public String getMsgSign() {
        return msgSign;
    }

    /**
     * @param msgSign
     */
    public void setMsgSign(String msgSign) {
        this.msgSign = msgSign;
    }

    /**
     * @return teacher_id
     */
    public Integer getTeacherId() {
        return teacherId;
    }

    /**
     * @param teacherId
     */
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * @return teacher_no
     */
    public String getTeacherNo() {
        return teacherNo;
    }

    /**
     * @param teacherNo
     */
    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    /**
     * @return teacher_name
     */
    public String getTeacherName() {
        return teacherName;
    }

    /**
     * @param teacherName
     */
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}