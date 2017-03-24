package com.bjike.goddess.organize.vo;

/**
 * 岗位工作详细展示对象
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 14:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PositionWorkDetailVO {

    /**
     * 岗位说明书ID
     */
    private String instructionId;

    /**
     * 编号
     */
    private String serialNumber;

    /**
     * 角度
     */
    private String angle;

    /**
     * 维度
     */
    private String dimension;

    /**
     * 分类
     */
    private String classify;

    /**
     * 对应功能
     */
    private String function;

    /**
     * 工作时间/频率
     */
    private String frequency;

    /**
     * 汇报时间
     */
    private String reportTime;

    /**
     * 汇报形式
     */
    private String reportWay;

    /**
     * 汇报内容详述
     */
    private String reportContent;

    /**
     * 公示内容
     */
    private String publicity;

    /**
     * 包含的附件
     */
    private String contain;

    /**
     * 是否有模板
     */
    private Boolean hasMould;

    /**
     * 模板存储位置
     */
    private String mouldStorage;

    /**
     * 工作文件存储位置
     */
    private String paperStorage;

    /**
     * 经验总结
     */
    private String summarize;

    /**
     * 公示对象&联系方式
     */
    private String contact;

    public String getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(String instructionId) {
        this.instructionId = instructionId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getAngle() {
        return angle;
    }

    public void setAngle(String angle) {
        this.angle = angle;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getReportWay() {
        return reportWay;
    }

    public void setReportWay(String reportWay) {
        this.reportWay = reportWay;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public String getPublicity() {
        return publicity;
    }

    public void setPublicity(String publicity) {
        this.publicity = publicity;
    }

    public String getContain() {
        return contain;
    }

    public void setContain(String contain) {
        this.contain = contain;
    }

    public Boolean getHasMould() {
        return hasMould;
    }

    public void setHasMould(Boolean hasMould) {
        this.hasMould = hasMould;
    }

    public String getMouldStorage() {
        return mouldStorage;
    }

    public void setMouldStorage(String mouldStorage) {
        this.mouldStorage = mouldStorage;
    }

    public String getPaperStorage() {
        return paperStorage;
    }

    public void setPaperStorage(String paperStorage) {
        this.paperStorage = paperStorage;
    }

    public String getSummarize() {
        return summarize;
    }

    public void setSummarize(String summarize) {
        this.summarize = summarize;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
