//package com.bjike.goddess.organize.to;
//
//import com.bjike.goddess.common.api.entity.ADD;
//import com.bjike.goddess.common.api.entity.EDIT;
//import com.bjike.goddess.common.api.to.BaseTO;
//import org.hibernate.validator.constraints.NotBlank;
//
//import javax.validation.constraints.NotNull;
//
///**
// * 岗位工作详细展示对象
// *
// * @Author: [dengjunren]
// * @Date: [2017-03-08 14:20]
// * @Description: [ ]
// * @Version: [1.0.0]
// * @Copy: [com.bjike]
// */
//public class PositionWorkDetailTO extends BaseTO {
//
////    /**
////     * 岗位说明书ID
////     */
////    @NotNull(message = "岗位说明书ID不能为空", groups = {ADD.class, EDIT.class})
////    private String instructionId;
////
////    /**
////     * 汇报时间
////     */
////    @NotBlank(message = "汇报时间不能为空", groups = {ADD.class, EDIT.class})
////    private String reportTime;
////
////    /**
////     * 汇报形式
////     */
////    @NotBlank(message = "汇报形式不能为空", groups = {ADD.class, EDIT.class})
////    private String reportWay;
////
////    /**
////     * 汇报内容详述
////     */
////    @NotBlank(message = "汇报内容详述不能为空", groups = {ADD.class, EDIT.class})
////    private String reportContent;
////
////    /**
////     * 公示内容
////     */
////    @NotBlank(message = "公示内容不能为空", groups = {ADD.class, EDIT.class})
////    private String publicity;
////
////    /**
////     * 包含的附件
////     */
////    @NotBlank(message = "包含的附件不能为空", groups = {ADD.class, EDIT.class})
////    private String contain;
////
////    /**
////     * 是否有模板
////     */
////    @NotNull(message = "是否有模板不能为空", groups = {ADD.class, EDIT.class})
////    private Boolean hasMould;
////
////    /**
////     * 模板存储位置
////     */
////    private String mouldStorage;
////
////    /**
////     * 工作文件存储位置
////     */
////    private String paperStorage;
////
////    /**
////     * 经验总结
////     */
////    @NotBlank(message = "经验总结不能为空", groups = {ADD.class, EDIT.class})
////    private String summarize;
////
////    /**
////     * 公示对象&联系方式
////     */
////    @NotBlank(message = "联系方式不能为空", groups = {ADD.class, EDIT.class})
////    private String contact;
////
////    public String getInstructionId() {
////        return instructionId;
////    }
////
////    public void setInstructionId(String instructionId) {
////        this.instructionId = instructionId;
////    }
////
////    public String getReportTime() {
////        return reportTime;
////    }
////
////    public void setReportTime(String reportTime) {
////        this.reportTime = reportTime;
////    }
////
////    public String getReportWay() {
////        return reportWay;
////    }
////
////    public void setReportWay(String reportWay) {
////        this.reportWay = reportWay;
////    }
////
////    public String getReportContent() {
////        return reportContent;
////    }
////
////    public void setReportContent(String reportContent) {
////        this.reportContent = reportContent;
////    }
////
////    public String getPublicity() {
////        return publicity;
////    }
////
////    public void setPublicity(String publicity) {
////        this.publicity = publicity;
////    }
////
////    public String getContain() {
////        return contain;
////    }
////
////    public void setContain(String contain) {
////        this.contain = contain;
////    }
////
////    public Boolean getHasMould() {
////        return hasMould;
////    }
////
////    public void setHasMould(Boolean hasMould) {
////        this.hasMould = hasMould;
////    }
////
////    public String getMouldStorage() {
////        return mouldStorage;
////    }
////
////    public void setMouldStorage(String mouldStorage) {
////        this.mouldStorage = mouldStorage;
////    }
////
////    public String getPaperStorage() {
////        return paperStorage;
////    }
////
////    public void setPaperStorage(String paperStorage) {
////        this.paperStorage = paperStorage;
////    }
////
////    public String getSummarize() {
////        return summarize;
////    }
////
////    public void setSummarize(String summarize) {
////        this.summarize = summarize;
////    }
////
////    public String getContact() {
////        return contact;
////    }
////
////    public void setContact(String contact) {
////        this.contact = contact;
////    }
//}
