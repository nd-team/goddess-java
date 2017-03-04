package com.bjike.goddess.card.action.test;

import com.bjike.goddess.card.bo.CardBO;
import com.bjike.goddess.card.entity.Card;
import com.bjike.goddess.card.service.CardAPI;
import com.bjike.goddess.card.vo.CardVO;
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

/**
 * 卡业务
 * Created by huanghuanlai on 2017/1/10.
 */
@RestController
@RequestMapping("{version}/demo/card")
public class CardAct {

    @Autowired
    private CardAPI cardAPI;

    /**
     * 注册一张卡
     *
     * @param account  账号
     * @param password 密码
     * @return class Card
     */
    @ApiVersion(1)
    @PostMapping("register")
    public Result register(String account, String password) throws ActException {
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
     * @deprecated 已过期
     */
    @ApiVersion(1)
    @GetMapping("{account}")
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
