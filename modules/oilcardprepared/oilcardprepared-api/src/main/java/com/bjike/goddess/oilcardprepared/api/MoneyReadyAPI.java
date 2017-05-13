package com.bjike.goddess.oilcardprepared.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.oilcardprepared.bo.MoneyReadyBO;
import com.bjike.goddess.oilcardprepared.bo.MoneyReadyCountBO;
import com.bjike.goddess.oilcardprepared.dto.MoneyReadyDTO;
import com.bjike.goddess.oilcardprepared.to.MoneyReadyTO;

import java.util.List;

/**
 * 资金准备审核业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-05 02:41 ]
 * @Description: [ 资金准备审核业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MoneyReadyAPI {
    /**
     * 添加
     *
     * @param to 资金准备审核信息
     * @return class MoneyReadyBO
     * @throws SerException
     */
    default MoneyReadyBO save(MoneyReadyTO to) throws SerException {
        return null;

    }

    /**
     * 编辑
     *
     * @param to 资金准备审核信息
     * @throws SerException
     */
    default void edit(MoneyReadyTO to) throws SerException {
    }

    /**
     * 删除
     *
     * @param id 资金准备审核信息id
     * @throws SerException
     */
    default void delete(String id) throws SerException {

    }

    /**
     * 查找
     *
     * @param dto 资金准备审核分页信息
     * @return class MoneyReadyBO
     * @throws SerException
     */
    default List<MoneyReadyBO> list(MoneyReadyDTO dto) throws SerException {
        return null;

    }

    /**
     * 通过id查找
     *
     * @param id 资金准备审核信息id
     * @return class MoneyReadyBO
     * @throws SerException
     */
    default MoneyReadyBO findByID(String id) throws SerException {
        return null;
    }

    /**
     * 汇总
     *
     * @param month 汇总的月份
     * @return class MoneyReadyCountBO
     * @throws SerException
     */
    default List<MoneyReadyCountBO> count(Integer month) throws SerException {
        return null;
    }
}