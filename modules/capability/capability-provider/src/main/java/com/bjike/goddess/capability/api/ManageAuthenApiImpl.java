package com.bjike.goddess.capability.api;

import com.bjike.goddess.capability.bo.ManageAuthenBO;
import com.bjike.goddess.capability.entity.ManageAuthen;
import com.bjike.goddess.capability.service.ManageAuthenSer;
import com.bjike.goddess.capability.to.ManageAuthenTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理资质认证业务接口实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-06-16 06:05 ]
 * @Description: [ 管理资质认证业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("manageAuthenApiImpl")
public class ManageAuthenApiImpl implements ManageAuthenAPI {

    @Autowired
    private ManageAuthenSer manageAuthenSer;

    @Override
    public ManageAuthenBO addManageAuthen(String[] managerAuths, String companyId) throws SerException {
        return manageAuthenSer.addManageAuthen(managerAuths, companyId);
    }

    @Override
    public ManageAuthenBO editManageAuthen(String[] managerAuths, String companyId) throws SerException {
        return manageAuthenSer.editManageAuthen(managerAuths, companyId);
    }

    @Override
    public void deleteManageAuthen(String id) throws SerException {
        manageAuthenSer.deleteManageAuthen(id);
    }
}