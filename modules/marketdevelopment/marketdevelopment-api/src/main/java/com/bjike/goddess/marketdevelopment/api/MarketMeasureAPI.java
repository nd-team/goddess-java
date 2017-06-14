package com.bjike.goddess.marketdevelopment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.marketdevelopment.bo.MarketMeasureBO;
import com.bjike.goddess.marketdevelopment.dto.MarketMeasureDTO;
import com.bjike.goddess.marketdevelopment.to.CollectTO;
import com.bjike.goddess.marketdevelopment.to.MarketMeasureTO;

import java.util.List;

/**
 * 市场测算业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:19 ]
 * @Description: [ 市场测算业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MarketMeasureAPI {

    /**
     * 保存市场测算数据
     *
     * @param to 市场测算传输对象
     * @return
     * @throws SerException
     */
    default MarketMeasureBO save(MarketMeasureTO to) throws SerException {
        return null;
    }

    /**
     * 修改市场测算数据
     *
     * @param to 市场测算传输对象
     * @return
     * @throws SerException
     */
    default MarketMeasureBO update(MarketMeasureTO to) throws SerException {
        return null;
    }

    /**
     * 删除市场测算数据
     *
     * @param to 市场测算传输对象
     * @return
     * @throws SerException
     */
    default MarketMeasureBO delete(MarketMeasureTO to) throws SerException {
        return null;
    }

    /**
     * 根据业务类型查询市场测算数据
     *
     * @param type 业务类型
     * @return
     * @throws SerException
     */
    default List<MarketMeasureBO> findByType(String type) throws SerException {
        return null;
    }

    /**
     * 根据业务方向科目查询市场测算数据
     *
     * @param course 业务方向科目
     * @return
     * @throws SerException
     */
    default List<MarketMeasureBO> findByCourse(String course) throws SerException {
        return null;
    }

    /**
     * 根据业务类型和方向科目查询市场测算数据
     *
     * @param type   业务类型
     * @param course 业务方向科目
     * @return
     * @throws SerException
     */
    default List<MarketMeasureBO> findByCourseType(String type, String course) throws SerException {
        return null;
    }

    /**
     * 根据id获取市场测算数据
     *
     * @param id 市场测算数据id
     * @return
     * @throws SerException
     */
    default MarketMeasureBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 市场测算数据传输对象
     * @return
     * @throws SerException
     */
    default List<MarketMeasureBO> maps(MarketMeasureDTO dto) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Integer getTotal() throws SerException {
        return null;
    }

    /**
     * 导出
     *
     * @param to 导出查询条件传输对象
     * @return
     * @throws SerException
     */
    default byte[] exportExcel(CollectTO to) throws SerException {
        return null;
    }
}