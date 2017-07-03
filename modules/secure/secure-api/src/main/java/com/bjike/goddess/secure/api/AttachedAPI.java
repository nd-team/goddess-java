package com.bjike.goddess.secure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.secure.bo.AttachedBO;
import com.bjike.goddess.secure.dto.AttachedDTO;
import com.bjike.goddess.secure.to.AttachedTO;

import java.util.List;

/**
 * 挂靠业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 02:12 ]
 * @Description: [ 挂靠业务接口 ]
 * @version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AttachedAPI {
    /**
     * 添加
     *
     * @param to 挂靠信息
     * @return class AttachedBO
     * @throws SerException
     * @version v1
     */
    default AttachedBO save(AttachedTO to) throws SerException {
        return null;
    }


    /**
     * 删除记录
     *
     * @param id 挂靠信息名单的id
     * @return class AttachedBO
     * @throws SerException
     * @version v1
     */
    default AttachedBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 查找
     *
     * @param dto 挂靠的页码信息
     * @return class AttachedBO
     * @throws SerException
     */
    default List<AttachedBO> find(AttachedDTO dto) throws SerException {
        return null;
    }

    /**
     * 通过id查找
     *
     * @param id 挂靠信息名单的id
     * @return class AttachedBO
     * @throws SerException
     */
    default AttachedBO findByID(String id) throws SerException {
        return null;
    }

    /**
     * 查找全部
     *
     * @return class AttachedBO
     * @throws SerException
     */
    default List<AttachedBO> findALL() throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to 挂靠信息
     * @return class AttachedBO
     * @throws SerException
     */
    AttachedBO edit(AttachedTO to) throws SerException;

    /**
     * 通过
     *
     * @param id id
     * @throws SerException
     */
    void pass(String id) throws SerException;

    /**
     * 不通过
     *
     * @param id
     * @throws SerException
     */
    void notPass(String id) throws SerException;

    /**
     * 补全信息
     *
     * @param to
     * @return
     * @throws SerException
     */
    AttachedBO complete(AttachedTO to) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(AttachedDTO dto) throws SerException;
}