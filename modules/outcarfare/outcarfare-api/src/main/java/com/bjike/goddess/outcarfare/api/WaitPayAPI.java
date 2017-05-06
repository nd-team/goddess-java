package com.bjike.goddess.outcarfare.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.outcarfare.bo.*;
import com.bjike.goddess.outcarfare.dto.WaitPayDTO;
import com.bjike.goddess.outcarfare.to.WaitPayTO;

import java.util.List;

/**
 * 等待付款业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-05 03:11 ]
 * @Description: [ 等待付款业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WaitPayAPI {
    /**
     * 添加
     *
     * @param to 等待付款信息
     * @return class WaitPayBO
     * @throws SerException
     */
    default WaitPayBO save(WaitPayTO to) throws SerException {
        return null;
    }

    /**
     * 确认付款
     *
     * @param to 等待付款信息
     * @throws SerException
     */
    default void pay(WaitPayTO to) throws SerException {
    }

    /**
     * 删除
     *
     * @param id 等待付款信息id
     * @throws SerException
     */
    default void delete(String id) throws SerException {

    }

    /**
     * 查找
     *
     * @param dto 等待付款分页信息
     * @return class WaitPayBO
     * @throws SerException
     */
    default List<WaitPayBO> list(WaitPayDTO dto) throws SerException {
        return null;

    }

    /**
     * 通过id查找
     *
     * @param id 等待付款信息id
     * @return class WaitPayBO
     * @throws SerException
     */
    default WaitPayBO findByID(String id) throws SerException {
        return null;
    }

    /**
     * 司机汇总
     *
     * @return class DriverCountBO
     * @throws SerException
     */
    default List<DriverCountBO> driverCount() throws SerException {
        return null;
    }

    /**
     * 地区汇总
     *
     * @return class ArrivalCountBO
     * @throws SerException
     */
    default List<ArrivalCountBO> arrivalCount() throws SerException {
        return null;
    }

    /**
     * 用车人汇总
     *
     * @return class CarUserCountBO
     * @throws SerException
     */
    default List<CarUserCountBO> carUserCount() throws SerException {
        return null;
    }

    /**
     * 查找所有已付款的名单
     *
     * @return class PayBO
     * @throws SerException
     */
    default List<PayBO> findAlreadyPays() throws SerException {
        return null;
    }
}