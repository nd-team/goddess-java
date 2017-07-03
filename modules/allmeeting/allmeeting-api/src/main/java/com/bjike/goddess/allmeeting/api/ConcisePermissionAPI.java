package com.bjike.goddess.allmeeting.api;

import com.bjike.goddess.allmeeting.bo.ConcisePermissionBO;
import com.bjike.goddess.allmeeting.dto.ConcisePermissionDTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
* 调阅权限业务接口
* @Author:			[ Jason ]
* @Date:			[  2017-06-28 11:33 ]
* @Description:	[ 调阅权限业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ConcisePermissionAPI  {

    ConcisePermissionBO add() throws SerException;

    List<ConcisePermissionBO> pageList(ConcisePermissionDTO dto) throws SerException;

    void agree(String id) throws SerException;
}