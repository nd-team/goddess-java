package com.bjike.goddess.salarymanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;
import java.util.Set;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-19 14:04]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ResultDirectionBO extends BaseBO{

    /**
     * 业务方向
     */
    private String businessDirection;

    /**
     * 工作年限子集
     */
    private List<ResultSkillPositionBO> resultSkillPosition;

    public String getBusinessDirection() {
        return businessDirection;
    }

    public void setBusinessDirection(String businessDirection) {
        this.businessDirection = businessDirection;
    }

    public List<ResultSkillPositionBO> getResultSkillPosition() {
        return resultSkillPosition;
    }

    public void setResultSkillPosition(List<ResultSkillPositionBO> resultSkillPosition) {
        this.resultSkillPosition = resultSkillPosition;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ResultDirectionBO){
            ResultDirectionBO bo=(ResultDirectionBO) obj;
            if (this.hashCode()==bo.hashCode()){
                if (this.businessDirection.equals(bo.getBusinessDirection())){
                    return true;
                }
            }
        }
        return false;
    }
}
