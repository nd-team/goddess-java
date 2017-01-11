package com.bjike.goddess.card.action;

import com.bjike.goddess.card.service.CardAPI;
import com.bjike.goddess.dbs.common.exception.ActException;
import com.bjike.goddess.dbs.common.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huanghuanlai on 2017/1/10.
 */
@RestController("demo/card")
public class CardAction {

    @Autowired
    private CardAPI cardAPI;

    @GetMapping("buy")
    public String buy() throws ActException{
        try {
            String message = cardAPI.buyTicketForCard(null,"123","123","adf");
            return "{\"msg\":\""+message+"\"}";
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
