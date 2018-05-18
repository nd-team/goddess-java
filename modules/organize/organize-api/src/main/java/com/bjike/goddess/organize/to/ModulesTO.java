package com.bjike.goddess.organize.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 岗位工作明细表-模块表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-12 01:58 ]
 * @Description: [ 岗位工作明细表-模块表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ModulesTO extends BaseTO {

    /**
     * 岗位明细id
     */
//    @NotBlank(message = "岗位明细id不能为空", groups = { EDIT.class})
    private String workDetailsId;

    /**
     * 模块名字
     */
    @NotBlank(message = "模块名字不能为空", groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 有无关联
     */
    @NotNull(message = "有无关联不能为空", groups = {ADD.class, EDIT.class})
    private Boolean hasConnet;

    /**
     * 通报时间节点
     */
    @NotBlank(message = "通报时间节点不能为空", groups = {ADD.class, EDIT.class})
    private String informTimeNode;

    /**
     * 通报形式
     */
    @NotBlank(message = "通报形式不能为空", groups = {ADD.class, EDIT.class})
    private String notificationForm;

    /**
     * 通报内容模板
     */
    @NotBlank(message = "通报内容模板不能为空", groups = {ADD.class, EDIT.class})
    private String notificationContent;

    /**
     * 协助时间节点
     */
    @NotBlank(message = "协助时间节点不能为空", groups = {ADD.class, EDIT.class})
    private String timeNode;

    /**
     * 协助函发送形式
     */
    @NotBlank(message = "协助函发送形式不能为空", groups = {ADD.class, EDIT.class})
    private String letterForm;

    /**
     * 协助内容模板
     */
    @NotBlank(message = "协助内容模板不能为空", groups = {ADD.class, EDIT.class})
    private String contentTemplate;

    /**
     * 功能
     */
    @NotBlank(message = "功能不能为空", groups = {ADD.class, EDIT.class})
    private String functions;

    /**
     * 指标
     */
//    @NotNull(message = "指标不能为空", groups = {ADD.class, EDIT.class})
    private List<IndicatorTO> indicatorTOList;

    public String getWorkDetailsId() {
        return workDetailsId;
    }

    public void setWorkDetailsId(String workDetailsId) {
        this.workDetailsId = workDetailsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getHasConnet() {
        return hasConnet;
    }

    public void setHasConnet(Boolean hasConnet) {
        this.hasConnet = hasConnet;
    }

    public String getInformTimeNode() {
        return informTimeNode;
    }

    public void setInformTimeNode(String informTimeNode) {
        this.informTimeNode = informTimeNode;
    }

    public String getNotificationForm() {
        return notificationForm;
    }

    public void setNotificationForm(String notificationForm) {
        this.notificationForm = notificationForm;
    }

    public String getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }

    public String getTimeNode() {
        return timeNode;
    }

    public void setTimeNode(String timeNode) {
        this.timeNode = timeNode;
    }

    public String getLetterForm() {
        return letterForm;
    }

    public void setLetterForm(String letterForm) {
        this.letterForm = letterForm;
    }

    public String getContentTemplate() {
        return contentTemplate;
    }

    public void setContentTemplate(String contentTemplate) {
        this.contentTemplate = contentTemplate;
    }

    public String getFunctions() {
        return functions;
    }

    public void setFunctions(String functions) {
        this.functions = functions;
    }

    public List<IndicatorTO> getIndicatorTOList() {
        return indicatorTOList;
    }

    public void setIndicatorTOList(List<IndicatorTO> indicatorTOList) {
        this.indicatorTOList = indicatorTOList;
    }
}