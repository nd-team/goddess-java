package com.bjike.goddess.taskallotment.vo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 分配及确认汇总项目表层
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-27 10:21]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ConfirmTableVO extends BaseBO {
    /**
     * 项目表
     */
    private String tableName;
    /**
     * 子表
     */
    private List<ConfirmGrandSonVO> grandSonS;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<ConfirmGrandSonVO> getGrandSonS() {
        return grandSonS;
    }

    public void setGrandSonS(List<ConfirmGrandSonVO> grandSonS) {
        this.grandSonS = grandSonS;
    }
}
