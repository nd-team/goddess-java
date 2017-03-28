package com.bjike.goddess.market.action.market;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.market.api.MarketEmailAPI;
import com.bjike.goddess.market.vo.MarketEmailVO;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 发送市场信息管理
 * @Author: [xiazhili]
 * @Date: [17-3-27]
 * @Description: [发送市场信息管理]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
@RestController
@RequestMapping("market/marketemail")
public class MarketEmailAction {
    @Autowired
    private MarketEmailAPI marketEmailAPI;
    /**
     * 汇总市场信息管理
     *
     * @param areas 地区
     * @des 项目市场信息管理
     * @return  class MarketEmailVO
     * @version v1
     */
    @GetMapping("v1/Collect")
    public Result Collect (@NotBlank String[] areas ) throws ActException {
        try {
            List<MarketEmailVO> marketEmailVOS = BeanTransform.copyProperties(
                    marketEmailAPI.collectMarketEmail(areas),MarketEmailVO.class,true);
            return ActResult.initialize(marketEmailVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
