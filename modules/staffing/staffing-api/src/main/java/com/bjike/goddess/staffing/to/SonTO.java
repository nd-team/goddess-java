package com.bjike.goddess.staffing.to;

import com.bjike.goddess.common.api.to.BaseTO;

import java.util.List;

/**
 * 人工成本计划子表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-31 11:57 ]
 * @Description: [ 人工成本计划子表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SonTO extends BaseTO {

    /**
     * 薪资区间
     */
    private String sal;

    /**
     * 人工成本计划子表详细信息
     */
    private List<ExpendPlanSonDetailTO> details;

    public List<ExpendPlanSonDetailTO> getSonDetailTOs() {
        return details;
    }

    public void setSonDetailTOs(List<ExpendPlanSonDetailTO> details) {
        this.details = details;
    }

    public String getSal() {
        return sal;
    }

    public void setSal(String sal) {
        this.sal = sal;
    }
}