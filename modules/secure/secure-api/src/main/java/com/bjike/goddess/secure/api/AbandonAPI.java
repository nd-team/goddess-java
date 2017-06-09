package com.bjike.goddess.secure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.secure.bo.AbandonBO;
import com.bjike.goddess.secure.dto.AbandonDTO;
import com.bjike.goddess.secure.to.AbandonTO;

import java.util.List;

/**
 * 放弃购买名单业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 02:52 ]
 * @Description: [ 放弃购买名单业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AbandonAPI {
    /**
     * 添加
     *
     * @param to 放弃购买信息
     * @return class AbandonBO
     * @throws SerException
     */
    default AbandonBO save(AbandonTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 放弃购买名单的id
     * @return class AbandonBO
     * @throws SerException
     */
    default AbandonBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to 放弃购买信息
     * @return class AbandonBO
     * @throws SerException
     */
    default AbandonBO edit(AbandonTO to) throws SerException {
        return null;
    }

    /**
     * 查找
     *
     * @param dto 放弃购买页码信息
     * @return class AbandonBO
     * @throws SerException
     */
    default List<AbandonBO> find(AbandonDTO dto) throws SerException {
        return null;
    }

    /**
     * 通过id查找
     *
     * @param id 放弃购名单的id
     * @return class AbandonBO
     * @throws SerException
     */
    default AbandonBO findByID(String id) throws SerException {
        return null;
    }

    /**
     * 查找全部
     *
     * @return class AbandonBO
     * @throws SerException
     */
    default List<AbandonBO> findALL() throws SerException {
        return null;
    }

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(AbandonDTO dto) throws SerException;
}