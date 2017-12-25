package com.bjike.goddess.balancecard.api;

import com.bjike.goddess.balancecard.bo.BalancecardPermissionBO;
import com.bjike.goddess.balancecard.dto.BalancecardPermissionDTO;
import com.bjike.goddess.balancecard.service.BalancecardPermissionSer;
import com.bjike.goddess.balancecard.to.BalancecardPermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.OpinionBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 平衡计分卡权限配置业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-12 05:43 ]
 * @Description: [ 平衡计分卡权限配置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("balancecardPermissionApiImpl")
public class BalancecardPermissionApiImpl implements BalancecardPermissionAPI {

    @Autowired
    private BalancecardPermissionSer cusPermissionSer;

    @Override
    public Long countPermission(BalancecardPermissionDTO cusPermissionDTO) throws SerException {
        return cusPermissionSer.countPermission(cusPermissionDTO);
    }

    @Override
    public List<BalancecardPermissionBO> list(BalancecardPermissionDTO cusPermissionDTO) throws SerException {
        return cusPermissionSer.list(cusPermissionDTO);
    }

    @Override
    public BalancecardPermissionBO getOneById(String id) throws SerException {
        return cusPermissionSer.getOneById(id);
    }

    @Override
    public List<OpinionBO>  listOperateById(String id) throws SerException {
        return cusPermissionSer.listOperateById(id);
    }

    @Override
    public BalancecardPermissionBO add(List<BalancecardPermissionTO> cusPermissionTO) throws SerException {
        return cusPermissionSer.add(cusPermissionTO);
    }

    @Override
    public BalancecardPermissionBO edit(BalancecardPermissionTO cusPermissionTO) throws SerException {
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
}