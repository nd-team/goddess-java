package com.bjike.goddess.moneyprepare.to;

import com.bjike.goddess.common.api.to.BaseTO;

import java.util.List;

/**
 * 资金准备list
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-07-08 02:23 ]
 * @Description: [ 资金准备 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FundPrepareObjectTO extends BaseTO {
    /**
     * 资金准备列表
     */
    private List<FundPrepareTO> fundPrepareTOList;

    public List<FundPrepareTO> getFundPrepareTOList() {
        return fundPrepareTOList;
    }

    public void setFundPrepareTOList(List<FundPrepareTO> fundPrepareTOList) {
        this.fundPrepareTOList = fundPrepareTOList;
    }
}