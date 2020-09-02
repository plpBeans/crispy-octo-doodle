package vip.hengnai.wine.entity;

import java.io.Serializable;

/**
 * on 2019/9/24.
 *
 * @author hua
 */

public class MessageEntity implements Serializable {
    private String id;
    /**
     * 标题
     */
    private String title;

    /**
     * 内容

     */
    private String content;
    /**
     * 发送者
     */
    private String senderId;
    /**
     * 接收人ID
     */
    private String recipientId;
    /**
     * 接受角色
     */
    private String roleName;
    /**
     * 接收角色ID
     */
    private String roleId;
    /**
     * 类型(0:系统通知 1:院内公告 2:服务提醒/私信)
     */
    private String messageType;
    private String organizationId;
    private String organizationName;
    /**
     * 消息状态(0未读 1已读)
     */
    private String messageStatus;
    /**
     * 平台(0:所有 1:web 2安卓 3:IOS)
     */
    private String messagePlatform;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建人
     */
    private String createdId;
    /**
     * 创建时间
     */
    private String createdTime;
    /**
     * 更新人
     */
    private String updatedBy;
    /**
     * 更新人
     */
    private String updatedId;
    /**
     * 更新时间
     */
    private String updatedTime;
    /**
     * 乐观锁
     */
    private String revision;
    /**
     * 删除标志位 0有效1无效
     */
    private String removed;

    public String getId() {
        return id == null ? "" : id;
    }

    public MessageEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public MessageEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content == null ? "" : content;
    }

    public MessageEntity setContent(String content) {
        this.content = content;
        return this;
    }

    public String getSenderId() {
        return senderId == null ? "" : senderId;
    }

    public MessageEntity setSenderId(String senderId) {
        this.senderId = senderId;
        return this;
    }

    public String getRecipientId() {
        return recipientId == null ? "" : recipientId;
    }

    public MessageEntity setRecipientId(String recipientId) {
        this.recipientId = recipientId;
        return this;
    }

    public String getRoleName() {
        return roleName == null ? "" : roleName;
    }

    public MessageEntity setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public String getRoleId() {
        return roleId == null ? "" : roleId;
    }

    public MessageEntity setRoleId(String roleId) {
        this.roleId = roleId;
        return this;
    }

    public String getMessageType() {
        return messageType == null ? "" : messageType;
    }

    public MessageEntity setMessageType(String messageType) {
        this.messageType = messageType;
        return this;
    }

    public String getOrganizationId() {
        return organizationId == null ? "" : organizationId;
    }

    public MessageEntity setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public String getOrganizationName() {
        return organizationName == null ? "" : organizationName;
    }

    public MessageEntity setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
        return this;
    }

    public String getMessageStatus() {
        return messageStatus == null ? "" : messageStatus;
    }

    public MessageEntity setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
        return this;
    }

    public String getMessagePlatform() {
        return messagePlatform == null ? "" : messagePlatform;
    }

    public MessageEntity setMessagePlatform(String messagePlatform) {
        this.messagePlatform = messagePlatform;
        return this;
    }

    public String getCreatedBy() {
        return createdBy == null ? "" : createdBy;
    }

    public MessageEntity setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public String getCreatedId() {
        return createdId == null ? "" : createdId;
    }

    public MessageEntity setCreatedId(String createdId) {
        this.createdId = createdId;
        return this;
    }

    public String getCreatedTime() {
        return createdTime == null ? "" : createdTime;
    }

    public MessageEntity setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getUpdatedBy() {
        return updatedBy == null ? "" : updatedBy;
    }

    public MessageEntity setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public String getUpdatedId() {
        return updatedId == null ? "" : updatedId;
    }

    public MessageEntity setUpdatedId(String updatedId) {
        this.updatedId = updatedId;
        return this;
    }

    public String getUpdatedTime() {
        return updatedTime == null ? "" : updatedTime;
    }

    public MessageEntity setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
        return this;
    }

    public String getRevision() {
        return revision == null ? "" : revision;
    }

    public MessageEntity setRevision(String revision) {
        this.revision = revision;
        return this;
    }

    public String getRemoved() {
        return removed == null ? "" : removed;
    }

    public MessageEntity setRemoved(String removed) {
        this.removed = removed;
        return this;
    }
}
