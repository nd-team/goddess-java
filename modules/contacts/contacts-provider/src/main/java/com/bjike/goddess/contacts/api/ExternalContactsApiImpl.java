package com.bjike.goddess.contacts.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contacts.bo.ExternalContactsBO;
import com.bjike.goddess.contacts.dto.ExternalContactsDTO;
import com.bjike.goddess.contacts.service.ExternalContactsSer;
import com.bjike.goddess.contacts.to.ExternalContactsTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 外部通讯录业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:26 ]
 * @Description: [ 外部通讯录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("externalContactsApiImpl")
public class ExternalContactsApiImpl implements ExternalContactsAPI {

    @Autowired
    private ExternalContactsSer externalContactsSer;

    @Override
    public ExternalContactsBO save(ExternalContactsTO to) throws SerException {
        return externalContactsSer.save(to);
    }

    @Override
    public ExternalContactsBO update(ExternalContactsTO to) throws SerException {
        return externalContactsSer.update(to);
    }

    @Override
    public ExternalContactsBO delete(ExternalContactsTO to) throws SerException {
        return externalContactsSer.delete(to);
    }

    @Override
    public List<ExternalContactsBO> findByArea(String area) throws SerException {
        return externalContactsSer.findByArea(area);
    }

    @Override
    public List<ExternalContactsBO> maps(ExternalContactsDTO dto) throws SerException {
        return externalContactsSer.maps(dto);
    }
}