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
    /**
     * 申请调阅权限
     * @return
     * @throws SerException
     */
    ConcisePermissionBO add() throws SerException;

    /**
     * 申请权限调阅列表
     * @param dto
     * @return
     * @throws SerException
     */

    List<ConcisePermissionBO> pageList(ConcisePermissionDTO dto) throws SerException;

    /**
     * 调阅权限审核
     * @param id
     * @throws SerException
     */
    void agree(String id) throws SerException;
}