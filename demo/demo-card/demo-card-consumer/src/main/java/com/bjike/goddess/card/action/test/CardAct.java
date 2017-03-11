package com.bjike.goddess.card.action.test;

import com.bjike.goddess.card.api.CardAPI;
import com.bjike.goddess.card.bo.CardBO;
import com.bjike.goddess.card.to.CardTO;
import com.bjike.goddess.card.vo.CardVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 卡业务
 * Created by huanghuanlai on 2017/1/10.
 */
@RestController
@RequestMapping("demo/card")
public class CardAct {

    @Autowired
    private CardAPI cardAPI;

    /**
     * 注册一张卡
     *
     * @version v1         版本
     * @return class Card
     */
    @PostMapping("v1/register")
    public Result register(@Validated CardTO cardTO) throws ActException {
        try {
            CardBO bo = cardAPI.initCard(cardTO);
            CardVO vo = BeanTransform.copyProperties(bo, CardVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }
    /**
     * 通过卡号查找卡信息
     * @version v1         版本
     * @param account 账号
     * @deprecated 已过期
     */
    @GetMapping("v1/{account}")
    public Result findByAccount(@PathVariable String account) throws ActException {
        try {
            CardBO bo = cardAPI.findByAccount(account);
            CardVO vo = BeanTransform.copyProperties(bo, CardVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }




}
