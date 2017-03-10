package com.bjike.goddess.card.action.test;

import com.bjike.goddess.card.api.CardAPI;
import com.bjike.goddess.card.entity.Card;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 购票
 *
 * @Author: [liguiqin]
 * @Date: [2017-03-03 11:38]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("demo/card")
public class BuyTicketAct {

    @Autowired
    private CardAPI cardAPI;


    /**
     * 通过卡号购票
     *
     * @param card          卡实体
     * @param position      座位号
     * @param bindingResult
     */
    @PostMapping("v1/buy/{position}")
    public Result buy(@Validated Card card, @PathVariable String position, BindingResult bindingResult) throws ActException {
        try {
            String message = cardAPI.buyTicket(null, card.getAccount(), position);
            return new ActResult(message);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    @PostMapping("v1/cancel/{position}")
    public Result cancel(@Validated Card card, @PathVariable String position, BindingResult bindingResult) throws ActException {
        try {
            String message = cardAPI.cancelTicket(null, card.getAccount(), position);
            return new ActResult(message);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}
