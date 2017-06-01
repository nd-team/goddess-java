package com.bjike.goddess.equipmentprepared.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.equipmentprepared.bo.PayCountBO;
import com.bjike.goddess.equipmentprepared.bo.WaitPayBO;
import com.bjike.goddess.equipmentprepared.dto.WaitPayDTO;
import com.bjike.goddess.equipmentprepared.entity.WaitPay;
import com.bjike.goddess.equipmentprepared.to.WaitPayTO;

import java.util.List;

/**
 * 等待付款业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-13 02:27 ]
 * @Description: [ 等待付款业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WaitPaySer extends Ser<WaitPay, WaitPayDTO> {

    /**
     * 等待付款列表
     *
     * @param dto 等待付款dto
     * @return class WaitPayBO
     * @throws SerException
     */
    default List<WaitPayBO> list(WaitPayDTO dto) throws SerException {
        return null;
    }

    /**
     * 已付款列表
     *
     * @param dto 等待付款dto
     * @return class WaitPayBO
     * @throws SerException
     */
    default List<WaitPayBO> pays(WaitPayDTO dto) throws SerException {
        return null;
    }

    /**
     * 部门汇总
     *
     * @return class PayCountBO
     * @throws SerException
     */
    default List<PayCountBO> departmentCount() throws SerException {
        return null;
    }

    /**
     * 地区汇总
     *
     * @return class PayCountBO
     * @throws SerException
     */
    default List<PayCountBO> areaCount() throws SerException {
        return null;
    }

    /**
     * 设备名称汇总
     *
     * @return class PayCountBO
     * @throws SerException
     */
    default List<PayCountBO> deviceNameCount() throws SerException {
        return null;
    }

    /**
     * 确认付款
     *
     * @param to 等待付款信息
     * @throws SerException
     */
    default void confirmPay(WaitPayTO to) throws SerException {
    }

    /**
     * 导出数据
     *
     * @param year  年份
     * @param month 月份
     * @return class WaitPayBO
     * @throws SerException
     */
    default List<WaitPayBO> export(Integer year, Integer month) throws SerException {
        return null;
    }

    /**
     * 通过id查找
     *
     * @param id 等待付款id
     * @return class WaitPayBO
     * @throws SerException
     */
    default WaitPayBO findByID(String id) throws SerException {
        return null;
    }

    /**
     * 查找等待付款的总记录数
     *
     * @param dto dto
     * @throws SerException
     */
    default Long waitCountSum(WaitPayDTO dto) throws SerException {
        return null;
    }

    /**
     * 查找已付款总记录数
     *
     * @param dto dto
     * @throws SerException
     */
    default Long payCountSum(WaitPayDTO dto) throws SerException {
        return null;
    }
}