package com.bjike.goddess.feedback.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 问题编码规则
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-01 03:17 ]
 * @Description: [ 问题编码规则 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProblemCodeRuleTO extends BaseTO {
    public interface TestEdit{}


    /**
     * 问题编码规则(问题)
     */
    @NotBlank(message = "问题编码规则(问题)不能为空",groups = {ProblemCodeRuleTO.TestEdit.class})
    private String problemCodeRule;

    /**
     * 问题编码规则(受理)
     */
    @NotBlank(message = "问题编码规则(受理)不能为空",groups = {ProblemCodeRuleTO.TestEdit.class})
    private String acceptCodeRule;




    public String getProblemCodeRule() {
        return problemCodeRule;
    }

    public void setProblemCodeRule(String problemCodeRule) {
        this.problemCodeRule = problemCodeRule;
    }

    public String getAcceptCodeRule() {
        return acceptCodeRule;
    }

    public void setAcceptCodeRule(String acceptCodeRule) {
        this.acceptCodeRule = acceptCodeRule;
    }
}