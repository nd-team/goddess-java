package com.bjike.goddess.assistance.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 补助方案数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-13 09:27 ]
 * @Description: [ 补助方案数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AssistancePlanDTO extends BaseDTO {

    public interface TestQueryByNum{}


    /**
     * 方案序号
     */
    @NotBlank(groups = {AssistancePlanDTO.TestQueryByNum.class},message = "方案序号不能为空")
    private String planNum;

    /**
     * 补助类型名称
     */
    private String typeName;

    /**
     * 补助对象(全体员工/一线实施项目)
     */
    private String helpObject;

    public String getPlanNum() {
        return planNum;
    }

    public void setPlanNum(String planNum) {
        this.planNum = planNum;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getHelpObject() {
        return helpObject;
    }

    public void setHelpObject(String helpObject) {
        this.helpObject = helpObject;
    }
}