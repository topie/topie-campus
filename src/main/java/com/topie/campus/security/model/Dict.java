package com.topie.campus.security.model;

import javax.persistence.*;

@Table(name = "t_dict")
public class Dict {
    @Id
    @Column(name = "dict_id")
    private Integer dictId;

    @Column(name = "dict_desc")
    private String dictDesc;

    @Column(name = "dict_name")
    private String dictName;

    @Column(name = "dict_seq")
    private Long dictSeq;

    @Column(name = "dict_status")
    private Long dictStatus;

    @Column(name = "dict_type")
    private String dictType;

    @Column(name = "dict_code")
    private String dictCode;

    /**
     * @return dict_id
     */
    public Integer getDictId() {
        return dictId;
    }

    /**
     * @param dictId
     */
    public void setDictId(Integer dictId) {
        this.dictId = dictId;
    }

    /**
     * @return dict_desc
     */
    public String getDictDesc() {
        return dictDesc;
    }

    /**
     * @param dictDesc
     */
    public void setDictDesc(String dictDesc) {
        this.dictDesc = dictDesc;
    }

    /**
     * @return dict_name
     */
    public String getDictName() {
        return dictName;
    }

    /**
     * @param dictName
     */
    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    /**
     * @return dict_seq
     */
    public Long getDictSeq() {
        return dictSeq;
    }

    /**
     * @param dictSeq
     */
    public void setDictSeq(Long dictSeq) {
        this.dictSeq = dictSeq;
    }

    /**
     * @return dict_status
     */
    public Long getDictStatus() {
        return dictStatus;
    }

    /**
     * @param dictStatus
     */
    public void setDictStatus(Long dictStatus) {
        this.dictStatus = dictStatus;
    }

    /**
     * @return dict_type
     */
    public String getDictType() {
        return dictType;
    }

    /**
     * @param dictType
     */
    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    /**
     * @return dict_code
     */
    public String getDictCode() {
        return dictCode;
    }

    /**
     * @param dictCode
     */
    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }
}