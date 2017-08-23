package com.bjike.goddess.businessinteraction.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 互动平台需求描述
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-28 03:21 ]
 * @Description: [ 互动平台需求描述 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DemandTO extends BaseTO {

    /**
     * 需求者来源
     */
    @NotBlank(message = "需求者来源不为空")
    private String origin;

    /**
     * 需求者姓名
     */
    @NotBlank(message = "需求者姓名不为空")
    private String name;

    /**
     * 需求者职能
     */
    private String work;

    /**
     * 需求者所在公司名称
     */
    private String companyName;

    /**
     * 需求者所在公司类型
     */
    private String companyType;

    /**
     * 联系方式
     */
    private String contactWay;

    /**
     * 地区
     */
    @NotBlank(message = "地区不为空")
    private String area;

    /**
     * 业务方向
     */
    @NotBlank(message = "业务方向不为空")
    private String businessTarget;

    /**
     * 规模
     */
    @NotBlank(message = "规模不为空")
    private String size;

    /**
     * 人工
     */
    private String artificial;

    /**
     * 设备
     */
    private String device;

    /**
     * 工时
     */
    private String workTime;

    /**
     * 价格
     */
    private String price;

    /**
     * 专业
     */
    @NotBlank(message = "专业不为空")
    private String profession;


    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBusinessTarget() {
        return businessTarget;
    }

    public void setBusinessTarget(String businessTarget) {
        this.businessTarget = businessTarget;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getArtificial() {
        return artificial;
    }

    public void setArtificial(String artificial) {
        this.artificial = artificial;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}