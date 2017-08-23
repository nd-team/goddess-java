package com.bjike.goddess.allmeeting.api;

import com.bjike.goddess.allmeeting.bo.ConcisePermissionBO;
import com.bjike.goddess.allmeeting.dto.ConcisePermissionDTO;
import com.bjike.goddess.allmeeting.service.ConcisePermissionSer;
import com.bjike.goddess.common.api.exception.SerException;
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
@Service("concisePermissionApiImpl")
public class ConcisePermissionApiImpl implements ConcisePermissionAPI {

    @Autowired
    private ConcisePermissionSer concisePermissionSer;

    @Override
    public ConcisePermissionBO add() throws SerException {
        return concisePermissionSer.insertModel();
    }

    @Override
    public List<ConcisePermissionBO> pageList(ConcisePermissionDTO dto) throws SerException {
        return concisePermissionSer.pageList(dto);
    }

    @Override
    public void agree(String id) throws SerException {
        concisePermissionSer.agree(id);
    }
}