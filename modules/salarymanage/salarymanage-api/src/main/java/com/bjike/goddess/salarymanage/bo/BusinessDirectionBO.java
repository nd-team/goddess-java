package com.bjike.goddess.salarymanage.bo;



import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;
import java.util.Set;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-18 14:12]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class BusinessDirectionBO extends BaseBO{
    /**
     * 业务方向
     */
    private String businessDirection;

    /**
     * 岗位子集
     */
    private List<SkillPositionBO> position;

    public String getBusinessDirection() {
        return businessDirection;
    }

    public void setBusinessDirection(String businessDirection) {
        this.businessDirection = businessDirection;
    }

    public List<SkillPositionBO> getPosition() {
        return position;
    }

    public void setPosition(List<SkillPositionBO> position) {
        this.position = position;
    }
}
