package com.bjike.goddess.contacts.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.contacts.bo.ExternalContactsBO;
import com.bjike.goddess.contacts.dto.ExternalContactsDTO;
import com.bjike.goddess.contacts.entity.ExternalContacts;
import com.bjike.goddess.contacts.to.ExternalContactsTO;

import java.util.List;

/**
 * 外部通讯录业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:26 ]
 * @Description: [ 外部通讯录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ExternalContactsSer extends Ser<ExternalContacts, ExternalContactsDTO> {

    /**
     * 保存
     * @param to 外部通讯录传输对象
     * @return
     * @throws SerException
     */
    default ExternalContactsBO save(ExternalContactsTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     * @param to 外部通讯录传输对象
     * @return
     * @throws SerException
     */
    default ExternalContactsBO update(ExternalContactsTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     * @param to 外部通讯录传输对象
     * @return
     * @throws SerException
     */
    default ExternalContactsBO delete(ExternalContactsTO to) throws SerException {
        return null;
    }

    /**
     * 根据地区查询
     * @param area 地区
     * @return
     * @throws SerException
     */
    default List<ExternalContactsBO> findByArea(String area) throws SerException {
        return null;
    }

    /**
     * 列表查询
     * @param dto 外部通讯录数据传输对象
     * @return
     * @throws SerException
     */
    default List<ExternalContactsBO> maps(ExternalContactsDTO dto) throws SerException {
        return null;
    }

}