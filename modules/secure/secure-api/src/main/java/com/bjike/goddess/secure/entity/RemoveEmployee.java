package com.bjike.goddess.secure.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 减员名单
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 09:48 ]
 * @Description: [ 减员名单 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "secure_removeemployee")
public class RemoveEmployee extends BaseEntity {

    /**
     * 当前各公司参保总人数
     */
    @Column(name = "countCompany", columnDefinition = "INT(11)   COMMENT '当前各公司参保总人数'")
    private Integer countCompany;

    /**
     * 当前各地市总参保人员
     */
    @Column(name = "countCity", columnDefinition = "INT(11)   COMMENT '当前各地市总参保人员'")
    private Integer countCity;

    /**
     * 减员类型
     */
    @Column(name = "removeType", columnDefinition = "VARCHAR(255)   COMMENT '减员类型'")
    private String removeType;

    /**
     * 公司名称
     */
    @Column(name = "company", columnDefinition = "VARCHAR(255)   COMMENT '公司名称'")
    private String company;

    /**
     * 减员的人员姓名
     */
    @Column(name = "removeName", columnDefinition = "VARCHAR(255)   COMMENT '减员的人员姓名'")
    private String removeName;

    /**
     * 减员的人员当前参保地市
     */
    @Column(name = "removeCity", columnDefinition = "VARCHAR(255)   COMMENT '减员的人员当前参保地市'")
    private String removeCity;

    /**
     * 资质名称
     */
    @Column(name = "quantityName", columnDefinition = "VARCHAR(255)   COMMENT '资质名称'")
    private String quantityName;

    /**
     * 需参保时长
     */
    @Column(name = "secureTime", columnDefinition = "DECIMAL(10,2)   COMMENT '需参保时长'")
    private Double secureTime;

    /**
     * 需减员总人数
     */
    @Column(name = "removeCount", columnDefinition = "INT(11)  COMMENT '需减员总人数'")
    private Integer removeCount;

    /**
     * 备注
     */
    @Column(name = "description", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String description;

    /**
     * 员工编号
     */
    @Column(name = "employeeId", columnDefinition = "VARCHAR(36)   COMMENT '员工编号'")
    private String employeeId;

    /**
     * 确认是否减员
     */
    @Column(name = "confirmRemove", columnDefinition = "TINYINT(1)  DEFAULT 0   COMMENT '确认是否减员'")
    private Boolean confirmRemove;


    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Boolean getConfirmRemoveemove() {
        return confirmRemove;
    }

    public void setConfirmRemove(Boolean confirmRemove) {
        this.confirmRemove = confirmRemove;
    }

    public Integer getCountCompany() {
        return countCompany;
    }

    public void setCountCompany(Integer countCompany) {
        this.countCompany = countCompany;
    }

    public Integer getCountCity() {
        return countCity;
    }

    public void setCountCity(Integer countCity) {
        this.countCity = countCity;
    }

    public String getRemoveType() {
        return removeType;
    }

    public void setRemoveType(String removeType) {
        this.removeType = removeType;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRemoveName() {
        return removeName;
    }

    public void setRemoveName(String removeName) {
        this.removeName = removeName;
    }

    public String getRemoveCity() {
        return removeCity;
    }

    public void setRemoveCity(String removeCity) {
        this.removeCity = removeCity;
    }

    public String getQuantityName() {
        return quantityName;
    }

    public void setQuantityName(String quantityName) {
        this.quantityName = quantityName;
    }

    public Double getSecureTime() {
        return secureTime;
    }

    public void setSecureTime(Double secureTime) {
        this.secureTime = secureTime;
    }

    public Integer getRemoveCount() {
        return removeCount;
    }

    public void setRemoveCount(Integer removeCount) {
        this.removeCount = removeCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}