package com.bjike.goddess.user.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.user.dto.UserLoginLogDTO;
import com.bjike.goddess.user.entity.UserLoginLog;
import com.bjike.goddess.user.bo.UserLoginLogBO;
import com.bjike.goddess.user.to.UserLoginLogTO;

import java.util.List;

/**
 * 用户登录日志接口
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-28 15:48]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface UserLoginLogSer extends Ser<UserLoginLog, UserLoginLogDTO> {
    /**
     * 保存登录日志
     * @param loginLogTO
     * @throws SerException
     */
    default void saveLoginLog(UserLoginLogTO loginLogTO) throws SerException{

    };

    /**
     * 获取用户登录日志，默认前5条（最多保存也是5条）
     *
     * @return
     */
    default List<UserLoginLogBO> findByUserId(String userId) throws SerException {
        return null;
    }

}
