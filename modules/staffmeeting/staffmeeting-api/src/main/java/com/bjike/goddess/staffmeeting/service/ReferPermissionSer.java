package com.bjike.goddess.staffmeeting.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffmeeting.bo.ReferPermissionBO;
import com.bjike.goddess.staffmeeting.dto.ReferPermissionDTO;
import com.bjike.goddess.staffmeeting.entity.ReferPermission;

import java.util.List;

/**
 * 调阅权限业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-28 11:33 ]
 * @Description: [ 调阅权限业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ReferPermissionSer extends Ser<ReferPermission, ReferPermissionDTO> {

    //查询调阅权限
    Boolean getPermission(String userNum) throws SerException;

    ReferPermissionBO insertModel() throws SerException;

    List<ReferPermissionBO> pageList(ReferPermissionDTO dto) throws SerException;

    void agree(String id) throws SerException;
}