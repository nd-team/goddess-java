package com.bjike.goddess.buyticket.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.buyticket.bo.BuyCusPermissionBO;
import com.bjike.goddess.buyticket.dto.BuyCusPermissionDTO;
import com.bjike.goddess.buyticket.service.BuyCusPermissionSer;
import com.bjike.goddess.buyticket.to.BuyCusPermissionTO;
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
@Service("buyCusPermissionApiImpl")
public class BuyCusPermissionApiImpl implements BuyCusPermissionAPI {

    @Autowired
    private BuyCusPermissionSer cusPermissionSer;

    @Override
    public Long countPermission(BuyCusPermissionDTO cusPermissionDTO) throws SerException {
        return cusPermissionSer.countPermission(cusPermissionDTO);
    }

    @Override
    public List<BuyCusPermissionBO> list(BuyCusPermissionDTO cusPermissionDTO) throws SerException {
        return cusPermissionSer.list(cusPermissionDTO);
    }

    @Override
    public BuyCusPermissionBO getOneById(String id) throws SerException {
        return cusPermissionSer.getOneById(id);
    }

    @Override
    public List<OpinionBO> listOperateById(String id) throws SerException {
        return cusPermissionSer.listOperateById(id);
    }

    @Override
    public BuyCusPermissionBO add(List<BuyCusPermissionTO> cusPermissionTO) throws SerException {
        return cusPermissionSer.add(cusPermissionTO);
    }

    @Override
    public BuyCusPermissionBO edit(BuyCusPermissionTO cusPermissionTO) throws SerException {
        return cusPermissionSer.edit(cusPermissionTO);
    }

    @Override
    public Boolean getCusPermission(String idFlag) throws SerException {
        return cusPermissionSer.getCusPermission(idFlag);
    }

    @Override
    public Boolean busCusPermission(String idFlag) throws SerException {
        return cusPermissionSer.busCusPermission(idFlag);
    }

    @Override
    public Boolean arrCusPermission(String idFlag) throws SerException {
        return cusPermissionSer.arrCusPermission(idFlag);
    }
}