package com.topie.campus.core.model;

import javax.persistence.*;

@Table(name = "t_common_attachment")
public class Attachment {
    /**
     * 附件id
     */
    @Id
    @Column(name = "attachment_id")
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
    private Boolean attachmentType;

    /**
     * 附件文件路径
     */
    @Column(name = "attachment_path")
    private String attachmentPath;

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
    public Boolean getAttachmentType() {
        return attachmentType;
    }

    /**
     * 设置附件类型:0:图片 1:文档 2:其他
     *
     * @param attachmentType 附件类型:0:图片 1:文档 2:其他
     */
    public void setAttachmentType(Boolean attachmentType) {
        this.attachmentType = attachmentType;
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
}