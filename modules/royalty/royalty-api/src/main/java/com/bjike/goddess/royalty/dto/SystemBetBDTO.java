package com.bjike.goddess.royalty.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 体系间对赌表B数据传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-10 04:52 ]
 * @Description: [ 体系间对赌表B数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SystemBetBDTO extends BaseDTO {
    /**
     * 部门总得分
     */
    private Double departmentTotalScore;

    public Double getDepartmentTotalScore() {
        return departmentTotalScore;
    }

    public void setDepartmentTotalScore(Double departmentTotalScore) {
        this.departmentTotalScore = departmentTotalScore;
    }
}