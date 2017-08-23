package com.bjike.goddess.version.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 答案
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-04 03:10 ]
 * @Description: [ 答案 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AnswerTO extends BaseTO {
    public interface AnswerADD {
    }

    /**
     * 参考答案
     */
    @NotBlank(groups = AnswerTO.AnswerADD.class, message = "参考答案不能为空")
    private String answer;

    /**
     * 提供人
     */
    @NotBlank(groups = AnswerTO.AnswerADD.class, message = "提供人不能为空")
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