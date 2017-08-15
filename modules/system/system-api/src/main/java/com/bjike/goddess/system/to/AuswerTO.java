package com.bjike.goddess.system.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.sun.deploy.security.MozillaJSSNONEwithRSASignature;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 答案
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-11 04:49 ]
 * @Description: [ 答案 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AuswerTO extends BaseTO {
    public interface TestAdd{}

    /**
     * 参考答案
     */
    @NotBlank(message = "参考答案不能为空",groups = {AuswerTO.TestAdd.class})
    private String answer;

    /**
     * 提供人
     */
    @NotBlank(message = "提供人不能为空",groups = {AuswerTO.TestAdd.class})
    private String provider;


    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}