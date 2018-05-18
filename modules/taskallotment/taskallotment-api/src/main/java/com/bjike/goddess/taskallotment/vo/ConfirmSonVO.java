package com.bjike.goddess.taskallotment.vo;

import java.util.List;

/**
 * 分配及确认汇总儿子
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-18 17:03]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ConfirmSonVO {
    /**
     * 项目组/部门
     */
    private String depart;
    /**
     * 子表信息
     */
    private List<ConfirmTableVO> tableS;

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public List<ConfirmTableVO> getTableS() {
        return tableS;
    }

    public void setTableS(List<ConfirmTableVO> tableS) {
        this.tableS = tableS;
    }
}
