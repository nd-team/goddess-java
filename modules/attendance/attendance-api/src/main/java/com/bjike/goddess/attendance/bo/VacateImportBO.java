package com.bjike.goddess.attendance.bo;

import com.bjike.goddess.attendance.enums.VacateType;
import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;


/**
 * 请假管理
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-07 05:15 ]
 * @Description: [ 请假管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class VacateImportBO extends BaseBO {

    /**
     * 请假时间
     */
//    private LocalDate date;
    private String date;

    /**
     * 员工编号
     */
    private String employeeNumber;

    /**
     * 请假人
     */
    private String name;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组/部门
     */
    private String depart;

    /**
     * 职位
     */
    private String position;

    /**
     * 请假类型
     */
    private VacateType vacateType;
//    private String vacateType;

    /**
     * 开始时间
     */
//    private LocalDateTime startTime;
    private String startTime;

    /**
     * 结束时间
     */
//    private LocalDateTime endTime;
    private String endTime;

    /**
     * 请假时长(天数)
     */
    private Double time;

    /**
     * 请假原因
     */
    private String reason;

    /**
     * 填单时间是否符合提前规范
     */
    private Boolean advance;
//    private String advance;

    /**
     * 是否符合时长
     */
    private Boolean conform;
//    private String conform;

    /**
     * 主送人
     */
    @ExcelHeader(name = "主送人", notNull = true)
    private String main;

    /**
     * 抄送人
     */
    @ExcelHeader(name = "抄送人", notNull = false)
    private String carbon;

    /**
     * 工作交接内容
     */
    @ExcelHeader(name = "工作交接内容", notNull = false)
    private String handoff;

    /**
     * uuid
     */
    @ExcelHeader(name = "uuid", notNull = false)
    private String uuid;

//    /**
//     * 请假审核信息
//     */
//    @ExcelHeader(name = "请假时间", notNull = true)
//    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, mappedBy = "vacate")
//    private List<VacateAudit> vacateAudits;


//    /**
//     * 审核时间
//     */
////    private LocalDate date;
//    private String date1;
//
//    /**
//     * 审核人
//     */
//    @ExcelHeader(name = "审核人", notNull = true)
////    private String name;
//    private String name1;
//
//    /**
//     * 审核意见
//     */
//    @ExcelHeader(name = "审核意见", notNull = false)
//    private String advice;
//
//    /**
//     * 审核状态
//     */
//    @ExcelHeader(name = "审核状态", notNull = false)
////    private AduitStatus aduitStatus;
//    private String aduitStatus;

//    /**
//     * 请假申请id
//     */
//    @ExcelHeader(name = "请假时间", notNull = true)
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "vacate_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '请假申请id'")
//    private Vacate vacate;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public VacateType getVacateType() {
        return vacateType;
    }

    public void setVacateType(VacateType vacateType) {
        this.vacateType = vacateType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Boolean getAdvance() {
        return advance;
    }

    public void setAdvance(Boolean advance) {
        this.advance = advance;
    }

    public Boolean getConform() {
        return conform;
    }

    public void setConform(Boolean conform) {
        this.conform = conform;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getCarbon() {
        return carbon;
    }

    public void setCarbon(String carbon) {
        this.carbon = carbon;
    }

    public String getHandoff() {
        return handoff;
    }

    public void setHandoff(String handoff) {
        this.handoff = handoff;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}