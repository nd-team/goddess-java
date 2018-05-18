package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.secure.bo.AttachedEndBO;
import com.bjike.goddess.secure.dto.AttachedEndDTO;
import com.bjike.goddess.secure.entity.AttachedEnd;
import com.bjike.goddess.secure.to.AttachedEndTO;
import com.bjike.goddess.secure.to.GuidePermissionTO;

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
public interface AttachedEndSer extends Ser<AttachedEnd, AttachedEndDTO> {
    /**
     * 下拉导航权限
     */
    Boolean sonPermission() throws SerException;

    /**
     * 导航权限
     */
    Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException;
    /**
     * 添加
     *
     * @param to 挂靠到期信息
     * @return
     * @throws SerException
     */
    AttachedEndBO save(AttachedEndTO to) throws SerException;

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

    /**
     * 定时方法，定时查找挂靠即将到期的人员
     *
     * @throws SerException
     */
    void send() throws SerException;

//    /**
//     * 启动定时方法
//     *
//     * @throws SerException
//     */
//    void quartz() throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(AttachedEndDTO dto) throws SerException;
}