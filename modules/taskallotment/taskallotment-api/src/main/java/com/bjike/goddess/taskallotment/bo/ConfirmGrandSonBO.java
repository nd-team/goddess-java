package com.bjike.goddess.taskallotment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 分配及确认汇总孙子
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-18 17:05]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ConfirmGrandSonBO extends BaseBO {
    /**
     * 姓名
     */
    private String name;
    /**
     * 工作内容
     */
    private String taskContent;

    /**
     * 子表
     */
    private List<ConfirmLastBO> confirmLastS;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public List<ConfirmLastBO> getConfirmLastS() {
        return confirmLastS;
    }

    public void setConfirmLastS(List<ConfirmLastBO> confirmLastS) {
        this.confirmLastS = confirmLastS;
    }
}
