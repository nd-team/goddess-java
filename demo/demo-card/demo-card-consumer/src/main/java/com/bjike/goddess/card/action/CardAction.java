package com.bjike.goddess.card.action;

import com.bjike.goddess.card.bo.CardBO;
import com.bjike.goddess.card.entity.Card;
import com.bjike.goddess.card.service.CardAPI;
import com.bjike.goddess.card.vo.CardVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by huanghuanlai on 2017/1/10.
 */
@RestController
@RequestMapping("demo/card")
public class CardAction {

    @Autowired
    private CardAPI cardAPI;

    /**
     * 注册一张卡
     *
     * @param account  账号
     * @param password 密码
     * @return 新的卡
     * @throws ActException
     */
    @PostMapping("register")
    public ActResult register(String account, String password) throws ActException {
        try {
            CardBO bo = cardAPI.initCard(account, password);
            CardVO vo = BeanTransform.copyProperties(bo, CardVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 通过卡号查找卡信息
     *
     * @param account 账号
     * @return
     * @throws ActException
     */
    @GetMapping("/{account}")
    public ActResult findByAccount(@PathVariable String account) throws ActException {
        try {
            CardBO bo = cardAPI.findByAccount(account);
            CardVO vo = BeanTransform.copyProperties(bo, CardVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }




}
