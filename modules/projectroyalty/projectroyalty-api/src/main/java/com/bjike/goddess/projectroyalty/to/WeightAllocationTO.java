package com.bjike.goddess.projectroyalty.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 项目提成权重分配
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:12 ]
 * @Description: [ 项目提成权重分配 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WeightAllocationTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空", groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 项目名称
     */
    @NotBlank(message = "项目名称不能为空", groups = {ADD.class, EDIT.class})
    private String project;

    /**
     * 完工时间id
     */
    @NotBlank(message = "完工时间id不能为空", groups = {ADD.class, EDIT.class})
    private String completeId;

    /**
     * 合同金额id
     */
    @NotBlank(message = "合同金额id不能为空", groups = {ADD.class, EDIT.class})
    private String moneyId;

    /**
     * 回款周期id
     */
    @NotBlank(message = "回款周期id不能为空", groups = {ADD.class, EDIT.class})
    private String cycleId;

    /**
     * 难易难度id
     */
    @NotBlank(message = "难易难度id不能为空", groups = {ADD.class, EDIT.class})
    private String difficultyId;

    /**
     * 毛利率id
     */
    @NotBlank(message = "毛利率id不能为空", groups = {ADD.class, EDIT.class})
    private String rateId;

    /**
     * 员工持股平均比例
     */
    @NotNull(message = "员工持股平均比例不能为空", groups = {ADD.class, EDIT.class})
    private Double staff;

    /**
     * 公司风控备用金平均比例
     */
    @NotNull(message = "公司风控备用金平均比例不能为空", groups = {ADD.class, EDIT.class})
    private Double risk;

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

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getCompleteId() {
        return completeId;
    }

    public void setCompleteId(String completeId) {
        this.completeId = completeId;
    }

    public String getMoneyId() {
        return moneyId;
    }

    public void setMoneyId(String moneyId) {
        this.moneyId = moneyId;
    }

    public String getCycleId() {
        return cycleId;
    }

    public void setCycleId(String cycleId) {
        this.cycleId = cycleId;
    }

    public String getDifficultyId() {
        return difficultyId;
    }

    public void setDifficultyId(String difficultyId) {
        this.difficultyId = difficultyId;
    }

    public String getRateId() {
        return rateId;
    }

    public void setRateId(String rateId) {
        this.rateId = rateId;
    }

    public Double getStaff() {
        return staff;
    }

    public void setStaff(Double staff) {
        this.staff = staff;
    }

    public Double getRisk() {
        return risk;
    }

    public void setRisk(Double risk) {
        this.risk = risk;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}