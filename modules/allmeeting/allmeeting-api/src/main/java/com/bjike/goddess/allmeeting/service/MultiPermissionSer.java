package com.bjike.goddess.allmeeting.service;

import com.bjike.goddess.allmeeting.bo.MultiPermissionBO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.allmeeting.entity.MultiPermission;
import com.bjike.goddess.allmeeting.dto.MultiPermissionDTO;

import java.util.List;

/**
* 调阅权限业务接口
* @Author:			[ Jason ]
* @Date:			[  2017-06-28 03:02 ]
* @Description:	[ 调阅权限业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface MultiPermissionSer extends Ser<MultiPermission, MultiPermissionDTO> {

    Boolean getPermission(String userNum) throws SerException;

    MultiPermissionBO insertModel() throws SerException;

    List<MultiPermissionBO> pageList(MultiPermissionDTO dto) throws SerException;

    void agree(String id) throws SerException;
}