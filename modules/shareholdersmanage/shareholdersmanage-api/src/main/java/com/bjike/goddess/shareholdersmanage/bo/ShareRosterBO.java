package com.bjike.goddess.shareholdersmanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.shareholdersmanage.type.Gender;
import com.bjike.goddess.shareholdersmanage.type.ShareholderStatus;
import com.bjike.goddess.shareholdersmanage.type.TypeName;

/**
 * 股东开户业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-18 02:41 ]
 * @Description: [ 股东开户业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ShareRosterBO extends BaseBO {

    /**
     * 地区
     */
    private String area;

    /**
     * 类型名称
     */
    private TypeName typeName;

    /**
     * 股东姓名
     */
    private String shareholderName;

    /**
     * 性别
     */
    private Gender gender;

    /**
     * 证件类型
     */
    private String documentType;

    /**
     * 证件号码
     */
    private String certifiID;

    /**
     * 联系住址
     */
    private String contactAddress;

    /**
     * 联系电话
     */
    private String contactNum;

    /**
     * 持股数量
     */
    private Integer holdNum;

    /**
     * 每股价格/元
     */
    private Double perSharePrice;

    /**
     * 出资额
     */
    private Double amount;

    /**
     * 出资方式
     */
    private String capitalWay;

    /**
     * 出资日期
     */
    private String openDate;

    /**
     * 股权类型
     */
    private String equityType;

    /**
     * 占股比例
     */
    private Double percentage;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public TypeName getTypeName() {
        return typeName;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
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

}