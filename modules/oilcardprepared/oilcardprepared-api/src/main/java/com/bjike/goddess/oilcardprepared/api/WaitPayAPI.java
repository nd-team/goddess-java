package com.bjike.goddess.oilcardprepared.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.oilcardprepared.bo.ContrastBO;
import com.bjike.goddess.oilcardprepared.bo.WaitPayBO;
import com.bjike.goddess.oilcardprepared.dto.WaitPayDTO;
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
public interface WaitPayAPI {
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
     * 查找所有已付款信息
     *
     * @return class WaitPayBO
     * @throws SerException
     */
    default List<WaitPayBO> allPay() throws SerException {
        return null;
    }
}