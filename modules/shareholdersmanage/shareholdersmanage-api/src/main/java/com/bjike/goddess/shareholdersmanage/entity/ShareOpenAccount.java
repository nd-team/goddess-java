package com.bjike.goddess.shareholdersmanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.shareholdersmanage.type.Gender;
import com.bjike.goddess.shareholdersmanage.type.ShareholderStatus;
import com.bjike.goddess.shareholdersmanage.type.TypeName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 股东开户
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-18 02:41 ]
 * @Description: [ 股东开户 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "shareholdersmanage_shareopenaccount")
public class ShareOpenAccount extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 开户日期
     */
    @Column(name = "openDate", nullable = false, columnDefinition = "DATE   COMMENT '开户日期'")
    private LocalDate openDate;

    /**
     * 类型名称
     */
    @Column(name = "typeName", nullable = false, columnDefinition = "INT(2)   COMMENT '类型名称'")
    private TypeName typeName;

    /**
     * 股东姓名
     */
    @Column(name = "shareholderName", nullable = false,unique = true, columnDefinition = "VARCHAR(255)   COMMENT '股东姓名'")
    private String shareholderName;

    /**
     * 性别
     */
    @Column(name = "gender", nullable = false, columnDefinition = "INT(2)   COMMENT '性别'")
    private Gender gender;

    /**
     * 证件类型
     */
    @Column(name = "documentType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '证件类型'")
    private String documentType;

    /**
     * 证件号码
     */
    @Column(name = "certifiID", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '证件号码'")
    private String certifiID;

    /**
     * 联系住址
     */
    @Column(name = "contactAddress", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '联系住址'")
    private String contactAddress;

    /**
     * 联系电话
     */
    @Column(name = "contactNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '联系电话'")
    private String contactNum;

    /**
     * 持股数量
     */
    @Column(name = "holdNum", nullable = false, columnDefinition = "INT(11)   COMMENT '持股数量'")
    private Integer holdNum;

    /**
     * 每股价格/元
     */
    @Column(name = "perSharePrice", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '每股价格/元'")
    private Double perSharePrice;

    /**
     * 出资额
     */
    @Column(name = "amount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '出资额'")
    private Double amount;

    /**
     * 占股比例
     */
    @Column(name = "percentage", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '占股比例'")
    private Double percentage;

    /**
     * 出资方式
     */
    @Column(name = "capitalWay", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '出资方式'")
    private String capitalWay;

    /**
     * 股权类型
     */
    @Column(name = "equityType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '股权类型'")
    private String equityType;

    /**
     * 股东状态
     */
    @Column(name = "shareholderStatus", nullable = false, columnDefinition = "INT(2)   COMMENT '股东状态'")
    private ShareholderStatus shareholderStatus;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public LocalDate getOpenDate() {
        return openDate;
    }

    public void setOpenDate(LocalDate openDate) {
        this.openDate = openDate;
    }

    public TypeName getTypeName() {
        return typeName;
    }

    public void setTypeName(TypeName typeName) {
        this.typeName = typeName;
    }

    public String getShareholderName() {
        return shareholderName;
    }

    public void setShareholderName(String shareholderName) {
        this.shareholderName = shareholderName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getCertifiID() {
        return certifiID;
    }

    public void setCertifiID(String certifiID) {
        this.certifiID = certifiID;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public Integer getHoldNum() {
        return holdNum;
    }

    public void setHoldNum(Integer holdNum) {
        this.holdNum = holdNum;
    }

    public Double getPerSharePrice() {
        return perSharePrice;
    }

    public void setPerSharePrice(Double perSharePrice) {
        this.perSharePrice = perSharePrice;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public String getCapitalWay() {
        return capitalWay;
    }

    public void setCapitalWay(String capitalWay) {
        this.capitalWay = capitalWay;
    }

    public String getEquityType() {
        return equityType;
    }

    public void setEquityType(String equityType) {
        this.equityType = equityType;
    }

    public ShareholderStatus getShareholderStatus() {
        return shareholderStatus;
    }

    public void setShareholderStatus(ShareholderStatus shareholderStatus) {
        this.shareholderStatus = shareholderStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}