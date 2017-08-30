package com.bjike.goddess.assistance.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 补助方案
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-13 09:27 ]
 * @Description: [ 补助方案 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FinaceAuditPlanTO extends BaseTO {

    public interface testAudit{}

    /**
     * 运营财务部意见
     */
    @NotBlank(groups = {ManageAuditPlanTO.testAudit.class} , message = "运营财务部意见不能为空")
    private String finiceAdvice;

    public String getFiniceAdvice() {
        return finiceAdvice;
    }

    public void setFiniceAdvice(String finiceAdvice) {
        this.finiceAdvice = finiceAdvice;
    }
}