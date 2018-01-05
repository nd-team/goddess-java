package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.organize.bo.UserSetPermissionBO;
import com.bjike.goddess.organize.dto.UserSetPermissionDTO;
import com.bjike.goddess.organize.service.UserSetPermissionSer;
import com.bjike.goddess.organize.to.UserSetPermissionTO;
import com.bjike.goddess.user.bo.UserBO;
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
@Service("userSetPermissionApiImpl")
public class UserSetPermissionApiImpl implements UserSetPermissionAPI {

    @Autowired
    private UserSetPermissionSer userSetPermissionSer;

    @Override
    public UserBO adminPermission() throws SerException {
        return userSetPermissionSer.adminPermission();
    }

    @Override
    public Long countPermission(UserSetPermissionDTO cusPermissionDTO) throws SerException {
        return userSetPermissionSer.countPermission(cusPermissionDTO);
    }

    @Override
    public List<UserSetPermissionBO> list(UserSetPermissionDTO cusPermissionDTO) throws SerException {
        return userSetPermissionSer.list(cusPermissionDTO);
    }

    @Override
    public UserSetPermissionBO getOneById(String id) throws SerException {
        return userSetPermissionSer.getOneById(id);
    }

    @Override
    public List<OpinionBO>  listOperateById(String id) throws SerException {
        return userSetPermissionSer.listOperateById(id);
    }

    @Override
    public UserSetPermissionBO add(List<UserSetPermissionTO> cusPermissionTO) throws SerException {
        return userSetPermissionSer.add(cusPermissionTO);
    }

    @Override
    public UserSetPermissionBO edit(UserSetPermissionTO cusPermissionTO) throws SerException {
        return userSetPermissionSer.edit(cusPermissionTO);
    }

    @Override
    public Boolean getCusPermission(String idFlag) throws SerException {
        return userSetPermissionSer.getCusPermission(idFlag);
    }

    @Override
    public Boolean busCusPermission(String idFlag) throws SerException {
        return userSetPermissionSer.busCusPermission(idFlag);
    }

    @Override
    public Boolean checkSetPermission( ) throws SerException {
        return userSetPermissionSer.checkSetPermission( );
    }
    @Override
    public Boolean checkSetPermission22( ) throws SerException {
        return userSetPermissionSer.checkSetPermission22( );
    }
}