package com.bjike.goddess.taskallotment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

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
public class ConfirmProBO extends BaseBO {
    /**
     * 项目
     */
    private String project;
    /**
     * 子表信息
     */
    private List<ConfirmTableBO> tableS;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public List<ConfirmTableBO> getTableS() {
        return tableS;
    }

    public void setTableS(List<ConfirmTableBO> tableS) {
        this.tableS = tableS;
    }
}
