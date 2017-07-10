package com.bjike.goddess.contacts.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contacts.bo.CommerceContactsBO;
import com.bjike.goddess.contacts.dto.CommerceContactsDTO;
import com.bjike.goddess.contacts.excel.SonPermissionObject;
import com.bjike.goddess.contacts.service.CommerceContactsSer;
import com.bjike.goddess.contacts.to.CommerceContactsTO;
import com.bjike.goddess.contacts.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商务通讯录业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 06:04 ]
 * @Description: [ 商务通讯录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("commerceContactsApiImpl")
public class CommerceContactsApiImpl implements CommerceContactsAPI {

    @Autowired
    private CommerceContactsSer commerceContactsSer;

    @Override
    public CommerceContactsBO save(CommerceContactsTO to) throws SerException {
        return commerceContactsSer.save(to);
    }

    @Override
    public CommerceContactsBO update(CommerceContactsTO to) throws SerException {
        return commerceContactsSer.update(to);
    }

    @Override
    public void delete(CommerceContactsTO to) throws SerException {
        commerceContactsSer.delete(to);
    }

    @Override
    public List<CommerceContactsBO> maps(CommerceContactsDTO dto) throws SerException {
        return commerceContactsSer.maps(dto);
    }

    @Override
    public CommerceContactsBO getById(String id) throws SerException {
        return commerceContactsSer.getById(id);
    }

    @Override
    public Long getTotal() throws SerException {
        return commerceContactsSer.getTotal();
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return commerceContactsSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return commerceContactsSer.guidePermission(guidePermissionTO);
    }
}