package com.bjike.goddess.budget.api;

import com.bjike.goddess.budget.bo.ArrivalMonthBO;
import com.bjike.goddess.budget.bo.ArrivalMonthCountBO;
import com.bjike.goddess.budget.bo.ArrivalWeekBO;
import com.bjike.goddess.budget.dto.ArrivalMonthDTO;
import com.bjike.goddess.budget.to.ArrivalMonthTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 地区收入月业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-02 04:06 ]
 * @Description: [ 地区收入月业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ArrivalMonthAPI {
    /**
     * 添加
     *
     * @param to 地区收入月信息
     * @return class ArrivalMonthBO
     * @throws SerException
     */
    default ArrivalMonthBO save(ArrivalMonthTO to) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to 地区收入月信息
     * @throws SerException
     */
    default void edit(ArrivalMonthTO to) throws SerException {
    }

    /**
     * 删除全部
     *
     * @throws SerException
     */
    default void deleteAll() throws SerException {

    }

    /**
     * 查找
     *
     * @param dto 地区收入月分页信息
     * @return class ArrivalMonthBO
     * @throws SerException
     */
    default List<ArrivalMonthBO> list(ArrivalMonthDTO dto) throws SerException {
        return null;
    }

    /**
     * 通过id查找
     *
     * @param id 地区收入月id
     * @return class ArrivalMonthBO
     * @throws SerException
     */
    default ArrivalMonthBO findByID(String id) throws SerException {
        return null;
    }

    /**
     * 汇总
     *
     * @return class ArrivalMonthCountBO
     * @throws SerException
     */
    default List<ArrivalMonthCountBO> count() throws SerException {
        return null;
    }

    /**
     * 分地区汇总
     *
     * @param arrivals 地区数组
     * @return class ArrivalMonthCountBO
     * @throws SerException
     */
    default List<ArrivalMonthCountBO> conditionsCount(String[] arrivals) throws SerException {
        return null;
    }

    /**
     * 查找该月明细
     *
     * @param id 地区收入月id
     * @return class ArrivalWeekBO>
     * @throws SerException
     */
    default List<ArrivalWeekBO> findDetail(String id) throws SerException {
        return null;
    }

    /**
     * 查询总记录数
     *
     * @param dto dto
     * @return class Long
     * @throws SerException
     */
    default Long countNum(ArrivalMonthDTO dto) throws SerException {
        return null;
    }
}