package com.bjike.goddess.oilcardprepared.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.oilcardprepared.bo.ContrastBO;
import com.bjike.goddess.oilcardprepared.bo.WaitPayBO;
import com.bjike.goddess.oilcardprepared.dto.WaitPayDTO;
import com.bjike.goddess.oilcardprepared.entity.WaitPay;
import com.bjike.goddess.oilcardprepared.to.WaitPayTO;

import java.util.List;

/**
 * 等待付款业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-12 11:05 ]
 * @Description: [ 等待付款业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WaitPaySer extends Ser<WaitPay, WaitPayDTO> {
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
     * 确认是否付款
     *
     * @param to 等待付款信息
     * @throws SerException
     */
    default void confirmPay(WaitPayTO to) throws SerException {
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
     * 汇总
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return class WaitPayBO
     * @throws SerException
     */
    default List<WaitPayBO> count(String startTime, String endTime) throws SerException {
        return null;
    }

    /**
     * 对比
     *
     * @param month 月份
     * @return class ContrastBO
     * @throws SerException
     */
    default List<ContrastBO> contrast(Integer month) throws SerException {
        return null;
    }

    /**
     * 已付款列表
     *
     * @param dto 付款dto
     * @return class WaitPayBO
     * @throws SerException
     */
    default List<WaitPayBO> pays(WaitPayDTO dto) throws SerException {
        return null;
    }

    /**
     * 等待付款总记录数
     *
     * @param dto dto
     * @throws SerException
     */
    Long waitCountSum(WaitPayDTO dto) throws SerException;

    /**
     * 已付款总记录数
     *
     * @param dto dto
     * @throws SerException
     */
    Long payCountSum(WaitPayDTO dto) throws SerException;
}