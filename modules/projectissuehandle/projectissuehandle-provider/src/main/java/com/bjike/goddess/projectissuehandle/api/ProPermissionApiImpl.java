package com.bjike.goddess.projectissuehandle.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.projectissuehandle.bo.ProPermissionBO;
import com.bjike.goddess.projectissuehandle.dto.ProPermissionDTO;
import com.bjike.goddess.projectissuehandle.service.ProPermissionSer;
import com.bjike.goddess.projectissuehandle.to.ProPermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 问题权限配置业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-26 05:43 ]
 * @Description: [ 问题权限配置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("proPermissionApiImpl")
public class ProPermissionApiImpl implements ProPermissionAPI {

    @Autowired
    private ProPermissionSer proPermissionSer;

    @Override
    public Long countPermission(ProPermissionDTO proPermissionDTO) throws SerException {
        return proPermissionSer.countPermission(proPermissionDTO);
    }

    @Override
    public List<ProPermissionBO> list(ProPermissionDTO proPermissionDTO) throws SerException {
        return proPermissionSer.list(proPermissionDTO);
    }

    @Override
    public ProPermissionBO getOneById(String id) throws SerException {
        return proPermissionSer.getOneById(id);
    }

    @Override
    public List<OpinionBO> listOperateById(String id) throws SerException {
        return proPermissionSer.listOperateById(id);
    }

    @Override
    public ProPermissionBO add(List<ProPermissionTO> proPermissionTO) throws SerException {
        return proPermissionSer.add(proPermissionTO);
    }

    @Override
    public ProPermissionBO edit(ProPermissionTO proPermissionTO) throws SerException {
        return proPermissionSer.edit(proPermissionTO);
    }

    @Override
    public Boolean getProPermission(String idFlag) throws SerException {
        return proPermissionSer.getProPermission(idFlag);
    }

    @Override
    public Boolean busProPermission(String idFlag) throws SerException {
        return proPermissionSer.busProPermission(idFlag);
    }
}