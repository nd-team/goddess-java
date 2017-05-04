package com.bjike.goddess.buyticket.action.buyticket;

import com.bjike.goddess.buyticket.api.BuyTicketCollectAPI;
import com.bjike.goddess.buyticket.dto.BuyTicketCollectDTO;
import com.bjike.goddess.buyticket.vo.BuyTicketCollectVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 车票购买汇总
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 06:13 ]
 * @Description: [ 车票购买汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("buyticketcollect")
public class BuyTicketCollectAction {
    @Autowired
    private BuyTicketCollectAPI buyTicketCollectAPI;

    /**
     * 汇总周期汇总
     *
     * @param buyTicketCollectDTO 汇总周期dto
     * @return class BuyTicketCollectVO
     * @des 根据汇总周期汇总
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect(BuyTicketCollectDTO buyTicketCollectDTO) throws ActException {
        try {
            List<BuyTicketCollectVO> buyTicketCollectVOS = BeanTransform.copyProperties(
                    buyTicketCollectAPI.collectByCollectCycle(buyTicketCollectDTO), BuyTicketCollectVO.class, true);
            return ActResult.initialize(buyTicketCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}