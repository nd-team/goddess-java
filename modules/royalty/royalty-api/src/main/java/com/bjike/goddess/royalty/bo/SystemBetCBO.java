package com.bjike.goddess.royalty.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.royalty.entity.SystemBetD;

import java.util.List;

/**
 * 体系间对赌表C业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-10 04:56 ]
 * @Description: [ 体系间对赌表C业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SystemBetCBO extends BaseBO {
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
     * 对赌得分（分值*目标-部门分配对赌权重）
     */
    private Double betScore;

    /**
     * 体系间对赌表B
     */
    private SystemBetBBO systemBetBBO;
    /**
     * 体系间对赌表d
     */
    private List<SystemBetDBO> systemBetDBOS;


    public Double getBetWeight() {
        return betWeight;
    }

    public void setBetWeight(Double betWeight) {
        this.betWeight = betWeight;
    }

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

    public Double getBetScore() {
        return betScore;
    }

    public void setBetScore(Double betScore) {
        this.betScore = betScore;
    }

    public SystemBetBBO getSystemBetBBO() {
        return systemBetBBO;
    }

    public void setSystemBetBBO(SystemBetBBO systemBetBBO) {
        this.systemBetBBO = systemBetBBO;
    }

    public List<SystemBetDBO> getSystemBetDBOS() {
        return systemBetDBOS;
    }

    public void setSystemBetDBOS(List<SystemBetDBO> systemBetDBOS) {
        this.systemBetDBOS = systemBetDBOS;
    }
}