package com.bjike.goddess.managepromotion.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 技能定级业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 10:22 ]
 * @Description: [ 技能定级业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SkillGradingBO extends BaseBO {
    /**
     * 技能定级A
     */
    private SkillGradingABO skillGradingABO;


    public SkillGradingABO getSkillGradingABO() {
        return skillGradingABO;
    }

    public void setSkillGradingABO(SkillGradingABO skillGradingABO) {
        this.skillGradingABO = skillGradingABO;
    }

}