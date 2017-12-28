package com.bjike.goddess.businsurance.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 团体意外险被保险人信息管理
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 10:07 ]
 * @Description: [ 团体意外险被保险人信息管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class GroupByInsurerTO extends BaseTO {

    public interface TestAdd{}

    /**
     * 被保险人姓名
     */
    @NotBlank(groups = {GroupByInsurerTO.TestAdd.class} , message = "被保险人姓名不能为空")
    private String name;

    /**
     * 证件号码
     */
    @NotBlank(groups = {GroupByInsurerTO.TestAdd.class} , message = "证件号码不能为空")
    private String fileNumber;

    /**
     * 性别(男女)
     */
    private String sex;

    /**
     * 投保年龄
     */
    private String payAge;

    /**
     * 层级
     */
    private String level;

    /**
     * 受益人
     */
    private String benefiter;

    /**
     * 主被保人姓名
     */
    private String majorName;

    /**
     * 与主被保人关系
     */
    private String relationByName;

    /**
     * 团体意外险id
     */
    private String groupInsureId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPayAge() {
        return payAge;
    }

    public void setPayAge(String payAge) {
        this.payAge = payAge;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getBenefiter() {
        return benefiter;
    }

    public void setBenefiter(String benefiter) {
        this.benefiter = benefiter;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getRelationByName() {
        return relationByName;
    }

    public void setRelationByName(String relationByName) {
        this.relationByName = relationByName;
    }

    public String getGroupInsureId() {
        return groupInsureId;
    }

    public void setGroupInsureId(String groupInsureId) {
        this.groupInsureId = groupInsureId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}