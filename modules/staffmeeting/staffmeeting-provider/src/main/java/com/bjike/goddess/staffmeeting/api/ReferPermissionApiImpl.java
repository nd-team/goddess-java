package com.bjike.goddess.staffmeeting.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffmeeting.bo.ReferPermissionBO;
import com.bjike.goddess.staffmeeting.dto.ReferPermissionDTO;
import com.bjike.goddess.staffmeeting.service.ReferPermissionSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 调阅权限业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-28 11:33 ]
 * @Description: [ 调阅权限业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("referPermissionApiImpl")
public class ReferPermissionApiImpl implements ReferPermissionAPI {

    @Autowired
    private ReferPermissionSer ReferPermissionSer;

    @Override
    public ReferPermissionBO add() throws SerException {
        return ReferPermissionSer.insertModel();
    }

    @Override
    public List<ReferPermissionBO> pageList(ReferPermissionDTO dto) throws SerException {
        return ReferPermissionSer.pageList(dto);
    }

    @Override
    public void agree(String id) throws SerException {
        ReferPermissionSer.agree(id);
    }
}