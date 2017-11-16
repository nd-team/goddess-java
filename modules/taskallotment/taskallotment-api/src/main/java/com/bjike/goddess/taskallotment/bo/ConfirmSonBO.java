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
public class ConfirmSonBO extends BaseBO {
    /**
     * 项目组/部门
     */
    private String depart;
    /**
     * 项目表信息
     */
    private List<ConfirmProBO> confirmProBOS;

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public List<ConfirmProBO> getConfirmProBOS() {
        return confirmProBOS;
    }

    public void setConfirmProBOS(List<ConfirmProBO> confirmProBOS) {
        this.confirmProBOS = confirmProBOS;
    }
}
