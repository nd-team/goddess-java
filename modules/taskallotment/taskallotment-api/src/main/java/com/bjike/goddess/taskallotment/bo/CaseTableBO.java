package com.bjike.goddess.taskallotment.bo;

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
public class CaseTableBO extends BaseBO {
    /**
     * 项目表
     */
    private String tableName;
    /**
     * 子表
     */
    private List<CaseGrandSonBO> grandSonS;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<CaseGrandSonBO> getGrandSonS() {
        return grandSonS;
    }

    public void setGrandSonS(List<CaseGrandSonBO> grandSonS) {
        this.grandSonS = grandSonS;
    }
}
