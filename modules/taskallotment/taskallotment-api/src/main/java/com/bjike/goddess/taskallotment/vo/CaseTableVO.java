package com.bjike.goddess.taskallotment.vo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 完成情况汇总项目表层
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-27 10:39]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CaseTableVO extends BaseBO {
    /**
     * 项目表
     */
    private String tableName;
    /**
     * 子表
     */
    private List<CaseGrandSonVO> grandSonS;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<CaseGrandSonVO> getGrandSonS() {
        return grandSonS;
    }

    public void setGrandSonS(List<CaseGrandSonVO> grandSonS) {
        this.grandSonS = grandSonS;
    }
}
