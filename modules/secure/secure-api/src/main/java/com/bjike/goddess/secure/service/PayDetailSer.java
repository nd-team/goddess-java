package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.secure.bo.PayDetailBO;
import com.bjike.goddess.secure.entity.PayDetail;
import com.bjike.goddess.secure.dto.PayDetailDTO;
import com.bjike.goddess.secure.to.PayDetailTO;

import java.util.List;

/**
 * 缴费比例明细业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-25 12:55 ]
 * @Description: [ 缴费比例明细业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PayDetailSer extends Ser<PayDetail, PayDetailDTO> {
    /**
     * 添加
     *
     * @param to 缴费比例信息
     * @return class PayDetailBO
     * @throws SerException
     */
    default PayDetailBO save(PayDetailTO to) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to 缴费比例信息
     * @throws SerException
     */
    default void edit(PayDetailTO to) throws SerException {
    }

    /**
     * 查找
     *
     * @param dto 缴费比例分页信息
     * @return class PayDetailBO
     * @throws SerException
     */
    default List<PayDetailBO> list(PayDetailDTO dto) throws SerException {
        return null;
    }

    /**
     * 通过id查找
     *
     * @param id 缴费比例id
     * @return class PayDetailBO
     * @throws SerException
     */
    default PayDetailBO findByID(String id) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 缴费比例id
     * @throws SerException
     */
    default void delete(String id) throws SerException {

    }
}