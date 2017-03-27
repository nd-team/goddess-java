package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.marketdevelopment.bo.MarketResearchBO;
import com.bjike.goddess.marketdevelopment.bo.MarketResearchBO;
import com.bjike.goddess.marketdevelopment.dto.MarketResearchDTO;
import com.bjike.goddess.marketdevelopment.entity.MarketResearch;
import com.bjike.goddess.marketdevelopment.to.MarketResearchTO;
import com.bjike.goddess.marketdevelopment.to.MarketResearchTO;

import java.util.List;

/**
 * 市场调研业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:16 ]
 * @Description: [ 市场调研业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MarketResearchSer extends Ser<MarketResearch, MarketResearchDTO> {

    /**
     * 保存市场调研数据
     *
     * @param to 市场调研传输对象
     * @return
     * @throws SerException
     */
    default MarketResearchBO save(MarketResearchTO to) throws SerException {
        return null;
    }

    /**
     * 修改市场调研数据
     *
     * @param to 市场调研传输对象
     * @return
     * @throws SerException
     */
    default MarketResearchBO update(MarketResearchTO to) throws SerException {
        return null;
    }

    /**
     * 删除市场调研数据
     *
     * @param to 市场调研传输对象
     * @return
     * @throws SerException
     */
    default MarketResearchBO delete(MarketResearchTO to) throws SerException {
        return null;
    }

    /**
     * 根据业务类型查询市场调研数据
     *
     * @param type 业务类型
     * @return
     * @throws SerException
     */
    default List<MarketResearchBO> findByType(String type) throws SerException {
        return null;
    }

    /**
     * 根据业务方向科目查询市场调研数据
     *
     * @param course 业务方向科目
     * @return
     * @throws SerException
     */
    default List<MarketResearchBO> findByCourse(String course) throws SerException {
        return null;
    }

    /**
     * 根据业务类型和方向科目查询市场调研数据
     *
     * @param type   业务类型
     * @param course 业务方向科目
     * @return
     * @throws SerException
     */
    default List<MarketResearchBO> findByCourseType(String type, String course) throws SerException {
        return null;
    }


}