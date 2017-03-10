package com.bjike.goddess.user.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.user.dto.UserDetailDTO;
import com.bjike.goddess.user.entity.UserDetail;
import com.bjike.goddess.user.bo.UserDetailBO;

/**
 * 部门业务接口
 *
 * @Author: [liguiqin]
 * @Date: [2016-12-28 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface UserDetailAPI extends Ser<UserDetail, UserDetailDTO> {

    default UserDetailBO add() throws SerException {
        return null;
    }
}
