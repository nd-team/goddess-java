package com.bjike.goddess.feedback.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 其他模块意见
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-02 06:48 ]
 * @Description: [ 其他模块意见 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OtherIdeaTO extends BaseTO {
    public interface TestAdd{}

    /**
     * 模块
     */
    @NotBlank(message = "模块不能为空",groups = {OtherIdeaTO.TestAdd.class})
    private String module;

    /**
     *
     * 意见
     */
    @NotBlank(message = "意见不能为空",groups = {OtherIdeaTO.TestAdd.class})
    private String idea;


    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }
}