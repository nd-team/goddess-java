package com.bjike.goddess.contacts.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contacts.bo.InternalContactsBO;
import com.bjike.goddess.contacts.dto.InternalContactsDTO;
import com.bjike.goddess.contacts.to.InternalContactsTO;

import java.util.List;

/**
 * 内部通讯录业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:08 ]
 * @Description: [ 内部通讯录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface InternalContactsAPI {


    /**
     * 保存
     *
     * @param to 内部通讯录传输对象
     * @return
     * @throws SerException
     */
    default InternalContactsBO save(InternalContactsTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 内部通讯录传输对象
     * @return
     * @throws SerException
     */
    default InternalContactsBO update(InternalContactsTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param to 内部通讯录传输对象
     * @return
     * @throws SerException
     */
    default InternalContactsBO delete(InternalContactsTO to) throws SerException {
        return null;
    }

    /**
     * 查询邮箱不为空数据
     *
     * @return
     * @throws SerException
     */
    default List<InternalContactsBO> findEmailNotNull() throws SerException {
        return null;
    }

    /**
     * 根据用户ID查询通讯录
     *
     * @param user_id 用户ID
     * @return
     * @throws SerException
     */
    default InternalContactsBO findByUser(String user_id) throws SerException {
        return null;
    }

    /**
     * 列表数据
     *
     * @param dto 内部通讯录数据传输对象
     * @return
     * @throws SerException
     */
    default List<InternalContactsBO> maps(InternalContactsDTO dto) throws SerException {
        return null;
    }

    /**
     * 通讯录信息确认邮件
     *
     * @throws SerException
     */
    void sendEmail() throws SerException;
}