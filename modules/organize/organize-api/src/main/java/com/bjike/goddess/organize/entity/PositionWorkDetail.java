package com.bjike.goddess.organize.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;

/**
 * 岗位工作详细
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 14:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "organize_work_detail")
public class PositionWorkDetail extends BaseEntity {

    /**
     * 岗位说明
     */
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name = "instruction_id",columnDefinition = "VARCHAR(36) COMMENT '岗位说明'")
    private PositionInstruction instruction;

    /**
     * 汇报时间
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '汇报时间'")
    private String reportTime;

    /**
     * 汇报形式
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '汇报形式'")
    private String reportWay;

    /**
     * 汇报内容详述
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '汇报内容详述'")
    private String reportContent;

    /**
     * 公示内容
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '汇报内容详述'")
    private String publicity;

    /**
     * 包含的附件
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '包含的附件'")
    private String contain;

    /**
     * 是否有模板
     */
    @Column(name ="is_hasMould", columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '是否有模板'", nullable = false, insertable = false)
    private Boolean hasMould;

    /**
     *模板存储位置
     */
    @Column(columnDefinition = "VARCHAR(100) COMMENT '模板存储位置'")
    private String mouldStorage;

    /**
     * 工作文件存储位置
     */
    @Column(columnDefinition = "VARCHAR(100) COMMENT '工作文件存储位置'")
    private String paperStorage;

    /**
     * 经验总结
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '经验总结'")
    private String summarize;

    /**
     * 公示对象&联系方式
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '公示对象&联系方式'")
    private String contact;

    public PositionInstruction getInstruction() {
        return instruction;
    }

    public void setInstructions(PositionInstruction instruction) {
        this.instruction = instruction;
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

    public Boolean isHasMould() {
        return hasMould;
    }

    public void isHasMould(Boolean hasMould) {
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
