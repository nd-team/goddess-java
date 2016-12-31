package com.bjike.goddess.user.common.service;

import com.bjike.goddess.dbs.jpa.exception.SerException;
import com.bjike.goddess.dbs.jpa.service.IService;
import com.bjike.goddess.user.common.dto.UserLoginLogDto;
import com.bjike.goddess.user.common.entity.UserLoginLog;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-28 15:48]
 * @Description: [用户登录日志接口]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IUserLoginLogSer extends IService<UserLoginLog,UserLoginLogDto> {

    /**
     * 获取用户登录日志，默认前5条（最多保存也是5条）
     * @param userId
     * @return
     */
    default List<UserLoginLog> findByUserId(String userId)throws SerException {
        return null;
    }

}
