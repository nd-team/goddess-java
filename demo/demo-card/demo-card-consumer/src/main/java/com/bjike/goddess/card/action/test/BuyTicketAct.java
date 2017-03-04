package com.bjike.goddess.card.action.test;

import com.bjike.goddess.card.entity.Card;
import com.bjike.goddess.card.service.CardAPI;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sun.security.krb5.internal.Ticket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * asdfklasdlkfj
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
    private CardAPI cardAPI ;


    /**
     * 通过卡号购票
     *
     * @param card 卡实体
     * @param bindingResult
     * @return
     * @throws ActException
     */
    @PostMapping("buy/{position}")
    public ActResult buy(@Validated Card card, BindingResult bindingResult, @PathVariable String position) throws ActException {
        try {
            String message = cardAPI.buyTicket(null, card.getAccount(), position);
            return new ActResult(message);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    @PostMapping("cancel/{position}")
    public ActResult cancel(@Validated Card card, BindingResult bindingResult, @PathVariable String position) throws ActException {
        try {
            String message = cardAPI.cancelTicket(null, card.getAccount(), position);
            return new ActResult(message);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}
