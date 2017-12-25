package com.bjike.goddess.allmeeting.service;

import com.bjike.goddess.allmeeting.bo.ConcisePermissionBO;
import com.bjike.goddess.allmeeting.dto.ConcisePermissionDTO;
import com.bjike.goddess.allmeeting.entity.ConcisePermission;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

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
public interface ConcisePermissionSer extends Ser<ConcisePermission, ConcisePermissionDTO> {

    //查询调阅权限
    Boolean getPermission(String userNum) throws SerException;

    ConcisePermissionBO insertModel() throws SerException;

    List<ConcisePermissionBO> pageList(ConcisePermissionDTO dto) throws SerException;

    void agree(String id) throws SerException;
}