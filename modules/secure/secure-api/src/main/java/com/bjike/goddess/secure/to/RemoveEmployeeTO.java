package com.bjike.goddess.secure.to;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 减员名单
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 09:48 ]
 * @Description: [ 减员名单 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RemoveEmployeeTO extends BaseTO {
//    public interface AUDIT {
//        //校验审核
//    }

    /**
     * 当前各公司参保总人数
     */
    @NotNull(groups = {EDIT.class}, message = "当前各公司参保总人数不能为空")
    @Min(value = 0, groups = {EDIT.class}, message = "当前各公司参保总人数必须大于等于0")
    private Integer countCompany;

    /**
     * 当前各地市总参保人员
     */
    @NotNull(groups = {EDIT.class}, message = "当前各地市总参保人员不能为空")
    @Min(value = 0, groups = {EDIT.class}, message = "当前各地市总参保人员必须大于等于0")
    private Integer countCity;

    /**
     * 减员类型
     */
    @NotBlank(groups = {EDIT.class}, message = "减员类型不能为空")
    private String removeType;

    /**
     * 公司名称
     */
    @NotBlank(groups = {EDIT.class}, message = "公司名称不能为空")
    private String company;

    /**
     * 减员的人员姓名
     */
    @NotBlank(groups = {EDIT.class}, message = "减员的人员姓名不能为空")
    private String removeName;

    /**
     * 减员的人员当前参保地市
     */
    @NotBlank(groups = {EDIT.class}, message = "减员的人员当前参保地市不能为空")
    private String removeCity;

    /**
     * 资质名称
     */
    private String quantityName;

    /**
     * 需参保时长
     */
    private Double secureTime;

    /**
     * 需减员总人数
     */
    @NotNull(groups = {EDIT.class}, message = "需减员总人数不能为空")
    @Min(value = 0, groups = {EDIT.class}, message = "需减员总人数必须大于等于0")
    private Integer removeCount;

    /**
     * 备注
     */
    @NotBlank(groups = {EDIT.class}, message = "备注不能为空")
    private String description;

    /**
     * 员工编号
     */
    @NotBlank(groups = {EDIT.class}, message = "员工编号不能为空")
    private String employeeId;

    /**
     * 确认是否减员
     */
//    @NotNull(groups = {AUDIT.class}, message = "确认是否减员不能为空")
    private boolean confirmRemove;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public boolean getConfirmRemove() {
        return confirmRemove;
    }

    public void setConfirmRemove(boolean confirmRemove) {
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