package com.bjike.goddess.supplier.api;

import com.bjike.goddess.supplier.bo.SupCusPermissionBO;
import com.bjike.goddess.supplier.dto.SupCusPermissionDTO;
import com.bjike.goddess.supplier.service.SupCusPermissionSer;
import com.bjike.goddess.supplier.to.SupCusPermissionTO;
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
@Service("supCusPermissionApiImpl")
public class SupCusPermissionApiImpl implements SupCusPermissionAPI {

    @Autowired
    private SupCusPermissionSer cusPermissionSer;

    @Override
    public Long countPermission(SupCusPermissionDTO cusPermissionDTO) throws SerException {
        return cusPermissionSer.countPermission(cusPermissionDTO);
    }

    @Override
    public List<SupCusPermissionBO> list(SupCusPermissionDTO cusPermissionDTO) throws SerException {
        return cusPermissionSer.list(cusPermissionDTO);
    }

    @Override
    public SupCusPermissionBO getOneById(String id) throws SerException {
        return cusPermissionSer.getOneById(id);
    }

    @Override
    public List<OpinionBO>  listOperateById(String id) throws SerException {
        return cusPermissionSer.listOperateById(id);
    }

    @Override
    public SupCusPermissionBO add(List<SupCusPermissionTO> cusPermissionTO) throws SerException {
        return cusPermissionSer.add(cusPermissionTO);
    }

    @Override
    public SupCusPermissionBO edit(SupCusPermissionTO cusPermissionTO) throws SerException {
        return cusPermissionSer.edit(cusPermissionTO);
    }

    @Override
    public Boolean getSupCusPermission(String idFlag) throws SerException {
        return cusPermissionSer.getSupCusPermission(idFlag);
    }

    @Override
    public Boolean busSupCusPermission(String idFlag) throws SerException {
        return cusPermissionSer.busSupCusPermission(idFlag);
    }
}