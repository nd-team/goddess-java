package com.bjike.goddess.staffshares.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffshares.bo.SellscheduleBO;
import com.bjike.goddess.staffshares.bo.SellscheduleCollectBO;
import com.bjike.goddess.staffshares.dto.SellscheduleDTO;
import com.bjike.goddess.staffshares.entity.Sellschedule;
import com.bjike.goddess.staffshares.to.SellscheduleTO;

import java.util.List;

/**
 * 出售记录表业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 10:15 ]
 * @Description: [ 出售记录表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SellscheduleSer extends Ser<Sellschedule, SellscheduleDTO> {

    /**
     * 出售
     *
     * @param to
     * @throws SerException
     */
    default void sell(SellscheduleTO to) throws SerException {
    }

    /**
     * 出售记录表列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<SellscheduleBO> maps(SellscheduleDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取出售记录表数据
     *
     * @param id
     * @return
     * @throws SerException
     */
    default SellscheduleBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @param sellscheduleDTO
     * @return
     * @throws SerException
     */
    default Long getTotal(SellscheduleDTO sellscheduleDTO) throws SerException {
        return null;
    }

    /**
     * 汇总
     *
     * @return
     * @throws SerException
     */
    default List<SellscheduleCollectBO> collect() throws SerException {
        return null;
    }
}