package com.bjike.goddess.user.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.SerAPI;
import com.bjike.goddess.user.dto.UserDetailDTO;
import com.bjike.goddess.user.entity.UserDetail;

/**
 * @Author: [liguiqin]
 * @Date: [2016-12-28 15:47]
 * @Description: [部门业务接口]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface UserDetailAPI extends SerAPI<UserDetail, UserDetailDTO> {

    default UserDetail add()throws SerException{
        return null;
    }
}
