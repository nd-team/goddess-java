package com.bjike.goddess.lendreimbursement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.lendreimbursement.bo.LendPermissionBO;
import com.bjike.goddess.lendreimbursement.dto.LendPermissionDTO;
import com.bjike.goddess.lendreimbursement.service.LendPermissionSer;
import com.bjike.goddess.lendreimbursement.to.LendPermissionTO;
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
@Service("lendPermissionApiImpl")
public class LendPermissionApiImpl implements LendPermissionAPI {

    @Autowired
    private LendPermissionSer lendPermissionSer;

    @Override
    public Long countPermission(LendPermissionDTO cusPermissionDTO) throws SerException {
        return lendPermissionSer.countPermission(cusPermissionDTO);
    }

    @Override
    public List<LendPermissionBO> list(LendPermissionDTO cusPermissionDTO) throws SerException {
        return lendPermissionSer.list(cusPermissionDTO);
    }

    @Override
    public LendPermissionBO getOneById(String id) throws SerException {
        return lendPermissionSer.getOneById(id);
    }

    @Override
    public List<OpinionBO>  listOperateById(String id) throws SerException {
        return lendPermissionSer.listOperateById(id);
    }

    @Override
    public LendPermissionBO add(List<LendPermissionTO> cusPermissionTO) throws SerException {
        return lendPermissionSer.add(cusPermissionTO);
    }

    @Override
    public LendPermissionBO edit(LendPermissionTO cusPermissionTO) throws SerException {
        return lendPermissionSer.edit(cusPermissionTO);
    }

    @Override
    public Boolean getCusPermission(String idFlag) throws SerException {
        return lendPermissionSer.getCusPermission(idFlag);
    }

    @Override
    public Boolean busCusPermission(String idFlag) throws SerException {
        return lendPermissionSer.busCusPermission(idFlag);
    }
}