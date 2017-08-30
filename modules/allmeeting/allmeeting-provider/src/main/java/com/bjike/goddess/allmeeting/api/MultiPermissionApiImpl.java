package com.bjike.goddess.allmeeting.api;

import com.bjike.goddess.allmeeting.bo.MultiPermissionBO;
import com.bjike.goddess.allmeeting.dto.ConcisePermissionDTO;
import com.bjike.goddess.allmeeting.dto.MultiPermissionDTO;
import com.bjike.goddess.allmeeting.service.MultiPermissionSer;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 调阅权限业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-28 03:02 ]
 * @Description: [ 调阅权限业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("multiPermissionApiImpl")
public class MultiPermissionApiImpl implements MultiPermissionAPI {
    @Autowired
    private MultiPermissionSer multiPermissionSer;

    @Override
    public MultiPermissionBO add() throws SerException {
        return multiPermissionSer.insertModel();
    }

    @Override
    public List<MultiPermissionBO> pageList(MultiPermissionDTO dto) throws SerException {
        return multiPermissionSer.pageList(dto);
    }

    @Override
    public void agree(String id) throws SerException {
        multiPermissionSer.agree(id);
    }
}