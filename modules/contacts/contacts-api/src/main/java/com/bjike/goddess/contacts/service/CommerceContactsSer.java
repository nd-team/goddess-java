package com.bjike.goddess.contacts.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.contacts.bo.CommerceContactsBO;
import com.bjike.goddess.contacts.dto.CommerceContactsDTO;
import com.bjike.goddess.contacts.entity.CommerceContacts;
import com.bjike.goddess.contacts.to.CommerceContactsTO;

/**
 * 商务通讯录业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 06:04 ]
 * @Description: [ 商务通讯录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CommerceContactsSer extends Ser<CommerceContacts, CommerceContactsDTO> {

    /**
     * 保存
     *
     * @param to 商务通讯录传输对象
     * @return
     * @throws SerException
     */
    default CommerceContactsBO save(CommerceContactsTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 商务通讯录传输对象
     * @return
     * @throws SerException
     */
    default CommerceContactsBO update(CommerceContactsTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param to 商务通讯录传输对象
     * @throws SerException
     */
    void delete(CommerceContactsTO to) throws SerException;

}