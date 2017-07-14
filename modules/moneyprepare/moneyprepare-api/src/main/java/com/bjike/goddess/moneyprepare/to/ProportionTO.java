package com.bjike.goddess.moneyprepare.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 比例分配
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-07-08 11:59 ]
 * @Description: [ 比例分配 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProportionTO extends BaseTO {

    public interface TestAdd {
    }

    /**
     * 时间
     */
    @NotBlank(groups = {ProportionTO.TestAdd.class}, message = "时间不能为空,格式为年月日")
    private String time;

    /**
     * 项目组
     */
    @NotBlank(groups = {ProportionTO.TestAdd.class}, message = "项目组不能为空")
    private String projectTeam;

    /**
     * 分配比例
     */
    @NotNull(groups = {ProportionTO.TestAdd.class}, message = "分配比例不能为空")
    private Double ratio;

    /**
     * 各个项目组的资金准备
     */
    private Double fund;


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProjectTeam() {
        return projectTeam;
    }

    public void setProjectTeam(String projectTeam) {
        this.projectTeam = projectTeam;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public Double getFund() {
        return fund;
    }

    public void setFund(Double fund) {
        this.fund = fund;
    }
}