package com.bjike.goddess.secure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.secure.bo.AttachedBO;
import com.bjike.goddess.secure.bo.AttachedEndBO;
import com.bjike.goddess.secure.bo.RemoveEmployeeBO;
import com.bjike.goddess.secure.dto.AttachedEndDTO;
import com.bjike.goddess.secure.dto.RemoveEmployeeDTO;
import com.bjike.goddess.secure.to.AttachedEndTO;
import com.bjike.goddess.secure.to.AttachedTO;
import com.bjike.goddess.secure.to.RemoveEmployeeTO;

import java.util.List;

/**
 * 挂靠到期业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 10:04 ]
 * @Description: [ 挂靠到期业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AttachedEndAPI {
    /**
     * 添加
     *
     * @throws SerException
     */
    default void save() throws SerException {
    }

    /**
     * 是否继续挂靠
     *
     * @param to 挂靠到期信息
     * @return class AttachedEndBO
     * @throws SerException
     */
    default AttachedEndBO is_Again(AttachedEndTO to) throws SerException {
        return null;
    }

    /**
     * 查找
     *
     * @param dto 挂靠到期页码信息
     * @return class AttachedEndBO
     * @throws SerException
     */
    default List<AttachedEndBO> find(AttachedEndDTO dto) throws SerException {
        return null;
    }

    /**
     * 通过id查找
     *
     * @param id 挂靠到期名单的id
     * @return class AttachedEndBO
     * @throws SerException
     */
    default AttachedEndBO findByID(String id) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 挂靠到期名单的id
     * @return class AttachedEndBO
     * @throws SerException
     */
    default AttachedEndBO delete(String id) throws SerException {
        return null;
    }
}