package com.bjike.goddess.supplier.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.supplier.bo.SupPermissionBO;
import com.bjike.goddess.supplier.dto.SupPermissionDTO;
import com.bjike.goddess.supplier.service.SupPermissionSer;
import com.bjike.goddess.supplier.to.SupPermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 供应商权限配置业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-27 10:43 ]
 * @Description: [ 供应商权限配置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("supPermissionApiImpl")
public class SupPermissionApiImpl implements SupPermissionAPI {

    @Autowired
    private SupPermissionSer supPermissionSer;

    @Override
    public Long countPermission(SupPermissionDTO cusPermissionDTO) throws SerException {
        return supPermissionSer.countPermission(cusPermissionDTO);
    }

    @Override
    public List<SupPermissionBO> list(SupPermissionDTO cusPermissionDTO) throws SerException {
        return supPermissionSer.list(cusPermissionDTO);
    }

    @Override
    public SupPermissionBO getOneById(String id) throws SerException {
        return supPermissionSer.getOneById(id);
    }

    @Override
    public List<OpinionBO> listOperateById(String id) throws SerException {
        return supPermissionSer.listOperateById(id);
    }

    @Override
    public SupPermissionBO add(List<SupPermissionTO> cusPermissionTO) throws SerException {
        return supPermissionSer.add(cusPermissionTO);
    }

    @Override
    public SupPermissionBO edit(SupPermissionTO cusPermissionTO) throws SerException {
        return supPermissionSer.edit(cusPermissionTO);
    }
}