package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.secure.bo.AttachedBO;
import com.bjike.goddess.secure.entity.Attached;
import com.bjike.goddess.secure.dto.AttachedDTO;
import com.bjike.goddess.secure.to.AttachedTO;

import java.util.List;

/**
 * 挂靠业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 02:12 ]
 * @Description: [ 挂靠业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AttachedSer extends Ser<Attached, AttachedDTO> {
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
     * 审核和补全信息
     *
     * @param to 挂靠信息
     * @return class AttachedBO
     * @throws SerException
     * @version v1
     */
    default AttachedBO exameAndComplete(AttachedTO to) throws SerException {
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
     * 更新
     *
     * @param to 挂靠信息
     * @return class AttachedBO
     * @throws SerException
     */
    default AttachedBO update(AttachedTO to) throws SerException {
        return null;
    }
}