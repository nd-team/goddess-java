package com.bjike.goddess.buyticket.api;

import com.bjike.goddess.buyticket.bo.BuyTicketCollectBO;
import com.bjike.goddess.buyticket.dto.BuyTicketCollectDTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 车票购买汇总业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 06:13 ]
 * @Description: [ 车票购买汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BuyTicketCollectAPI {
    /**
     * 根据汇总周期汇总
     *
     * @return class BuyTicketCollectBO
     */
    default List<BuyTicketCollectBO> collectByCollectCycle(BuyTicketCollectDTO buyTicketCollectDTO) throws SerException {
        return null;
    }

}