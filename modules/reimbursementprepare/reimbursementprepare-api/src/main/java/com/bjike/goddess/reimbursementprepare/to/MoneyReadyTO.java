package com.bjike.goddess.reimbursementprepare.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * 资金准备
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-03 03:07 ]
 * @Description: [ 资金准备 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MoneyReadyTO extends BaseTO {

    /**
     * 时间
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "时间不能为空")
    private String time;

    /**
     * 科目
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "科目不能为空")
    private String subject;

    /**
     * 总准备金
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "总准备金不能为空")
    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, message = "总准备金不能小于0")
    private Double totalReserve;

    /**
     * 项目组
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "项目组不能为空")
    private String projectGroup;

    /**
     * 比例分配
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "比例分配不能为空")
    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, message = "比例分配不能为小于0")
    private Double prorate;

//    /**
//     * 准备金
//     */
//    @NotNull(groups = {ADD.class, EDIT.class}, message = "准备金不能为空")
//    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, message = "准备金不能小于0")
//    private Double reserve;


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Double getTotalReserve() {
        return totalReserve;
    }

    public void setTotalReserve(Double totalReserve) {
        this.totalReserve = totalReserve;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public Double getProrate() {
        return prorate;
    }

    public void setProrate(Double prorate) {
        this.prorate = prorate;
    }

//    public Double getReserve() {
//        return reserve;
//    }
//
//    public void setReserve(Double reserve) {
//        this.reserve = reserve;
//    }
}