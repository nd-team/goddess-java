package com.bjike.goddess.royalty.to;

import com.bjike.goddess.common.api.to.BaseTO;

import java.util.List;

/**
 * 部门间对赌表C
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 02:13 ]
 * @Description: [ 部门间对赌表C ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DepartmentBetCTO extends BaseTO {

    /**
     * 目标-部门分配对赌权重（%）
     */
    private Double betWeight;

    /**
     * 指标编号
     */
    private String indexNum;

    /**
     * 指标名称
     */
    private String indexName;

    /**
     * 部门对赌承诺-确认目标值
     */
    private String confirmTargetValue;

    /**
     * 实际目标值
     */
    private String actualTargetValue;

    /**
     * 项目对赌是否达标
     */
    private Boolean standard;
    /**
     * 对赌得分（部门总得分*目标-部门分配对赌权重）
     */
    private Double betScore;
    /**
     * 体系间对赌表D
     */
    private List<DepartmentBetETO> departmentBetETOS;


    public String getIndexNum() {
        return indexNum;
    }

    public void setIndexNum(String indexNum) {
        this.indexNum = indexNum;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getConfirmTargetValue() {
        return confirmTargetValue;
    }

    public void setConfirmTargetValue(String confirmTargetValue) {
        this.confirmTargetValue = confirmTargetValue;
    }

    public String getActualTargetValue() {
        return actualTargetValue;
    }

    public void setActualTargetValue(String actualTargetValue) {
        this.actualTargetValue = actualTargetValue;
    }

    public Boolean getStandard() {
        return standard;
    }

    public void setStandard(Boolean standard) {
        this.standard = standard;
    }

    public Double getBetWeight() {
        return betWeight;
    }

    public void setBetWeight(Double betWeight) {
        this.betWeight = betWeight;
    }

    public Double getBetScore() {
        return betScore;
    }

    public void setBetScore(Double betScore) {
        this.betScore = betScore;
    }


    public List<DepartmentBetETO> getDepartmentBetETOS() {
        return departmentBetETOS;
    }

    public void setDepartmentBetETOS(List<DepartmentBetETO> departmentBetETOS) {
        this.departmentBetETOS = departmentBetETOS;
    }
}