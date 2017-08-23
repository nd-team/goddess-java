package com.bjike.goddess.archive.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.archive.bo.RotainCusPermissionBO;
import com.bjike.goddess.archive.dto.RotainCusPermissionDTO;
import com.bjike.goddess.archive.service.RotainCusPermissionSer;
import com.bjike.goddess.archive.to.RotainCusPermissionTO;
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
@Service("rotainCusPermissionApiImpl")
public class RotainCusPermissionApiImpl implements RotainCusPermissionAPI {

    @Autowired
    private RotainCusPermissionSer rotainCusPermissionSer;

    @Override
    public Long countPermission(RotainCusPermissionDTO cusPermissionDTO) throws SerException {
        return rotainCusPermissionSer.countPermission(cusPermissionDTO);
    }

    @Override
    public List<RotainCusPermissionBO> list(RotainCusPermissionDTO cusPermissionDTO) throws SerException {
        return rotainCusPermissionSer.list(cusPermissionDTO);
    }

    @Override
    public RotainCusPermissionBO getOneById(String id) throws SerException {
        return rotainCusPermissionSer.getOneById(id);
    }

    @Override
    public List<OpinionBO>  listOperateById(String id) throws SerException {
        return rotainCusPermissionSer.listOperateById(id);
    }

    @Override
    public RotainCusPermissionBO add(List<RotainCusPermissionTO> cusPermissionTO) throws SerException {
        return rotainCusPermissionSer.add(cusPermissionTO);
    }

    @Override
    public RotainCusPermissionBO edit(RotainCusPermissionTO cusPermissionTO) throws SerException {
        return rotainCusPermissionSer.edit(cusPermissionTO);
    }

    @Override
    public Boolean getRotainCusPermission(String idFlag) throws SerException {
        return rotainCusPermissionSer.getRotainCusPermission(idFlag);
    }

    @Override
    public Boolean busRotainCusPermission(String idFlag) throws SerException {
        return rotainCusPermissionSer.busRotainCusPermission(idFlag);
    }
}