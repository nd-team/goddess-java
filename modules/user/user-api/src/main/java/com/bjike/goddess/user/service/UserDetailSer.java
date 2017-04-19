package com.bjike.goddess.user.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.user.dto.UserDetailDTO;
import com.bjike.goddess.user.entity.UserDetail;
import com.bjike.goddess.user.bo.UserDetailBO;

import java.util.List;

/**
 * 部门业务接口
 *
 * @Author: [liguiqin]
 * @Date: [2016-12-28 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface UserDetailSer extends Ser<UserDetail, UserDetailDTO> {

    default UserDetailBO add() throws SerException {
        return null;
    }

    default UserDetailBO findByUserId(String userId)throws SerException{
        return  null;
    }

    /**
     * 根据月份分页查询用户详细信息 Jason
     * @param dto 用户详细信息
     * @return
     * @throws SerException
     */
    List<UserDetailBO> findByMonth(UserDetailDTO dto ,Integer month) throws SerException;
}
