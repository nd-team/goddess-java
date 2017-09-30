package com.bjike.goddess.salarymanage.vo;

import java.util.List;
import java.util.Set;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-18 14:40]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class BusinessDirectionVO {

    /**
     * 业务方向
     */
    private String businessDirection;

    /**
     * 岗位子集
     */
    private List<SkillPositionVO> position;


    public String getBusinessDirection() {
        return businessDirection;
    }

    public void setBusinessDirection(String businessDirection) {
        this.businessDirection = businessDirection;
    }

    public List<SkillPositionVO> getPosition() {
        return position;
    }

    public void setPosition(List<SkillPositionVO> position) {
        this.position = position;
    }
}
