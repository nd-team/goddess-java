package com.bjike.goddess.contacts.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.contacts.bo.OtherContactsBO;
import com.bjike.goddess.contacts.dto.OtherContactsDTO;
import com.bjike.goddess.contacts.entity.OtherContacts;
import com.bjike.goddess.contacts.to.OtherContactsTO;

import java.util.List;

/**
 * 其他通讯录业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:44 ]
 * @Description: [ 其他通讯录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface OtherContactsSer extends Ser<OtherContacts, OtherContactsDTO> {

    /**
     * 保存
     * @param to 其他通讯录传输对象
     * @return
     * @throws SerException
     */
    default OtherContactsBO save(OtherContactsTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     * @param to  其他通讯录传输对象
     * @return
     * @throws SerException
     */
    default OtherContactsBO update(OtherContactsTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     * @param to  其他通讯录传输对象
     * @return
     * @throws SerException
     */
    default OtherContactsBO delete(OtherContactsTO to) throws SerException {
        return null;
    }

    /**
     * 列表数据
     * @param dto 其他通讯录数据传输对象
     * @return
     * @throws SerException
     */
    default List<OtherContactsBO> maps(OtherContactsDTO dto) throws SerException {
        return null;
    }
}