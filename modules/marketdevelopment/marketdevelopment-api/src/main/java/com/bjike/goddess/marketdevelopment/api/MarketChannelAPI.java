package com.bjike.goddess.marketdevelopment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.marketdevelopment.bo.MarketChannelBO;
import com.bjike.goddess.marketdevelopment.dto.MarketChannelDTO;
import com.bjike.goddess.marketdevelopment.to.MarketChannelTO;

import java.util.List;

/**
 * 市场挖掘业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:15 ]
 * @Description: [ 市场挖掘业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MarketChannelAPI {

    /**
     * 保存市场挖掘数据
     *
     * @param to 市场挖掘传输对象
     * @return
     * @throws SerException
     */
    default MarketChannelBO save(MarketChannelTO to) throws SerException {
        return null;
    }

    /**
     * 修改市场挖掘数据
     *
     * @param to 市场挖掘传输对象
     * @return
     * @throws SerException
     */
    default MarketChannelBO update(MarketChannelTO to) throws SerException {
        return null;
    }

    /**
     * 删除市场挖掘数据
     *
     * @param to 市场挖掘传输对象
     * @return
     * @throws SerException
     */
    default MarketChannelBO delete(MarketChannelTO to) throws SerException {
        return null;
    }

    /**
     * 根据业务类型查询市场挖掘数据
     *
     * @param type 业务类型
     * @return
     * @throws SerException
     */
    default List<MarketChannelBO> findByType(String type) throws SerException {
        return null;
    }

    /**
     * 根据业务方向科目查询市场挖掘数据
     *
     * @param course 业务方向科目
     * @return
     * @throws SerException
     */
    default List<MarketChannelBO> findByCourse(String course) throws SerException {
        return null;
    }

    /**
     * 根据业务类型和方向科目查询市场挖掘数据
     *
     * @param type   业务类型
     * @param course 业务方向科目
     * @return
     * @throws SerException
     */
    default List<MarketChannelBO> findByCourseType(String type, String course) throws SerException {
        return null;
    }

    /**
     * 根据id获取市场挖掘数据
     *
     * @param id 市场挖掘数据id
     * @return
     * @throws SerException
     */
    default MarketChannelBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 工作交接数据传输对象
     * @return
     * @throws SerException
     */
    default List<MarketChannelBO> maps(MarketChannelDTO dto) throws SerException {
        return null;
    }
}