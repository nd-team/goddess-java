package com.bjike.goddess.buyticket.dto;

import com.bjike.goddess.buyticket.enums.CollectCycle;
import com.bjike.goddess.common.api.dto.BaseDTO;

import javax.persistence.Column;

/**
 * 车票购买汇总数据传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 06:13 ]
 * @Description: [ 车票购买汇总数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BuyTicketCollectDTO extends BaseDTO {

    /**
     * 汇总周期
     */
    private CollectCycle collectCycle;

    public CollectCycle getCollectCycle() {
        return collectCycle;
    }

    public void setCollectCycle(CollectCycle collectCycle) {
        this.collectCycle = collectCycle;
    }
}