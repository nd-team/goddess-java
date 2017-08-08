package com.bjike.goddess.annual.to;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 年假申请审核传输对象
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-28 19:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AnnualApplyAuditTo extends BaseTO {

    /**
     * 请假人
     */
    @NotBlank(message = "请假人不能为空", groups = EDIT.class)
    private String infoUsername;

    /**
     * 审核意见
     */
    private String opinion;

    /**
     * 审核结果
     */
    @NotNull(message = "审核结果不能为空", groups = EDIT.class)
    private Boolean fruit;

    public String getInfoUsername() {
        return infoUsername;
    }

    public void setInfoUsername(String infoUsername) {
        this.infoUsername = infoUsername;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Boolean getFruit() {
        return fruit;
    }

    public void setFruit(Boolean fruit) {
        this.fruit = fruit;
    }
}
