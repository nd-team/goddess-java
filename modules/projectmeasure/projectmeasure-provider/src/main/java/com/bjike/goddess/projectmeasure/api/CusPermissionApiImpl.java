package com.bjike.goddess.projectmeasure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.projectmeasure.bo.CusPermissionBO;
import com.bjike.goddess.projectmeasure.dto.CusPermissionDTO;
import com.bjike.goddess.projectmeasure.service.CusPermissionSer;
import com.bjike.goddess.projectmeasure.to.CusPermissionTO;
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
public class CusPermissionApiImpl implements CusPermissionAPI {

    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Override
    public Long countPermission(CusPermissionDTO dto) throws SerException {
        return cusPermissionSer.countPermission(dto);
    }

    @Override
    public List<CusPermissionBO> list(CusPermissionDTO dto) throws SerException {
        return cusPermissionSer.list(dto);
    }

    @Override
    public CusPermissionBO getOneById(String id) throws SerException {
        return cusPermissionSer.getOneById(id);
    }

    @Override
    public List<OpinionBO>  listOperateById(String id) throws SerException {
        return cusPermissionSer.listOperateById(id);
    }

    @Override
    public CusPermissionBO add(List<CusPermissionTO> to) throws SerException {
        return cusPermissionSer.add(to);
    }

    @Override
    public CusPermissionBO edit(CusPermissionTO to) throws SerException {
        return cusPermissionSer.edit(to);
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