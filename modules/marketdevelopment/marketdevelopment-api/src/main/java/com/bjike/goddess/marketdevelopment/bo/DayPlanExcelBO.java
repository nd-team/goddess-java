package com.bjike.goddess.marketdevelopment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * 天计划业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:08 ]
 * @Description: [ 天计划业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DayPlanExcelBO extends BaseBO {

    /**
     * 序号
     */
    @ExcelHeader(name = "序号")
    private String serialNumber;

    /**
     * 时间
     */
    @ExcelHeader(name = "时间")
    private String time;

    /**
     * 业务状态(立项前/立项后)
     */
    @ExcelHeader(name = "业务状态")
    private String businessString;

    /**
     * 业务类型
     */
    @ExcelHeader(name = "业务类型")
    private String type;

    /**
     * 业务方向科目
     */
    @ExcelHeader(name = "业务方向科目")
    private String source;

    /**
     * 公司名称
     */
    @ExcelHeader(name = "公司名称")
    private String name;

    /**
     * 计划工作内容
     */
    @ExcelHeader(name = "计划工作内容")
    private String content;

    /**
     * 所属阶段
     */
    @ExcelHeader(name = "所属阶段")
    private String phase;

    /**
     * 任务量
     */
    @ExcelHeader(name = "任务量")
    private Double quota;

    /**
     * 任务人
     */
    @ExcelHeader(name = "任务人")
    private String own;

    /**
     * 洽谈方式
     */
    @ExcelHeader(name = "洽谈方式")
    private String negotiation;

    /**
     * 商务预算费
     */
    @ExcelHeader(name = "商务预算费")
    private Double budget;

    /**
     * 客户姓名
     */
    @ExcelHeader(name = "客户姓名")
    private String customer;

    /**
     * 客户电话
     */
    @ExcelHeader(name = "客户电话")
    private String phone;

    /**
     * 商务内容
     */
    @ExcelHeader(name = "商务内容")
    private String businessContent;

    /**
     * 配合人
     */
    @ExcelHeader(name = "配合人")
    private String coordinate;


    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBusinessString() {
        return businessString;
    }

    public void setBusinessString(String businessString) {
        this.businessString = businessString;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public Double getQuota() {
        return quota;
    }

    public void setQuota(Double quota) {
        this.quota = quota;
    }

    public String getOwn() {
        return own;
    }

    public void setOwn(String own) {
        this.own = own;
    }

    public String getNegotiation() {
        return negotiation;
    }

    public void setNegotiation(String negotiation) {
        this.negotiation = negotiation;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBusinessContent() {
        return businessContent;
    }

    public void setBusinessContent(String businessContent) {
        this.businessContent = businessContent;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }
}