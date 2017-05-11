package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.secure.bo.SecureCartBO;
import com.bjike.goddess.secure.entity.SecureCart;
import com.bjike.goddess.secure.dto.SecureCartDTO;
import com.bjike.goddess.secure.to.SecureCartTO;

import java.util.List;

/**
 * 社保卡基本信息业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-25 01:44 ]
 * @Description: [ 社保卡基本信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SecureCartSer extends Ser<SecureCart, SecureCartDTO> {
    /**
     * 添加
     *
     * @throws SerException
     */
    default void save() throws SerException {
    }

    /**
     * 编辑
     *
     * @param to 社保卡基本信息
     * @throws SerException
     */
    default void edit(SecureCartTO to) throws SerException {
    }

    /**
     * 查找
     *
     * @param dto 社保卡分页信息
     * @return class SecureCartBO
     * @throws SerException
     */
    default List<SecureCartBO> list(SecureCartDTO dto) throws SerException {
        return null;
    }

    /**
     * 通过id查找
     *
     * @param id 社保卡id
     * @return class SecureCartBO
     * @throws SerException
     */
    default SecureCartBO findByID(String id) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 社保卡id
     * @throws SerException
     */
    default void delete(String id) throws SerException {
    }
}