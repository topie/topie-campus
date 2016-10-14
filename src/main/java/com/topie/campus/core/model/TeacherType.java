package com.topie.campus.core.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_teacher_type")
public class TeacherType {
    @Id
    @Column(name = "type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer typeId;

    /**
     * 类型名称
     */
    @Column(name = "type_name")
    private String typeName;

    /**
     * 创建时间
     */
    @Column(name = "c_time")
    private Date cTime;

    /**
     * 创建时间
     */
    @Column(name = "u_time")
    private Date uTime;

    /**
     * @return type_id
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * @param typeId
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取类型名称
     *
     * @return type_name - 类型名称
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 设置类型名称
     *
     * @param typeName 类型名称
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * 获取创建时间
     *
     * @return c_time - 创建时间
     */
    public Date getcTime() {
        return cTime;
    }

    /**
     * 设置创建时间
     *
     * @param cTime 创建时间
     */
    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    /**
     * 获取创建时间
     *
     * @return u_time - 创建时间
     */
    public Date getuTime() {
        return uTime;
    }

    /**
     * 设置创建时间
     *
     * @param uTime 创建时间
     */
    public void setuTime(Date uTime) {
        this.uTime = uTime;
    }
}