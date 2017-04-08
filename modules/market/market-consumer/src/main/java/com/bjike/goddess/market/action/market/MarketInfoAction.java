package com.bjike.goddess.market.action.market;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.market.api.MarketInfoAPI;
import com.bjike.goddess.market.bo.MarketInfoBO;
import com.bjike.goddess.market.dto.MarketInfoDTO;
import com.bjike.goddess.market.to.MarketInfoTO;
import com.bjike.goddess.market.vo.MarketInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 市场信息管理
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-21T11:00:01.563 ]
 * @Description: [ 市场信息管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("market/marketinfo")
public class MarketInfoAction {
    @Autowired
    private MarketInfoAPI marketInfoAPI;

    /**
     * 获取市场信息
     *
     * @param marketInfoDTO 市场信息dto
     * @return class MarketInfoVO
     * @des 获取所有市场信息
     * @version v1
     */
    @GetMapping("v1/listMarketInfo")
    public Result findListMarketInfo(MarketInfoDTO marketInfoDTO) throws ActException {
        try {
            List<MarketInfoVO> marketInfoVOS = BeanTransform.copyProperties
                    (marketInfoAPI.findListMarketInfo(marketInfoDTO), MarketInfoVO.class);
            return ActResult.initialize(marketInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加市场信息
     *
     * @param marketInfoTO 市场信息数据to
     * @return class MarketInfoVO
     * @des 添加市场信息
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addMarketInfo(MarketInfoTO marketInfoTO) throws ActException {
        try {
            MarketInfoBO marketInfoBO = marketInfoAPI.insertMarketInfo(marketInfoTO);
            return ActResult.initialize(marketInfoBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑市场信息
     *
     * @param marketInfoTO 市场信息数据to
     * @return class MarketInfoVO
     * @des 编辑市场信息
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result editMarketInfo(MarketInfoTO marketInfoTO) throws ActException {
        try {
            MarketInfoBO marketInfoBO = marketInfoAPI.editMarketInfo(marketInfoTO);
            return ActResult.initialize(marketInfoBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除市场信息
     *
     * @param id 用户id
     * @des 根据用户id删除市场信息记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result removeMarketInfo(@PathVariable String id) throws ActException {
        try {
            marketInfoAPI.removeMarketInfo(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 市场信息导出
     *
     * @param customerName 客户信息名称
     * @version v1
     */
    @PostMapping("v1/exportExcel")
    public Result exportExcel(String customerName) throws ActException {
        String excel = null;
        try {
            excel = marketInfoAPI.exportExcel(customerName);
            return new ActResult(excel);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

}