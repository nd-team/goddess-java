package com.bjike.goddess.card.action.test;

import com.bjike.goddess.card.entity.Card;
import com.bjike.goddess.card.service.CardAPI;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.dounine.japi.common.springmvc.ApiVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sun.security.krb5.internal.Ticket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 购票
 * @Author: [liguiqin]
 * @Date: [2017-03-03 11:38]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("{version}/demo/card")
public class BuyTicketAct {

    @Autowired
    private CardAPI cardAPI ;


    /**
     * 通过卡号购票
     *
     * @param card 卡实体
     * @param position 座位号
     * @param bindingResult
     */
    @ApiVersion(1)
    @PostMapping("buy/{position}")
    public Result buy(@Validated Card card, @PathVariable String position,BindingResult bindingResult) throws ActException {
        try {
            String message = cardAPI.buyTicket(null, card.getAccount(), position);
            return new ActResult(message);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    @PostMapping("cancel/{position}")
    public Result cancel(@Validated Card card, @PathVariable String position, BindingResult bindingResult) throws ActException {
        try {
            String message = cardAPI.cancelTicket(null, card.getAccount(), position);
            return new ActResult(message);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}
