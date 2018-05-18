package com.bjike.goddess.shareholdersmanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.shareholdersmanage.type.Gender;
import com.bjike.goddess.shareholdersmanage.type.TypeName;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 股东开户
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-18 02:41 ]
 * @Description: [ 股东开户 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ShareOpenAccountTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(groups = {ADD.class}, message = "地区不能为空")
    private String area;

    /**
     * 开户日期
     */
    @NotBlank(groups = {ADD.class}, message = "开户日期不能为空")
    private String openDate;

    /**
     * 类型名称
     */
    @NotNull(groups = {ADD.class}, message = "类型名称不能为空")
    private TypeName typeName;

    /**
     * 股东姓名
     */
    @NotBlank(groups = {ADD.class}, message = "股东姓名不能为空")
    private String shareholderName;

    /**
     * 性别
     */
    @NotNull(groups = {ADD.class}, message = "性别不能为空")
    private Gender gender;

    /**
     * 证件类型
     */
    @NotBlank(groups = {ADD.class}, message = "证件类型不能为空")
    private String documentType;

    /**
     * 证件号码
     */
    @NotBlank(groups = {ADD.class}, message = "证件号码不能为空")
    private String certifiID;

    /**
     * 联系住址
     */
    @NotBlank(groups = {ADD.class}, message = "联系住址不能为空")
    private String contactAddress;

    /**
     * 联系电话
     */
    @NotBlank(groups = {ADD.class}, message = "联系电话不能为空")
    private String contactNum;

    /**
     * 持股数量
     */
    @NotNull(groups = {ADD.class}, message = "持股数量不能为空")
    private Integer holdNum;

    /**
     * 每股价格/元
     */
    @NotNull(groups = {ADD.class}, message = "每股价格不能为空")
    private Double perSharePrice;

    /**
     * 出资方式
     */
    @NotBlank(groups = {ADD.class}, message = "出资方式不能为空")
    private String capitalWay;

    /**
     * 股权类型
     */
    @NotBlank(groups = {ADD.class}, message = "股权类型不能为空")
    private String equityType;

    /**
     * 备注
     */
    private String remark;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}