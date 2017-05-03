package com.bjike.goddess.market.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.market.bo.MarketInfoBO;
import com.bjike.goddess.market.entity.MarketInfo;
import com.bjike.goddess.market.dto.MarketInfoDTO;
import com.bjike.goddess.market.to.MarketInfoTO;

import java.util.List;

/**
 * 市场信息管理业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-21T11:00:01.560 ]
 * @Description: [ 市场信息管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MarketInfoSer extends Ser<MarketInfo, MarketInfoDTO> {
    /**
     *市场信息管理列表总条数
     */
    default Long countMarketInfo(MarketInfoDTO marketInfoDTO) throws SerException {
        return null;
    }
    /**
     * 一个市场信息管理
     * @return class MarketInfoBO
     */
    default MarketInfoBO getOne(String id) throws SerException {return null;}

    /**
     * 市场信息
     *
     * @param marketInfoDTO 市场信息dto
     * @return class MarketInfoBO
     * @throws SerException
     */
    default List<MarketInfoBO> findListMarketInfo(MarketInfoDTO marketInfoDTO) throws SerException {
        return null;
    }

    /**
     * 添加市场信息
     *
     * @param marketInfoTO 市场信息数据to
     * @return class MarketInfoBO
     * @throws SerException
     */
    default MarketInfoBO insertMarketInfo(MarketInfoTO marketInfoTO) throws SerException {
        return null;
    }

    /**
     * 编辑市场信息
     *
     * @param marketInfoTO 市场信息数据to
     * @return class MarketInfoBO
     * @throws SerException
     */
    default MarketInfoBO editMarketInfo(MarketInfoTO marketInfoTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除市场信息
     *
     * @param id
     * @throws SerException
     */
    default void removeMarketInfo(String id) throws SerException {

    }

    /**
     * 导出
     *
     * @param customerName
     * @throws SerException
     */
    default String exportExcel(String customerName) throws SerException {
        return null;
    }

    /**
     * 汇总
     *
     * @param area area
     * @return class marketInfoBO
     * @throws SerException
     */
    default MarketInfoBO collectMarketInfo(String[] area) throws SerException {
        return null;
    }

    /**
     * 获取地区
     *
     * @return class String
     */
    default List<String> getMarketInfoArea() throws SerException {
        return null;
    }

}