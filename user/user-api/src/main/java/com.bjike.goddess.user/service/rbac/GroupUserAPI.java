package com.bjike.goddess.user.service.rbac;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.SerAPI;
import com.bjike.goddess.user.dto.rbac.GroupUserDTO;
import com.bjike.goddess.user.entity.rbac.GroupUser;

import java.util.List;

/**
 * 组用户业务接口
 *
 * @Author: [liguiqin]
 * @Date: [2017-02-28 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface GroupUserAPI extends SerAPI<GroupUser, GroupUserDTO> {

    /**
     * 通过用户id 查询其在的所有组
     * @param user_id 用户id
     * @return
     */
    default List<GroupUser> findByUserId(String user_id)throws SerException{
        return null;
    }

}
