package com.bjike.goddess.reportmanagement.to;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 现金流量比率表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-20 05:15 ]
 * @Description: [ 现金流量比率表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CashRateListTO extends BaseTO {
    /**
     * 现金流量表比率
     */
    @NotNull(message = "现金流量表比率不能为空", groups = {EDIT.class})
    private List<CashRateTO> cashRateTOs;

    public List<CashRateTO> getCashRateTOs() {
        return cashRateTOs;
    }

    public void setCashRateTOs(List<CashRateTO> cashRateTOs) {
        this.cashRateTOs = cashRateTOs;
    }
}