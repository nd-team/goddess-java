package com.bjike.goddess.supplier.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.supplier.bo.ContactSituationBO;
import com.bjike.goddess.supplier.service.ContactSituationSer;
import com.bjike.goddess.supplier.to.ContactSituationTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 联系情况业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T11:03:21.708 ]
 * @Description: [ 联系情况业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("contactSituationApiImpl")
public class ContactSituationApiImpl implements ContactSituationAPI {

    @Autowired
    private ContactSituationSer contactSituationSer;

    @Override
    public List<ContactSituationBO> findByInformation(String info_id) throws SerException {
        return contactSituationSer.findByInformation(info_id);
    }

    @Override
    public ContactSituationBO save(ContactSituationTO to) throws SerException {
        return contactSituationSer.save(to);
    }

    @Override
    public ContactSituationBO update(ContactSituationTO to) throws SerException {
        return contactSituationSer.update(to);
    }

    @Override
    public ContactSituationBO delete(String id) throws SerException {
        return contactSituationSer.delete(id);
    }

    @Override
    public ContactSituationBO getById(String id) throws SerException {
        return contactSituationSer.getById(id);
    }
}