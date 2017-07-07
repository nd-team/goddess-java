package com.bjike.goddess.enterpriseculturemanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.enterpriseculturemanage.enums.AuditResult;
import com.bjike.goddess.enterpriseculturemanage.enums.PublicizeWay;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 宣传方案信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-31 05:28 ]
 * @Description: [ 宣传方案信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PublicizeProgramInfoTO extends BaseTO {

    /**
     * 方案名称
     */
    @NotBlank(message = "方案名称不能为空",groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 宣传形式
     */
    @NotNull(message = "宣传形式不能为空",groups = {ADD.class, EDIT.class})
    private PublicizeWay way;

    /**
     * 执行人
     */
    @NotBlank(message = "执行人不能为空",groups = {ADD.class, EDIT.class})
    private String executer;

    /**
     * 执行周期
     */
    @NotBlank(message = "执行周期不能为空",groups = {ADD.class, EDIT.class})
    private String executeCycle;

    /**
     * 执行费用
     */
    @NotBlank(message = "执行费用不能为空",groups = {ADD.class, EDIT.class})
    private String executeCost;

    /**
     * 企业文化信息Id
     */
    @NotBlank(message = "企业文化信息Id不能为空",groups = {ADD.class, EDIT.class})
    private String infoId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PublicizeWay getWay() {
        return way;
    }

    public void setWay(PublicizeWay way) {
        this.way = way;
    }

    public String getExecuter() {
        return executer;
    }

    public void setExecuter(String executer) {
        this.executer = executer;
    }

    public String getExecuteCycle() {
        return executeCycle;
    }

    public void setExecuteCycle(String executeCycle) {
        this.executeCycle = executeCycle;
    }

    public String getExecuteCost() {
        return executeCost;
    }

    public void setExecuteCost(String executeCost) {
        this.executeCost = executeCost;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }
}