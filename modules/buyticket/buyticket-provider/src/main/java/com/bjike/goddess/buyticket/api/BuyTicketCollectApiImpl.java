package com.bjike.goddess.buyticket.api;

import com.bjike.goddess.buyticket.bo.BuyTicketCollectBO;
import com.bjike.goddess.buyticket.dto.BuyTicketCollectDTO;
import com.bjike.goddess.buyticket.service.BuyTicketCollectSer;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车票购买汇总业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 06:13 ]
 * @Description: [ 车票购买汇总业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("buyTicketCollectApiImpl")
public class BuyTicketCollectApiImpl implements BuyTicketCollectAPI {
    @Autowired
    private BuyTicketCollectSer buyTicketCollectSer;
    @Override
    public List<BuyTicketCollectBO> collectByCollectCycle(BuyTicketCollectDTO buyTicketCollectDTO) throws SerException {
        return buyTicketCollectSer.collectByCollectCycle(buyTicketCollectDTO);
    }

}