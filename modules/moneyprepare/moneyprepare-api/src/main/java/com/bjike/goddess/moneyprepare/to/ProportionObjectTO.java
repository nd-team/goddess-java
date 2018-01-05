package com.bjike.goddess.moneyprepare.to;

import com.bjike.goddess.common.api.to.BaseTO;

import java.util.List;

/**
 * 比例分配
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-07-08 11:59 ]
 * @Description: [ 比例分配 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProportionObjectTO extends BaseTO {

    /**
     * 比例分配添加列表
     */
    private List<ProportionTO> proportionTOList;

    public List<ProportionTO> getProportionTOList() {
        return proportionTOList;
    }

    /**
     * 资金准备id
     */
    private String fundId;

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public void setProportionTOList(List<ProportionTO> proportionTOList) {
        this.proportionTOList = proportionTOList;
    }
}