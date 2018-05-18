package com.bjike.goddess.salarymanage.vo;

import java.util.List;
import java.util.Set;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-19 14:04]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ResultDirectionVO {
    /**
     * 业务方向
     */
    private String businessDirection;

    /**
     * 技能岗位子集
     */
    private List<ResultSkillPositionVO> resultSkillPosition;

    public String getBusinessDirection() {
        return businessDirection;
    }

    public void setBusinessDirection(String businessDirection) {
        this.businessDirection = businessDirection;
    }

    public List<ResultSkillPositionVO> getResultSkillPosition() {
        return resultSkillPosition;
    }

    public void setResultSkillPosition(List<ResultSkillPositionVO> resultSkillPosition) {
        this.resultSkillPosition = resultSkillPosition;
    }
}
