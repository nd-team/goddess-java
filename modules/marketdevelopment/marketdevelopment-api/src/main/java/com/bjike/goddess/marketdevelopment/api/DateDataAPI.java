package com.bjike.goddess.marketdevelopment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.marketdevelopment.bo.DateDataBO;
import com.bjike.goddess.marketdevelopment.bo.MonthMoneyBO;
import com.bjike.goddess.marketdevelopment.dto.DateDataDTO;
import com.bjike.goddess.marketdevelopment.to.DateDataTO;
import com.bjike.goddess.marketdevelopment.to.DateDataUpdateTO;

import java.util.List;

/**
 * 日期数据业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 04:08 ]
 * @Description: [ 日期数据业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DateDataAPI {

    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<MonthMoneyBO> maps(DateDataDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param to
     * @throws SerException
     */
    default void save(DateDataTO to) throws SerException {
        return;
    }

    /**
     * 修改外出单数据
     *
     * @param to
     * @throws SerException
     */
    default void update(DateDataUpdateTO to) throws SerException {
        return;
    }

    /**
     * 删除外出单数据
     *
     * @throws SerException
     */
    default void delete(String dateDataId) throws SerException {
        return;
    }

    /**
     * 获取总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default Long getTotal(DateDataDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取日汇总数据
     *
     * @param dateDataId
     * @return
     * @throws SerException
     */
    default DateDataBO getById(String dateDataId) throws SerException {
        return null;
    }
}