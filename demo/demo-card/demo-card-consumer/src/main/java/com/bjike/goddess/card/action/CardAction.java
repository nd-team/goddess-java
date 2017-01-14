package com.bjike.goddess.card.action;

import com.bjike.goddess.card.entity.Card;
import com.bjike.goddess.card.service.CardAPI;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.consumer.restful.ActResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huanghuanlai on 2017/1/10.
 */
@RestController
@RequestMapping("demo/card")
public class CardAction{

    @Autowired
    private CardAPI cardAPI;

    @GetMapping("buy")
    public ActResult buy(@Validated(value = {EDIT.class}) Card card, BindingResult bindingResult) throws ActException {
        try {
            String message = cardAPI.buyTicketForCard(null,card.getAccount(),card.getPassword(),"adf");
            return new ActResult(message);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
