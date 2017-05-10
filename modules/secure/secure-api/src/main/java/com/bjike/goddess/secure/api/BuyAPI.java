package com.bjike.goddess.secure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.secure.bo.BuyBO;
import com.bjike.goddess.secure.dto.BuyDTO;
import com.bjike.goddess.secure.to.BuyTO;

import java.util.List;

/**
 * 购买社保人员业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 02:45 ]
 * @Description: [ 购买社保人员业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BuyAPI {
    /**
     * 查找
     *
     * @param dto 购买社保人员分页信息
     * @return class BuyBO
     * @throws SerException
     */
    default List<BuyBO> find(BuyDTO dto) throws SerException {
        return null;
    }

    /**
     * 编辑和审批
     *
     * @param to 购买社保人员信息
     * @return class BuyBO
     * @throws SerException
     */
    default BuyBO edit(BuyTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 购买社保人员名单的id
     * @return class BuyBO
     * @throws SerException
     */
    default BuyBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 通过id查找
     *
     * @param id 购买社保人员的id
     * @return class BuyBO
     * @throws SerException
     */
    default BuyBO findByID(String id) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param to 购买社保人员的信息
     * @return class BuyBO
     * @throws SerException
     */
    default BuyBO save(BuyTO to) throws SerException {
        return null;
    }
}