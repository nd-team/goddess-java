package com.bjike.goddess.moneyside.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 客户信息
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-07 09:58 ]
 * @Description: [ 客户信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CustomerInfoTO extends BaseTO {
    public interface TestAdd {
    }

    public interface TestEdit {
    }

    /**
     * 姓名
     */
    @NotBlank(groups = {CustomerInfoTO.TestAdd.class},message = "姓名不能为空")
    private String name;

    /**
     * 年龄
     */
    @NotNull(groups = {CustomerInfoTO.TestAdd.class,CustomerInfoTO.TestEdit.class},message = "年龄不能为空")
    private Integer age;

    /**
     * 性别
     */
    @NotBlank(groups = {CustomerInfoTO.TestAdd.class,CustomerInfoTO.TestEdit.class},message = "性别不能为空")
    private String sex;

    /**
     * 籍贯
     */
    @NotBlank(groups = {CustomerInfoTO.TestAdd.class,CustomerInfoTO.TestEdit.class},message = "籍贯不能为空")
    private String nativePlace;

    /**
     * 身份证号
     */
    @NotBlank(groups = {CustomerInfoTO.TestAdd.class,CustomerInfoTO.TestEdit.class},message = "身份证号不能为空")
    private String idNumber;

    /**
     * 联系方式
     */
    @NotBlank(groups = {CustomerInfoTO.TestAdd.class,CustomerInfoTO.TestEdit.class},message = "联系方式不能为空")
    private String phone;

    /**
     * 邮箱
     */
    @NotBlank(groups = {CustomerInfoTO.TestAdd.class,CustomerInfoTO.TestEdit.class},message = "邮箱不能为空")
    private String email;

    /**
     * 地址
     */
    @NotBlank(groups = {CustomerInfoTO.TestAdd.class,CustomerInfoTO.TestEdit.class},message = "地址不能为空")
    private String address;

    /**
     * 其他
     */
    @NotBlank(groups = {CustomerInfoTO.TestAdd.class,CustomerInfoTO.TestEdit.class},message = "其他不能为空")
    private String other;

    /**
     * 银行开户名
     */
    @NotBlank(groups = {CustomerInfoTO.TestAdd.class,CustomerInfoTO.TestEdit.class},message = "银行开户名不能为空")
    private String bankAccountName;

    /**
     * 银行卡号
     */
    @NotBlank(groups = {CustomerInfoTO.TestAdd.class,CustomerInfoTO.TestEdit.class},message = "银行卡号不能为空")
    private String bankCardNumber;

    /**
     * 银行开户行
     */
    @NotBlank(groups = {CustomerInfoTO.TestAdd.class,CustomerInfoTO.TestEdit.class},message = "银行开户行不能为空")
    private String bankOfDeposit;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBankCardNumber() {
        return bankCardNumber;
    }

    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber;
    }

    public String getBankOfDeposit() {
        return bankOfDeposit;
    }

    public void setBankOfDeposit(String bankOfDeposit) {
        this.bankOfDeposit = bankOfDeposit;
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