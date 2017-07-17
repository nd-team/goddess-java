package com.bjike.goddess.announcement.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 公告
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-07 02:37 ]
 * @Description: [ 公告 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AnnouncementTO extends BaseTO {

    /**
     * 分类
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "分类不能为空")
    private String classify;

    /**
     * 标题
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "标题不能为空")
    private String title;

    /**
     * 作者
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "作者不能为空")
    private String author;

    /**
     * 发布内容
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "发布内容不能为空")
    private String publishContent;

    /**
     * 发布对象数组(传用户id)
     */
    private String[] recipients;

    /**
     * 发送公邮数组
     */
    private String[] mails;

    /**
     * 是否必读
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否必读不能为空")
    private Boolean required;

    /**
     * 是否发送邮件
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否发送邮件不能为空")
    private Boolean send;

    /**
     * 是否全部发送
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否全部发送不能为空")
    private Boolean all;

    public Boolean getAll() {
        return all;
    }

    public void setAll(Boolean all) {
        this.all = all;
    }

    public String[] getMails() {
        return mails;
    }

    public void setMails(String[] mails) {
        this.mails = mails;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishContent() {
        return publishContent;
    }

    public void setPublishContent(String publishContent) {
        this.publishContent = publishContent;
    }

    public String[] getRecipients() {
        return recipients;
    }

    public void setRecipients(String[] recipients) {
        this.recipients = recipients;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Boolean getSend() {
        return send;
    }

    public void setSend(Boolean send) {
        this.send = send;
    }
}