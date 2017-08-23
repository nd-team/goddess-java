package com.bjike.goddess.eggert.bo;

import com.bjike.goddess.common.api.bo.BaseBO;


/**
 * 调研设置信息业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-31 05:22 ]
 * @Description: [ 调研设置信息业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ResearchSettingsInfoBO extends BaseBO {

    /**
     * 调研目的
     */
    private String purpose;

    /**
     * 调研对象
     */
    private String object;

    /**
     * 调研时间
     */
    private String time;

    /**
     * 是否循环
     */
    private Boolean cycle;

    /**
     * 循环周期
     */
    private String cyclePeriod;

    /**
     * 调研内容
     */
    private String content;

    /**
     * 调研内容明细
     */
    private String contentSubsidiary;

    /**
     * 调研工具
     */
    private String tool;

    /**
     * 工具来源
     */
    private String toolSource;


    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getCycle() {
        return cycle;
    }

    public void setCycle(Boolean cycle) {
        this.cycle = cycle;
    }

    public String getCyclePeriod() {
        return cyclePeriod;
    }

    public void setCyclePeriod(String cyclePeriod) {
        this.cyclePeriod = cyclePeriod;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentSubsidiary() {
        return contentSubsidiary;
    }

    public void setContentSubsidiary(String contentSubsidiary) {
        this.contentSubsidiary = contentSubsidiary;
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    public String getToolSource() {
        return toolSource;
    }

    public void setToolSource(String toolSource) {
        this.toolSource = toolSource;
    }
}