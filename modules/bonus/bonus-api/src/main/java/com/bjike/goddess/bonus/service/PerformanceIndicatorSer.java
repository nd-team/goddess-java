package com.bjike.goddess.bonus.service;

import com.bjike.goddess.bonus.bo.PerformanceIndicatorBO;
import com.bjike.goddess.bonus.dto.PerformanceIndicatorDTO;
import com.bjike.goddess.bonus.entity.PerformanceIndicator;
import com.bjike.goddess.bonus.to.PerformanceIndicatorTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 绩效指标业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-08 05:40 ]
 * @Description: [ 绩效指标业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PerformanceIndicatorSer extends Ser<PerformanceIndicator, PerformanceIndicatorDTO> {

    /**
     * 添加
     *
     * @param to 绩效指标传输对象
     * @return
     * @throws SerException
     */
    default PerformanceIndicatorBO save(PerformanceIndicatorTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 绩效指标传输对象
     * @return
     * @throws SerException
     */
    default PerformanceIndicatorBO update(PerformanceIndicatorTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 绩效指标数据id
     * @return
     * @throws SerException
     */
    default PerformanceIndicatorBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 启动
     *
     * @param id 绩效指标数据id
     * @return
     * @throws SerException
     */
    default PerformanceIndicatorBO start(String id) throws SerException {
        return null;
    }

    /**
     * 关闭
     *
     * @param id 绩效指标数据id
     * @return
     * @throws SerException
     */
    default PerformanceIndicatorBO close(String id) throws SerException {
        return null;
    }

    /**
     * 根据指标状态查询绩效指标数据
     *
     * @param status 绩效指标状态
     * @return
     * @throws SerException
     */
    default List<PerformanceIndicatorBO> findByStatus(Boolean status) throws SerException {
        return null;
    }

}