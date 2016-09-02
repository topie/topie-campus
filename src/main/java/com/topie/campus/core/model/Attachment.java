package com.topie.campus.core.model;

import javax.persistence.*;

@Table(name = "t_common_attachment")
public class Attachment {

    /**
     * 附件id
     */
    @Id
    @Column(name = "attachment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attachmentId;

    /**
     * 附件名称
     */
    @Column(name = "attachment_name")
    private String attachmentName;

    /**
     * 附件类型:0:图片 1:文档 2:其他
     */
    @Column(name = "attachment_type")
    private Integer attachmentType;

    /**
     * 附件后缀
     */
    @Column(name = "attachment_suffix")
    private String attachmentSuffix;

    /**
     * 附件文件路径
     */
    @Column(name = "attachment_path")
    private String attachmentPath;

    /**
     * 附件文件路径
     */
    @Column(name = "attachment_url")
    private String attachmentUrl;

    /**
     * 附件文件大小
     */
    @Column(name = "attachment_size")
    private Long attachmentSize;

    /**
     * 上传附件用户
     */
    @Column(name = "upload_login_name")
    private String uploadLoginName;

    /**
     * 获取附件id
     *
     * @return attachment_id - 附件id
     */
    public Integer getAttachmentId() {
        return attachmentId;
    }

    /**
     * 设置附件id
     *
     * @param attachmentId 附件id
     */
    public void setAttachmentId(Integer attachmentId) {
        this.attachmentId = attachmentId;
    }

    /**
     * 获取附件名称
     *
     * @return attachment_name - 附件名称
     */
    public String getAttachmentName() {
        return attachmentName;
    }

    /**
     * 设置附件名称
     *
     * @param attachmentName 附件名称
     */
    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    /**
     * 获取附件类型:0:图片 1:文档 2:其他
     *
     * @return attachment_type - 附件类型:0:图片 1:文档 2:其他
     */
    public Integer getAttachmentType() {
        return attachmentType;
    }

    /**
     * 设置附件类型:0:图片 1:文档 2:其他
     *
     * @param attachmentType 附件类型:0:图片 1:文档 2:其他
     */
    public void setAttachmentType(Integer attachmentType) {
        this.attachmentType = attachmentType;
    }

    /**
     * 获取附件后缀
     *
     * @return attachment_suffix - 附件后缀
     */
    public String getAttachmentSuffix() {
        return attachmentSuffix;
    }

    /**
     * 设置附件后缀
     *
     * @param attachmentSuffix 附件后缀
     */
    public void setAttachmentSuffix(String attachmentSuffix) {
        this.attachmentSuffix = attachmentSuffix;
    }

    /**
     * 获取附件文件路径
     *
     * @return attachment_path - 附件文件路径
     */
    public String getAttachmentPath() {
        return attachmentPath;
    }

    /**
     * 设置附件文件路径
     *
     * @param attachmentPath 附件文件路径
     */
    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    /**
     * 获取附件文件路径
     *
     * @return attachment_url - 附件文件路径
     */
    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    /**
     * 设置附件文件路径
     *
     * @param attachmentUrl 附件文件路径
     */
    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    /**
     * 获取附件文件大小
     *
     * @return attachment_size - 附件文件大小
     */
    public Long getAttachmentSize() {
        return attachmentSize;
    }

    /**
     * 设置附件文件大小
     *
     * @param attachmentSize 附件文件大小
     */
    public void setAttachmentSize(Long attachmentSize) {
        this.attachmentSize = attachmentSize;
    }

    /**
     * 获取上传附件用户
     *
     * @return upload_login_name - 上传附件用户
     */
    public String getUploadLoginName() {
        return uploadLoginName;
    }

    /**
     * 设置上传附件用户
     *
     * @param uploadLoginName 上传附件用户
     */
    public void setUploadLoginName(String uploadLoginName) {
        this.uploadLoginName = uploadLoginName;
    }
}
