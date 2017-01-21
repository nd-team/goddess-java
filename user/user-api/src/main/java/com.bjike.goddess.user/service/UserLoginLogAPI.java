package com.bjike.goddess.user.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.SerAPI;
import com.bjike.goddess.user.dto.UserLoginLogDTO;
import com.bjike.goddess.user.entity.UserLoginLog;
import com.bjike.goddess.user.sto.UserLoginLogSTO;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-28 15:48]
 * @Description: [用户登录日志接口]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface UserLoginLogAPI extends SerAPI<UserLoginLog,UserLoginLogDTO> {

    /**
     * 获取用户登录日志，默认前5条（最多保存也是5条）
     * @param userId
     * @return
     */
    default List<UserLoginLogSTO> findByUserId(String userId)throws SerException {
        return null;
    }

}
