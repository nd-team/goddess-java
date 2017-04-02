package com.bjike.goddess.contacts.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contacts.bo.InternalContactsBO;
import com.bjike.goddess.contacts.dto.InternalContactsDTO;
import com.bjike.goddess.contacts.service.InternalContactsSer;
import com.bjike.goddess.contacts.to.InternalContactsTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 内部通讯录业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:08 ]
 * @Description: [ 内部通讯录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("internalContactsApiImpl")
public class InternalContactsApiImpl implements InternalContactsAPI {

    @Autowired
    private InternalContactsSer internalContactsSer;

    @Override
    public InternalContactsBO save(InternalContactsTO to) throws SerException {
        return internalContactsSer.save(to);
    }

    @Override
    public InternalContactsBO update(InternalContactsTO to) throws SerException {
        return internalContactsSer.update(to);
    }

    @Override
    public InternalContactsBO delete(InternalContactsTO to) throws SerException {
        return internalContactsSer.delete(to);
    }

    @Override
    public List<InternalContactsBO> findEmailNotNull() throws SerException {
        return internalContactsSer.findEmailNotNull();
    }

    @Override
    public InternalContactsBO findByUser(String user_id) throws SerException {
        return internalContactsSer.findByUser(user_id);
    }

    @Override
    public List<InternalContactsBO> maps(InternalContactsDTO dto) throws SerException {
        return internalContactsSer.maps(dto);
    }

    @Override
    public void sendEmail() throws SerException {
        internalContactsSer.sendEmail();
    }
}