package com.bjike.goddess.contacts.api;

import com.bjike.goddess.contacts.bo.ContactPermissionBO;
import com.bjike.goddess.contacts.dto.ContactPermissionDTO;
import com.bjike.goddess.contacts.service.ContactPermissionSer;
import com.bjike.goddess.contacts.to.ContactPermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.OpinionBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户权限配置业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-12 05:43 ]
 * @Description: [ 客户权限配置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("cusPermissionApiImpl")
public class ContactPermissionApiImpl implements ContactPermissionAPI {

    @Autowired
    private ContactPermissionSer cusPermissionSer;

    @Override
    public Long countPermission(ContactPermissionDTO cusPermissionDTO) throws SerException {
        return cusPermissionSer.countPermission(cusPermissionDTO);
    }

    @Override
    public List<ContactPermissionBO> list(ContactPermissionDTO cusPermissionDTO) throws SerException {
        return cusPermissionSer.list(cusPermissionDTO);
    }

    @Override
    public ContactPermissionBO getOneById(String id) throws SerException {
        return cusPermissionSer.getOneById(id);
    }

    @Override
    public List<OpinionBO>  listOperateById(String id) throws SerException {
        return cusPermissionSer.listOperateById(id);
    }

    @Override
    public ContactPermissionBO add(List<ContactPermissionTO> cusPermissionTO) throws SerException {
        return cusPermissionSer.add(cusPermissionTO);
    }

    @Override
    public ContactPermissionBO edit(ContactPermissionTO cusPermissionTO) throws SerException {
        return cusPermissionSer.edit(cusPermissionTO);
    }

    @Override
    public Boolean getCusPermission(String idFlag) throws SerException {
        return cusPermissionSer.getCusPermission(idFlag);
    }

    @Override
    public Boolean busCusPermission(String idFlag) throws SerException {
        return cusPermissionSer.busCusPermission(idFlag);
    }
}