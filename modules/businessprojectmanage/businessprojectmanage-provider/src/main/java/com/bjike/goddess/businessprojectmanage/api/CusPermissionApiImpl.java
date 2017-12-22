package com.bjike.goddess.businessprojectmanage.api;

import com.bjike.goddess.businessprojectmanage.bo.CusPermissionBO;
import com.bjike.goddess.businessprojectmanage.dto.CusPermissionDTO;
import com.bjike.goddess.businessprojectmanage.excel.SonPermissionObject;
import com.bjike.goddess.businessprojectmanage.service.CusPermissionSer;
import com.bjike.goddess.businessprojectmanage.to.CusPermissionTO;
import com.bjike.goddess.businessprojectmanage.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户权限设置业务接口实现
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2017-12-12 05:56 ]
 * @Description: [ 客户权限设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("cusPermissionApiImpl")
public class CusPermissionApiImpl implements CusPermissionAPI {

    @Autowired
    CusPermissionSer cusPermissionSer;

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return cusPermissionSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return cusPermissionSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countPermission(CusPermissionDTO cusPermissionDTO) throws SerException {
        return cusPermissionSer.countPermission(cusPermissionDTO);
    }

    @Override
    public List<CusPermissionBO> list(CusPermissionDTO cusPermissionDTO) throws SerException {
        return cusPermissionSer.list(cusPermissionDTO);
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
    public CusPermissionBO add(List<CusPermissionTO> cusPermissionTO) throws SerException {
        return cusPermissionSer.add(cusPermissionTO);
    }

    @Override
    public CusPermissionBO edit(CusPermissionTO cusPermissionTO) throws SerException {
        return cusPermissionSer.edit(cusPermissionTO);
    }

    @Override
    public Boolean getCusPermission(String idFlag, UserBO user) throws SerException {
        return cusPermissionSer.getCusPermission(idFlag,user);
    }

    @Override
    public Boolean busCusPermission(String idFlag, UserBO user) throws SerException {
        return cusPermissionSer.busCusPermission(idFlag,user);
    }
    @Override
    public Boolean modCusPermission(String idFlag, UserBO user) throws SerException {
        return cusPermissionSer.modCusPermission(idFlag,user);
    }
}