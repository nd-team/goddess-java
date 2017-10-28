package com.bjike.goddess.dispatchcar.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
* 薪资测算结果
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-25 11:24 ]
* @Description:	[ 薪资测算结果 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class CheckChangeCarTO extends BaseTO {

    public interface TestAdd{}
    /**
     * 核对意见
     */
    @NotBlank(groups = {CheckChangeCarTO.TestAdd.class},message = "核对意见不能为空")
    private String auditSugg;
    /**
     * 问题类型
     */
    @NotBlank(groups = {CheckChangeCarTO.TestAdd.class},message = "问题类型不能为空")
    private String  problemType;

    /**
     * 问题描述
     */
    @NotBlank(groups = {CheckChangeCarTO.TestAdd.class},message = "问题描述不能为空")
    private String  problemDes;

    /**
     * 是否冻结
     */
    private Boolean ifFreeze;
    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getProblemDes() {
        return problemDes;
    }

    public void setProblemDes(String problemDes) {
        this.problemDes = problemDes;
    }

    public String getAuditSugg() {
        return auditSugg;
    }

    public void setAuditSugg(String auditSugg) {
        this.auditSugg = auditSugg;
    }

    public Boolean getIfFreeze() {
        return ifFreeze;
    }

    public void setIfFreeze(Boolean ifFreeze) {
        this.ifFreeze = ifFreeze;
    }
}