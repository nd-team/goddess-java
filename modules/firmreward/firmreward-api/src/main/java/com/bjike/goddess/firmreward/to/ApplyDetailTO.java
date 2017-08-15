package com.bjike.goddess.firmreward.to;

import com.bjike.goddess.common.api.to.BaseTO;

import java.util.List;

/**
 * 奖品申请
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-13 09:04 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ApplyDetailTO extends BaseTO {
    /**
     * 奖品明细
     */
    private List<DetailTO> detailTOS;

    public List<DetailTO> getDetailTOS() {
        return detailTOS;
    }

    public void setDetailTOS(List<DetailTO> detailTOS) {
        this.detailTOS = detailTOS;
    }
}