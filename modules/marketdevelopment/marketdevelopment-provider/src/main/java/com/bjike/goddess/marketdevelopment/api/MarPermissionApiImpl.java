package com.bjike.goddess.marketdevelopment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.marketdevelopment.bo.MarPermissionBO;
import com.bjike.goddess.marketdevelopment.dto.MarPermissionDTO;
import com.bjike.goddess.marketdevelopment.service.MarPermissionSer;
import com.bjike.goddess.marketdevelopment.to.MarPermissionTO;
import com.bjike.goddess.organize.bo.OpinionBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 市场计划进度管理权限配置业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-31 11:37 ]
 * @Description: [ 市场计划进度管理权限配置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("marPermissionApiImpl")
public class MarPermissionApiImpl implements MarPermissionAPI {

    @Autowired
    private MarPermissionSer marPermissionSer;

    @Override
    public Long countPermission(MarPermissionDTO cusPermissionDTO) throws SerException {
        return marPermissionSer.countPermission(cusPermissionDTO);
    }

    @Override
    public List<MarPermissionBO> list(MarPermissionDTO cusPermissionDTO) throws SerException {
        return marPermissionSer.list(cusPermissionDTO);
    }

    @Override
    public MarPermissionBO getOneById(String id) throws SerException {
        return marPermissionSer.getOneById(id);
    }

    @Override
    public List<OpinionBO> listOperateById(String id) throws SerException {
        return marPermissionSer.listOperateById(id);
    }

    @Override
    public MarPermissionBO add(List<MarPermissionTO> cusPermissionTO) throws SerException {
        return marPermissionSer.add(cusPermissionTO);
    }

    @Override
    public MarPermissionBO edit(MarPermissionTO cusPermissionTO) throws SerException {
        return marPermissionSer.edit(cusPermissionTO);
    }

    @Override
    public Boolean getMarPermission(String idFlag) throws SerException {
        return marPermissionSer.getMarPermission(idFlag);
    }
}